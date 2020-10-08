package com.meetingRooms.service;

import java.util.List;

import com.meetingRooms.entity.Meeting;

import com.meetingRooms.entity.loginUserEntity;

public interface MeetingRoomsServiceInterface {
	public loginUserEntity managerInfoService(loginUserEntity u);
	public List<Meeting> listOfScheduledMeetingsService(loginUserEntity u);
}
