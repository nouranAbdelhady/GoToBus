package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejbs.User;
import services.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	@EJB
	UserService userService;
	
	private static final long serialVersionUID = 1L;
	// Method to handle POST method request
	   @Override
	   public void doPost(HttpServletRequest req, HttpServletResponse res) 
	               throws ServletException, IOException {
		   
		   //User newUser = new User("nouran", "password", "nouran_abdelhady", "client");
	       //userService.addUser(newUser);
		   
		   System.out.println("servlet");

	       // declare variables
	       PrintWriter pw = null;
	       String username = null;
	       String password = null;
	      
	       // set content type
	       res.setContentType("text/html");

	       // get PrintWriter object
	       pw = res.getWriter();

	       // get form data (from req parameter)
	       username = req.getParameter("username");
	       password = req.getParameter("password");

	       User taregetdUser = new User(username,password);
	       //userService.login(taregetdUser);	       	

	       // check if correct
	       if(userService.login(taregetdUser) ) {
	    	   pw.println("<h1 style='text-align: center; color:blue'>"
		                 + "Logged in successfully"+ "</h1>");   
	       }
	       else {
	    	   pw.println("<h1 style='text-align: center; color:blue'>"
		                 + "Logged in failed"+ "</h1>");   
	       }
	       
	       // close stream
	       pw.close();
	   }

	   // Method to handle GET method request
	   @Override
	   public void doGet(HttpServletRequest req, HttpServletResponse res) 
	               throws ServletException, IOException {
	       // call doPost() method
	       doPost(req, res);
	   }

}
