<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.meetingRooms.service.OrganizeMeetingServiceInterface"%>
<%@page import="com.meetingRooms.service.OrganizeMeetingService"%>
<%@page import="com.meetingRooms.utility.OrganizeMeetingServiceFactory"%>
<%@page import="com.meetingRooms.entity.MeetingRoom"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.meetingRooms.entity.Meeting"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%

String meetingDate = request.getParameter("meetingDate");
String startTime = request.getParameter("startTime");
int duration = Integer.parseInt(request.getParameter("duration"));
String meetingType = request.getParameter("meetingType");

Meeting meeting = new Meeting();
//meeting.setStartTime(DateFormat.getDateInstance().parse(startTime));
meeting.setDuration(duration);
meeting.setMeetingType(meetingType);
meeting.setMeetingDate(DateFormat.getDateInstance().parse(meetingDate));


// get suitable meeting rooms according to the criteria

OrganizeMeetingServiceInterface meetingService = OrganizeMeetingServiceFactory.createObject();
ArrayList<MeetingRoom> meetingRoomsList = meetingService.filterRoomsService(meeting);



//convert arraylist to json string

String meetingRoomsListJson = "[";
for (int i = 0; i < meetingRoomsList.size(); i++) {
	if (i != 0)
		meetingRoomsListJson = meetingRoomsListJson + ",";
	meetingRoomsListJson = meetingRoomsListJson + meetingRoomsList.get(i).toString();
}
meetingRoomsListJson = meetingRoomsListJson + "]";


// return json

response.setContentType("application/json");
response.setCharacterEncoding("UTF-8");
out.print(meetingRoomsListJson);

%>