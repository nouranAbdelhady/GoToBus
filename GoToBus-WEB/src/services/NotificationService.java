package services;

import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ejbs.Notification;
import ejbs.User;


@Stateless
public class NotificationService {
	
	@PersistenceContext(unitName="hello")
	private EntityManager entityManager;
	
	public String hello()
	{
		return "hello - notifications";
	}
	
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

	public List<Notification> getNotifications()
	{
		TypedQuery<Notification> query = entityManager.createQuery("SELECT n FROM Notification n", Notification.class);
		List<Notification> notifications = query.getResultList();
		return notifications;
	}

	public List<Notification> getNotificationById(int id)
	{
		//System.out.println("Looking for :"+id);
		TypedQuery<Notification> query = entityManager.createQuery("SELECT n FROM Notification n WHERE n.id=:id", Notification.class);
		query.setParameter("id", id);
		List<Notification> notifications = query.getResultList();
		return notifications;
	}
	
	//dummy function to add notification to user
	public String addNotificationToUser(Notification notification,int id)
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
		
	public List<Notification> getAllUserNotifications(int user_id)
	{
	    TypedQuery<Notification> query = entityManager.createQuery("SELECT ma FROM Notification ma JOIN FETCH ma.user WHERE user_id=:user_id" , Notification.class);
	    query.setParameter("user_id", user_id);
	    
	    List<Notification> notifications = query.getResultList();
	    return notifications;
	}
	
}
