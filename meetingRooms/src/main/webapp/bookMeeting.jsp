<%@page import="com.meetingRooms.entity.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.meetingRooms.entity.Meeting"%>
<%@page import="com.meetingRooms.utility.OrganizeMeetingServiceFactory"%>
<%@page import="com.meetingRooms.service.OrganizeMeetingServiceInterface"%>


<%

String usersListString = request.getParameter("usersList");
int meetingTypeId = Integer.parseInt(request.getParameter("meetingTypeId"));
String meetingDate = request.getParameter("meetingDate");
String meetingTitle = request.getParameter("meetingTitle");
String startTime = request.getParameter("startTime");
String endTime = request.getParameter("endTime");
String meetingRoomId = request.getParameter("meetingRoomId");
String organizedBy = session.getAttribute("user_id").toString();


Meeting meeting = new Meeting();
meeting.setEndTime(endTime);
meeting.setMeetingDate(meetingDate);
meeting.setMeetingRoomId(meetingRoomId);
meeting.setMeetingTypeId(meetingTypeId);
meeting.setTitle(meetingTitle);
meeting.setStartTime(startTime);
meeting.setOrganizedBy(organizedBy);

ArrayList<User> members = new ArrayList<>();
String[] users = request.getParameter("members").split(",");
for (String userId: users) {
	
	User user = new User();
	user.setUserId(userId);
	members.add(user);
}


response.setContentType("text/html");
OrganizeMeetingServiceInterface service = OrganizeMeetingServiceFactory.createObject();
if (service.saveMeetingService(meeting, members)) {
	
	response.sendRedirect("manager.jsp");
}
else
	out.println("failed");

%>