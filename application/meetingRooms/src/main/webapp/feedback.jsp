<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>



<%@ page isELIgnored="false" %>

<%@page import="java.util.List"%>
<%@page import="com.meetingRooms.service.FeedbackService"%>
<%@page import="com.meetingRooms.service.FeedbackServiceInterface"%>
<%@page import="com.meetingRooms.utility.FeedbackServiceFactory"%>
<%@page import="com.meetingRooms.entity.MeetingRoomEntity"%>

<% 
	//verifying session details and returning to login page if not member
	
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

	response.setHeader("Pragma", "no-cache");

	response.setHeader("Expires", "0");

	
	if ( session.getAttribute ( "role" ) == null || !session.getAttribute ( "role" ).toString().equals ( "member" ) ) {
		request.getRequestDispatcher("login.jsp").forward ( request, response ); 
	}
   
%>


<%
				
FeedbackServiceInterface feedbackservice = FeedbackServiceFactory.createObject ();

List<MeetingRoomEntity>  roomList= feedbackservice.getAllRooms();
%>


<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="javaScript/feedback.js"></script>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/responsive.css" rel="stylesheet">

    <link href="css/Footer-with-button-logo.css" rel="stylesheet">

    <script src="javaScript/jQuery_v3.5.1.js"></script>
    <script src="javaScript/bootstrap_v4.5.2.js"></script>

    <title>Give your feedback</title>
    <link rel="stylesheet" href="css/feedback.css">


</head>

<body>

    <!-- NAVBAR -->

    <div class="container">

        <nav class="navbar navbar-inverse navbar-fixed-top">

            <div class="container-fluid">

                <div class="navbar-header">

                    <a class="navbar-brand" href="member.jsp"> <img src="images/logo/hsbc-logo-dark_navbar.png"
                            style=" height:20px; width:110px;" /> </a>

                </div>

                <ul class="nav navbar-nav">

                    <li class="active"> <a href="member.jsp"> Home </a> </li>

                    <li><a href="#myModal" role="button" data-toggle="modal"> Member Information </a></li>

                    <li> <a href="Logout"> Logout </a> </li>

                </ul>

            </div>

        </nav>
    </div>
    <!-- NAVBAR -->

	<div class="row"><br><br><br></div>


    <div class='mycontainer'>
        <h2> Give your rating </h2>

        <div class='item-container'>
            <div class='title'> Select room</div>
            <select class='userinput' id='room'>
                <%
              for(MeetingRoomEntity m: roomList) {
            %>
                <option value="<%=m.getUniqueName()%>"> <%=m.getUniqueName()%></option>
                <%
				 }
				%>
            </select>
        </div>

        <div class='item-container'>
            <div class='title'> Enter rating here( 1-5 )</div>
            <input class='userinput' id='feedbackrating' type="number" min="1" max="5" onkeyup="enableSubmit()"
                onchange="enableSubmit()">
        </div>

        <div class='item-container'>
            <button class='userinput' id='submitbtn' disabled onclick="submitRating()"> Submit</button>
        </div>

    </div>


    <!-- Footer -->

    <div class="content"> </div>

    <footer id="myFooter">

        <div class="container">

            <div class="row">

                <div class="col-sm-3">

                    <h2 class="logo"> <a href="member.jsp"> <img src="images/logo/hsbc-logo-dark_2.png"
                                style=" height:70px; width:150px;" align="left" /> </a> </h2>

                </div>

                <div class="col-sm-2">

                    <h5> Get started </h5>

                    <ul>

                        <li> <a href="member.jsp"> Home </a> </li>
                        <li> <a href="Logout"> Logout </a> </li>

                    </ul>

                </div>

                <div class="col-sm-2">

                    <h5>About us</h5>

                    <ul>

                        <li> <a href="about_us.jsp"> Information </a> </li>

                        <li> <a href="feedback.jsp"> Give Feedback </a> </li>

                    </ul>

                </div>

                <div class="col-sm-2">

                    <h5> Support </h5>

                    <ul>

                        <li> <a href="#"> FAQ </a> </li>

                        <li> <a href="#"> Help desk </a> </li>

                    </ul>

                </div>

                <div class="col-sm-3">

                    <br>

                    <a href="#"> <button type="button" class="btn"> Contact us </button> </a>

                </div>

            </div>

        </div>

        <div class="footer-copyright">

            <p> Developed By WFS BATCH-1 @ HSBC-CodeFury </p>

        </div>

    </footer>

    <!-- Footer -->


</body>

</html>