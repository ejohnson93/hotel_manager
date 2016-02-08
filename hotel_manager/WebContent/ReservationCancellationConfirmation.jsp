<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<h1>Cancellation Confirmation</h1>
	<h3>Your Reservation to Gen-Eric Hotels has been successfully cancelled</h3>
	<div>
		3 Rooms
	</div>
	<div>
		Your payment of $202.46 will be refunded.
	</div>
	<form action="CustomerHomePage.jsp">
		<input type="submit" class="btn" value="Return Home" />
	</form>
</t:layout>