<%@page import="java.util.* "%>
<%@page import="com.meetingRooms.service.AdminServiceInterface"%>
<%@page import="com.meetingRooms.utility.AdminServiceFactory"%>
<%@page import="com.meetingRooms.entity.loginUserEntity"%>
<%@page import="com.meetingRooms.entity.MeetingRoomEntity"%>
<%@page import="com.meetingRooms.entity.AmenitiesEntity"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	//fetch data from form and update in database
	
	int is_room_created = 0;   // to see if room is created
	loginUserEntity adminUser = new loginUserEntity();

	adminUser.setUser_id(session.getAttribute("user_id").toString()); /////// Set user Id obtained from session
   
	///Text Box Values from form
	
	String meetingRoomName = request.getParameter("mname");
	int seatingCapacity = Integer.parseInt(request.getParameter("scapacity"));
	
	//Values of checkboxes from form
		
	    String projectorValue = request.getParameter("projector");
		String wiFiConnectionrValue = request.getParameter("WiFiConnection");
		String conferenceCallFacilityValue = request.getParameter("Conferencecallfacility");
		String whiteBoardValue = request.getParameter("Whiteboard");
		String waterDispenserValue = request.getParameter("Waterdispenser");
		String tvValue = request.getParameter("TV");
		String coffeeMachineValue = request.getParameter("Coffeemachine");

	//Amenities object set to values obtained from form
	
	AmenitiesEntity amenitiesEntity = new AmenitiesEntity();
	AdminServiceInterface adminService = AdminServiceFactory.createObject("adminservice");
		amenitiesEntity.setUniqueName(meetingRoomName);
		amenitiesEntity.setSeatingCapacity(seatingCapacity);
		amenitiesEntity.setProjector(projectorValue);
		amenitiesEntity.setConferenceCallFacility(conferenceCallFacilityValue);
		amenitiesEntity.setCoffeeMachine(coffeeMachineValue);
		amenitiesEntity.setWhiteBoard(whiteBoardValue);
		amenitiesEntity.setWaterDispenser(waterDispenserValue);
		amenitiesEntity.setWiFiConnection(wiFiConnectionrValue);
		amenitiesEntity.setTV(tvValue);

//wrapping data to the object and transeferring to service layer.

		is_room_created = adminService.createRoomService(amenitiesEntity, adminUser);

		if (is_room_created > 0)
				System.out.println("Room created...");        //Printing on Console if Room Created change Later             

		else
				System.out.println("Room not created...");

%>

<%
	out.println("<a href=AdminHomePage.jsp>Click Here for HomePage</a>");
%>
