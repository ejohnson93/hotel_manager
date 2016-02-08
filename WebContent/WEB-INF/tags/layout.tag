<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<html>
<head>
<script src="//code.jquery.com/jquery-2.2.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/Hotel.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<script src="js/bootstrap.js"></script>



</head>
<ul class="header">
<h1>Hotel Manager</h1>
</ul>

 <ul class="nav-bar">
  <li><a href="CustomerHomePage.jsp">Home</a></li>
  <li><a href="ManageReservations.jsp">Manage Reservations</a></li>
  <ul style="float:right;list-style-type:none;">
    <li><a href="login.jsp">Logout</a></li>
  </ul>
</ul>
<br>
  <body>
  	<div class="body">
    	<jsp:doBody/><br></br>
    </div>
  </body>
</html>