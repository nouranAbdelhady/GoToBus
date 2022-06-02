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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	@EJB
	UserService userService;
	
	private static final long serialVersionUID = 1L;
	// Method to handle POST method request
	   @Override
	   public void doPost(HttpServletRequest req, HttpServletResponse res) 
	               throws ServletException, IOException {
		   
	       // declare variables
	       PrintWriter pw = null;
	       String username = null;
	       String password = null;
	       String full_name = null;
	       String role = null;
	      
	       // set content type
	       res.setContentType("text/html");

	       // get PrintWriter object
	       pw = res.getWriter();

	       // get form data (from req parameter)
	       username = req.getParameter("username");
	       password = req.getParameter("password");
	       full_name = req.getParameter("full_name");
	       role = req.getParameter("role");

	       User newUser = new User(username,password,full_name,role);
	       userService.addUser(newUser);
	       
	       pw.println("<h1 style='text-align: center; color:blue'>"
	                 + "New user added successfully"+ "</h1>"); 
	       
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
