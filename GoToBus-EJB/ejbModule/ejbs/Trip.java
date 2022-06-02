package ejbs;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Stateless
public class Trip implements Serializable {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	int trip_id;
	
	@ManyToOne
	@JoinColumn(name="to_station_id")
	private Station to_station;
	
	@ManyToOne
	@JoinColumn(name="from_station_id")
	private Station from_station;
	
	private String from_station_name;
	private String to_station_name;
	
	int available_seats;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date departure_time;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date arrival_time; 	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name="TripXUser",
		joinColumns=@JoinColumn(name="trip_id"),
		inverseJoinColumns=@JoinColumn(name="user_id"))
	private Set<User> users = new HashSet<User>();

	public int getTripId() {
		return trip_id;
	}
	public void setTripId(int tripId) {
		this.trip_id = tripId;
	}
//	public Station getTo_station() {
//		return to_station;
//	}
	public void setTo_station(Station to_station) {
		this.to_station = to_station;
	}
//	public Station getFrom_station() {
//		return from_station;
//	}
	public void setFrom_station(Station from_station) {
		this.from_station = from_station;
	}
	public int getAvailable_seats() {
		return available_seats;
	}
	public void setAvailable_seats(int available_seats) {
		this.available_seats = available_seats;
	}
//	public String getDeparture_time() {
//		return departure_time;
//	}
//	public void setDeparture_time(String departure_time) {
//		this.departure_time = departure_time;
//	}
//	public String getArrival_time() {
//		return arrival_time;
//	}
//	public void setArrival_time(String arrival_time) {
//		this.arrival_time = arrival_time;
//	}
	public String getFrom_station_name() {
		return from_station_name;
	}
	public Date getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(Date departure_time) {
		this.departure_time = departure_time;
	}
	public Date getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(Date arrival_time) {
		this.arrival_time = arrival_time;
	}
	public void setFrom_station_name(String from_station_name) {
		this.from_station_name = from_station_name;
	}
	public String getTo_station_name() {
		return to_station_name;
	}
	public void setTo_station_name(String to_station_name) {
		this.to_station_name = to_station_name;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public void registerUser(User newUser) {
		this.users.add(newUser);
	}
		
}
