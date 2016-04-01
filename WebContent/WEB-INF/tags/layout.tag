<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <li><a href="CustomerHomePage">Home</a></li>
   <c:choose>
   		<c:when test="${ sessionScope.username != null }">
			<li><a href="ManageReservations">Manage Reservations</a></li>
  			<li><a href="ShoppingCart">Shopping Cart</a></li>
  		</c:when>
	</c:choose>
  <ul style="float:right;list-style-type:none;">
  <c:choose>
   		<c:when test="${ sessionScope.username != null }">
		  	<li><a>Hello, ${sessionScope.username }</a></li>
		    <li><a href="Login">Logout</a></li>
		</c:when>
		<c:otherwise>
    	<li><a href="Login">Login</a></li>
    	</c:otherwise>
  	</c:choose>
  </ul>
</ul>
<br>
  <body>
  	<div class="body">
    	<jsp:doBody/><br></br>
    </div>
  </body>
</html>