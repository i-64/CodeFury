package com.meetingRooms.entity;

import java.util.List;

/**
 * Entity class for a physical meeting room
 * 
 * @author Aishwarya Sonavane
 *
 */

public class MeetingRoomEntity {
	
	private String uniqueName;
	private int seatingCapacity;
	private String meetingType;
	private String createdBy;
	private int perHourCost, total_meetings_conducted;
	private List<String> amenityList;
	

	/**
	 * @return list of amenities
	 */
	public List<String> getAmenityList() {
		return amenityList;
	}

	/**
	 * @param amenityList
	 */
	public void setAmenityList(List<String> amenityList) {
		this.amenityList = amenityList;
	}

	/**
	 * @return name (id) of the meeting room
	 */
	public String getUniqueName() {
		return uniqueName;
	}

	/**
	 * @param uniqueName
	 */
	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}

	/**
	 * @return
	 */
	public int getTotal_meetings_conducted() {
		return total_meetings_conducted;
	}

	/**
	 * @param total_meetings_conducted
	 */
	public void setTotal_meetings_conducted(int total_meetings_conducted) {
		this.total_meetings_conducted = total_meetings_conducted;
	}

	/**
	 * @return
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	
	/**
	 * @param created_by
	 */
	public void setCreatedBy(String created_by) {
		this.createdBy = created_by;
	}

	/**
	 * @return seating capacity of room
	 */
	public int getSeatingCapacity() {
		return seatingCapacity;
	}

	/**
	 * @param seatingCapacity
	 */
	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	/**
	 * @return cost per hour of meeting room
	 */
	public int getPerHourCost() {
		return perHourCost;
	}

	/**
	 * @param perHourCost
	 */
	public void setPerHourCost(int perHourCost) {
		this.perHourCost = perHourCost;
	}

	/**
	 * @return type of meeting
	 */
	public String getMeetingType() {
		return meetingType;
	}

	/**
	 * @param meetingType
	 */
	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}

	/**
	 * @return json string of object
	 */
	@Override
	public String toString() {
		return "{\"RoomName\":\"" + uniqueName + "\", \"SeatingCapacity\":\"" + seatingCapacity
				+ "\", \"costPerHour\":\"" + perHourCost + "\", \"Total Meetings Conducted\":\""
				+ total_meetings_conducted + "\"}";
	}

}