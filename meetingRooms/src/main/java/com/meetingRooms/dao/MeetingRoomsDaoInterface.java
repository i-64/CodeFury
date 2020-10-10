package com.meetingRooms.dao;

import java.util.List;

import com.meetingRooms.entity.Meeting;
import com.meetingRooms.entity.User;
import com.meetingRooms.entity.loginUserEntity;

public interface MeetingRoomsDaoInterface {
	//public loginUserEntity managerInfoDao(loginUserEntity u);
	
	//List<Meeting> listOfScheduledMeetingsDao(loginUserEntity u);
	
	User managerInfoDao(User u);

	List<Meeting> listOfScheduledMeetingsDao(User u);
}
