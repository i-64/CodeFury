<%@page import="com.meetingRooms.utility.OrganizeMeetingServiceFactory"%>
<%@page import="com.meetingRooms.service.OrganizeMeetingServiceInterface"%>
<%@page import="com.meetingRooms.entity.User"%>
<%

//verifying session details and returning to login page if not manager

if ( session.getAttribute ( "role" ) == null || !session.getAttribute ( "role" ).toString().equals ( "manager" )) {
	request.getRequestDispatcher("login.jsp").forward ( request, response ); 
}

User user = new User();

user.setUserId(request.getParameter("userId"));

response.setContentType("text/html");

OrganizeMeetingServiceInterface service = OrganizeMeetingServiceFactory.createObject();

out.println(service.getCredits(user));

%>