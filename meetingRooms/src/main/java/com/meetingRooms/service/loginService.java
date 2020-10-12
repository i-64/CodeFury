package com.meetingRooms.service;

import java.util.List;

import com.meetingRooms.dao.loginDAOInterface;
import com.meetingRooms.entity.DataDisplayForIndex;
import com.meetingRooms.entity.loginUserEntity;
import com.meetingRooms.utility.loginUserDAOFactory;


/**
 * Login service layer implementation
 * 
 * @author Ashutosh Danwe
 *
 */
public class loginService implements loginServiceInterface {
	
	private loginDAOInterface login_object;
	
	// constructor
	
	public loginService () {
		
		login_object = loginUserDAOFactory.createObject ();
		
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
