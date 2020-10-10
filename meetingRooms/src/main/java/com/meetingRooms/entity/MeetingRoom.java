/**
 * 
 */
package com.meetingRooms.entity;

/**
 * Entity class for a physical meeting room
 * 
 * @author Mrunal Ahire
 *
 */
public class MeetingRoom {

	private String roomName;
	private int seatingCapacity, costPerHour;
	private double averageRating;
	
	
	/**
	 * @return the averageRating
	 */
	public double getAverageRating() {
		return averageRating;
	}

	/**
	 * @param averageRating the averageRating to set
	 */
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	/**
	 * @return the roomName
	 */
	public String getRoomName() {
		return roomName;
	}
	
	/**
	 * @param roomName the roomName to set
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	/**
	 * @return the seatingCapacity
	 */
	public int getSeatingCapacity() {
		return seatingCapacity;
	}
	
	/**
	 * @param seatingCapacity the seatingCapacity to set
	 */
	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	
	/**
	 * @return the costPerHour
	 */
	public int getCostPerHour() {
		return costPerHour;
	}
	
	/**
	 * @param costPerHour the costPerHour to set
	 */
	public void setCostPerHour(int costPerHour) {
		this.costPerHour = costPerHour;
	}
	
	/**
	 * @return json string of object
	 */
	@Override
	public String toString() {
		return "{\"roomName\":\"" + roomName + "\", \"seatingCapacity\":" + seatingCapacity + ", \"costPerHour\":"
				+ costPerHour + ", \"averageRating\":" + averageRating + "}";
	}
}