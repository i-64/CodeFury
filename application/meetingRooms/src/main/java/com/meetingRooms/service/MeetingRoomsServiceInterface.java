package com.meetingRooms.service;

import java.util.List;

import com.meetingRooms.entity.Meeting;
import com.meetingRooms.entity.User;
import com.meetingRooms.entity.loginUserEntity;

/**
 * meeting rooms service 
 * 
 * @author Akspreet Kaur
 *
 */
public interface MeetingRoomsServiceInterface {
	
	public User managerInfoService(User u);

	public List<Meeting> listOfScheduledMeetingsService(User u);
}
