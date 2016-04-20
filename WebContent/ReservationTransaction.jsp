<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="models.*" %>

<t:layout>
	<div id="success" style="display:none"><p>Reservation was Successful!!</p></div>
	<div id="resInfo" >
		<c:set var="i" value="0" scope="page" />
		<c:forEach items="${hrList}" var="hr">
			<h3><c:out value="${hr.getHotel().getName()}" /></h3>
			<b>Price: </b><p><c:out value="${ prices.get(i) }"/></p>
			<b>Check in Date: </b><p><c:out value="${ hr.getCheckInDate() }"/></p>
			<b>Check out Date: </b><p><c:out value="${ hr.getCheckOutDate() }"/></p>
			<b>Reservation Number: </b><p><c:out value="${ hr.getReservationNum() }"/></p>
			<c:set var="i" value="${ i + 1 }" scope="page" />
			<input type="hidden" value="${hr.getId()}" name="hrIds"/>
		</c:forEach>
		<b>Total Price: </b><p>$<c:out value="${ totalPrice }"/></p>
		<input type="button" class="btn" value="Print" onclick="makePrintable('resInfo')" id="print" style="display:none"/>
	</div>
	<div id="error_div" style="color: red;"></div>
	<div id="display" class="form-group">
	<form id="this_form">
		<h3>Payment Information</h3>
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
			<input name="billAddress" type="text" />
		</div>
		<br/>
		<div>
			<div id="info" style="color: red; display:none;">
				<p><b>Please wait wile the transaction is being processed...</b></p>
				<p>Please do not close the page or hit any other buttons...</p>
			</div>
			<input type="submit" class="btn" value="Confirm Reservation"/> 
		</div>
		<input type="hidden" value="${totalPrice}" name="price"/>
	</form>
	</div>
	<br />
	 <button class="btn" onclick="goBack()">Go Back</button>

<script>
function goBack() {
    window.history.back();
}

$("#this_form").submit(function(event) {

    event.preventDefault();
    
    var statusId = -1;
    
    $("#info").show();
    $.post( "../Banking/BankingValidation", { name: $("#this_form div input[name=firstName]").val() + " " +  $("#this_form div input[name=lastName]").val(),
    										 number: $("#this_form div input[name=cardNumber]").val(),
    										 expMonth: $("#this_form div select[name=expMonth]").val(),
    										 expYear: $("#this_form div select[name=expYear]").val(),
    										 code: $("#this_form div input[name=securityCode]").val(),
    										 price: $("#this_form input[name=price]").val()},
    	function(data,status) {	
		statusId = data;
		}).done(function(){

			if(statusId == 0){
			$("#error_div").html("");
			var idList = {'idList[]' : []};
			$("input[name=hrIds]").each(function(){
				idList['idList[]'].push($(this).val());
			});
			$.post("UpdateReservationHistory", idList).done(function(){
					 $("#display").hide();
					 $("#print").show();
					 $("#success").show();
					 $("#info").hide();
				 });
			}else if(statusId == 1){

				$("#error_div").html("Insufficient funds");
				$("#info").hide();
			}
			else if(statusId == 2){

				$("#error_div").html("<p>Incorrect Credit Card information</p>");
				$("#info").hide();
			}
			else if(statusId == 3){
				$("#error_div").html("<p>Error, credit card expired</p>");
				$("#info").hide();
			}
			
		});
    
    
  });
  
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
</t:layout>