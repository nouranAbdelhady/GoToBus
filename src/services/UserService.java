package services;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Notification;
import entities.User;

@Stateless
@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
	
	@PersistenceContext(unitName="hello")
	private EntityManager entityManager;
	
	@GET
	@Path("hello")
	public String hello()	//http://localhost:8080/GoToBusVM/app/NotificationService/hello
	{
		return "hello user!";
	}
	
	@POST
	@Path("user")
	public String addUser(User user)
	{
		try
		{
			entityManager.persist(user);
			return "New user added";
		}
		catch (Exception e)
		{
			throw new EJBException(e);
		}	
	}

	@GET
	@Path("getUsers")
	public List<User> getUsers()
	{
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
		List<User> users = query.getResultList();
		return users;
	}
	
	@POST
	@Path("login")
	public Response login(User user) {
		String username=user.getUsername();
		String password = user.getPassword();
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username=:username AND u.password=:password", User.class);
		query.setParameter("username", username);
		query.setParameter("password", password);

		List<User> users = query.getResultList();
		if(!users.isEmpty()) {
			users.get(0).login();
			//return user.getUsername()+" successfully logged in";
			return Response.status(Response.Status.OK).build();
		}
		//return "invalid username or password";
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	
}