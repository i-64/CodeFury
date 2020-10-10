<%@page import="com.meetingRooms.exceptions.NotEnoughCreditsException"%>
<%@page import="com.meetingRooms.exceptions.MeetingRoomAlreadyBookedException"%>
<%@page import="com.meetingRooms.entity.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.meetingRooms.entity.Meeting"%>
<%@page import="com.meetingRooms.utility.OrganizeMeetingServiceFactory"%>
<%@page import="com.meetingRooms.service.OrganizeMeetingServiceInterface"%>


<%

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

try {
	if (service.saveMeetingService(meeting, members)) {
		out.println("Meeting Booked");
	}
	else
		out.println("Could not book meeting :(");
}
catch (MeetingRoomAlreadyBookedException e) {
	
	out.println("Meeting Room already booked, any inconvinience caused is deeply regretted :(");
}
catch (NotEnoughCreditsException e) {
	
	out.println("You do not have enough credits to book this room");
}


%>