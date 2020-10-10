package com.meetingRooms.service;

import com.meetingRooms.entity.loginUserEntity;

public interface loginServiceInterface {
	
	public loginUserEntity logInUser ( loginUserEntity user );
	
	public void renewCredits (String user); // to check for credit renewal

}
