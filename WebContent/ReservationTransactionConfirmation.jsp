<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<h3>Reservation to Gen-Eric Hotel Confirmed</h3>
	Total is $202.46
	<p style="color: red;">${ error }</p>
	<form action="CustomerHomePage">
		<input type="submit" class="btn" value="Return Home" />
	</form>
</t:layout>