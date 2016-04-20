<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="models.*" %>

<c:set var="i" value="0" scope="page" />

<t:layout>
<h1>Manage Account</h1>
<form action="ManageAccount" method="post">
<div class="container">
	<div class="col-sm-6" style="height:75px;">
	<p style="color: red;">${noPass}</p>
	<h3>Update Password</h3>
		<div class='col-md-5'>
			<div class="form-group">
				<div>
					Current Password: <input name='currentPass' type='password'/> <br/>
					New Password: <input name='newPass' type='password'/> <br/>
					Confirm New Password: <input name='confirmNewPass' type='password'/> <br/>
				</div>
			</div>
		</div>
	<h3>User Info</h3>
	   <div class='col-md-5'>
			<div class="form-group">
				<div>
					<c:out value="Username: ${userInfo.getUsername()}" /> <br/>
					<input name='username' type='hidden' value='${userInfo.getUsername()}'/>
				</div>
			</div>
		</div>
		<div class='col-md-5'>
			<div class="form-group">
				<div>
					<c:out value="Current first name: ${userInfo.getFirstName()}" /> <br/>
					Update first name: <input name='firstName' type='text'/>
				</div>
			</div>
		</div>
		<div class='col-md-5'>
			<div class="form-group">
				<div>
					<c:out value="Current last name: ${userInfo.getLastName()}" /> <br/>
					Update last name: <input name='lastName' type='text'/>
				</div>
			</div>
		</div>
		<div class='col-md-5'>
			<div class="form-group">
				<div>
					<c:out value="Current address line 1: ${userInfo.getAddressLine1()}" /> <br/>
					Update address line 1: <input name='address1' type='text'/> <br/>
					<c:out value="Current address line 2: ${userInfo.getAddressLine2()}" /> <br/>
					Update address line 2: <input name='address2' type='text'/> <br/>
					<c:out value="Current City: ${userInfo.getCity()}" /> <br/>
					Update city: <input name='city' type='text'/> <br/>
					<c:out value="Current State: ${userInfo.getState()}" /> <br/>
					Update state: <input name='state' type='text'/> <br/>
					<c:out value="Current postal code: ${userInfo.getPostalCode()}" /> <br/>
					Update postal code: <input name='postalCode' type='text'/> <br/>
				</div>
			</div>
		</div>
	</div>
	<input type="submit" class='btn' value="submit"/> 
</div>
	
</form>
</body>
</t:layout>