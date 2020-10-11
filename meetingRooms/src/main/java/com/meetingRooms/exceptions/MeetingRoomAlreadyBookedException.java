/**
 * 
 */
package com.meetingRooms.exceptions;

/**
 * Exception class thrown in case a manager tries to book a room which is already booked by someone else
 * 
 * @author Mrunal ahire
 *
 */
@SuppressWarnings("serial")
public class MeetingRoomAlreadyBookedException extends Exception {
	
	/**
	 * @return exception message
	 */
	public String toString () {
		
		return "The meeting room is already booked";
	}
}
