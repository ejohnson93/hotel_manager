<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="models.*" %>

<t:layout>
<h1>Your Reservations</h1>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>
					Hotel Name
				</th>
				<th>
					Number of Rooms
				</th>
				<th>
					Total
				</th>
				<th>
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${hrList}" var="hr">
			<tr>
				<td>
					<c:out value="${hr.getHotel().getName()}" />
				</td>
				<td>
					<c:out value="${hr.getNumRooms()}" />
				</td>
				<td>
					<c:out value="${hr.getNumRooms()*hr.getRoom().getPricePerNight()}" />
				</td>
				<td>
					<form action="CancelReservations" method="post">
						<input type="hidden" name="rid" value="${hr.getId()}" />
						<input type="submit" class="btn" value="Cancel Reservation" />
					</form>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</t:layout>