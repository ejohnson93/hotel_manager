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
	<p id="success" style="display:none">
		This Reservation has successfully been added to your Shopping Cart!<br/>
		Click "Check Out" to go to the shopping cart or click "New Search" to search for a new hotel
	</p>
	<form name="form" id="form">
		<c:choose>
			<c:when test="${ notLoggedIn != null }">
				<p style="color: red;">${ notLoggedIn  }</p>
			</c:when>
			<c:otherwise>
			<div>Confirm/Change Number of Rooms</div>
			<div style="color: red;">${ error }</div>
			<input type="text" name="numRooms" value="${ requestRooms }"/>
			</c:otherwise>
		</c:choose>
		<input type="hidden" name="roomId" value="${ hotel.getHotelRoomByIndex(0).getId() }" />
		<input type="hidden" name="checkInDate" value="${ checkInDate }"/>
		<input type="hidden" name="checkOutDate" value ="${ checkOutDate }" />
		<input type="hidden" name="hotelId" value ="${ hotel.getId() }" />
	</form>
	
	<button class="btn" id="addToCart" style="display:inline-block">Add To Cart</button>
	<form action="ShoppingCart" method="GET" style="display:inline-block">
	 	<input class="btn" type="submit" value="Check Out" />
	</form>
	<br/>
	<button class="btn" id="back" onClick="goBack();" style="display:inline-block">Go Back</button>
	<form action="CustomerHomePage" method="GET" style="display:inline-block">
	 	<input class="btn" type="submit" value="New Search" />
	</form>

<script>
function goBack() {
    window.history.back();
}

$("#addToCart").click(function(){
	var rooms = $('input[name="numRooms"]', 'form[name="form"]').get(0).value;
	var id = $('input[name="roomId"]', 'form[name="form"]').get(0).value;
	var checkIn = $('input[name="checkInDate"]', 'form[name="form"]').get(0).value;
	var checkOut = $('input[name="checkOutDate"]', 'form[name="form"]').get(0).value;
	var hId = $('input[name="hotelId"]', 'form[name="form"]').get(0).value;
	$.post('ShoppingCart', {numRooms: rooms,
							roomId: id,
							checkInDate: checkIn,
							checkOutDate: checkOut,
							hotelId: hId}).done(function(){
								$("#form").hide();
								$("#success").show();
							});
});

</script>

</t:layout>