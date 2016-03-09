<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="models.*" %>

<t:layout>
	<h3>${ hotel.getName() }</h3>
	<p><b>Description:</b><br /> ${ hotel.getDescription() }</p>
	<p><b>Address:</b> <br />${ hotel.getAddress() }</p>
	<p><b>Points of interest:</b> <br />${ hotel.getNearestPoints()}</p>
	<p><b>Room Type:</b> ${ hotel.getHotelRoomByIndex(0).getRoomType().getRoomType() }</p>
	<form action="ViewAndBook" method="POST">
		<div>Confirm/Change Number of Rooms</div>
		<input type="text" name="numRooms" value="${ requestRooms }" />
		<input type="submit" class="btn" value="Book"/>
		<input type="hidden" name="roomId" value="${ hotel.getHotelRoomByIndex(0).getId() }" />
		<input type="hidden" name="checkInDate" value="${ checkInDate }"/>
		<input type="hidden" name="checkOutDate" value ="${ checkOutDate }" />
	</form>
	 <button class="btn" onclick="goBack()">Go Back</button>

<script>
function goBack() {
    window.history.back();
}
</script>

</t:layout>