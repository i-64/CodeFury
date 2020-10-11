package com.meetingRooms.entity;

public class AmenitiesEntity extends MeetingRoomEntity{
	
	private int ID;  // to store amenities ID
	
	private String amenity;// to store checkbox values 
	
	private int credits; // to store credits
	
	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getAmenity() {
		return amenity;
	}
	
		public void setAmenity(String amenity) {
		this.amenity = amenity;
	}
	
}	