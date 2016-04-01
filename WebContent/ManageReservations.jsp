<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="models.*" %>

<c:set var="i" value="0" scope="page" />

<t:layout>
<h1>Your Reservations</h1>
	<c:choose>
		<c:when test="${ resEmpty != null }">
			<p style="color: black;">${ resEmpty  }</p>
		</c:when>
		<c:otherwise>
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
							Check In/Out
						</th>
						<th>
							Price
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
							Check In: <c:out value="${ hr.getCheckInDate() }"/>
							<br/>
							Check Out: <c:out value="${ hr.getCheckOutDate() }" />
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
		</c:otherwise>
	</c:choose>
</body>
</t:layout>