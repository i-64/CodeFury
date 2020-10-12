package com.meetingRooms.exceptions;


/**
 * Custom Exception class for raising exception when User does not exist
 * 
 * @author Haritha Jayan
 *
 */
@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {
	
	/**
	 * return the message of the exception
	 * 
	 * @return exception in string
	 */
	public String toString() {
		
		return "User does not exist";
	}
}
