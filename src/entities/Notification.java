package entities;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Stateless
@Entity
public class Notification{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	
	private String message;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date")
	private Date notification_datetime;
	
	public Notification() {
		super();
		this.notification_datetime = new Date();
		System.out.println("Date: "+this.notification_datetime);
	}
	public Notification(int id, String message, Calendar notification_datetime) {
		super();
		this.id = id;
		this.message = message;
		this.notification_datetime = new Date();
		System.out.println("Date: "+this.notification_datetime);
	}
	public Notification(String message) {
		super();
		this.message = message;
		this.notification_datetime = new Date();
		System.out.println("Date: "+this.notification_datetime);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getNotification_datetime() {
		return notification_datetime;
	}
	public void setNotification_datetime(Date notification_datetime) {
		this.notification_datetime = notification_datetime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
		
}
