<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
<h1>Your Reservations</h1>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>
					Hotel Name
				</th>
				<th>
					Number of Rooms
				</th>
				<th>
					Total
				</th>
				<th>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					Gen-Eric Hotels
				</td>
				<td>
					3
				</td>
				<td>
					$202.46
				</td>
				<td>
					<form action="CancelReservations.jsp">
						<input type="submit" class="btn" value="Cancel Reservation" />
					</form>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</t:layout>