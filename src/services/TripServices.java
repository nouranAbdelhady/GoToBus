package WebServices;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import ejbs.Search;
import ejbs.Station;
import ejbs.Trip;
@Stateless
@Path("/Trips")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TripServices {
    
	 
	@PersistenceContext(unitName="hello") 
	private EntityManager em;
 
	
	@GET
	@Path("CreateTrip")
	public String addTrip(Trip trip)
	{
		  return"hello world";
	}
	@GET
	@Path("GetByname/{name}")
	public Trip getbyname(@PathParam("name")String name)
	{
		  Query q=em.createQuery("SELECT e from Trip e where e.name =:nam");
		  q.setParameter("nam", name);
		   Trip r=(Trip) q.getSingleResult();
		  return  r;
	}
	@GET
	@Path("GetByid/{id}")
	public Trip getbyid(@PathParam("id") int id)
	{
		  Query q=em.createQuery("SELECT e from Trip e where e.TripId =:nam");
		  q.setParameter("nam", id);
		   Trip r=(Trip) q.getSingleResult();
		  return  r;
	}
	@GET
	@Path("Getalltrips")
	public List<Trip> getalltrips()
	{
		  Query q=em.createQuery("SELECT e from Trip e ");
		  return  q.getResultList();
	}
	
	@POST
	@Path("new")
	public  String Create(Trip t)
	{     
		 Query q=em.createQuery("SELECT e from  Station e where e.name =: nam");
		 q.setParameter("nam", t.getTostation());
		 q.getResultList();
		 
		 Query q1=em.createQuery("SELECT e from  Station e where e.name =: WE");
		 q1.setParameter("WE", t.getTostation());
		 q1.getResultList();
		 
		 if(q.getResultList().isEmpty()||q1.getResultList().isEmpty())
		 {
			 return "Stations is not available";
		 }
		 else
		 {
			 em.persist(t);
				return "Successfully";
		 }
		 
			    
		 
	}
	
	@POST
	@Path("SearchTrips")
	public List<Trip>SearchTrips(Search s)
	{
		 Query q=em.createQuery("SELECT e from Trip e where e.depdate =: nam AND e.arrdate =: we AND e.fromstation =: are AND e.tostation =: is ");
		 q.setParameter("nam", s.getFrom_date());
		 q.setParameter("we", s.getTo_date());
		 q.setParameter("are", s.getFrom_station());
		 q.setParameter("is", s.getTo_station());
		 List <Trip> allsol=new ArrayList();
		 allsol.addAll(q.getResultList());
		
		/*
		 Query q=em.createQuery("SELECT e from Trip e where e.depdate =: nam AND e.arrdate =: we AND SELECT r from Station r where r.ID =: are AND r.ID =: is ");
		 q.setParameter("nam", s.getFrom_date());
		 q.setParameter("we", s.getTo_date());
		 q.setParameter("are", s.getFrom_station());
		 q.setParameter("is", s.getTo_station());
		 List <Trip> allsol=new ArrayList();
		 allsol.addAll(q.getResultList());
		 */
		 
		 return allsol;
	}
	 
 

}
