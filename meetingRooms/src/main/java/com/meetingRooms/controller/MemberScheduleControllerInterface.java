package com.meetingRooms.controller;

import java.util.List;

import com.meetingRooms.entity.Meeting;
import com.meetingRooms.entity.loginUserEntity;

public interface MemberScheduleControllerInterface {
	
	List<Meeting> loadMeeting(loginUserEntity user);

}
