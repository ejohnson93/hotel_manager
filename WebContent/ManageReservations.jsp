<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="models.*" %>

<c:set var="i" value="0" scope="page" />

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
					<c:out value="${prices.get(i)}" />
					<c:set var="i" value="${ i + 1 }" scope="page" />
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