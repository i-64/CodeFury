package com.meetingRooms.utility;

import com.meetingRooms.dao.loginDAO;

public class loginUserDAOFactory {
	
	// private constructor
	
	private loginUserDAOFactory () {}	
	
	public static loginDAO createObject () {
		
		return new loginDAO ();
	}

}
