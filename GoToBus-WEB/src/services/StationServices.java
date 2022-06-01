package services;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ejbs.Station;
import ejbs.User;

import javax.persistence.*;


@Stateless
@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StationServices {

	@PersistenceContext(unitName = "hello")
	private EntityManager em;

	@GET
	@Path("print")
	public String hello() {
		return "Hello station! ";
	}

	@POST
	@Path("{user_id}/station")
	public String CreateStation(Station station,@PathParam("user_id")int user_id) {
		User thisUser = em.find(User.class, user_id);
		
		if (thisUser==null) {
			return "Invalid user id provided";
		}
				
		if(thisUser.getRole().compareTo("admin")==0) {	//only create station if this user is an admin
			try {
				em.persist(station);
				return "sucess";
			} catch (Exception e) {
				throw new EJBException(e);
			}
		}
		else {
			return "This user is not an admin; cannot create station";
		}	
	}
	
	@GET
	@Path("station/{id}")
	public Station getStation(@PathParam("id") int id) {
		Station station = new Station();
		station = em.find(Station.class, id);
		return station;
	}
	
/*
	@DELETE
	@Path("removeStationsByID/{id}")
	public void remove(@PathParam("id") int id) {
		// TODO lookup the item by its ID then remove it using the entity manager
		Station station;
		station = em.find(Station.class, id);
		em.remove(station);
	}
  
	@GET
	@Path("getnamequery/{name :[a-zA-Z]+}")
	public List<Station> getPersonsWithName(@PathParam("name") String name) {
		TypedQuery<Station> query = em.createQuery("SELECT p from Station p where p.name =:pname", Station.class);
		query.setParameter("pname", name);
		return query.getResultList();
	}
 
	@GET
	@Path("getLat/{id}")
	public String getLat(@PathParam("id") int id)
	{   Station station;
	    String slat;
	    slat = em.find(Station.class, id).getLatitude();
		return slat;
	}
	@GET
	@Path("getName/{id}")
	public String getname(@PathParam("id") int id)
	{   Station station;
	    String slat;
	    slat = em.find(Station.class, id).getStationName();
		return slat;
		
	}
	
	
	@GET
	@Path("getStationsByID/{id}")
	public Station getStation(@PathParam("id") int id) {
		Station station = new Station();
		station = em.find(Station.class, id);
		return station;
	}
	 
  */
	  
	@GET
	@Path("getAllStations")
	public List<Station> getAllStations() {
		TypedQuery<Station> query = em.createQuery("SELECT s FROM Station s", Station.class);
		List<Station> stations = query.getResultList();
		return stations;
	}
	 	

}
