package classentity;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.ws.rs.ApplicationPath;

/**
 * Entity implementation class for Entity: user
 */

@Stateless
@Entity

public class user implements Serializable {

	   
	@Id
	private Integer id;
	private String username;
	private String password;
	private String full_name;
	private String role;
	private static final long serialVersionUID = 1L;

	public user() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	} 
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return this.full_name;
	}
	public void setFullName(String full_name) {
		this.full_name = full_name;
	}
	public String getRole() {
		return this.role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
