package com.meetingRooms.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meetingRooms.entity.loginUserEntity;
import com.meetingRooms.service.loginServiceInterface;
import com.meetingRooms.utility.LoginUserFactory;
import com.meetingRooms.utility.LoginUserServiceFactory;

/**
 * Servlet implementation class Login
 * 
 * @author Ashutosh Danwe
 * 
 */
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final static Logger LOGR = LoggerFactory.getLogger(Login.class);
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			// variable to hold data
		try {

			loginUserEntity user = LoginUserFactory.createObject ();		
		
			// get data from FORM
		
			user.setUser_id ( request.getParameter ( "user_id" ) );
			user.setPassword ( request.getParameter ( "user_password" ) );
			
			// login variable to handle user data
		
			loginServiceInterface login_object = LoginUserServiceFactory.createObject ();
			
			user = login_object.logInUser ( user );	// verify user
			
			if ( user != null ) {
				
				HttpSession session = request.getSession ();	// get session
				
					// set session attributes
				
				session.setAttribute ( "user_id", user.getUser_id () );
				session.setAttribute ( "name", user.getName () );
				
				session.setAttribute ( "email", user.getEmail () );
				session.setAttribute ( "phone", user.getPhone () );
				
				session.setAttribute ( "role", user.getRole () );
				
					// redirect to target page based on based on role 
				
				if ( user.getRole ().equals ( "member" ) ) {
					
					request.getRequestDispatcher("member.jsp").forward ( request, response );
					
				} else if ( user.getRole ().equals ( "admin" ) ) {
					
					request.getRequestDispatcher("AdminHomePage.jsp").forward ( request, response );
					
				} else {
					
					login_object.renewCredits(user.getUser_id());
					
					request.getRequestDispatcher("ManagerHomePage.jsp").forward ( request, response );
				}
				
			} else {	// if user does not exists redirect to login page
				
				String login_message = "<div class='alert alert-danger alert-dismissible fade in'>" +
						"<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
						"<strong> Invalid Login Credentials </strong>" + 
						"</div>";
				
				request.setAttribute ( "login_message", login_message );
				
				request.getRequestDispatcher("login.jsp").forward ( request, response );
			}
		}
		catch (ServletException | IOException e) {
			
			LOGR.error(e.toString());
		}
		catch (Exception e) {
			
			LOGR.error("Unhandled Exception: " + e);
		}
		
	}
}
