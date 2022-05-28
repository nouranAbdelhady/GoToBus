package webServices;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.eclipse.persistence.oxm.MediaType;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import classentity.user;

@Stateless
@Path("/api")
@RolesAllowed({"admin","client"})
@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON )
public class userServices {

	@PersistenceContext(unitName="hello")
	private EntityManager em;
	
	@GET
	@Path("hello")
	public String hello() {
		return "hello user! ";
	}
	
	@POST
	@Path("/user")
	@RolesAllowed("user")
	public String newUser(user User) {
		boolean added=false;
		try {
			em.persist(User);
			added=true;
		}catch (Exception e) {
			throw new EJBException(e);
		}if(added=true) {
			return "new user created successfully";
		}else {
			return "failed creating new user";
		}
	}
	
	@POST
	@Path("/login")
	@RolesAllowed("user")
	public String login(user User) {
		boolean loggedin=false;
		try {
			em.persist(User);
			loggedin=true;
		}catch(Exception e) {
			throw new EJBException(e);
		}if (loggedin==true) {
			return "successful login";
		}else {
			return "invalid username or password";
		}
	}