package com.meetingRooms.service;

import java.util.List;

import com.meetingRooms.entity.Meeting;
import com.meetingRooms.entity.loginUserEntity;

public interface MemberScheduleServiceInterface {
	

	List<Meeting> loadMeetingService(loginUserEntity user);
	
	
	

}
