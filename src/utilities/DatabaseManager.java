package utilities;

import models.*;

import java.sql.*;
import java.util.List;

public class DatabaseManager {
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	private String user = "ejohnson";
	private String pass = "hz8EyQ";
	private String dbUrl = "cse.unl.edu";
	
	List<Hotel> getAllHotels(){
		
		List<Hotel> hotels = null;
		
		try{
			conn = DriverManager.getConnection(dbUrl,user,pass);
			
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
			ps.close();
			conn.close();
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		return hotels;
		
	}
	
	List<Amenity> getAllAmenities(){
		
		List<Amenity> amenities = null;
		
		try{
			conn = DriverManager.getConnection(dbUrl,user,pass);
		
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
			ps.close();
			conn.close();
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		return amenities;
		
	}
	//Probably won't need this
	List<HotelReview> getAllHotelReviews(){
		
		List<HotelReview> hotelReviews = null;
		
		try{
			conn = DriverManager.getConnection(dbUrl,user,pass);
		
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
			ps.close();
			conn.close();
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return hotelReviews;
		
	}
	//Create Get all Room Types.
	//PUT FUNCTION HERE
	
	
	//Need to create search to search hotels based on:
	//Check in date
	//Check out date
	//City
	//Num rooms
	//Room Type
	//Amenities
	//PUT FUNCTION HERE
}
