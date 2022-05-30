package ejbs;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;


@Stateless
@Entity
public class Station implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    int ID;
	@NotNull(message="Name cannot be blank")
	String name;
	String longitude;
	String latitude;
	
	@OneToMany(mappedBy="from_station", fetch=FetchType.EAGER)
	private Set<Trip> fromTrips = new HashSet<Trip>();

	@OneToMany(mappedBy="to_station", fetch=FetchType.EAGER)
	private Set<Trip> toTrips = new HashSet<Trip>();

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
//	public Set<Trip> getFromTrips() {
//		return fromTrips;
//	}
	public void setFromTrips(Set<Trip> fromTrips) {
		this.fromTrips = fromTrips;
	}
//	public Set<Trip> getToTrips() {
//		return toTrips;
//	}
	public void setToTrips(Set<Trip> toTrips) {
		this.toTrips = toTrips;
	}

}
