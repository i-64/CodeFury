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
	
	<script src="javaScript/bootstrap_v4.5.2.js"></script>
	<script src="javaScript/jQuery_v3.5.1.js"></script>
	
	<script type="text/javascript" src="javaScript/AdminCreateRoomValidation.js"></script>

	<title> Admin Home Page - Create Room </title>

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
	      	
	      		<li class="active"> <a href="AdminCreateRoom.jsp"> Create Room  </a> </li>
	      		
	      		<li> <a href="Logout"> Logout </a> </li>

            
	    	</ul>
	    	
	  	</div>

	</nav>

</div>

<!-- NAVBAR -->

<div class="row"> <br> <br> <br> </div>

${Admin_home_page_create_room_message}


<!-- CREATE ROOM FORM -->

<div class=container>

	<div class = "row">
		
		<div class="col-sm-3"></div>
	
		<div class="col-sm-6 text-center">
		
		<div class="row"><h3>CREATE MEETING ROOM</h3></div>
		
			<form action="AdminCreateRoom" method="post" name = "AdminCreateRoomForm" id = "AdminCreateRoomForm">
			
				<div class="row"><br></div>
			
				<div class="row">
								
					<div class="input-group">
					
						<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
						<input type=text placeholder="Meeting Name" id="meeting_name" name="meeting_name" class=form-control onkeyup="validateMeetingName()" required>
						<div id="meetingRoomError"></div>					
						
					</div>
					
				</div>
				
				<div class="row"><br></div>
				
				<div class="row">
				 
				 	<div class="input-group">
					
						<span class="input-group-addon"><i class="glyphicon glyphicon-sort-by-order"></i></span>
						<input type=text placeholder="Seating Capacity" id="seating_capacity" name="seating_capacity" class=form-control onkeyup="validateSeatingCapacity()" required>
						<div id="seatingRoomError"></div>					
						
				 	</div>
				 	
				 </div>
					
				<div class="row"><br></div>
				
				<%@page import="com.meetingRooms.service.GetDataForAdminCreateRoomServiceInterface"%>
				<%@page import="com.meetingRooms.service.GetDataForAdminCreateRoomService"%>
				<%@page import="com.meetingRooms.utility.GetDataForAdminCreateRoomFactory"%>
				<%@page import="com.meetingRooms.entity.AmenitiesEntity"%>
				<%@page import="com.meetingRooms.entity.MeetingTypes"%>
				<%@page import="java.util.List, java.util.ArrayList"%>
				
				<%
				
					GetDataForAdminCreateRoomServiceInterface service = GetDataForAdminCreateRoomFactory.createObjectForService ();
				
					List <MeetingTypes> meeting_list = new ArrayList <MeetingTypes> ();
					
					meeting_list = service.getMeetingTypes();
					
				%>
				
				<div class="form-group">
				
				  <label for="meetingType">Meeting Type</label>
				  
				  <select class="form-control" id='meetingType' name='meetingType' required>
				  
				  	<option value="" onclick="setCheckBoxes('nothing')" disabled selected> -----Select Meeting Type----- </option>
				  
				  	<%
				  	
				  		if ( meeting_list == null ) {}
				  	
				  		else {
				  			
				  			for ( MeetingTypes a : meeting_list ) {
				  	%>			
				  				<option onclick="setCheckBoxes('<%=a.getAmenitites()%>')" value="<%=a.getID()%>"><%=a.getType()%></option>
				  	<%
				  			}
				  		}
				  	%>
				    
				  </select>
				  
				</div>
				
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
					          
				
				<div class = "row"> <br> </div>
				
				<div class = "row"> <br> </div>
				
				<div class="row">
				
					<div class = "col-sm-4 text-right">
                     	
                     	<button type="button" class="btn  btn-info" name="validateButton" id="validateButton" onclick="preFinalValidation()">Validate Form</button>
                     	
                    </div>
				
					<div class = "col-sm-4 text-right">
                     	
                     	<button type="submit" class="btn  btn-success" name="submitButton" id="submitButton" disabled>Create</button>
                     	
                    </div>
                     
                     <div class = "col-sm-4 text-left">
                     
                     	<button type="reset" class="btn  btn-warning">Reset</button>
                     	
                     </div>
				
				</div>
			
			</form>
			
		</div>		
		
		<div class="col-sm-3"></div>
		
	</div>
	
</div>


<!-- CREATE ROOM FORM -->

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