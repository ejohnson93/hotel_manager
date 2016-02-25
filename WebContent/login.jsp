<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<% if (request.getAttribute("errorPass") != null){
	Object error = 	request.getAttribute("errorPass");
	request.setAttribute("error", error);
}
else if (request.getAttribute("errorUser") != null){
	Object error = 	request.getAttribute("errorUser");
	request.setAttribute("error", error);
}
%>

<t:layout>
	<h1>Login here</h1>
	<p style="color: red;">${error}</>
	<form name="input" action="Login" method="post">
		User name: <input type="text" name="username"> <br></br>
		Password: <input type="password" name="password"> <input
			type="submit" value="Submit">
	</form>
	<form action="register.jsp">
		<br></br> <input type="submit" value="Register">
	</form>
</t:layout>