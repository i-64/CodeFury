package com.meetingRooms.utility;

import com.meetingRooms.entity.loginUserEntity;

/**
 * factory to create object LoginUserEntity
 * 
 * @author Ashutosh Danwe
 *
 */
public class LoginUserFactory {
	
		// private constructor 
	
	private LoginUserFactory () {}
	
	/**
	 * @return loginuser entity
	 */
	public static loginUserEntity createObject () {
		
		return new loginUserEntity ();
	}
	
} // end of loginUserFactory Class
