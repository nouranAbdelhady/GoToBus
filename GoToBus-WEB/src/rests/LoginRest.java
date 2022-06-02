package rests;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ejbs.User;
import services.UserService;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginRest {

	@EJB
	private UserService userService;
	
	@POST
	public Response login(User user) {
		if ( userService.login(user) ) {
			return Response.status(Response.Status.OK).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}	
	
}
