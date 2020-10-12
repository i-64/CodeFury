package com.meetingRooms.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meetingRooms.service.GetDataForAdminCreateRoomServiceInterface;
import com.meetingRooms.utility.GetDataForAdminCreateRoomFactory;

/**
 * Servlet implementation class AdminGetData
 * 
 * @author Ashutosh Danwe
 * 
 */
public class AdminGetData extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGR = LoggerFactory.getLogger(AdminGetData.class);
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			PrintWriter out = response.getWriter ();
			
			GetDataForAdminCreateRoomServiceInterface service = GetDataForAdminCreateRoomFactory.createObjectForService ();
			
			if ( service.getNameStatus(request.getParameter("name")) == 0 ) {
				
				out.print("0");
				
			} else {
				
				out.print("1");
			}
		}
		catch (IOException e) {
			
			LOGR.error(e.toString());
		}
		catch (Exception e) {
			
			LOGR.error("Unhandled Exception: " + e);
		}
		
	}

}
