package utilities;

import models.*;
import models.User.VALIDATE;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class DatabaseManager {
	
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private final String DB_CONNECTION = "jdbc:mysql://cse.unl.edu:3306/ejohnson";
	private static final String DB_USER = "ejohnson";
	private static final String DB_PASSWORD = "hz8EyQ";
	
	public DatabaseManager(){
		
	}
	
	private List<Hotel> getAllHotels(){
		
		List<Hotel> hotels = new ArrayList<Hotel>();
		
		Connection conn = null;
		
		PreparedStatement ps = null; 
		
		try{
			
			Class.forName(DB_DRIVER);
			
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
	
	public List<Amenity> getAllAmenities(){
		
		List<Amenity> amenities = new ArrayList<Amenity>();
		
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
	public List<HotelReview> getAllHotelReviews(){
		
		List<HotelReview> hotelReviews = new ArrayList<HotelReview>();
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		try{
			
			Class.forName(DB_DRIVER);
			
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
	
	public List<HotelRoomType> getAllRoomTypes(){
		
		List<HotelRoomType> roomTypes = new ArrayList<HotelRoomType>();
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		try{
			
			Class.forName(DB_DRIVER);
			
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
	
	//TODO: Need to create search to search hotels based on:
	//Check in date
	//Check out date
	//City
	//Num rooms
	//Room Type
	//Amenities
	//PUT FUNCTION HERE
	/*
	public List<Hotel> searchHotels(Hotel h, List<Amenity> amenities, 
									HotelRoom r, HotelRoomType t){
		
		List<Hotel> hotels = getAllHotels();
		
		if(h.getCity() != null){
			
			//trim hotels that don't match city
			for(Iterator<Hotel> iterator = hotels.iterator(); iterator.hasNext();){
				Hotel cur_hotel = iterator.next();
				if(cur_hotel.getCity().equals(h.getCity())){
					iterator.remove();
				}
			}
		
		}
		if(!amenities.isEmpty()){
			
			//trim Hotels that don't have the amenities
			for(Iterator<Hotel> iterator = hotels.iterator(); iterator.hasNext();){
				Hotel cur_hotel = iterator.next();
				for(Amenity a: amenities){
					if(cur_hotel.getAmenityByName(a.getName()) == null){
						iterator.remove();
						break;
					}
				}
			}
		
		}
		//trim the hotels that don't have the startDate, endDate, or roomType
			
		for(Iterator<Hotel> iterator = hotels.iterator(); iterator.hasNext();){
			Hotel cur_hotel = iterator.next();
			
			for(Iterator<HotelRoom> roomIt = cur_hotel.getAllHotelRooms().iterator(); roomIt.hasNext();){
					HotelRoom cur_room = roomIt.next();
					
					if(r.getStartDate() != null && r.getEndDate() != null){
						
						if(cur_room.getStartDate().after(r.getStartDate())){
							roomIt.remove();
							break;
						}
						if(cur_room.getEndDate().after(r.getEndDate())){
							roomIt.remove();
							break;
						}
						
					}
					if(!cur_room.getRoomType().getRoomType().equals(t.getRoomType())){
						roomIt.remove();
						break;
					}
					
					
					//If there is no number, then don't trim, otherwise, trim
					if(r.getAvailableNum() > 0 && cur_room.getAvailableNum() < r.getAvailableNum()){
						roomIt.remove();
					}
						
					
			}
			//If after trimming, no rooms for hotel is left,
			//trim the hotel
			if(cur_hotel.getAllHotelRooms().isEmpty()){
				iterator.remove();
			}
		}
			
		
		return hotels;
		
	}
	*/
	
	public List<Hotel> searchHotels(Date checkInDate, Date checkOutDate, String city,
									 int numRooms, HotelRoomType roomType, List<Amenity> amenities){
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		
		List<Hotel> hotels = new ArrayList<Hotel>();
		
		
		try{
			
			Class.forName(DB_DRIVER);
			
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
			
			String query = "SELECT DISTINCT(hr.Id), hr.HotelId FROM Hotels AS h JOIN HotelRooms AS hr ON h.Id = hr.HotelId "+
						  "JOIN HotelRoomType AS r ON r.Id = hr.RoomTypeId " +
                          "JOIN HotelAmenities AS ha ON h.Id = ha.HotelId " +
                          "JOIN Amenities AS a ON a.Id = ha.AmenityId " +
                          "WHERE ";
			
			if(checkInDate != null){
				
				query += "hr.StartDate < ? AND ";
				System.out.println(format.format(checkInDate).toString());
			
			}
			if(checkOutDate != null){
				
				query += "hr.EndDate > ? AND ";
				System.out.println(format.format(checkOutDate).toString());
				
			}
			if(city != null){
				query += "h.City = ? AND ";
			}
			if(numRooms > 0){
				query += "hr.AvailableNumber >= ? AND ";
			}
			if(roomType != null){
				query += "r.RoomType = ? AND ";
			}
			if(amenities.isEmpty()){
				for(Amenity a: amenities){
					query += "? IN (SELECT a.Name FROM Hotels AS ho JOIN HotelAmenities AS ha ON ho.Id = ha.HotelId"
							+ "									   JOIN Amenities AS a ON a.Id = ha.AmenityId"
							+ "									   WHERE ho.Id = h.Id) AND ";
				}
			}
			
			String fin_query = query.substring(0, query.length()-4);
			
			System.out.println(fin_query);
			
			ps = conn.prepareStatement(fin_query);
			
			int i = 1;
			
			if(checkInDate != null){
				
				ps.setDate(i, checkInDate);
				i++;
			
			}
			if(checkOutDate != null){
				
				ps.setDate(i, checkOutDate);
				System.out.println(checkOutDate);
				i++;
				
			}
			if(city != null){
				ps.setString(i, city);
				i++;
			}
			if(numRooms > 0){
				ps.setInt(i, numRooms);
				System.out.println(numRooms);
				i++;
			}
			if(roomType != null){
				ps.setString(i, roomType.getRoomType());
				System.out.println(roomType.getRoomType());
				i++;
			}
			if(amenities.isEmpty()){
				for(Amenity a: amenities){
					ps.setString(i, a.getName());
					i++;
				}
			}
			
			ResultSet rs = ps.executeQuery();
			
			int numHotels = 0;
			
			while(rs.next()){
				
				Hotel h = getHotel(rs.getInt("HotelId"));
				
				System.out.println("found a room");
				
				if(!hotels.contains(h)){
					hotels.add(h);
					numHotels++;
				}else{
					hotels.get(numHotels).addRoom(getHotelRoom(rs.getInt("Id")));
				}
				
			}
			
			rs.close();
			
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
		
		return hotels;
	}
	
	//TODO: Create getUserFuction
	
	public boolean addUser(User u){
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		boolean success = false;
		
		try{
			Class.forName(DB_DRIVER);
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		
			
			String query = "SELECT * FROM Users " + 
							"WHERE Username = ?";
			System.out.println(query);

			ps = conn.prepareStatement(query);
			ps.setString(1, u.getUsername());

			ResultSet rs = ps.executeQuery();	
			
			if(rs.next()){

				if(rs.getString("Username").equals(u.getUsername())){
					success = false;
				}
			}

			rs.close();
		
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
			
			Class.forName(DB_DRIVER);
			
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		
			String query = "SELECT * FROM Users " + 
							"WHERE Username = ?";
			System.out.println(query);

			ps = conn.prepareStatement(query);
			ps.setString(1, u.getUsername());
	
			ResultSet rs = ps.executeQuery();	
			
			rs.next();
			
			if(rs != null){
				pass = rs.getString("Password");
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
			
			Class.forName(DB_DRIVER);
			
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
			
			Class.forName(DB_DRIVER);
			
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
	
	public int addHotelReservation(HotelReservation h){
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		int newHotelReservationId = -1;
		
		try{
			
			Class.forName(DB_DRIVER);

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
			ps.setInt(9, h.getRoom().getId());
	
			ps.executeUpdate();	
			
			String getId = "SELECT Id FROM HotelReservations " + 
							"WHERE ReservationNumber = ?";
			
			ResultSet rs = ps.executeQuery(getId);
			
			rs.next();
			
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
	
	public Hotel getHotel(int hotelId){
		
		Connection conn = null;
				
		PreparedStatement ps = null;
		
		Hotel h = new Hotel();
		
		String query = "SELECT * FROM Hotels WHERE Id = ?";
		
		try
		{
			Class.forName(DB_DRIVER);
			
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
			
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, hotelId);
			
			ResultSet rs = ps.executeQuery();
			
			
			
			while(rs.next()){
				
				h.setName(rs.getString("Name"));
				h.setCity(rs.getString("City"));
				h.setState(rs.getString("State"));
				h.setDescription(rs.getString("Description"));
				h.setNearestPoints(rs.getString("NearestPoints"));
				h.setAddress(rs.getString("Address"));
				h.setId(rs.getInt("Id"));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
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
		
		return h;
		
	}
	
	public void updateHotelReservation(HotelReservation r){

		Connection conn = null;
		
		PreparedStatement ps = null;
		
		try{
			
			Class.forName(DB_DRIVER);
			
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
			ps.setInt(9, r.getRoom().getId());
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
			
			Class.forName(DB_DRIVER);
			
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
			
			Class.forName(DB_DRIVER);
			
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		
			String query = "SELECT * FROM HotelReviews "+ 
					"WHERE HotelId =?";
					
					
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
	
	public void getHotelHotelRooms(Hotel h){
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		try{
			
			Class.forName(DB_DRIVER);
			
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		
			String query = "SELECT * FROM HotelRooms "+ 
					"WHERE HotelId =?";
					
					ps = conn.prepareStatement(query);
					ps.setInt(1, h.getId());
					ResultSet rs = ps.executeQuery();
					
					
					while(rs.next()){
						
						HotelRoom r = new HotelRoom();
						
						query = "SELECT * FROM HotelRoomType AS t"+
								"JOIN HotelRooms AS r ON t.Id = r.RoomTypeId" + 
								"WHERE r.Id = ?";
						ps = conn.prepareStatement(query);
						ps.setInt(1, rs.getInt("Id"));
						ResultSet rs1 = ps.executeQuery();
						
						rs1.next();
						
						HotelRoomType rt = new HotelRoomType();
						
						rt.setId(rs1.getInt("t.Id"));
						rt.setRoomType(rs1.getString("RoomType"));
						rt.setDescription(rs1.getString("Description"));
						
						r.setId(rs.getInt("Id"));
						r.setHotelId(rs.getInt("HotelId"));
						r.setAvailableNum(rs.getInt("AvailableNumber"));
						r.setPricePerNight(rs.getDouble("PricePerNight"));
						r.setStartDate(rs.getDate("StartDate"));
						r.setEndDate(rs.getDate("EndDate"));
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
	
	/*public void getHotelHotelReservations(Hotel h){
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		try{
			
			Class.forName(DB_DRIVER);
			
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		
			String query = "SELECT * FROM HotelReservation "+  
					"WHERE HotelId =?";
					
					
					ps = conn.prepareStatement(query);
					ps.setInt(1, h.getId());
					ResultSet rs = ps.executeQuery();
					
					
					while(rs.next()){
						
						HotelReservation hr = new HotelReservation();
						hr.setId(rs.getInt("Id"));
						hr.setHotelId(rs.getInt("HotelId"));
						hr.setCheckInDate(rs.getDate("CheckInDate"));
						hr.setCheckOutDate(rs.getDate("CheckOutDate"));
						hr.setNumRooms(rs.getInt("NumberOfRooms"));
						hr.setReservationNum(rs.getString("ReservationNumber"));
						hr.setUserId(rs.getInt("UserId"));
						hr.setStatus(rs.getInt("Status"));
						hr.setNotes(rs.getString("Notes"));
						
						query = "SELECT * FROM HotelRoomType " + 
								"WHERE Id = ?";
						ps = conn.prepareStatement(query);
						ps.setInt(1, rs.getInt("RoomTypeId"));
						
						ResultSet rs1 = ps.executeQuery();
						
						rs1.next();
						
						HotelRoomType ht = new HotelRoomType();
						
						ht.setId(rs1.getInt("Id"));
						ht.setRoomType(rs1.getString("RoomType"));
						ht.setDescription(rs1.getString("Description"));
						
						rs1.close();
						
						//hr.setRoomType(ht);
						
						h.addReservation(hr);
						
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
		
	}*/
	
	public HotelRoom getHotelRoom(int id){
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		HotelRoom hr = new HotelRoom();
		
		String query = "SELECT * FROM HotelRooms WHERE Id = ?";
		
		try
		{
			Class.forName(DB_DRIVER);
			
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
			
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			
			
			while(rs.next()){
				
				hr.setId(rs.getInt("Id"));
				hr.setRoomType(getHotelRoomType(rs.getInt("RoomTypeId")));
				hr.setAvailableNum(rs.getInt("AvailableNumber"));
				hr.setPricePerNight(rs.getDouble("PricePerNight"));
				hr.setStartDate(rs.getDate("StartDate"));
				hr.setEndDate(rs.getDate("EndDate"));
			}
			
			rs.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
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
		
		return hr;
		
	}
	
	public HotelRoomType getHotelRoomType(int id){
		
Connection conn = null;
		
		PreparedStatement ps = null;
		
		HotelRoomType ht = new HotelRoomType();
		
		String query = "SELECT * FROM HotelRoomType WHERE Id = ?";
		
		try
		{
			Class.forName(DB_DRIVER);
			
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
			
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			
			
			while(rs.next()){
				
				ht.setId(rs.getInt("Id"));
				ht.setRoomType(rs.getString("RoomType"));
				ht.setDescription(rs.getString("Description"));
			}
			
			rs.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
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
		
		return ht;
		
	}
	
	public void getUserCreditCards(User u){
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		try{
			
			Class.forName(DB_DRIVER);
			
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		
			String query = "SELECT * FROM CreditCards "+ 
					"WHERE UserId =?";
					
					
					ps = conn.prepareStatement(query);
					ps.setInt(1, u.getId());
					ResultSet rs = ps.executeQuery();
					
					
					while(rs.next()){
						
						CreditCard c = new CreditCard();
						c.setId(rs.getInt("Id"));
						c.setCardHolderName(rs.getString("CardHolderName"));
						c.setCreditCardNumber(rs.getString("CreditCardNumber"));
						c.setBalance(rs.getDouble("Balance"));
						c.setCardNickname(rs.getString("CardNickname"));
						c.setUserId(rs.getInt("UserId"));
						c.setcVV(rs.getString("CVV"));
						
						u.addCreditCard(c);
						
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
	
	public void getUserReservations(User u){
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		try{
			
			Class.forName(DB_DRIVER);
			
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		
			String query = "SELECT * FROM HotelReservations "+ 
					"WHERE UserId =?";
					
					
					ps = conn.prepareStatement(query);
					ps.setInt(1, u.getId());
					ResultSet rs = ps.executeQuery();
					
					
					while(rs.next()){
						
						HotelReservation hr = new HotelReservation();
						hr.setId(rs.getInt("Id"));
						hr.setHotelId(rs.getInt("HotelId"));
						hr.setCheckInDate(rs.getDate("CheckInDate"));
						hr.setCheckOutDate(rs.getDate("CheckOutDate"));
						hr.setNumRooms(rs.getInt("NumberOfRooms"));
						hr.setReservationNum(rs.getString("ReservationNumber"));
						hr.setUserId(rs.getInt("UserId"));
						hr.setStatus(rs.getInt("Status"));
						hr.setNotes(rs.getString("Notes"));
						
						query = "SELECT * FROM HotelRooms " + 
								"WHERE Id = ?";
						ps = conn.prepareStatement(query);
						ps.setInt(1, rs.getInt("RoomTypeId"));
						
						ResultSet rs1 = ps.executeQuery();
						
						rs1.next();
						
						HotelRoom ht = new HotelRoom();
						
						ht.setId(rs1.getInt("Id"));
						ht.setAvailableNum(rs1.getInt("AvailableNumber"));
						ht.setHotelId(rs1.getInt("HotelId"));
						HotelRoomType t = new HotelRoomType();
						t.setId(rs1.getInt("RoomTypeId"));
						ht.setRoomType(t);
						ht.setPricePerNight(rs1.getDouble("PricePerNight"));
						ht.setStartDate(rs1.getDate("StartDate"));
						ht.setEndDate(rs1.getDate("EndDate"));
						
						
						rs1.close();
						
						hr.setRoom(ht);
						
						u.addReservation(hr);
						
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
	
	public void removeCreditCard(CreditCard c){
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		try{
			
			Class.forName(DB_DRIVER);
			
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		
			String delete = "DELETE FROM CreditCards "+ 
					"WHERE Id =?";		
					
				ps = conn.prepareStatement(delete);
				ps.setInt(1, c.getId());
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
	
	public void removeReservation(HotelReservation h){
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		try{
			
			Class.forName(DB_DRIVER);
			
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		
			String delete = "DELETE FROM HotelReservations "+ 
					"WHERE Id =?";		
					
				ps = conn.prepareStatement(delete);
				ps.setInt(1, h.getId());
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
	
public User getUserById(int id){
		
		Connection conn = null;
		
		PreparedStatement ps = null;
		
		User u = null;
		
		String query = "SELECT * FROM Users WHERE Id = ?";
		
		try
		{
			Class.forName(DB_DRIVER);
			
			conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
			
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			
			
			while(rs.next()){
				
				u = new User(rs.getString("Username"), rs.getString("Password"));
				u.setFirstName(rs.getString("FirstName"));
				u.setLastName(rs.getString("LastName"));
				u.setAddressLine1(rs.getString("AddressLine1"));
				u.setAddressLine1(rs.getString("AddressLine2"));
				u.setCity(rs.getString("City"));
				u.setState(rs.getString("State"));
				u.setPostalCode(rs.getString("PostalCode"));
				u.setType(rs.getInt("Type"));
				u.setStatus(rs.getInt("Status"));
				u.setId(rs.getInt("Id"));
			}
			
			rs.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
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
		
		return u;
		
	}

public User getUserByUsername(String name){
	
	Connection conn = null;
	
	PreparedStatement ps = null;
	
	User u = null;
	
	String query = "SELECT * FROM Users WHERE Username = ?";
	
	try
	{
		Class.forName(DB_DRIVER);
		
		conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		
		ps = conn.prepareStatement(query);
		
		ps.setString(1, name);
		
		ResultSet rs = ps.executeQuery();
		
		
		
		while(rs.next()){
			
			u = new User(rs.getString("Username"), rs.getString("Password"));
			u.setFirstName(rs.getString("FirstName"));
			u.setLastName(rs.getString("LastName"));
			u.setAddressLine1(rs.getString("AddressLine1"));
			u.setAddressLine1(rs.getString("AddressLine2"));
			u.setCity(rs.getString("City"));
			u.setState(rs.getString("State"));
			u.setPostalCode(rs.getString("PostalCode"));
			u.setType(rs.getInt("Type"));
			u.setStatus(rs.getInt("Status"));
			u.setId(rs.getInt("Id"));
		}
		
		rs.close();
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
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
	
	return u;
	
}

public HotelReservation getReservationById(int id){
	
	Connection conn = null;
	
	PreparedStatement ps = null;
	
	HotelReservation hr = null;
	
	String query = "SELECT * FROM HotelReservations WHERE Id = ?";
	
	try
	{
		Class.forName(DB_DRIVER);
		
		conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
		
		ps = conn.prepareStatement(query);
		
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		
		
		while(rs.next()){
			
			hr = new HotelReservation();
			hr.setId(rs.getInt("Id"));
			hr.setCheckInDate(rs.getDate("CheckInDate"));
			hr.setCheckOutDate(rs.getDate("CheckOutDate"));
			hr.setNumRooms(rs.getInt("NumberOfRooms"));
			hr.setHotelId(rs.getInt("HotelId"));
			hr.setReservationNum(rs.getString("ReservationNumber"));
			hr.setUserId(rs.getInt("UserId"));
			hr.setStatus(rs.getInt("Status"));
			hr.setRoom(getHotelRoom(rs.getInt("RoomTypeId")));
			hr.setHotel(getHotel(rs.getInt("HotelId")));
			
		}
		
		rs.close();
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
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
	
	return hr;
	
}


//TODO: addCreditcard


//TODO: updateCreditcard

}


