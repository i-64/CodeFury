package com.meetingRooms.dao;

import com.meetingRooms.entity.loginUserEntity;

/**
 * Interface for login feature
 * 
 * @author Ashutosh Danwe
 *
 */
public interface LoginDAOInterface {

	public loginUserEntity logInUser ( loginUserEntity user );
	
	public void renewCredits(String user);
}
