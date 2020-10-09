
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page isELIgnored="false" %>
    
<!DOCTYPE html>
<%
		// check for existing session

	if ( session.getAttribute ( "role" ) == null ) {
		
		request.getRequestDispatcher("login.jsp").forward ( request, response );	
	
	} else { // session exists
		
		if ( session.getAttribute ( "role" ).toString().equals ( "member" ) ) {
			
			//Do nothing
			
		} else if ( session.getAttribute ( "role" ).toString().equals ( "admin" ) ) {
			
			request.getRequestDispatcher("AdminHomePage.jsp").forward ( request, response );
			
		} else {
			
			request.getRequestDispatcher("ManagerHomePage.jsp").forward ( request, response );
		}
	}

%>
    
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
	
	<title> Member Home Page  </title>

</head>

<body>

<!-- NAVBAR -->

<div class="container">

	<nav class="navbar navbar-inverse navbar-fixed-top">
	
		<div class="container-fluid">
	    	
	    	<div class="navbar-header">
	    	
	      		<a class="navbar-brand" href="member.jsp"> <img src="images/logo/hsbc-logo-dark_navbar.png"  style=" height:20px; width:110px;"/> </a>
	      		
	    	</div>
	    	
	    	<ul class="nav navbar-nav">
	      	
	      		<li class="active"> <a href="member.jsp"> Home </a> </li>
	      	
	      		
	      		<li > <a href="Logout"> Logout </a> </li>	      		
	      		
	    	</ul>
	    	
	  	</div>

	</nav>
</div>
<!-- NAVBAR -->

<div class="row"> <br> <br> <br> </div>

${member_message}


<%@page import="java.util.*,com.meetingRooms.controller.MemberScheduleControllerInterface,com.meetingRooms.controller.MemberScheduleController, com.meetingRooms.entity.Meeting,com.meetingRooms.entity.loginUserEntity,com.meetingRooms.service.loginServiceInterface,com.meetingRooms.utility.loginUserServiceFactory"%>
<%
/**
 * 
 * JSP to display current member details
 * @author Drishika Dey
 * 
 */
String str_userId=request.getParameter("user_id"); // get user id from session


 loginServiceInterface lsi = loginUserServiceFactory.createObject();  //factory to creste object
 loginUserEntity user= new loginUserEntity();
 user.setUser_id(str_userId); //user details set in object
 
 //memberschedule retrieved from controller-service-dao
 MemberScheduleControllerInterface memberSchedule= new MemberScheduleController();
 List<Meeting> meetingList = memberSchedule.loadMeeting(user); 
 Meeting obj =new Meeting();
 


%>
<div class="container">

  <h2> Your Meeting Schedule </h2>
          
  <table class="table table-striped table-hover">
  
    <thead>
    
      <tr>
      
        <th> Meeting Room Id</th>
        <th> Title </th>
        <th> Meeting Date </th>
        <th> Start Time </th>
        <th> Duration </th>
        <th> Organized By </th>
        <th> Meeting Type </th>
        
      </tr>
      
    </thead>
    
    <tbody>
    
<%		
		for(Meeting meetingObject:meetingList) { //each obj out of list

%>
			<tr>
			
			   <td> <%=meetingObject.getId()%> </td>
			   <td> <%= meetingObject.getTitle()%> </td>
			   
			   <td> <%=meetingObject.getMeetingDate()%> </td>
			   <td> <%= meetingObject.getStartTime()%> </td>
			   <td> <%= meetingObject.getDuration()%> </td>
			   
			   <td> <%= meetingObject.getOrganizedBy()%> </td>

			   <td> <%= meetingObject.getMeetingTypeName()%> </td>
			  
			 
			</tr>
			


<%		
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
            
                <h2 class="logo"> <a href="member.jsp"> <img src="images/logo/hsbc-logo-dark_2.png" style=" height:70px; width:150px;"  align="left"/> </a> </h2>
                
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
            
             <a href = "#"> <button type="button" class="btn" > Contact us </button> </a>	                
         
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
