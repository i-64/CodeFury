<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page isELIgnored="false" %>

<%
		// check for existing session
		
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

	response.setHeader("Pragma", "no-cache");

	response.setHeader("Expires", "0");

	if ( session.getAttribute ( "role" ) == null ) {
		
			// session does not exists
			// do nothing			
	
	} else { // session exists
		
		if ( session.getAttribute ( "role" ).toString().equals ( "member" ) ) {
			
			//request.getRequestDispatcher("login.jsp").forward ( request, response );
			
		} else if ( session.getAttribute ( "role" ).toString().equals ( "admin" ) ) {
			
		//	request.getRequestDispatcher("login.jsp").forward ( request, response );
			
		} else {
			
			//request.getRequestDispatcher("login.jsp").forward ( request, response );
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
	<link href="css/aboutus.css" rel="stylesheet">
	<link href="css/Footer-with-button-logo.css" rel="stylesheet">
	
	<script src="javaScript/bootstrap_v4.5.2.js"></script>
	<script src="javaScript/jQuery_v3.5.1.js"></script>
	

	<title> About Us </title>

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
	      			            
                    <% if ( session.getAttribute ( "role" ) == null ) { %>
                    
                    <li> <a href="login.jsp"> Login </a> </li>
                    <li> <a href="index.jsp"> Home </a> </li>
                    
                    <% } else if ( session.getAttribute ( "role" ).toString().equals ( "admin" ) ) {//role is admin  %>
                    
                    <li> <a href="AdminHomePage.jsp"> Home </a> </li>
                    <li> <a href="Logout"> Logout </a> </li>
                    
                     <% } else if ( session.getAttribute ( "role" ).toString().equals ( "manager" ) ) {//role is manager %>
                    
                    <li> <a href="ManagerHomePage.jsp"> Home </a> </li>
                    <li> <a href="Logout"> Logout </a> </li>
                    
                     <% } else if ( session.getAttribute ( "role" ).toString().equals ( "member" ) ) {//role is member %>
                    
                    <li> <a href="member.jsp"> Home </a> </li>
                    <li> <a href="Logout"> Logout </a> </li>
                    
                    <% } %>
                    
	    	</ul>
	    	
	  	</div>

	</nav>

</div>

<!-- NAVBAR -->

<div class="row"> <br> <br> <br> </div>


<!-- ABOUT US DATA -->



		
<div class="container">

	<h2>About Us </h2>
	
	<i class="fa fa-quote-left fa-1x fa-pull-left fa-border"></i>
	Meeting Rooms is an Easy-To-Use interface to create and organize meetings. 
	A simple interface allows selection of various amenities , such as a Projector , a Whiteboard  , and much more.
	It has a few rooms preset with amenities to allow for ease in booking such as a Classroom Training Room and Conferencing Room. 
	It is further equipped with a credit system proportional to resources used. 
	Hence it is a to-go system to book any meetings or conferences.
	
    <br>
    <br>
    <h3>Int Elegance </h3>
    <i class="fa fa-quote-left fa-1x fa-pull-left fa-border"></i>
    Int_Elegance is a 10 member team from WFS-2. 
    They are enthusiastic about coding , and are guided by mentor Rajesh Upadhyay for Code Fury 2020.
    
    <div class="row"> <br><br> </div>
    
    <br>
    <br>
    <h4>Meet our Team!</h4>
    
    
   <div class="row">
  <div class="columnnew">
    <div class="card" style='text-align:center;'>
      <img src="images/team/ashutosh.jpg" alt="ashutosh" style="width:50%">
      <h5>Ashutosh Danwe</h5>
      <div class="container">
      </div>
    </div>
  </div>

  <div class="columnnew">
    <div class="card" style='text-align:center;'>
      <img src="images/team/haritha.jpg" alt="haritha" style="width:50%">
      <h5>Haritha Jayan</h5>
      <div class="container">
      </div>
    </div>
  </div>

  <div class="columnnew">
    <div class="card" style='text-align:center;'>
      <img src="images/team/mrunal.jpg" alt="mrunal" style="width:50%">
      <h5>Mrunal Ahire</h5>
      <div class="container">
      </div>
    </div>
  </div>  
  
  <div class="columnnew">
    <div class="card" style='text-align:center;'>
      <img src="images/team/drishika.jpg" alt="drishika" style="width:50%">
      <h5>Drishka Dey</h5>
      <div class="container">
      </div>
    </div>
  </div>
  
  <div class="columnnew">
    <div class="card" style='text-align:center;'>
      <img src="images/team/ravi.jpg" alt="ravi" style="width:50%">
      <h5>Ravi Kachhadiya</h5>
      <div class="container">
      </div>
    </div>
  </div>
  </div>
  
  <div class="row">
  <div class="columnnew">
    <div class="card" style='text-align:center;'>
      <img src="images/team/sophia.jpg" alt="sophia" style="width:50%">
      <h5>Sophia Tiwari</h5>
      <div class="container">
      </div>
    </div>
   </div>
 
  
  
  <div class="columnnew">
    <div class="card" style='text-align:center;'>
      <img src="images/team/akspreet.jpg" alt="akspreet" style="width:50%">
      <h5>Akspreet Kaur</h5>
      <div class="container">
      </div>
    </div>
  </div>
  
  <div class="columnnew">
    <div class="card" style='text-align:center;'>
      <img src="images/team/kunal.jpg" alt="kunal" style="width:50%">
      <h5>Kunal Rasam</h5>
      <div class="container">
      </div>
    </div>
  </div>
  
 <div class="columnnew">
    <div class="card" style='text-align:center;'>
      <img src="images/team/aishwarya.jpg" alt="aishwarya" style="width:50%">
      <h5>Aishwarya Sonavane</h5>
      <div class="container">
      </div>
    </div>
  </div>
  
  <div class="columnnew">
    <div class="card" style='text-align:center;'>
      <img src="images/team/sakshi.jpg" alt="sakshi" style="width:50%">
      <h5>Sakshi Karanjekar</h5>
      <div class="container">
      </div>
    </div>
  </div>
  
</div>

</div>

<!-- DISPLAY MEETING LISTS -->


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
                               
                    <% if ( session.getAttribute ( "role" ) == null ) { %>
                    
                    <li> <a href="login.jsp"> Login </a> </li>
                    <li> <a href="index.jsp"> Home </a> </li>
                    
                    <% } else if ( session.getAttribute ( "role" ).toString().equals ( "admin" ) ) { %>
                    
                    <li> <a href="AdminHomePage.jsp"> Home </a> </li>
                    <li> <a href="Logout"> Logout </a> </li>
                    
                     <% } else if ( session.getAttribute ( "role" ).toString().equals ( "manager" ) ) { %>
                    
                    <li> <a href="ManagerHomePage.jsp"> Home </a> </li>
                    <li> <a href="Logout"> Logout </a> </li>
                    
                     <% } else if ( session.getAttribute ( "role" ).toString().equals ( "member" ) ) { %>
                    
                    <li> <a href="member.jsp"> Home </a> </li>
                    <li> <a href="Logout"> Logout </a> </li>
                    
                    <% } %>                    
                    
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
