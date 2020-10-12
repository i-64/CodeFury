package com.meetingRooms.service;

import java.util.List;

import com.meetingRooms.entity.Meeting;
import com.meetingRooms.entity.loginUserEntity;

/**
 * Interface for Displaying member meeting schedule feature
 * 
 * @author Haritha Jayan
 *
 */
public interface MemberScheduleServiceInterface {
	
	public List<Meeting> loadMeetingService(loginUserEntity user);
}
