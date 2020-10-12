package com.meetingRooms.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meetingRooms.service.GetDataForAdminCreateRoomServiceInterface;
import com.meetingRooms.utility.GetDataForAdminCreateRoomFactory;

/**
 * Servlet implementation class AdminDeleteMeetingRoom
 * 
 * @author Ashutosh Danwe
 */
public class AdminDeleteMeetingRoom extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGR = LoggerFactory.getLogger(AdminDeleteMeetingRoom.class);
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			GetDataForAdminCreateRoomServiceInterface service = GetDataForAdminCreateRoomFactory.createObjectForService ();
			
			if ( service.deleteRoom ( request.getParameter( "unique_name" ) ) == 1 ) {
				
				String errorMessage = "<div class='alert alert-success alert-dismissible fade in'>" +
						"<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
						"<strong> Delete Successful... </strong>" + 
						"</div>";
	
				request.setAttribute ( "Admin_home_page_message", errorMessage );
				
				request.getRequestDispatcher("AdminHomePage.jsp").forward ( request, response );
				
			} else {
				
				String errorMessage = "<div class='alert alert-danger alert-dismissible fade in'>" +
						"<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
						"<strong> Delete Unsuccessful... </strong>" + 
						"</div>";
	
				request.setAttribute ( "Admin_home_page_message", errorMessage );
				
				request.getRequestDispatcher("AdminHomePage.jsp").forward ( request, response );
			}
		}
		catch (ServletException e) {
			
			LOGR.error(e.toString());
		}
		catch (Exception e) {
			
			LOGR.error("Unhandled Exception: " + e);
		}
		
	}

}
