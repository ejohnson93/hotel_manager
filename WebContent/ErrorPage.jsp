<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="models.*" %>

<t:layout>
<h1>Error Handling Page</h1>
<h2>Details</h2>
<c:choose>
	<c:when test="${pageContext.exception != null }">
		<p>Type: ${pageContext.exception["class"] }</p>
		<p>Message: ${pageContext.exception.message }</p>
	</c:when>
	<c:when test="${pageContext.errorData.statusCode == 401 || pageContext.errorData.statusCode == 403}">
		<p>Type: Authorization Error</p>
		<p>Message: Stop! Go back now! You are not authorized to access this page!</p>
	</c:when>
	<c:otherwise>
		<p>Type: 404 Error</p>
		<p>Message: The URL you are typed in is about as accurate as a Stormtrooper's blaster...</p>
	</c:otherwise>
</c:choose>

</t:layout>