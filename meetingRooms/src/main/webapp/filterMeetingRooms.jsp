<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.meetingRooms.service.OrganizeMeetingServiceInterface"%>
<%@page import="com.meetingRooms.service.OrganizeMeetingService"%>
<%@page import="com.meetingRooms.utility.OrganizeMeetingServiceFactory"%>
<%@page import="com.meetingRooms.entity.MeetingRoom"%>
<%@page import="com.meetingRooms.entity.MeetingType"%>
<%@page import="com.meetingRooms.entity.Meeting"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%

String meetingDate = request.getParameter("meetingDate");
String startTime = request.getParameter("startTime");
String endTime = request.getParameter("endTime");
int meetingTypeId = Integer.parseInt(request.getParameter("meetingType"));


Meeting meeting = new Meeting();
meeting.setStartTime(startTime);
meeting.setEndTime(endTime);
meeting.setMeetingDate(meetingDate);

MeetingType meetingType = new MeetingType();
meetingType.setMeetingTypeId(meetingTypeId);


// get suitable meeting rooms according to the criteria

OrganizeMeetingServiceInterface meetingService = OrganizeMeetingServiceFactory.createObject();
ArrayList<MeetingRoom> meetingRoomsList = meetingService.filterRoomsService(meeting, meetingType);



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