package rests;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ejbs.Station;
import services.StationServices;


@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StationRest {

	@EJB
	private StationServices stationService;

	@GET
	@Path("print")
	public String hello() {
		return stationService.hello();
	}

	@POST
	@Path("{user_id}/station")
	public String CreateStation(Station station,@PathParam("user_id")int user_id) {
		return stationService.CreateStation(station, user_id);
	}
	
	@GET
	@Path("station/{id}")
	public Station getStation(@PathParam("id") int id) {
		return stationService.getStation(id);
	}
	
	@GET
	@Path("getAllStations")
	public List<Station> getAllStations() {
		return stationService.getAllStations();
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
	  	

}
