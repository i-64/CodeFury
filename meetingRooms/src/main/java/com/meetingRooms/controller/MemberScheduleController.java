package com.meetingRooms.controller;

import java.util.List;

import com.meetingRooms.entity.Meeting;
import com.meetingRooms.entity.loginUserEntity;
import com.meetingRooms.service.MemberScheduleServiceInterface;
import com.meetingRooms.utility.MemberScheduleServiceFactory;

/**
 * Controller class for member page feature
 * 
 * @author Haritha Jayan
 *
 */
public class MemberScheduleController implements MemberScheduleControllerInterface{

	private MemberScheduleServiceInterface meeting_schedule = null;
	
	public MemberScheduleController() {
		
		meeting_schedule = MemberScheduleServiceFactory.createObject("create");
		
	}
	
	/**
	 * loads the meeting
	 * 
	 * @param user object
	 * @return list of meetings
	 */
	public List<Meeting> loadMeeting(loginUserEntity user) {
		
		List<Meeting> meetingList;
		
		
		meetingList = meeting_schedule.loadMeetingService(user);
		return meetingList;
	}
}
