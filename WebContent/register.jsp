<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
	<h1>Register here</h1>
	<h2>Please enter a username and password to create an account.</h2>
	<form id="regForm" name="input" action="Register" method="post">
		User name: <input type="text" name="username"> <br></br>
		Password: <input type="password" name="password"> <br></br>
		Confirm Password: <input type="password" name="confirm"> <br></br>
		<input type="hidden" name="matching"> <input type="button"
			value="Submit" name="btnSubmit" onclick="validate()">
	</form>
	<p style="color: red;">${passError}</p>
	<p style="color: red;">${nameTaken}</p>
	<p style="color: red;">${emptyString}</p>
	
</t:layout>
	<script>
		function validate() {
			var password = document.getElementById("regForm").elements[1].value;
			var confirm = document.getElementById("regForm").elements[2].value;
			if (password != confirm) {
				document.getElementById("regForm").elements[3].value = "notMatching";
				document.forms['regForm'].submit();
			} else {
				document.getElementById("regForm").elements[3].value = "matching";
				document.forms['regForm'].submit();
			}
		}
	</script>