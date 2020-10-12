<%@page import="com.meetingRooms.service.FeedbackService"%>
<%@page import="com.meetingRooms.service.FeedbackServiceInterface"%>
<%@page import="com.meetingRooms.utility.FeedbackServiceFactory"%>
<%@page import="com.meetingRooms.entity.Feedback"%>

<%


String userId = session.getAttribute("user_id").toString();
String meetingRoomId = request.getParameter("roomId");
int rating = Integer.parseInt( request.getParameter("rating")) ;

Feedback fb = new Feedback();
fb.setMeetingRoomId(meetingRoomId);
fb.setRating(rating);
fb.setUserId(userId);

FeedbackServiceInterface service = FeedbackServiceFactory.createObject();
boolean isSuccess = service.saveFeedbackService(fb);

response.setContentType("text/html");
if(isSuccess){
	out.println("Feedback submitted successfully");
}else{
	out.println("Some error occured, please try later!");
}

%>