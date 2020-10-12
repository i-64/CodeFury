package com.meetingRooms.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class Logout
 * 
 * @author Ashutosh Danwe
 * 
 */
public class Logout extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGR = LoggerFactory.getLogger(Logout.class);
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			HttpSession session = request.getSession ( false );
			
			if ( session != null ) {
				
				//remove session parameters
				session.removeAttribute ( "name" );
				  
				session.removeAttribute ( "email" ); 
				  
				session.removeAttribute ( "phone" );
				  
				session.removeAttribute ( "role" );
					 		
				session.invalidate();
			}
			
			response.sendRedirect ( "index.jsp" ); // redirect to home page
		}
		catch (IOException e) {
			
			LOGR.error(e.toString());
		}
		catch (Exception e) {
			
			LOGR.error("Unhandled Exception: " + e);
		}
		
	}
}