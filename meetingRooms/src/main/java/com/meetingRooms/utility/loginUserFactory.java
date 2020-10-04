package com.meetingRooms.utility;

import com.meetingRooms.entity.loginUserEntity;

public class loginUserFactory {
	
		// private constructor 
	
	private loginUserFactory () {}
	
	public static loginUserEntity createObject () {
		
		return new loginUserEntity ();
	}
	
} // end of loginUserFactory Class
