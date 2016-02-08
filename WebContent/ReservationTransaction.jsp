<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<h3>Gen-Eric Hotel</h3>
	<div>
	Total Cost $202.46
	</div>
	<div class="form-group">
	<form action="ReservationTransactionConfirmation.jsp" method="post">
		<div>
			<div>Cardholder First Name</div>
			<input name="firstName" type="text" />
		</div>
		<div>
			<div>Cardholder Last Name</div>
			<input name="lastName" type="text" />
		</div>
		<div>
			<div>Card Type</div>
			<select name="cardType" value="Card Type">
				<option value="visa">Visa</option>
				<option value="masterCard">Master Card</option>
				<option value="discover">Discover</option>
				<option value="amExpress">American Express</option>
			</select>
		</div>
		<div>
			<div>Card Number</div>
			<input name="cardNumber" type="text" />
		</div>
		<div>
			<div>Security Code</div>
			<input name="securityCode" type="text" />
		</div>
		<div>
			<div>Expiration Month</div>
			<select name="expMonth" value="Month" >
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
			</select>
		</div>
		<div>
			<div>Expiration Year</div>
			<select name="expYear" value="Year" >
				<option value="2016">2016</option>
				<option value="2017">2017</option>
				<option value="2018">2018</option>
				<option value="2019">2019</option>
				<option value="2020">2020</option>
			</select>
		</div>
		<div>
			<div>Billing Address</div>
			<input name="bilAddress" type="text" />
		</div>
		<br/>
		<div>
			<input type="submit" class="btn" value="Confirm Reservation"/> 
		</div>
	</form>
	</div>
	<br />
	<div>
		<form action="ReservationSearchResults.jsp">
			<input type="submit" class="btn btn-danger" value="Cancel" />
		</form>
	</div>
</t:layout>