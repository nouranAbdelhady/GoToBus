package rests;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ejbs.TripXUser;
import services.UserService;

@Path("/booktrip")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookRest {

	@EJB
	private UserService userService;
	
	@POST
	public String bookTrip(TripXUser newTrip) {
		return userService.bookTrip(newTrip);
	}	
	
}
