<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="models.*" %>

<t:layout>
	<h1>Cancellation Confirmation</h1>
	<h3>
		<c:out value="Your Reservation to ${hr.getHotel().getName()} in ${hr.getHotel().getCity()} has been successfully cancelled" />
	</h3>
	<div>
		<c:out value="${hr.getNumRooms()} Rooms" />
	</div>
	<div>
		<c:out value="Your payment of ${hr.getNumRooms()*hr.getRoom().getPricePerNight()} will be fully refunded." />
	</div>
	<form action="CustomerHomePage">
		<input type="submit" class="btn" value="Return Home" />
	</form>
</t:layout>