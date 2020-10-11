package com.meetingRooms.service;

import com.meetingRooms.dao.loginDAOInterface;
import com.meetingRooms.entity.loginUserEntity;
import com.meetingRooms.utility.loginUserDAOFactory;


public class loginService implements loginServiceInterface {
	
	private loginDAOInterface login_object;
	
	// constructor
	
	public loginService () {
		
		login_object = loginUserDAOFactory.createObject ();
		
	} // end of constructor
	
	@Override
	public loginUserEntity logInUser ( loginUserEntity user ) {
		
		return login_object.logInUser ( user );
		
	} // end of loginUserEntity


	@Override
	public void renewCredits(String user) {
		
		login_object.renewCredits(user);		
	}
	
} // end of loginService
