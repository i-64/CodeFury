package com.meetingRooms.dao;

import com.meetingRooms.entity.loginUserEntity;

public interface loginDAOInterface {

	public loginUserEntity logInUser ( loginUserEntity user );
	
	public void renewCredits(String user);
}
