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
				
				query = "SELECT * FROM Amenities AS a"+ 
				"JOIN HotelAmenities AS ha ON a.Id = ha.AmenityId" + 
				"JOIN Hotels AS h ON ha.HotelId = h.Id" + 
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
				
				query = "SELECT * FROM HotelReviews AS r"+ 
				"JOIN Hotels AS h ON r.HotelId = h.Id" + 
				"WHERE h.Id =?";
				
				
				ps = conn.prepareStatement(query);
				ps.setInt(1, h.getId());
				rs1 = ps.executeQuery();
				
				
				while(rs1.next()){
					
					HotelReview r = new HotelReview();
					r.setId(rs1.getInt("r.Id"));
					r.setReviewerName(rs1.getString("r.ReviewerName"));
					r.setReviewDate(rs.getDate("r.ReviewDate"));
					r.setReview(rs.getString("r.Review"));
					r.setHotelId(rs.getInt("r.HotelId"));
					
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
					ps.setInt(1, rs.getInt("r.Id"));
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
					
				}
				
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
		
			String insert = "INSERT INTO Users" +
							"(Username, Password) VALUES" + 
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
		
			String query = "SELECT * FROM Users" + 
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
}