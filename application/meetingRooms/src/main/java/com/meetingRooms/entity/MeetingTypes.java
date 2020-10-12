package com.meetingRooms.entity;

/**
 * Entity class for meeting type
 * 
 * @author Sophia Tiwari
 *
 */
public class MeetingTypes {
	
	private int ID;
	
	private String type;
	
	private String amenitites;

	/**
	 * @return id of the user
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
	 * @return type of the meeting
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return amenities
	 */
	public String getAmenitites() {
		return amenitites;
	}

	/**
	 * @param amenitites
	 */
	public void setAmenitites(String amenitites) {
		this.amenitites = amenitites;
	}

}
