<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
	<h1>Login here</h1>
	<p style="color: red;">${errorPass}</p>
	<p style="color: red;">${errorUser}</p>
	<form name="input" action="Login" method="post">
		User name: <input type="text" name="username"> <br></br>
		Password: <input type="password" name="password"> <input
			type="submit" value="Submit">
	</form>
	<form action="register.jsp">
		<br></br> <input type="submit" value="Register">
	</form>
</t:layout>