<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page isELIgnored="false" %>


<%
		// check for existing session

	if ( session.getAttribute ( "role" ) == null ) {
		
			// session does not exists
			// do nothing			
	
	} else { // session exists
		
		if ( session.getAttribute ( "role" ).toString().equals ( "member" ) ) {
			
			request.getRequestDispatcher("member.jsp").forward ( request, response );
			
		} else if ( session.getAttribute ( "role" ).toString().equals ( "admin" ) ) {
			
			request.getRequestDispatcher("AdminHomePage.jsp").forward ( request, response );
			
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

<div class="row"> <br> <br> <br> </div>

${login_page_message}

<!-- Divison Header -->
  
<div class="row">

	<div class = "col-sm-1"> </div>

  		<div class = "col-sm-4"> </div>

   			<div class = "col-sm-2">
  
    			<div class = "">
    
      				<a href="index.jsp"> <img src="images/logo/hsbc-logo-light_2.png" style=" height:70px; width:150px;"  align="left"/> </a>
        
      			</div>
      
     		</div>
     
     	<div class="col-sm-5"> </div>
      
  	</div>
    
</div>
      
<!-- Divison Header -->

<div class="row"> <br> </div>

<!-- FORM -->

<div class="container">

	<div class="col-sm-4"> </div>

	<!--login form-->			
			
		<div class="col-sm-4">
				
			<div class = "row">
	
				<div class = "row">
				
					<div class = "col-sm-2"></div>	
				
					<div class = "col-sm-8">
					
						<div class = "row">
							
							<div class = "text-center">	<h3>Login to your account</h3> </div>
							
						</div>
						
					</div>
					
					<div class = "col-sm-2"></div>
						
				</div>
		
				<div class = "row"> <br> <br> </div>	
	
				<div class = "row">
				
					<div class = "col-sm-2"></div>
				
					<div class = "col-sm-8">
						
						<div class = "text-center">	<img src="images/login/login-user-icon.png" style="width:200px;height:200px;"> </div>
					
					</div>
					
					<div class = "col-sm-2"></div>
					
				</div>
	
				<div class = "row"> <br> <br> </div>	
		
				<div class = "row">	
	
					<div class = "row"> ${login_message} </div>
					
					<form action="Login" method="post">  						
  						
  						<div class = "row">	
							
							<div class="input-group">
  							
  								<span class="input-group-addon"><i class="glyphicon glyphicon-cog"></i></span>
  								<input type="text" placeholder="ID" name="user_id" id="user_id" class="form-control" required>
							
							</div>
							
						</div>
							
						<div class = "row"> <br> </div>
							
						<div class = "row">	
							
							<div class="input-group">
  							
  									<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
  									<input type="password" placeholder="Password" name="user_password" id="user_password" class="form-control" required>
							
							</div>
						
						</div>
							
						<div class = "row"> <br> </div>
							
						<div class = "row">
						
							<button type="submit" class="btn btn-success"> Login </button>
							
						</div>
							
					</form>
			</div>	
	
		</div>
		
	</div>

	<!--/login form-->

	<div class="col-sm-4"> </div>

</div>

<!-- FORM -->


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
                    
                    <% if ( session.getAttribute ( "role" ) == null ) { %>
                    
                    <li> <a href="login.jsp"> Login </a> </li>
                    
                    <% } else { %>
                    
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