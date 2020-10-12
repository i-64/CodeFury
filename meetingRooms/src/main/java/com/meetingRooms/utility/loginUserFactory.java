package com.meetingRooms.utility;

import com.meetingRooms.entity.loginUserEntity;

/**
 * factory to create object LoginUserEntity
 * 
 * @author Ashutosh Danwe
 *
 */
public class loginUserFactory {
	
		// private constructor 
	
	private loginUserFactory () {}
	
	/**
	 * @return loginuser entity
	 */
	public static loginUserEntity createObject () {
		
		return new loginUserEntity ();
	}
	
} // end of loginUserFactory Class
