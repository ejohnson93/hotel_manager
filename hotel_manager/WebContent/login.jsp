<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<h1> Login here</h1>
<%if (request.getAttribute("errorMessage") != null){ %>
<h2><%= request.getAttribute("errorMessage") %></h2>
<%}else { %>
<h2>If you do not have an account, please click the "Register" button below to create an account.</h2>
<%} %>
<form name="input" action="Login" method="post">
User name: <input type="text" name="username">
<br></br>
Password: <input type="password" name="password">
<input type="submit" value="Submit">
</form> 
<form action="register.jsp">
<br></br>
	<input type="submit" value="Register">
</form>
</body>
</html>