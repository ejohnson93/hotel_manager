<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="models.*" %>

<c:set var="i" value="0" scope="page" />

<t:layout>
<h1>Your Shopping Cart</h1>
	<p style="color: red;">${insufficientRooms}</p>
	<c:choose>
		<c:when test="${ cartEmpty != null }">
			<p style="color: black;">${ cartEmpty  }</p>
			<form action="CustomerHomePage" method="GET">
			 	<input class="btn" type="submit" value="Reservation Search" />
			</form>
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
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<h4>
				The total price of your shopping cart is <c:out value="${totalPrice}" />!
			</h4>
			<form action="CustomerHomePage" method="GET" style="display:inline-block">
			 	<input class="btn" type="submit" value="Continue Shopping" />
			</form>
			<form action="ViewAndBook" method="POST" style="display:inline-block">
				<c:forEach items="${hrList}" var="hr">
					<input type="hidden" name="idList" value="${ hr.getId() }"/>
					<input type="hidden" name="totalPrice" value="${totalPrice}"/>
				</c:forEach>
			 	<input class="btn" type="submit" value="Check Out" />
			</form>
		</c:otherwise>
	</c:choose>
</body>
</t:layout>