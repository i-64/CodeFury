package com.meetingRooms.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 * 
 * @author Ashutosh Danwe
 * 
 */
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
}