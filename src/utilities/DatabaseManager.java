package utilities;

import models.*;
import models.User.VALIDATE;

import java.sql.*;
import java.util.List;

public class DatabaseManager {
	
//private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private final String DB_CONNECTION = "jdbc:mysql://cse.unl.edu:3306/ejohnson";
	private static final String DB_USER = "ejohnson";
	private static final String DB_PASSWORD = "hz8EyQ";
	
	public DatabaseManager(){
		
	}
	
	List<Hotel> getAllHotels(){
		
		List<Hotel> hotels = null;
		
		Connection conn = null;
		
		PreparedStatement ps = null; 
		
		try{
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
			
			//Get the hotels
			String query = "SELECT * FROM Hotels";
		
			ps = conn.prepareStatement(query);
		
			ResultSet rs = ps.executeQuery();	
		
			while(rs.next()){
			
				Hotel h = new Hotel();
				h.setName(rs.getString("Name"));
				h.setCity(rs.getString("City"));
				h.setId(rs.getInt("Id"));
				h.setDescription(rs.getString("Description"));
				h.setNearestPoints(rs.getString("NearestPoints"));
				h.setAddress(rs.getString("Address"));
				
				query = "SELECT * FROM Amenities AS a "+ 
				"JOIN HotelAmenities AS ha ON a.Id = ha.AmenityId " + 
				"JOIN Hotels AS h ON ha.HotelId = h.Id " + 
				"WHERE h.Id =?";
				
				
				ps = conn.prepareStatement(query);
				ps.setInt(1, h.getId());
				ResultSet rs1 = ps.executeQuery();
				
				
				while(rs1.next()){
					
					Amenity a = new Amenity();
					a.setId(rs1.getInt("a.Id"));
					a.setName(rs1.getString("a.Name"));
					
					h.addAmenity(a);
					
				}
				
				
				//Get the hotel reviews for the hotel
				
				query = "SELECT * FROM HotelReviews AS r "+ 
				"JOIN Hotels AS h ON r.HotelId = h.Id " + 
				"WHERE h.Id =?";
				
				
				ps = conn.prepareStatement(query);
				ps.setInt(1, h.getId());
				rs1 = ps.executeQuery();
				
				
				while(rs1.next()){
					
					HotelReview r = new HotelReview();
					r.setId(rs1.getInt("r.Id"));
					r.setReviewerName(rs1.getString("r.ReviewerName"));
					r.setReviewDate(rs1.getDate("r.ReviewDate"));
					r.setReview(rs1.getString("r.Review"));
					r.setHotelId(rs1.getInt("r.HotelId"));
					
					h.addReview(r);
					
				}
				
				//Get the HotelRooms for the hotel
				
				query = "SELECT * FROM HotelRooms AS r"+ 
				"JOIN Hotels AS h ON r.HotelId = h.Id" + 
				"WHERE h.Id =?";
				
				ps = conn.prepareStatement(query);
				ps.setInt(1, h.getId());
				rs1 = ps.executeQuery();
				
				
				while(rs1.next()){
					
					HotelRoom r = new HotelRoom();
					
					query = "SELECT * FROM HotelRoomType AS t"+
							"JOIN HotelRooms AS r ON t.Id = r.RoomTypeId" + 
							"WHERE r.Id = ?";
					ps = conn.prepareStatement(query);
					ps.setInt(1, rs1.getInt("r.Id"));
					ResultSet rs2 = ps.executeQuery();
					HotelRoomType rt = new HotelRoomType();
					
					rt.setId(rs2.getInt("t.Id"));
					rt.setRoomType(rs2.getString("RoomType"));
					rt.setDescription(rs2.getString("Description"));
					
					r.setId(rs1.getInt("r.Id"));
					r.setHotelId(rs1.getInt("r.HotelId"));
					r.setAvailableNum(rs1.getInt("r.AvailableNumber"));
					r.setPricePerNight(rs1.getDouble("r.PricePerNight"));
					r.setStartDate(rs1.getDate("r.StartDate"));
					r.setEndDate(rs1.getDate("r.EndDate"));
					r.setRoomType(rt);
					
					h.addRoom(r);
					rs2.close();
					
				}
				
				rs1.close();
				
				hotels.add(h);
				
			}
				
			rs.close();

		}catch(Exception e){
			
			e.printStackTrace();
			
		}finally{
			
			try {
				if(ps != null){
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return hotels;
		
	}
	
	List<Amenity> getAllAmenities(){
		
		List<Amenity> amenities = null;
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		try{
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		
			String query = "SELECT * FROM Amenities";
		
			ps = conn.prepareStatement(query);
		
			ResultSet rs = ps.executeQuery();	
		
			while(rs.next()){
			
				Amenity a = new Amenity();
				a.setId(rs.getInt("Id"));
				a.setName(rs.getString("Name"));
				amenities.add(a);
				
			}
		
			rs.close();
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}finally{
			
			try {
				if(ps != null){
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
		return amenities;
		
	}
	//Probably won't need this
	List<HotelReview> getAllHotelReviews(){
		
		List<HotelReview> hotelReviews = null;
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		try{
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		
			String query = "SELECT * FROM HotelReviews";
	
			ps = conn.prepareStatement(query);
	
			ResultSet rs = ps.executeQuery();	
	
			while(rs.next()){
				
				HotelReview r = new HotelReview();
				r.setReviewerName(rs.getString("ReviewerName"));
				r.setId(rs.getInt("Id"));
				r.setReviewDate(rs.getDate("ReviewDate"));
				r.setRating(rs.getInt("Rating"));
				r.setReview(rs.getString("Review"));
				r.setHotelId(rs.getInt("HotelId"));
				hotelReviews.add(r);
			
			}
			
			rs.close();
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			try {
				if(ps != null){
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return hotelReviews;
		
	}
	//Create Get all Room Types.
	//PUT FUNCTION HERE
	
	List<HotelRoomType> getAllRoomTypes(){
		
		List<HotelRoomType> roomTypes = null;
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		try{
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		
			String query = "SELECT * FROM HotelRoomType";
	
			ps = conn.prepareStatement(query);
	
			ResultSet rs = ps.executeQuery();	
	
			while(rs.next()){
				
				HotelRoomType r = new HotelRoomType();
				r.setId(rs.getInt("Id"));
				r.setDescription(rs.getString("Description"));
				r.setRoomType(rs.getString("RoomType"));
				roomTypes.add(r);
			}
			
			rs.close();
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			try {
				if(ps != null){
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return roomTypes;
		
	}
	
	//Need to create search to search hotels based on:
	//Check in date
	//Check out date
	//City
	//Num rooms
	//Room Type
	//Amenities
	//PUT FUNCTION HERE
	
	//TODO: Create getUserFuction
	
	public boolean addUser(User u){
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		boolean success = false;
		
		try{
		//	Class.forName(DB_DRIVER);
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		
			String insert = "INSERT INTO Users " +
							"(Username, Password) VALUES " + 
							"(?, ?)";
			
			ps = conn.prepareStatement(insert);
			
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
	
			ps.executeUpdate();	
			success = true;
		
		}catch(Exception e){
			success = false;
			e.printStackTrace();
			System.out.println("Connection Error");
		}finally{
			
			try {
				if(ps != null){
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return success;
		
		
	}
	
	//TODO: Create validateUser
	
	public VALIDATE validateUser(User u){
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		String pass = null;
		
		try{
			
		//	Class.forName(DB_DRIVER);
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		
			String query = "SELECT * FROM Users " + 
							"WHERE Username = ?";
	
			ps = conn.prepareStatement(query);
			ps.setString(1, u.getUsername());
	
			ResultSet rs = ps.executeQuery();	
			
			pass = rs.getString("Password");

			System.out.println(pass);
			
			rs.close();
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			try {
				if(ps != null){
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(pass == null){
			return VALIDATE.NOTFOUND;
		}else{
			
			if(pass.equals(u.getPassword())){
				return VALIDATE.VALID;
			}else{
				return VALIDATE.INVALID;
			}
			
		}
		
	}
	
	public void updateUser(User u){
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		try{
			
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		
			String update = "UPDATE Users SET " + 
							"FirstName = ?, " +
							"LastName = ?, " +
							"AddressLine1 = ?, " +
							"AddressLine2 = ?, " +
							"City = ?, " + 
							"State = ?, " + 
							"PostalCode = ?, " +
							"Type = ?, " + 
							"Status = ?, " + 
							"Username = ?, " + 
							"Password ? " + 
							"WHERE Id = ?";
	
			ps = conn.prepareStatement(update);
			ps.setString(1, u.getFirstName());
			ps.setString(2, u.getLastName());
			ps.setString(3, u.getAddressLine1());
			ps.setString(4, u.getAddressLine2());
			ps.setString(5, u.getCity());
			ps.setString(6, u.getState());
			ps.setString(7, u.getPostalCode());
			ps.setInt(8, u.getType());
			ps.setInt(9, u.getStatus());
			ps.setString(10, u.getUsername());
			ps.setString(11, u.getPassword());
			ps.setInt(12, u.getId());
	
			ps.executeUpdate();	
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			try {
				if(ps != null){
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void updateCreditCard(CreditCard c){
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		try{
			
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		
			String update = "UPDATE CreditCards SET " + 
							"CardholderName = ?, " +
							"CreditCardNumber = ?, " +
							"Balance = ?, " +
							"CardNickname = ?, " +
							"UserId = ?, " + 
							"CVV = ? " + 
							"WHERE Id = ?";
	
			ps = conn.prepareStatement(update);
			ps.setString(1, c.getCardHolderName());
			ps.setString(2, c.getCreditCardNumber());
			ps.setDouble(3, c.getBalance());
			ps.setString(4, c.getCardNickname());
			ps.setInt(5, c.getUserId());
			ps.setString(6, c.getcVV());
			ps.setInt(7, c.getId());

	
			ps.executeUpdate();	
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			try {
				if(ps != null){
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	//TODO: Create a addHotelReservation
	
	public int addHotelReservation(HotelReservation h){
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		int newHotelReservationId = -1;
		
		try{

			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		
			String insert = "INSERT INTO HotelReservations " +
							"(HotelId, CheckInDate, CheckOutDate, " +
							"NumberOfRooms, ReservationNumber, UserId, "+
							"Status, Notes, RoomTypeId) VALUES " + 
							"(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			ps = conn.prepareStatement(insert);
			
			ps.setInt(1, h.getHotelId());
			ps.setDate(2, (Date)h.getCheckInDate());
			ps.setDate(3, (Date)h.getCheckOutDate());
			ps.setInt(4, h.getNumRooms());
			ps.setString(5, h.getReservationNum());
			ps.setInt(6, h.getUserId());
			ps.setInt(7, h.getStatus());
			ps.setString(8, h.getNotes());
			ps.setInt(9, h.getRoomType().getId());
	
			ps.executeUpdate();	
			
			String getId = "SELECT Id FROM HotelReservations " + 
							"WHERE ReservationNumber = ?";
			
			ResultSet rs = ps.executeQuery(getId);
			
			newHotelReservationId = rs.getInt("Id");
		
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Connection Error");
		}finally{
			
			try {
				if(ps != null){
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return newHotelReservationId;
		
	}
	
	//TODO: Create updateHotelReservation
	
	public void updateHotelReservation(HotelReservation r){

		Connection conn = null;
		
		PreparedStatement ps = null;
		
		try{
			
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		
			String update = "UPDATE HotelReservation SET" + 
							"HotelId = ?, " +
							"CheckInDate = ?, " +
							"CheckOutDate = ?, " +
							"NumberOfRooms = ?, " +
							"ReservationNumber = ?, " + 
							"UserId = ?, " + 
							"Status = ?, " +
							"Notes = ?, " + 
							"RoomTypeId = ? " + 
							"WHERE Id = ?";
	
			ps = conn.prepareStatement(update);
			ps.setInt(1, r.getHotelId());
			ps.setDate(2, (Date) r.getCheckInDate());
			ps.setDate(3, (Date) r.getCheckOutDate());
			ps.setInt(4, r.getNumRooms());
			ps.setString(5, r.getReservationNum());
			ps.setInt(6, r.getUserId());
			ps.setInt(7, r.getStatus());
			ps.setString(8, r.getNotes());
			ps.setInt(9, r.getRoomType().getId());
			ps.setInt(10, r.getId());

	
			ps.executeUpdate();	
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			try {
				if(ps != null){
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	public void getHotelAmenities(Hotel h){
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		try{
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		
			String query = "SELECT * FROM Amenities AS a "+ 
					"JOIN HotelAmenities AS ha ON a.Id = ha.AmenityId " + 
					"JOIN Hotels AS h ON ha.HotelId = h.Id " + 
					"WHERE h.Id =?";
					
					
					ps = conn.prepareStatement(query);
					ps.setInt(1, h.getId());
					ResultSet rs = ps.executeQuery();
					
					
					while(rs.next()){
						
						Amenity a = new Amenity();
						a.setId(rs.getInt("a.Id"));
						a.setName(rs.getString("a.Name"));
						
						h.addAmenity(a);
						
					}
				
			rs.close();
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}finally{
			
			try {
				if(ps != null){
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
		
	}
	
	public void getHotelHotelReviews(Hotel h){
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		try{
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		
			String query = "SELECT * FROM HotelReviews AS r "+ 
					"JOIN Hotels AS h ON r.HotelId = h.Id " + 
					"WHERE h.Id =?";
					
					
					ps = conn.prepareStatement(query);
					ps.setInt(1, h.getId());
					ResultSet rs = ps.executeQuery();
					
					
					while(rs.next()){
						
						HotelReview r = new HotelReview();
						r.setId(rs.getInt("r.Id"));
						r.setReviewerName(rs.getString("r.ReviewerName"));
						r.setReviewDate(rs.getDate("r.ReviewDate"));
						r.setReview(rs.getString("r.Review"));
						r.setHotelId(rs.getInt("r.HotelId"));
						
						h.addReview(r);
						
					}
				
			rs.close();
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}finally{
			
			try {
				if(ps != null){
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	void getHotelHotelRooms(Hotel h){
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		try{
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		
			String query = "SELECT * FROM HotelRooms AS r"+ 
					"JOIN Hotels AS h ON r.HotelId = h.Id" + 
					"WHERE h.Id =?";
					
					ps = conn.prepareStatement(query);
					ps.setInt(1, h.getId());
					ResultSet rs = ps.executeQuery();
					
					
					while(rs.next()){
						
						HotelRoom r = new HotelRoom();
						
						query = "SELECT * FROM HotelRoomType AS t"+
								"JOIN HotelRooms AS r ON t.Id = r.RoomTypeId" + 
								"WHERE r.Id = ?";
						ps = conn.prepareStatement(query);
						ps.setInt(1, rs.getInt("r.Id"));
						ResultSet rs1 = ps.executeQuery();
						HotelRoomType rt = new HotelRoomType();
						
						rt.setId(rs1.getInt("t.Id"));
						rt.setRoomType(rs1.getString("RoomType"));
						rt.setDescription(rs1.getString("Description"));
						
						r.setId(rs.getInt("r.Id"));
						r.setHotelId(rs.getInt("r.HotelId"));
						r.setAvailableNum(rs.getInt("r.AvailableNumber"));
						r.setPricePerNight(rs.getDouble("r.PricePerNight"));
						r.setStartDate(rs.getDate("r.StartDate"));
						r.setEndDate(rs.getDate("r.EndDate"));
						r.setRoomType(rt);
						
						h.addRoom(r);
						rs1.close();
						
					}
					
					rs.close();

			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}finally{
			
			try {
				if(ps != null){
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
}
