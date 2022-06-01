package services;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ejbs.Notification;
import ejbs.Trip;
import ejbs.TripXUser;
import ejbs.User;


@Stateless
@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
	
	@PersistenceContext(unitName="hello")
	private EntityManager entityManager;
	
	@Inject
	NotificationService notificationService;
		
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
	
	@POST
	@Path("booktrip")
	public String bookTrip(TripXUser newTrip) {
		//System.out.println("You entered trip_id: "+newTrip.getTrip_id());
		//System.out.println("You entered user_id: "+newTrip.getUser_id());
		User targetedUser = entityManager.find(User.class, newTrip.getUser_id());
		Trip targetedTrip = entityManager.find(Trip.class, newTrip.getTrip_id());
		
		if(targetedTrip!=null && targetedUser!=null) {	
			if(targetedTrip.getAvailable_seats()<=0) {		//check available seats
				//send notification
				String message = "Sorry, trip "+targetedTrip.getFrom_station_name()+" to "
						+targetedTrip.getTo_station_name()+" has no available seats";
				Notification toSend = new Notification(message);
				notificationService.addNotificationToUser(toSend, newTrip.getUser_id());
				return "No seats available";
			}
			else {
				//send notification
				String message = "You have booked trip from "+targetedTrip.getFrom_station_name()+" to "
						+targetedTrip.getTo_station_name()+" successfully";
				Notification toSend = new Notification(message);
				notificationService.addNotificationToUser(toSend, newTrip.getUser_id());
				
				targetedUser.registerToTrip(targetedTrip);
				targetedTrip.registerUser(targetedUser);
				
				return "Booked trip successfully";
			}
			
		}
		else {
			return "Invalid booking details entered";
		}
	}
	
	
	
}
