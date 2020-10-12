<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page isELIgnored="false" %>

<%
		// check for existing session

	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

	response.setHeader("Pragma", "no-cache");

	response.setHeader("Expires", "0");

		
	if ( session.getAttribute ( "role" ) == null ) {
		
		request.getRequestDispatcher("login.jsp").forward ( request, response );		
	
	} else { // session exists
		
		if ( session.getAttribute ( "role" ).toString().equals ( "member" ) ) {
			
			request.getRequestDispatcher("member.jsp").forward ( request, response );
			
		} else if ( session.getAttribute ( "role" ).toString().equals ( "admin" ) ) {
			
			// Do Nothing
			
		} else {
			
			request.getRequestDispatcher("ManagerHomePage.jsp").forward ( request, response );
		}
	}

	if(request.getParameter("unique_name")==null)
	{
		request.getRequestDispatcher("AdminHomePage.jsp").forward ( request, response );
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
		
	<script type="text/javascript" src="javaScript/AdminEditRoom.js"></script>	

	<title> Admin Edit Room </title>

</head>

<body>

<!-- NAVBAR -->


<div class="container">

	<nav class="navbar navbar-inverse navbar-fixed-top">
	
		<div class="container-fluid">
	    	
	    	<div class="navbar-header">
	    	
	      		<a class="navbar-brand" href="AdminHomePage.jsp"> <img src="images/logo/hsbc-logo-dark_navbar.png"  style=" height:20px; width:110px;"/> </a>
	      		
	    	</div>
	    	
	    	<ul class="nav navbar-nav">
	      	
	      		<li> <a href="AdminHomePage.jsp"> Admin Home </a> </li>
	      	
	      		<li class="active"> <a href = "#"> Edit Room </a> </li>
	      		
                <li> <a href="Logout"> Logout </a> </li>
            
	    	</ul>
	    	
	  	</div>

	</nav>

</div>

<!-- NAVBAR -->

<div class="row"> <br> <br> <br> </div>

${Admin_edit_page_room_message}

<!-- EDIT ROOM FORM -->

<div class=container>

	<div class = "row">
	
		<div class="col-sm-3"></div>
		
		<div class="col-sm-6 text-center">
		
			<div class="row"><h3>EDIT MEETING ROOM</h3></div>
			
			<form action="AdminEditRoom" method="post" name = "AdminEditRoomForm" id = "AdminEditRoomForm">

				<div class="row"><br></div>
				
				<%@page import="com.meetingRooms.service.GetDataForAdminCreateRoomServiceInterface"%>
				<%@page import="com.meetingRooms.service.GetDataForAdminCreateRoomService"%>
				<%@page import="com.meetingRooms.utility.GetDataForAdminCreateRoomFactory"%>
				<%@page import="com.meetingRooms.entity.AmenitiesEntity"%>
				<%@page import="com.meetingRooms.entity.MeetingRoomEntity"%>
				<%@page import="com.meetingRooms.entity.MeetingTypes"%>
				<%@page import="java.util.List, java.util.ArrayList"%>
				
				<%
				
					GetDataForAdminCreateRoomServiceInterface service = GetDataForAdminCreateRoomFactory.createObjectForService ();
				
					MeetingRoomEntity meeting_details = service.getEditRoomInfo(request.getParameter( "unique_name" ));
				
				%>
				
				<div class="row">
								
					<div class="input-group">
					
						<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
						<input type=text placeholder="Meeting Name" id="meeting_name" name="meeting_name" class=form-control value='<%=meeting_details.getUniqueName()%>'  disabled>
						<div id="meetingRoomError"></div>					
						
					</div>
					
				</div>
				
				<div class="row"><br></div>
				
				<div class="row">
				 
				 	<div class="input-group">
					
						<span class="input-group-addon"><i class="glyphicon glyphicon-sort-by-order"></i></span>
						<input type=text placeholder="Seating Capacity" id="seating_capacity" name="seating_capacity" value='<%=meeting_details.getSeatingCapacity()%>' class=form-control onkeyup="validateSeatingCapacity()" disabled required>
						<div id="seatingRoomError"></div>					
						
				 	</div>
				 	
				 </div>
				 
				 <div class="row"><br></div>
				 
				 <div class="row text-left"><button type="button" class="btn  btn-success" name="editSeatingCapacityButton" id="editSeatingCapacityButton" onclick="enableSeatingCapacity()" >Edit Seating Capacity</button></div>
				 
				<div class="row"><br></div>
				
				<div class="row text-center">
				
					<div class="col-sm-12">
						<h4><span class="input-group-addon"><i class="glyphicon glyphicon-tasks"> Select Amenities </i></span></h4>
					</div>
				
				</div>
				
				<div class="row"><br></div>
				
				<%

					List <AmenitiesEntity> amenity_list = new ArrayList <AmenitiesEntity> ();
					
					amenity_list = service.getAmenities ();
					
					int i = 0;
					
					if ( amenity_list == null ) {
					
					%>
						<div class = "row"> No Amenities Found </div>
					
					<%} else {					
					
						for ( AmenitiesEntity a : amenity_list ) {
							
							
							if ( i == 0 ) {%>
								
								<div class = "row text-left">
								
							<%}%>
							
								<div class = "col-sm-4">
                    				<label  class="checkbox-inline"> <input title="CREDITS: <%=a.getCredits()%>" type="checkbox" id="amenitites" name="amenitites" value="<%=a.getID()%>"><%=a.getAmenity()%></label>  
                     			</div>
                     			
                     		<%
                     		
                     			i++;
                     				
                     			if ( i == 3 ) {    
                     				
                     				i = 0;  
                     				
                     		%> </div> <div class = "row"> <br> </div>
                     					
                     			<%}%>							 
				
					<%}
						
						if ( i != 0 ) {
							
					%>	</div> <div class = "row"> <br> </div>
					
					<%}}%>
				
				<%
					String list = "";
					
					for ( String temp : meeting_details.getAmenityList() ) {
						
						list += temp + ",";
					}
				%>
					
				<script type="text/javascript"> setCheckBoxes( '<%=list%>' ); </script>
				
				<div class="row"><br></div>
				 
				<div class="row text-left"><button type="button" class="btn  btn-success" name="editAmenityButton" id="editAmenityButton" onclick="enableAmenitySelection()" >Edit Amenities</button></div>
				
				<div class = "row"> <br> </div>
				
				<div class="row"> <hr style="height:3px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);"> </div>

				<div class = "row"> <br> </div>
				
				<div class="row">

					<div class = "col-sm-12 text-center">
                     	
                     	<button type="button" class="btn  btn-success" name="submitButton" id="submitButton" onclick="finalSubmit()">Edit</button>
                     	
                    </div>
                    
            	</div>
			
			</form>
			
			<div class = "row"> <br> </div>
			
			<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal">Delete</button>
			
			<!-- MODAL -->
		
			<div class="modal" id="myModal">
		
				<div class="modal-dialog">
		
					<div class="modal-content">
		
						<!-- Modal Header -->
						<div class="modal-header">
		
							<h4 class="modal-title">CONFIRM DELETE</h4>
		
							<button type="button" class="close" data-dismiss="modal">&times;</button>
		
						</div>
		
						<!-- Modal body -->
		
						<div class="modal-body"> ACTION CANNOT BE REVERSED </div>
		
		      			<!-- Modal footer -->
		      			<div class="modal-footer">
		      
		        			<form action="AdminDeleteMeetingRoom" method="post" id = "DeleteMeetingRoom" name = "DeleteMeetingRoom">
			
								<button type="button" class="btn  btn-danger" onclick="deleteRoom()"> Delete Room </button>
								<input type="hidden" name = "unique_name" id = "unique_name" value = "<%=meeting_details.getUniqueName()%>">
				
							</form>
		      
		      			</div>
		
		    		</div>
		  
		  		</div>
		
			</div>
			
		<!-- MODAL -->
			
		</div>
		
		<div class="col-sm-3"></div>		
	
	</div>

</div>


<!-- EDIT ROOM FORM -->



<!-- Footer -->

<div class="content"> </div>
    
<footer id="myFooter">

    <div class="container">
        
        <div class="row">
            
            <div class="col-sm-3">
            
                <h2 class="logo"> <a href="AdminHomePage.jsp"> <img src="images/logo/hsbc-logo-dark_2.png" style=" height:70px; width:150px;"  align="left"/> </a> </h2>
                
            </div>
            
            <div class="col-sm-2">
                
                <h5> Get started </h5>
                
                <ul>
                
                    <li> <a href="AdminHomePage.jsp"> Home </a> </li>
                    
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
            
             <a href = "#"> <button type="button" class="btn" > Contact us </button> </a>	                
         
         </div>
            
        </div>
        
    </div>
    
    <div class="footer-copyright">
    
        <p> Developed By WFS BATCH-2 @ HSBC-CodeFury </p>
        
    </div>
    
</footer>

<!-- Footer -->

</body>

</html>
