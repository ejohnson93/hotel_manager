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
   <% Cookie[] cookies = request.getCookies();
   Cookie cookie = null;
   if( cookies != null ){
	for (int i = 0; i < cookies.length; i++){
		cookie = cookies[i];
		if (cookie.getName().equals("login")) {
			break;
		}
	}
   if (cookie != null && cookie.getValue().equals("true")) { %>
  <li><a href="ManageReservations.jsp">Manage Reservations</a></li>
  <% } %>
  <ul style="float:right;list-style-type:none;">
  <% if (cookie != null && cookie.getValue().equals("true")) { %>
    <li><a href="Login">Logout</a></li>
    <%} else { 
    %>
    	<li><a href="Login">Login</a></li>
    <% }}	%>
  </ul>
</ul>
<br>
  <body>
  	<div class="body">
    	<jsp:doBody/><br></br>
    </div>
  </body>
</html>