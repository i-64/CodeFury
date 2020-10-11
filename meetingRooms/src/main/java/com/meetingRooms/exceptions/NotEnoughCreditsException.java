/**
 * 
 */
package com.meetingRooms.exceptions;

/**
 * Custom Exception class for raising exception when the manager 
 * does not have enough credits to book a meeting room
 * 
 * @author Mrunal Ahire
 *
 */
@SuppressWarnings("serial")
public class NotEnoughCreditsException extends Exception {
	
	public String toString() {
		
		return "Manager does not have enough credits to book the meeting room";
	}

}
