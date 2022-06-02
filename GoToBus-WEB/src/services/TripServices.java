package services;

import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;

import ejbs.SearchTrip;
import ejbs.Station;
import ejbs.Trip;
import ejbs.User;

@Stateless
public class TripServices {

	@PersistenceContext(unitName = "hello")
	private EntityManager em;
	
	public String helloTrip() {
		return "hello trip";
	}

	public String CreateTrip(Trip t, @PathParam("user_id") int user_id) {
		Query q = em.createQuery("SELECT e from  Station e where e.name =: nam");
		q.setParameter("nam", t.getTo_station_name());
		q.getResultList();

		Query q1 = em.createQuery("SELECT e from  Station e where e.name =: WE");
		q1.setParameter("WE", t.getFrom_station_name());
		q1.getResultList();

		if (q.getResultList().isEmpty() || q1.getResultList().isEmpty()) {
			return "Stations are not available";
		} else {
			User thisUser = em.find(User.class, user_id);

			if (thisUser == null) {
				return "Invalid user id provided";
			}

			if (thisUser.getRole().compareTo("admin") == 0) { // only create trip if this user is an admin
				try {
					t.setTo_station((Station) q.getResultList().get(0));
					t.setFrom_station((Station) q1.getResultList().get(0));

					em.persist(t);
					return "Successfully";
				} catch (Exception e) {
					throw new EJBException(e);
				}
			} else {
				return "This user is not an admin; cannot create trip";
			}

		}
	}

	public Trip getbyname(@PathParam("name") String name) {
		Query q = em.createQuery("SELECT e from Trip e where e.name =:nam");
		q.setParameter("nam", name);
		Trip r = (Trip) q.getSingleResult();
		return r;
	}

	public Trip getbyid(@PathParam("id") int id) {
		Query q = em.createQuery("SELECT e from Trip e where e.TripId =:nam");
		q.setParameter("nam", id);
		Trip r = (Trip) q.getSingleResult();
		return r;
	}

	public List<Trip> getalltrips() {
		TypedQuery<Trip> q = em.createQuery("SELECT e from Trip e",Trip.class);
		List<Trip> trips = q.getResultList();
		return trips;
	}

	public List<Trip> searchTrips(SearchTrip toSearch) {	
		TypedQuery<Trip> query = em.createQuery("SELECT t FROM Trip t " //+ "JOIN FETCH t.from_station "
				+ "WHERE from_station_id=:from_station " + "AND to_station_id=:to_station "
						+ "AND FORMATDATETIME(t.departure_time,'dd/MM/yyyy') >=: from_date "
						+ "AND FORMATDATETIME(t.arrival_time,'dd/MM/yyyy') <=: to_date"
						, Trip.class);
		query.setParameter("from_station", toSearch.getFrom_station());
		query.setParameter("to_station", toSearch.getTo_station());
		query.setParameter("from_date", toSearch.getFrom_date());
		query.setParameter("to_date", toSearch.getTo_date());

		List<Trip> trips = query.getResultList();
		return trips;
	}

	public List<Trip> getAllUserNotifications(@PathParam("user_id") int user_id) {
		TypedQuery<Trip> query = em.createQuery("SELECT ma FROM Trip ma JOIN FETCH ma.users WHERE user_id=:user_id",
				Trip.class);
		query.setParameter("user_id", user_id);

		List<Trip> trips = query.getResultList();
		return trips;
	}
}
