package com.meetingRooms.entity;

/**
 * Entity class for a physical meeting room
 * 
 * @author Aishwarya Sonavane
 *
 */

public class MeetingRoomEntity {
	private String uniqueName;
	private int seatingCapacity;
	private int perHourCost, total_meetings_conducted;
	private String created_by;


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