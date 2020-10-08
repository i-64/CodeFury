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
	private String created_by;
	
	private int perHourCost, total_meetings_conducted;
	
	private List<String> amenityList;

	public List<String> getAmenityList() {
		return amenityList;
	}

	public void setAmenityList(List<String> amenityList) {
		this.amenityList = amenityList;
	}

	public String getUniqueName() {
		return uniqueName;
	}

	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}

	public int getTotal_meetings_conducted() {
		return total_meetings_conducted;
	}

	public void setTotal_meetings_conducted(int total_meetings_conducted) {
		this.total_meetings_conducted = total_meetings_conducted;
	}

	public String getCreated_by() {
		return created_by;
	}
	
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public int getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public int getPerHourCost() {
		return perHourCost;
	}

	public void setPerHourCost(int perHourCost) {
		this.perHourCost = perHourCost;
	}

	public String getMeetingType() {
		return meetingType;
	}

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