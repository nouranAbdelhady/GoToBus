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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entities.Notification;
import entities.User;

@Stateless
@Path("/NotificationService")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotificationService {
	
	@PersistenceContext(unitName="hello")
	private EntityManager entityManager;
	
	@GET
	@Path("hello")
	public String hello()	//http://localhost:8080/GoToBusVM/app/NotificationService/hello
	{
		return "hello - notifications";
	}
	
	@POST
	@Path("addNotification")
	public String addNotification(Notification notification)
	{
		try
		{
			entityManager.persist(notification);
			return "New notification added";
		}
		catch (Exception e)
		{
			throw new EJBException(e);
		}	
	}

	@GET
	@Path("viewNotifications")
	public List<Notification> getNotifications()
	{
		TypedQuery<Notification> query = entityManager.createQuery("SELECT n FROM Notification n", Notification.class);
		List<Notification> notifications = query.getResultList();
		return notifications;
	}

	@GET
	@Path("viewNotifications/{id}")
	public List<Notification> getNotificationById(@PathParam("id")int id)
	{
		//System.out.println("Looking for :"+id);
		TypedQuery<Notification> query = entityManager.createQuery("SELECT n FROM Notification n WHERE n.id=:id", Notification.class);
		query.setParameter("id", id);
		List<Notification> notifications = query.getResultList();
		return notifications;
	}
	
	

	//dummy function to add notification to user
	@POST
	@Path("addNotification/{id}")	//id for user
	public String addNotificationToUser(Notification notification,@PathParam("id")int id)
	{
		try
		{	
			TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.id=:id", User.class);
			query.setParameter("id", id);
			List<User> users = query.getResultList();
			//User targetedUser = users.get(0);
			
			users.get(0).addNotification(notification);
			notification.setUser(users.get(0));	//set user for notification before persisting
			
			addNotification(notification);
			//entityManager.persist(notification);
			
			return "New notification added for user: "+users.get(0).getUsername();
		}
		catch (Exception e)
		{
			throw new EJBException(e);
		}	
	}
		
	@GET
	@Path("notifications/{user_id}")
	public List<Notification> getAllUserNotifications(@PathParam("user_id")int user_id)
	{
	    TypedQuery<Notification> query = entityManager.createQuery("SELECT ma FROM Notification ma JOIN FETCH ma.user" , Notification.class);
	    List<Notification> notifications = query.getResultList();
	    return notifications;
	}
	
}
