package com.meetingRooms.dao;

import java.util.List;

import com.meetingRooms.entity.DataDisplayForIndex;
import com.meetingRooms.entity.loginUserEntity;

/**
 * Interface for login feature
 * 
 * @author Ashutosh Danwe
 *
 */
public interface loginDAOInterface {

	public loginUserEntity logInUser ( loginUserEntity user );
	
	public void renewCredits(String user);
	
	public List<DataDisplayForIndex> getWelcomePageData ();
}
