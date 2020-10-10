/**
 * 
 */
package com.meetingRooms.entity;

/**
 * Entity class for Meeting type
 * 
 * @author Mrunal Ahire
 *
 */
public class MeetingType {
	
	private int meetingTypeId;
	private String meetingType, mandatoryAmenities;
	
	
	/**
	 * @return the meetingTypeId
	 */
	public int getMeetingTypeId() {
		return meetingTypeId;
	}
	
	/**
	 * @param meetingTypeId the meetingTypeId to set
	 */
	public void setMeetingTypeId(int meetingTypeId) {
		this.meetingTypeId = meetingTypeId;
	}
	
	/**
	 * @return the meetingType
	 */
	public String getMeetingType() {
		return meetingType;
	}
	
	/**
	 * @param meetingType the meetingType to set
	 */
	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}
	
	/**
	 * @return the mandatoryAmenities
	 */
	public String getMandatoryAmenities() {
		return mandatoryAmenities;
	}
	
	/**
	 * @param mandatoryAmenities the mandatoryAmenities to set
	 */
	public void setMandatoryAmenities(String mandatoryAmenities) {
		this.mandatoryAmenities = mandatoryAmenities;
	}

	@Override
	public String toString() {
		return "{\"meetingTypeId\":\"" + meetingTypeId + "\", \"meetingType\":\"" + meetingType + "\", \"mandatoryAmenities\":\""
				+ mandatoryAmenities + "}";
	}

}
