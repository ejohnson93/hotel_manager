<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>

	<h1>Confirm Cancellation</h1>
	<div>
		<div>
			Gen-Eric Hotel
		</div>
		<div>
			3 Rooms
		</div>
		<div>
			Total: $202.46
		</div>
	</div>
	<form action="ReservationCancellationConfirmation.jsp">
		<input type="submit" class="btn" value="Confirm Cancellation"/>
	</form>
	<form action="CustomerHomePage.jsp">
		<input type="submit" class="btn btn-danger" value="Cancel" />
	</form>
</t:layout>