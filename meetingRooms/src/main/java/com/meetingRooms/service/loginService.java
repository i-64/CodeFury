package com.meetingRooms.service;

import com.meetingRooms.dao.LoginDAOInterface;
import com.meetingRooms.entity.loginUserEntity;
import com.meetingRooms.utility.LoginUserDAOFactory;


/**
 * Login service layer implementation
 * 
 * @author Ashutosh Danwe
 *
 */
public class loginService implements loginServiceInterface {
	
	private LoginDAOInterface login_object;
	
	// constructor
	
	public loginService () {
		
		login_object = LoginUserDAOFactory.createObject ();
		
	} // end of constructor
	
	/**
	 * get the user
	 * 
	 * @param user credentials in object
	 * @return entity with user details if logged in user
	 *
	 */
	@Override
	public loginUserEntity logInUser ( loginUserEntity user ) {
		
		return login_object.logInUser ( user );
		
	} // end of loginUserEntity


	/**
	 * renew credits of manager users
	 * 
	 * @param manager user object
	 *
	 */
	@Override
	public void renewCredits(String user) {
		
		login_object.renewCredits(user);		
	}
	
} // end of loginService
