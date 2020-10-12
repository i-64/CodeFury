package com.meetingRooms.service;

import com.meetingRooms.entity.loginUserEntity;

import java.util.List;

import com.meetingRooms.entity.DataDisplayForIndex;

/**
 * Interface for login feature service layer
 * 
 * @author Ashutosh Danwe
 *
 */
public interface LoginServiceInterface {
	
	public loginUserEntity logInUser ( loginUserEntity user );	// to check for valid users
	
	public void renewCredits (String user); // to check for credit renewal
	
	public List<DataDisplayForIndex> getWelcomePageData (); // for getting data for home page

}
