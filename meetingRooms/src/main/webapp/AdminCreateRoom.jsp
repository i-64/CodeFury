<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page isELIgnored="false" %>

<%--
		// check for existing session

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

--%>

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

            
	    	</ul>
	    	
	    	<ul class="nav navbar-nav navbar-right">
	    		
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
	
		<div class="col-sm-6 text-center" >
		
		<div class="row"><h3>CREATE MEETING ROOM</h3></div>
		
			<form action="AdminCreateRoom" method="post">
			
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
				
				  <label for="sel1">Meeting Type</label>
				  
				  <select class="form-control" id='meetingType' name='meetingType' onchange="setCheckBoxes()" >
				  
				  	<option value=""> -----Select Meeting Type----- </option>
				  
				  	<%
				  	
				  		if ( meeting_list == null ) {}
				  	
				  		else {
				  			
				  			for ( MeetingTypes a : meeting_list ) {
				  	%>			
				  				<option value="<%=a.getID()%>"><%=a.getType()%></option>
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
				
					<div class = "col-sm-6 text-right">
                     	
                     	<button type="submit" class="btn  btn-success" onclick="validateForm()">Create</button>
                     	
                    </div>
                     
                     <div class = "col-sm-6 text-left">
                     
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


<%@page import="com.meetingRooms.service.AdminServiceInterface"%>
<%@page import="com.meetingRooms.utility.AdminServiceFactory"%>
<%@page import="com.meetingRooms.entity.loginUserEntity"%>
<%@page import="com.meetingRooms.entity.MeetingRoomEntity"%>
<%@page import="com.meetingRooms.entity.AmenitiesEntity"%>


<%--
	//fetch data from form and update in database
	
	int is_room_created = 0;   // to see if room is created
	loginUserEntity adminUser = new loginUserEntity();

	adminUser.setUser_id(session.getAttribute("user_id").toString()); /////// Set user Id obtained from session
   
	///Text Box Values from form
	
	String meetingRoomName = request.getParameter("mname");
	int seatingCapacity = Integer.parseInt(request.getParameter("scapacity"));
	
	//Values of checkboxes from form
		
	    String projectorValue = request.getParameter("projector");
		String wiFiConnectionrValue = request.getParameter("WiFiConnection");
		String conferenceCallFacilityValue = request.getParameter("Conferencecallfacility");
		String whiteBoardValue = request.getParameter("Whiteboard");
		String waterDispenserValue = request.getParameter("Waterdispenser");
		String tvValue = request.getParameter("TV");
		String coffeeMachineValue = request.getParameter("Coffeemachine");

	//Amenities object set to values obtained from form
	
	AmenitiesEntity amenitiesEntity = new AmenitiesEntity();
	AdminServiceInterface adminService = AdminServiceFactory.createObject("adminservice");
		amenitiesEntity.setUniqueName(meetingRoomName);
		amenitiesEntity.setSeatingCapacity(seatingCapacity);
		amenitiesEntity.setProjector(projectorValue);
		amenitiesEntity.setConferenceCallFacility(conferenceCallFacilityValue);
		amenitiesEntity.setCoffeeMachine(coffeeMachineValue);
		amenitiesEntity.setWhiteBoard(whiteBoardValue);
		amenitiesEntity.setWaterDispenser(waterDispenserValue);
		amenitiesEntity.setWiFiConnection(wiFiConnectionrValue);
		amenitiesEntity.setTV(tvValue);

//wrapping data to the object and transeferring to service layer.

		is_room_created = adminService.createRoomService(amenitiesEntity, adminUser);

		if (is_room_created > 0)
				System.out.println("Room created...");        //Printing on Console if Room Created change Later             

		else
				System.out.println("Room not created...");

--%>

<%
	out.println("<a href=AdminHomePage.jsp>Click Here for HomePage</a>");
%>


