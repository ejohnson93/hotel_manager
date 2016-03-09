<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="models.*" %>
<%@page import="javax.servlet.jsp.*,
				utilities.GzipUtilities,
				java.io.*,
				java.util.zip.*" %>
				
<%/*
response.setContentType("text/html");

PrintWriter pw;

if(GzipUtilities.isGzipSupported(request) &&
		!GzipUtilities.isGzipDisabled(request)){
	response.setHeader("Content-Encoding", "gzip");
	pw = GzipUtilities.getGzipWriter(response);
	
	out.print(pw);
	
}else{
	pw = new PrintWriter(response.getOutputStream(), false);
}*/		
%>
<t:layout>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>
					Hotel Name
				</th>
				<th>
					Hotel Address
				</th>
				<th>
					Hotel Description
				</th>
				<th>
					Room Type
				</th>
				<th>
					Hotel Rating
				</th>
				<th>
					Price per night
				</th>
				<th>
					Amenities Available
				</th>
				<th>
				</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${hotels}" var="hotel">
			<c:forEach items="${hotel.getAllHotelRooms()}" var = "room" >
			<tr>
				<td>
					${hotel.getName()}
				</td>
				<td>
					${hotel.getAddress()}
				</td>
				<td>
					${hotel.getDescription() }
				</td>
				<td>
					${room.getRoomType().getRoomType() }
				</td>
				<td>
					${ hotel.getAverageHotelRating() }
				</td>
				<td>
					${room.getPricePerNight() }
				</td>
				<td>
					<c:forEach items="${hotel.getAllAmenities() }" var="amenity">
					
						${amenity.getName()} <br />
						
					</c:forEach>
				</td>
				<td>
					<form action="ReservationSearchResults" method="post">
						<input type="submit" class="btn" value="View and Book"/>
						<input type="hidden" name="roomId" value=${ room.getId() } />
						<input type="hidden" name="numRooms" value ="${ requestRooms }" />
						<input type="hidden" name="checkInDate" value="${ checkInDate }"/>
						<input type="hidden" name="checkOutDate" value = "${ checkOutDate }" />
					</form>
				</td>
			</tr>
			</c:forEach>
		</c:forEach>
		</tbody>
	</table>
</t:layout>