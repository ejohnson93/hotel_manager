<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>



<t:layout>
    <h1>Welcome</h1>
    
<form action="ReservationSearchResults.jsp" method="post">
<div class="container">
	<div class="col-sm-6" style="height:75px;">
	   <div class='col-md-5'>
			<div class="form-group">
				<div>Start</div>
				
				<div class='input-group date' id='startDate'>
					<input name='startDate' type='text' class="form-control" />
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
					</span>
				</div>
			</div>
		</div>
		<div class='col-md-5'>
			<div class="form-group">
				<div>End</div>
				
				<div class='input-group date' id='endDate'>
					<input name='endDate' type='text' class="form-control" />
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
					</span>
				</div>
			</div>
		</div>
		<div class='col-md-5'>
			<div>City</div>
			<div class='form-group'>
				<input name='city' type='text' placeholder='Type a city' />
			</div>
		</div>
		<div class='col-md-5'>
			<div>Number of Rooms</div>
			<div class='form-group'>
				<input name="numRooms" type='text' placeholder='Number of Rooms' />
			</div>
		</div>
		<div class='col-md-5'>
			<div>Room Type</div>
			<div class='form-group'>
				<select name="roomType" value='' >
					<option id='default' value='0' selected=''>Select Room Type</option>
					<option value="1">Standard</option>
					<option value="2">Family</option>
					<option value="3">Suite</option>
				</select>
			</div>
		</div>
		<div class='col-md-5'>
			<div>Amenities</div>
			<div class='form-group'>
				<input type="checkbox" name="Amenities" value="bathtub">Bathtub in Room<br>
				<input type="checkbox" name="Amenities" value="breakfast">Free breakfast<br>
				<input type="checkbox" name="Amenities" value="wifi">Free Wi-Fi<br>
				<input type="checkbox" name="Amenities" value="gym">Gym<br>
				<input type="checkbox" name="Amenities" value="internet">Paid Internet Access<br>
				<input type="checkbox" name="Amenities" value="pool">Pool<br>
				<input type="checkbox" name="Amenities" value="pet">Pet friendly 
			</div>
		</div>
	</div>
	<input type="submit" class='btn' value="submit"/> 
</div>
	
</form>
<script src="http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.5.1/moment.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/3.0.0/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet" type="text/css" href="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />

    <script type="text/javascript">
    $(function () {
    	$('#startDate').datetimepicker();
    	$('#endDate').datetimepicker();
    	$("#startDate").on("dp.change",function (select) {
            $('#endDate').data("DateTimePicker").setMinDate(select.date);
            $('.bootstrap-datetimepicker-widget').hide();
    	});
    	$("#endDate").on("dp.change",function (select) {
            $('#startDate').data("DateTimePicker").setMaxDate(select.date);
            $('.bootstrap-datetimepicker-widget').hide();
    	});
    });
    </script>
</t:layout>