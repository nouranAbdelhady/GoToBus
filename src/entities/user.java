package entities;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Stateless
@Entity
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	
	private String username;
	private String password;
	private String full_name;
	private String role;
	private Boolean is_loggedin;
	private static final long serialVersionUID = 1L;

	public User() {
		super();
		this.is_loggedin=false;
	}
	
	public User(String username, String password, String full_name, String role, Boolean is_loggedin) {
		super();
		this.username = username;
		this.password = password;
		this.full_name = full_name;
		this.role = role;
		this.is_loggedin = false;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Boolean getIs_loggedin() {
		return is_loggedin;
	}
	public void setIs_loggedin(Boolean is_loggedin) {
		this.is_loggedin = is_loggedin;
	}
	public void login() {
		this.is_loggedin=true;
	}
		
}
