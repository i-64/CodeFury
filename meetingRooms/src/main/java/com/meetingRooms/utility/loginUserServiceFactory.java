package com.meetingRooms.utility;

import com.meetingRooms.service.loginService;

public class loginUserServiceFactory {
	
	// private constructor
	
	private loginUserServiceFactory () {}
	
	public static loginService createObject () {
		
		return new loginService ();
	}

} // end of loginUserServiceFactory class
