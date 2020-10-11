<%@page import="org.slf4j.LoggerFactory"%>
<%@page import="org.slf4j.Logger"%>
<%@page import="com.meetingRooms.entity.User"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.meetingRooms.service.OrganizeMeetingServiceInterface"%>
<%@page import="com.meetingRooms.service.OrganizeMeetingService"%>
<%@page import="com.meetingRooms.utility.OrganizeMeetingServiceFactory"%>
<%@page import="com.meetingRooms.entity.User"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%

	final Logger LOGR = LoggerFactory.getLogger(page.getClass());
	
	try {

		String name = request.getParameter("name");
		
		User user = new User();
		user.setName(name);
		
		
		
		// search users by name
		
		OrganizeMeetingServiceInterface meetingService = OrganizeMeetingServiceFactory.createObject();
		ArrayList<User> userList = meetingService.searchUserService(user);
		
		
		
		//convert arraylist to json string
		
		String userListJson = "[";
		for (int i = 0; i < userList.size(); i++) {
			if (i != 0)
				userListJson = userListJson + ",";
			userListJson = userListJson + userList.get(i).toString();
		}
		userListJson = userListJson + "]";
		
		
		// return json
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(userListJson);
	}
	catch (Exception e) {
		
		LOGR.error("Unhandled Exception: " + e.toString());
	}

%>