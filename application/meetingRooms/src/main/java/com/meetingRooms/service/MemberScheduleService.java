package com.meetingRooms.service;

import java.util.List;

import com.meetingRooms.dao.MemberScheduleDaoInterface;
import com.meetingRooms.entity.Meeting;
import com.meetingRooms.entity.loginUserEntity;
import com.meetingRooms.utility.MemberScheduleDaoFactory;
import com.meetingRooms.utility.MemberScheduleServiceFactory;


/**
 * Display member schedule of meetings feature implementation
 * 
 * @author Haritha Jayan
 *
 */
public class MemberScheduleService implements MemberScheduleServiceInterface{
	
	private MemberScheduleDaoInterface meeting_scheduleDao = null;
	
	public MemberScheduleService() {
		
		//creating object for dao interface
		meeting_scheduleDao = MemberScheduleDaoFactory.createObject("create");
		
	}

	/**
	 * get the list of meetings for the current user
	 * 
	 * @param user
	 * @return list of meetings
	 *
	 */
	@Override
	public List<Meeting> loadMeetingService(loginUserEntity user) {
		
		List<Meeting> meetingList;
		
		meetingList = meeting_scheduleDao.loadMeetingServiceDao(user);
		return meetingList;
		
	}
	
	


	
	
	
	
	

}
