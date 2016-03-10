<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
	<h1>Login here</h1>
	<p style="color: red;">${errorPass}</p>
	<p style="color: red;">${errorUser}</p>
	<form name="input" action="Login" method="post">
		User name: <input type="text" name="username" value="${existingName}"> <br></br>
		Password: <input type="password" name="password"> <br/>
		<br/>
		<input type="checkbox" name="remember"> Remember me on this computer<br/>
		<br/>
		<input type="submit" class="btn" value="Submit">
	</form>
	<form action="register.jsp">
		<br/> <input type="submit" class="btn" value="Register">
	</form>
</t:layout>