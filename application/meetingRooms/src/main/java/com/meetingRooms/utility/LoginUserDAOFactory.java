package com.meetingRooms.utility;

import com.meetingRooms.dao.LoginDAO;

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
	public static LoginDAO createObject () {
		
		return new LoginDAO ();
	}

}
