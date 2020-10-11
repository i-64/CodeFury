package com.meetingRooms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.meetingRooms.entity.MeetingRoomEntity;
import com.meetingRooms.service.GetDataForAdminCreateRoomServiceInterface;
import com.meetingRooms.utility.GetDataForAdminCreateRoomFactory;

/**
 * Servlet implementation class AdminCreateRoom
 */
public class AdminCreateRoom extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			// pojo object
		
		MeetingRoomEntity entity = new MeetingRoomEntity ();
		
		HttpSession session = request.getSession (false);
			
		entity.setUniqueName ( request.getParameter("meeting_name") ); // getting meeting name
		
		entity.setSeatingCapacity ( Integer.parseInt ( request.getParameter ( "seating_capacity" ) ) ); // get seating capacity
		
		entity.setCreated_by ( session.getAttribute ( "user_id" ).toString() ); // to store who created the meeting
		
		
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
		
		if ( service.createRoom ( entity ) == 1 ) {
			
			String errorMessage = "<div class='alert alert-success alert-dismissible fade in'>" +
					"<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
					"<strong> Room Creation Successful... </strong>" + 
					"</div>";

			request.setAttribute ( "Admin_home_page_message", errorMessage );
			
			request.getRequestDispatcher("AdminHomePage.jsp").forward ( request, response );
			
		} else {
			
			String errorMessage = "<div class='alert alert-danger alert-dismissible fade in'>" +
					"<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>" +
					"<strong> Room Creation Unsuccessful... </strong>" + 
					"</div>";

			request.setAttribute ( "Admin_home_page_message", errorMessage );
			
			request.getRequestDispatcher("AdminHomePage.jsp").forward ( request, response );
		}
		
	}

}
