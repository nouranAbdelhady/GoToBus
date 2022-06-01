package rests;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ejbs.SearchTrip;
import ejbs.Trip;
import services.TripServices;

@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TripRest {

	@EJB
	private TripServices tripService;
	
	
	@GET
	@Path("helloTrip")
	public String helloTrip() {
		return tripService.helloTrip();
	}

	@POST
	@Path("{user_id}/trip")
	public String CreateTrip(Trip t, @PathParam("user_id") int user_id) {
		return tripService.CreateTrip(t, user_id);
	}

	@GET
	@Path("trips/{name}")
	public Trip getbyname(@PathParam("name") String name) {
		return tripService.getbyname(name);
	}

	@GET
	@Path("trips/{id}")
	public Trip getbyid(@PathParam("id") int id) {
		return tripService.getbyid(id);
	}

	@GET
	@Path("trips")
	public List<Trip> getalltrips() {
		return tripService.getalltrips();
	}

	@POST
	@Path("searchtrips")
	public List<Trip> searchTrips(SearchTrip toSearch) {
		return tripService.searchTrips(toSearch);
	}

	@GET
	@Path("viewtrips/{user_id}")
	public List<Trip> getAllUserNotifications(@PathParam("user_id") int user_id) {
		return tripService.getAllUserNotifications(user_id);
	}
}
