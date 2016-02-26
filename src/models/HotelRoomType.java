package models;

public class HotelRoomType {

	private int id;
	private String RoomType;
	private String Description;
	
	public HotelRoomType(){
		
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setRoomType(String roomType) {
		RoomType = roomType;
	}

	public String getRoomType() {
		return RoomType;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getDescription() {
		return Description;
	}
}
