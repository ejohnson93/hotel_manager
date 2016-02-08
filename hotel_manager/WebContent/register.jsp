<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Register</title>
</head>
<body>
	<h1> Register here</h1>
	<h2>Please enter a username and password to create an account.</h2>
	<form id="regForm" name="input" action="Register" method="post" >
		User name: <input type="text" name="username">
		<br></br>
		Password: <input type="password" name="password">
		<br></br>
		Confirm Password: <input type="password" name="confirm">
		<br></br>
		<input type="hidden" name="matching">
		<input type="button" value="Submit" name="btnSubmit" onclick="validate()" >
	</form> 
	<% if (request.getAttribute("passError") != null) {%>
		<p style="color: red;"><%= request.getAttribute("passError") %></p>
	<%} %>
	<%if (request.getAttribute("nameTaken") != null){ %>
		<p style="color: red;"><%= request.getAttribute("nameTaken") %></p>
	<%} %>
	<%if (request.getAttribute("emptyString") != null){ %>
		<p style="color: red;"><%= request.getAttribute("emptyString") %></p>
	<%} %>
	<script>
		function validate() {
    		var password = document.getElementById("regForm").elements[1].value;
    		var confirm = document.getElementById("regForm").elements[2].value;
    		if ( password != confirm ) {
    			document.getElementById("regForm").elements[3].value = "notMatching";
    			document.forms['regForm'].submit();
    		}else{
    			document.getElementById("regForm").elements[3].value = "matching";
    			document.forms['regForm'].submit();
    		}
		}
	</script>
</body>
</html>