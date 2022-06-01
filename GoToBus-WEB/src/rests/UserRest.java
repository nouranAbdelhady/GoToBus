package rests;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ejbs.TripXUser;
import ejbs.User;
import services.UserService;

@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserRest {

	@EJB
	private UserService userService;
	
	@GET
	@Path("hello")
	public String sayHello() // http://localhost:8080/GoToBus-WEB/app/api/hello
	{
		return userService.hello();
	}

	@POST
	@Path("user")
	public String addUser(User user) {
		return userService.addUser(user);
	}

	@GET
	@Path("getUsers")
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
	@POST
	@Path("login")
	public Response login(User user) {
		return userService.login(user);
	}

	@POST
	@Path("booktrip")
	public String bookTrip(TripXUser newTrip) {
		return userService.bookTrip(newTrip);
	}
	
}
