package models;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

	private int id;
	private String name;
	private String city;
	private String state;
	private String description;
	private String nearestPoints;
	private String address;
	private List<Amenity> amenities;
	private List<HotelRoom> rooms;
	private List<HotelReview> reviews;
	private List<HotelReservation> reservations;
	
	public Hotel(){
		this.amenities = 	new ArrayList<Amenity>();
		this.rooms = 		new ArrayList<HotelRoom>();
		this.reviews = 		new ArrayList<HotelReview>();
		this.reservations = new ArrayList<HotelReservation>();
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setNearestPoints(String nearest_Points) {
		this.nearestPoints = nearest_Points;
	}

	public String getNearestPoints() {
		return nearestPoints;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}
	
	public Amenity getAmenitiyByIndex(int i){
		return this.amenities.get(i);
	}
	public Amenity getAmenityByName(String name){
		for(Amenity a: this.amenities){
			if(a.getName().equals(name)){
				return a;
			}
		}
		return null;
		
	}
	public void addAmenity(Amenity a){
		
		this.amenities.add(a);
		
	}
	
	public List<Amenity> getAllAmenities(){
		return this.amenities;
	}
	
	public HotelRoom getHotelRoomByIndex(int i){
		return this.rooms.get(i);
	}

	public List<HotelRoom> getHotelRoomsByType(String roomType){
		List<HotelRoom> roomList = new ArrayList<HotelRoom>();
		for(HotelRoom r: this.rooms){
			if(r.getRoomType().getRoomType().equals(roomType)){
				roomList.add(r);
			}
		}
		return roomList;
		
	}
	public void addRoom(HotelRoom r){
		
		this.rooms.add(r);
		
	}
	
	public List<HotelRoom> getAllHotelRooms(){
		
		return this.rooms;
	}
	
	public void addReview(HotelReview review){
		
		this.reviews.add(review);
		
	}
	
	public List<HotelReview> getAllReviews(){
		return this.reviews;
	}
	
	public HotelReview getReviewByReviewerName(String name){
		
		for(HotelReview r: this.reviews){
			if(r.getReviewerName().equals(name)){
				return r;
			}
		}
		return null;
		
	}
	
	public void addReservation(HotelReservation r){
		
		this.reservations.add(r);
		
	}
	
	public List<HotelReservation> getAllReservations(){
		
		return this.reservations;
		
	}
	
	public HotelReservation getReservationByResNum(String resNum){
		
		for(HotelReservation r: this.reservations){
			if(r.getReservationNum().equals(resNum)){
				return r;
			}
		}
		return null;
		
	}
	public double getAverageHotelRating(){
		
		int sum = 0;
		int numReviews = 0;
		
		for(HotelReview hr: this.reviews){
			sum += hr.getRating();
			numReviews++;
		}
		
		if(numReviews == 0){
			return 0;
		}
		else
		{
			return sum/numReviews;
		}
		
	}
	
}
