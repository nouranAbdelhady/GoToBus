package services;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ejbs.Notification;
import ejbs.SearchTrip;
import ejbs.Station;
import ejbs.Trip;

@Stateless
@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TripServices {
    
	 
	@PersistenceContext(unitName="hello") 
	private EntityManager em; 
		
	@POST
	@Path("trip")
	public  String CreateTrip(Trip t)
	{     
		 Query q=em.createQuery("SELECT e from  Station e where e.name =: nam");
		 q.setParameter("nam", t.getTo_station_name());
		 q.getResultList();
		 
		 Query q1=em.createQuery("SELECT e from  Station e where e.name =: WE");
		 q1.setParameter("WE", t.getFrom_station_name());
		 q1.getResultList();
		 
		 if(q.getResultList().isEmpty()||q1.getResultList().isEmpty())
		 {
			 return "Stations is not available";
		 }
		 else
		 {
			 t.setTo_station((Station) q.getResultList().get(0));
			 t.setFrom_station((Station) q1.getResultList().get(0));
			 
			 em.persist(t);
			 return "Successfully";
		 }	 
	}
	
	@GET
	@Path("helloTrip")
	public String helloTrip(Trip trip)
	{
		  return"hello world";
	}
	
	@GET
	@Path("trips/{name}")
	public Trip getbyname(@PathParam("name")String name)
	{
		  Query q=em.createQuery("SELECT e from Trip e where e.name =:nam");
		  q.setParameter("nam", name);
		  Trip r=(Trip) q.getSingleResult();
		  return  r;
	}
	@GET
	@Path("trips/{id}")
	public Trip getbyid(@PathParam("id") int id)
	{
		  Query q=em.createQuery("SELECT e from Trip e where e.TripId =:nam");
		  q.setParameter("nam", id);
		   Trip r=(Trip) q.getSingleResult();
		  return  r;
	}
	@GET
	@Path("trips")
	public List<Trip> getalltrips()
	{
		  Query q=em.createQuery("SELECT e from Trip e ");
		  return  q.getResultList();
	}	
	
	
	@POST
	@Path("searchtrips")
	public List<Trip> searchTrips(SearchTrip toSearch)
	{
	    TypedQuery<Trip> query = em.createQuery("SELECT t FROM Trip t "
	    		+ "JOIN FETCH t.from_station "
	    		+ "WHERE from_station_id=:from_station "
	    		+ "AND to_station_id=:to_station"
	    		, Trip.class);
	    query.setParameter("from_station", toSearch.getFrom_station());
	    query.setParameter("to_station", toSearch.getTo_station());
	    //query.setParameter("from_date", toSearch.getFrom_date());
	    //query.setParameter("to_date", toSearch.getTo_date());
		    
	    List<Trip> trips = query.getResultList();
	    return trips;
	}
}
