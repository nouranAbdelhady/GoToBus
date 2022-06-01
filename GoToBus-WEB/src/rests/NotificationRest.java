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

import ejbs.Notification;
import services.NotificationService;

@Path("/NotificationService")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotificationRest {
	
	@EJB
	private NotificationService notificationService;
	
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
		return notificationService.addNotification(notification);
	}

	@GET
	@Path("viewNotifications")
	public List<Notification> getNotifications()
	{
		return notificationService.getNotifications();
	}

	@GET
	@Path("viewNotifications/{id}")
	public List<Notification> getNotificationById(@PathParam("id")int id)
	{
		return notificationService.getNotificationById(id);
	}	

	@POST
	@Path("addNotification/{id}")	//id for user
	public String addNotificationToUser(Notification notification,@PathParam("id")int id)
	{
		return notificationService.addNotificationToUser(notification, id);	
	}
		
	@GET
	@Path("notifications/{user_id}")
	public List<Notification> getAllUserNotifications(@PathParam("user_id")int user_id)
	{
	    return notificationService.getAllUserNotifications(user_id);
	}
	
}
