package rests;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ejbs.User;
import services.UserService;

@Path("/user")
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

	@POST		//default path
	public String addUser(User user) {
		return userService.addUser(user);
	}

	@GET
	@Path("all")
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
}
