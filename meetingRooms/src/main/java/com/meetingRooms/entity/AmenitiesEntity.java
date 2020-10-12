package com.meetingRooms.entity;

/**
 * Entity class for Room Amenities
 * 
 * @author Sophia Tiwari
 * @author Aishwarya Sonawane
 *
 */
public class AmenitiesEntity extends MeetingRoomEntity{
	
	private int ID;  // to store amenities ID
	private String amenity;// to store checkbox values 
	private int credits; // to store credits
	
	/**
	 * @return credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * @param credits
	 */
	public void setCredits(int credits) {
		this.credits = credits;
	}
	
	/**
	 * @return id of amenity
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * @param iD
	 */
	public void setID(int iD) {
		ID = iD;
	}
	
	/**
	 * @return amenity name
	 */
	public String getAmenity() {
		return amenity;
	}
	
	/**
	 * @param amenity
	 */
	public void setAmenity(String amenity) {
		this.amenity = amenity;
	}
	
}	