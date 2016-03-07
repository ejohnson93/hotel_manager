<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="models.*" %>

<t:layout>

	<h1>Confirm Cancellation</h1>
	<div>
		<div>
			<c:out value="${hr.getHotel().getName()}" />
		</div>
		<div>
			<c:out value="${hr.getNumRooms()} Rooms" />
		</div>
		<div>
			<c:out value="Total: ${hr.getNumRooms()*hr.getRoom().getPricePerNight()}" />
		</div>
	</div>
	<form action="ReservationCancellationConfirmation" method="post">
		<input type="hidden" name="hrid" value="${hr.getId()}" />
		<input type="submit" class="btn" value="Confirm Cancellation"/>
	</form>
	<form action="CustomerHomePage.jsp">
		<input type="submit" class="btn btn-danger" value="Cancel" />
	</form>
</t:layout>