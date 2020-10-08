<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page isELIgnored="false" %>

<%
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
	
	<script src="javaScript/bootstrap_v4.5.2.js"></script>
	<script src="javaScript/jQuery_v3.5.1.js"></script>
	

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
	      	
	      		<li class="active"> <a href="#"> Edit Room </a> </li>
	      		
                <li> <a href="Logout"> Logout </a> </li>
            
	    	</ul>
	    	
	  	</div>

	</nav>

</div>

<!-- NAVBAR -->

<div class="row"> <br> <br> <br> </div>

<%


%>

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
