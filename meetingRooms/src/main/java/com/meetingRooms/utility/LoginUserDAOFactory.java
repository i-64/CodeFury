package com.meetingRooms.utility;

import com.meetingRooms.dao.loginDAO;

/**
 * factory to create object Login
 * 
 * @author Ashutosh Danwe
 *
 */
public class LoginUserDAOFactory {
	
	// private constructor
	
	private LoginUserDAOFactory () {}	
	
	/**
	 * @return logindao object
	 */
	public static loginDAO createObject () {
		
		return new loginDAO ();
	}

}
