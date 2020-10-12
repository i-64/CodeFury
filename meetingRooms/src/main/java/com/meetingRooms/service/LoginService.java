package com.meetingRooms.service;

import com.meetingRooms.dao.LoginDAOInterface;

import java.util.List;

import com.meetingRooms.entity.DataDisplayForIndex;
import com.meetingRooms.entity.loginUserEntity;
import com.meetingRooms.utility.LoginUserDAOFactory;


/**
 * Login service layer implementation
 * 
 * @author Ashutosh Danwe
 *
 */
public class LoginService implements LoginServiceInterface {
	
	private LoginDAOInterface login_object;
	 
	// constructor
	
	public LoginService () {
		
		login_object = LoginUserDAOFactory.createObject ();
		
	} // end of constructor
	
		
	/**
	 * get the data for display on home page
	 * 
	 * @param none
	 * @return entity with display details for index(welcome) page
	 *
	 */
	@Override
	public List<DataDisplayForIndex> getWelcomePageData () {
		
		return login_object.getWelcomePageData();
	}
	
	
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
