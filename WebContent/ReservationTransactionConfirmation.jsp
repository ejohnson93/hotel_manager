<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>

	function makePrintable(id){
		var html="<html>";
		html += document.getElementById(id).innerHTML;
		html +="</html>";
		
		var printWin = window.open('', '', 'left=0, top=0, width=' + window.innerWidth*2 + ', height =' + window.innerHeight*2 + ', toolbar=0,scrollbars=0, status=0');
		
		printWin.document.write(html);
		printWin.document.close();
		printWin.focus();
		printWin.print();
		printWin.close();
	}

</script>

<t:layout>
	<c:choose>
		<c:when test="${ error == null }">
		<div id="resInfo">
		<h3>${ hotel.getName() }</h3>
		Total is $${ price }<br/>
		<b>Check in Date: </b><p>${ reservation.getCheckInDate() }</p>
		<b>Check out Date: </b><p>${ reservation.getCheckOutDate() }</p>
		<b>Reservation Number: </b><p>${ resNum }</p>
		<h5>Reservation Successful!</h5>
		</div>
		<input type="button" class="btn" value="Print" onclick="makePrintable('resInfo')"/>
		<br ></br>
		</c:when>
		<c:otherwise>
		<p style="color: red;"> ${ error } </p>
		</c:otherwise>
	</c:choose>
	<form action="CustomerHomePage">
		<input type="submit" class="btn" value="Return Home" />
	</form>
</t:layout>