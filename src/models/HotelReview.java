package models;

import java.util.Date;

public class HotelReview {
	private int id;
	private String ReviewerName;
	private Date ReviewDate;
	private int Rating;
	private String Review;
	private int hotelId;
	
	public HotelReview(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReviewerName() {
		return ReviewerName;
	}

	public void setReviewerName(String reviewerName) {
		ReviewerName = reviewerName;
	}

	public Date getReviewDate() {
		return ReviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		ReviewDate = reviewDate;
	}

	public int getRating() {
		return Rating;
	}

	public void setRating(int rating) {
		Rating = rating;
	}

	public String getReview() {
		return Review;
	}

	public void setReview(String review) {
		Review = review;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

}
