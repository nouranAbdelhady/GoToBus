 package ejbs;
import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Stateless
public class Trip implements Serializable {
	//Station FromStation;
	//Station ToStation;
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	int TripId;
	@NotNull(message="Name cannot be blank")
	String tostation;
	@NotNull(message="Name cannot be blank")
	String fromstation;

	 
	public String getFromstation() {
		return fromstation;
	}

	public void setFromstation(String fromstation) {
		this.fromstation = fromstation;
	}

	public String getTostation() {
		return tostation;
	}

	public void setTostation(String tostation) {
		this.tostation = tostation;
	}
	@NotNull(message="Name cannot be blank")
	String depdate;
	@NotNull(message="Name cannot be blank")
	String  arrdate;
	public String getArrdate() {
		return arrdate;
	}

	public void setArrdate(String arrdate) {
		this.arrdate = arrdate;
	}
	int Seats;
 
	public int getTripId() {
		return TripId;
	}
	 
	public String getDate() {
		return depdate;
	}
	public void setDepdate(String date) {
		this.depdate = date;
	}
	public int getSeats() {
		return Seats;
	}
	public void setSeats(int seats) {
		Seats = seats;
	}
	 
		
	 
	}

 
