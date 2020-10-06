package com.meetingRooms.service;

import java.util.List;

import com.meetingRooms.entity.loginUserEntity;
import com.meetingRooms.entity.Meeting;
public interface loginServiceInterface {
	
	public loginUserEntity logInUser ( loginUserEntity user );
	public List<Meeting> loadMeeting(loginUserEntity user);
}
