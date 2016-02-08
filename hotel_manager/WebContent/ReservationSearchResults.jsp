<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

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
			<tr>
				<td>
					Gen-Eric
				</td>
				<td>
					601 Drury Lane
				</td>
				<td>
					Very Nice hotel
				</td>
				<td>
					Suite
				</td>
				<td>
					****
				</td>
				<td>
					$101.23
				</td>
				<td>
					Lots of Amenities
				</td>
				<td>
					<form action="ViewAndBookReservations.jsp">
						<input type="submit" class="btn" value="View and Book"/>
					</form>
				</td>
			</tr>
		</tbody>
	</table>
</t:layout>