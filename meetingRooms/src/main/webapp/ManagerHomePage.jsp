<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page isELIgnored="false" %>

<%@page import = "java.util.*, com.meetingRooms.entity.loginUserEntity,com.meetingRooms.entity.Meeting, com.meetingRooms.service.MeetingRoomsServiceInterface, com.meetingRooms.utility.MeetingServiceFactory"%>
<%@ page import = "com.meetingRooms.utility.ConnectionManager" %>

<% 
						//verifying session details and returning to login page if not manager
	
	if ( session.getAttribute ( "role" ) == null || !session.getAttribute ( "role" ).toString().equals ( "manager" )) 
	{
	
		request.getRequestDispatcher("login.jsp").forward ( request, response ); 
	}
   
%>

<!DOCTYPE html>

<html>

<head>

	<meta charset="utf-8">

	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/font-awesome.min.css" rel="stylesheet">

	<link href="css/animate.css" rel="stylesheet">
	<link href="css/main.css" rel="stylesheet">
	<link href="css/responsive.css" rel="stylesheet">

	<link href="css/Footer-with-button-logo.css" rel="stylesheet">

	<script src="javaScript/jQuery_v3.5.1.js"></script>
	<script src="javaScript/bootstrap_v4.5.2.js"></script>


	<title> Manager Home Page </title>

</head>

<body>

	<!-- NAVBAR -->

	<div class="container">

		<nav class="navbar navbar-inverse navbar-fixed-top">

			<div class="container-fluid">

				<div class="navbar-header">

					<a class="navbar-brand" href="ManagerHomePage.jsp"> <img src="images/logo/hsbc-logo-dark_navbar.png"
							style=" height:20px; width:110px;" /> </a>

				</div>

				<ul class="nav navbar-nav">

					<li class="active"> <a href="ManagerHomePage.jsp"> Manager Home </a> </li>

					<li> <a href="organizeMeeting.jsp"> Organize Meeting </a> </li>

					<li> <a href="#myModal" role="button" data-toggle="modal"> Manager Information </a> </li>

					<li> <a href="Logout"> Logout </a> </li>

				</ul>

			</div>

		</nav>

	</div>

	<!-- NAVBAR -->


	<!-- Importing ConnectionManager Modules -->

	<%@ page import = "java.sql.Connection" %>
	<%@ page import = "java.sql.DriverManager" %>
	<%@ page import = "java.sql.PreparedStatement" %>
	<%@ page import = "java.sql.ResultSet" %>
	<%@ page import = "java.sql.SQLException" %>
	<%@ page import = "java.text.*" %>


	<!-- The Modal for Manager information in the navbar -->

	<div class="modal" id="myModal">

		<div class="modal-dialog">

			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">

					<h4 class="modal-title">Manager Information</h4>

					<button type="button" class="close" data-dismiss="modal">&times;</button>

				</div>

				<!-- Modal body -->

				<div class="modal-body">


					<%
   try {
		
		// load driver
		
		Class.forName ( "org.apache.derby.jdbc.EmbeddedDriver" );
					
		// get connection to database
					
		Connection con =  ConnectionManager.getConnection();
		
		//executing query for displaying manager information in modal
		
		PreparedStatement ps = con.prepareStatement ( "select * from users where user_id=?" );
		
		ps.setString(1, session.getAttribute("user_id").toString());
		
		ResultSet set_1 = ps.executeQuery ();
		%>

					<table class="table">
						<tr>
							<th>User ID</th>

							<th>Email id</th>

							<th> Last Logged In </th>
						</tr>
						<%
		
		while ( set_1.next() ){
			// for getting last accessed time of the manager
			
			Date d = new Date(session.getLastAccessedTime());  				//.getLastAccessedTime() returns time in milliseconds
			
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");		//converting milliseconds to date format 
			
			df.setTimeZone(TimeZone.getDefault());							//changing timezome from GMT to IST
%>
						<tr>

							<td> <%=set_1.getString(3)%> </td>

							<td> <%=set_1.getString(4)%> </td>

							<td> <%=df.format(d) %> </td>


						</tr>

						<%}%>		
		</table> 
       
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
      
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      
      </div>

    </div>
  
  </div>

</div>


<div class="row"> <br> <br> <br> </div>

${manager_home_page_message}


<!-- DISPLAY MEETING LISTS -->

			
<div class="container">

<!-- Displaying list of meetings scheduled by the manager  -->
  
  <h2> Scheduled Meetings </h2>
          
  <table class="table table-striped table-hover">
  
    <thead>
    
      <tr>
           
		<th>Meeting Name</th>
		
		<th>Meeting Room</th>
		
		<th>Start Date</th>
		
		<th>Start Time</th>
		
		<th>End Time</th>
		
		<th>Meeting type</th>
	       
      </tr>
      
    </thead>
    
    <tbody>
    
    
    
<%

	//joining meeting table, meeting_types table and meeting_room table for getting the details of scheduled meetings
	
	PreparedStatement ps_1 = con.prepareStatement ( "select a.id, title, organized_by, meeting_date, start_time, end_time, b.meeting_type, d.unique_name from meeting a INNER JOIN MEETING_TYPES b  ON a.meeting_type_id=b.id INNER JOIN MEETING_ROOM d ON d.created_by = a.organized_by where organized_by=?");

	ps_1.setString(1, session.getAttribute("user_id").toString());

	ResultSet set_2 = ps_1.executeQuery();	

		while ( set_2.next() ) {

%>
						<tr>

							<td> <%=set_2.getString(2)%> </td>
							<td> <%=set_2.getString(8)%> </td>
							<td> <%=set_2.getString(4)%> </td>
							<td> <%=set_2.getString(5)%> </td>
							<td> <%=set_2.getString(6)%> </td>
							<td> <%=set_2.getString(7)%> </td>

						</tr>

						<%			
		}

	} catch ( SQLException | ClassNotFoundException e ) {
	
		e.printStackTrace ();
	}

%>



						</tbody>

					</table>

				</div>


				<!-- Footer -->

				<div class="content"> </div>

				<footer id="myFooter">

					<div class="container">

						<div class="row">

							<div class="col-sm-3">

								<h2 class="logo"> <a href="ManagerHomePage.jsp"> <img
											src="images/logo/hsbc-logo-dark_2.png" style=" height:70px; width:150px;"
											align="left" /> </a> </h2>

							</div>

							<div class="col-sm-2">

								<h5> Get started </h5>

								<ul>

									<li> <a href="ManagerHomePage.jsp"> Home </a> </li>
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
</body>

</html>