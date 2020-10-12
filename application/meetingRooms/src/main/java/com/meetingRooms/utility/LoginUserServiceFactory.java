package com.meetingRooms.utility;

import com.meetingRooms.service.LoginService;

/**
 * loginUserService object creation factory
 * 
 * @author Ashutosh Danwe
 *
 */
public class LoginUserServiceFactory {
	
	// private constructor
	
	private LoginUserServiceFactory () {}
	
	/**
	 * loginService create object
	 * 
	 * @return object of LoginService class
	 */
	public static LoginService createObject () {
		
		return new LoginService ();
	}

} // end of loginUserServiceFactory class
