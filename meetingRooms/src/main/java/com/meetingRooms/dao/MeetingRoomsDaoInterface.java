package com.meetingRooms.dao;

import java.util.List;

import com.meetingRooms.entity.Meeting;
import com.meetingRooms.entity.User;
import com.meetingRooms.entity.loginUserEntity;

/**
 * Interface for meeting rooms display
 * 
 * @author Akspreet Kaur
 *
 */
public interface MeetingRoomsDaoInterface {
	
	User managerInfoDao(User u);

	List<Meeting> listOfScheduledMeetingsDao(User u);
}
