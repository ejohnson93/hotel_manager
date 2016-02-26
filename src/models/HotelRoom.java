package models;

import java.util.Date;

public class HotelRoom {

	private int id;
	private HotelRoomType RoomType;
	private int HotelId;
	private int AvailableNum;
	private double PricePerNight;
	private Date StartDate;
	private Date EndDate;
	
	public HotelRoom(){
		
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setRoomType(HotelRoomType roomType) {
		RoomType = roomType;
	}
	public HotelRoomType getRoomType() {
		return RoomType;
	}
	public void setHotelId(int hotelId) {
		HotelId = hotelId;
	}
	public int getHotelId() {
		return HotelId;
	}
	public void setAvailableNum(int availableNum) {
		AvailableNum = availableNum;
	}
	public int getAvailableNum() {
		return AvailableNum;
	}
	public void setPricePerNight(double pricePerNight) {
		PricePerNight = pricePerNight;
	}
	public double getPricePerNight() {
		return PricePerNight;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	
}
