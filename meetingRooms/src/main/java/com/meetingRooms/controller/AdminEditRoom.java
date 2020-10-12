package com.meetingRooms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meetingRooms.entity.MeetingRoomEntity;
import com.meetingRooms.service.GetDataForAdminCreateRoomServiceInterface;
import com.meetingRooms.utility.GetDataForAdminCreateRoomFactory;

/**
 * Servlet implementation class AdminEditRoom
 */
public class AdminEditRoom extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGR = LoggerFactory.getLogger(AdminEditRoom.class);
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		
		MeetingRoomEntity entity = new MeetingRoomEntity ();
		HttpSession session = request.getSession (false);
		
		try {
			entity.setUniqueName ( request.getParameter("meeting_name") ); // getting meeting name
			
			entity.setSeatingCapacity ( Integer.parseInt ( request.getParameter ( "seating_capacity" ) ) ); // get seating capacity
			
			entity.setCreatedBy ( session.getAttribute ( "user_id" ).toString() ); // to store who created the meeting
			
			// get amenities List
			
			List <String> list = new ArrayList <String> (); 
			
			String amenity_list [] = request.getParameterValues( "amenitites" );	// store checkBoxes values 
			
			for ( String temp : amenity_list ) {
				
				if ( temp != null ) {
					
					list.add(temp);
				}
			}
			
			entity.setAmenityList(list); // setting amenity list
			
			GetDataForAdminCreateRoomServiceInterface service = GetDataForAdminCreateRoomFactory.createObjectForService ();
			
			if ( service.editRoom ( entity ) == 1 ) {
				
				String errorMessage = "<div class='alert alert-success alert-dismissible fade in'>" +
						"<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
						"<strong> Edit Successful... </strong>" + 
						"</div>";
	
				request.setAttribute ( "Admin_home_page_message", errorMessage );
				
				request.getRequestDispatcher("AdminHomePage.jsp").forward ( request, response );
				
			} else {
				
				String errorMessage = "<div class='alert alert-danger alert-dismissible fade in'>" +
						"<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
						"<strong> Edit Unsuccessful... </strong>" + 
						"</div>";
	
				request.setAttribute ( "Admin_home_page_message", errorMessage );
				
				request.getRequestDispatcher("AdminHomePage.jsp").forward ( request, response );
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
