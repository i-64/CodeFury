package com.meetingRooms.utility;

import com.meetingRooms.dao.loginDAO;

/**
 * factory to create object Login
 * 
 * @author Ashutosh Danwe
 *
 */
public class loginUserDAOFactory {
	
	// private constructor
	
	private loginUserDAOFactory () {}	
	
	/**
	 * @return logindao object
	 */
	public static loginDAO createObject () {
		
		return new loginDAO ();
	}

}
