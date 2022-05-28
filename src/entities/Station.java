package ejbs;
import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	/*  public int getID()
	{
		return ID;
		
	}
	
	public void setID(int id)
	{
		this.ID=id;
	}*/
	public String getStationName()
	{
		return name;
		
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public void setLongitude(String longtiude)
	{
	  this.longitude=longtiude;	
	}
	public void setLatitude(String latitude)
	{
		 this.latitude=latitude;	
	}
	public String getLongitude()
	{
		return longitude;
		
	}
	public String getLatitude()
	{
		return latitude;
		
	}
	
	

}
