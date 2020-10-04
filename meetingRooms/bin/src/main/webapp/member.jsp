
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page isELIgnored="false" %>
    
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
	
	<title> Login </title>

</head>

<body>

<!-- NAVBAR -->

<div class="container">

	<nav class="navbar navbar-inverse navbar-fixed-top">
	
		<div class="container-fluid">
	    	
	    	<div class="navbar-header">
	    	
	      		<a class="navbar-brand" href="index.jsp"> <img src="images/logo/hsbc-logo-dark_navbar.png"  style=" height:20px; width:110px;"/> </a>
	      		
	    	</div>
	    	
	    	<ul class="nav navbar-nav">
	      	
	      		<li> <a href="index.jsp"> Home </a> </li>
	      	
	      		<li> <a href="#"> Import Users </a> </li>
	      		
	      		<li class="active"> <a href="login.jsp"> Login </a> </li>	      		
	      		
	    	</ul>
	    	
	  	</div>

	</nav>

<!-- NAVBAR -->

<%@page import="java.util.*,com.meetingRooms.entity.Meeting,com.meetingRooms.entity.loginUserEntity,com.meetingRooms.service.loginServiceInterface,com.meetingRooms.utility.loginUserServiceFactory"%>
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
List<Meeting> meetingList = lsi.loadMeeting(user); //loads meeting details, returns list to be displayed


%>




<!-- Footer -->

<div class="content"> </div>
    
<footer id="myFooter">

    <div class="container">
        
        <div class="row">
            
            <div class="col-sm-3">
            
                <h2 class="logo"> <a href="index.jsp"> <img src="images/logo/hsbc-logo-dark_2.png" style=" height:70px; width:150px;"  align="left"/> </a> </h2>
                
            </div>
            
            <div class="col-sm-2">
                
                <h5> Get started </h5>
                
                <ul>
                
                    <li> <a href="index.jsp"> Home </a> </li>
                    <li> <a href="login.jsp"> Login </a> </li>
                    
                </ul>
                
            </div>
            
            <div class="col-sm-2">
            
                <h5>About us</h5>
                
                <ul>
                
                    <li> <a href="#"> Information </a> </li>
                    
                    <li> <a href="#"> Give Feedback </a> </li>
                    
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
