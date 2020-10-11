package com.meetingRooms.utility;

import com.meetingRooms.service.loginService;

/**
 * loginUserService object creation factory
 * 
 * @author Ashutosh Danwe
 *
 */
public class loginUserServiceFactory {
	
	// private constructor
	
	private loginUserServiceFactory () {}
	
	/**
	 * loginService create object
	 * 
	 * @return object of LoginService class
	 */
	public static loginService createObject () {
		
		return new loginService ();
	}

} // end of loginUserServiceFactory class
