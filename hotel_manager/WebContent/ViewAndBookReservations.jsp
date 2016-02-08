<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<h3>Gen-Eric</h3>
	<div>
	Details: This is a really really reallly good hotel.
	</div>
	<div>
	Reviews:
	</div>
	<div>
	Distance to Places of Interest:
	</div>
	<form action="ReservationTransaction.jsp">
		<div>Confirm/Change Number of Rooms</div>
		<input type="text" value=""/>
		<input type="submit" class="btn" value="Book"/>
	</form>
	<form action="ReservationSearchResults.jsp">
		<input type="submit" class="btn" value="Back"/>
	</form>

</t:layout>