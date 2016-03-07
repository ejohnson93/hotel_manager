package models;

import java.sql.Date;

import utilities.DatabaseManager;

public class HotelReservation {
	private int id;
	private int hotelId;
	private Date checkInDate;
	private Date checkOutDate;
	private int numRooms;
	private String reservationNum;
	private int userId;
	private int status;
	private String notes;
	private HotelRoom room;
	private Hotel hotel;

	
	public HotelReservation(){
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getHotelId() {
		return hotelId;
	}


	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}


	public Date getCheckInDate() {
		return checkInDate;
	}


	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}


	public Date getCheckOutDate() {
		return checkOutDate;
	}


	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}


	public int getNumRooms() {
		return numRooms;
	}


	public void setNumRooms(int numRooms) {
		this.numRooms = numRooms;
	}


	public String getReservationNum() {
		return reservationNum;
	}


	public void setReservationNum(String reservationNum) {
		this.reservationNum = reservationNum;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public HotelRoom getRoom() {
		return room;
	}


	public void setRoom(HotelRoom room) {
		this.room = room;
	}
	
	public String getHotelName(int hotelId){
		DatabaseManager db = new DatabaseManager();
		Hotel h = db.getHotel(hotelId);
		return h.getName();
	}


	public Hotel getHotel() {
		return hotel;
	}


	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
}
