package com.meetingRooms.service;

import com.meetingRooms.entity.loginUserEntity;

/**
 * Interface for login feature service layer
 * 
 * @author Ashutosh Danwe
 *
 */
public interface loginServiceInterface {
	
	public loginUserEntity logInUser ( loginUserEntity user );
	
	public void renewCredits (String user); // to check for credit renewal

}
