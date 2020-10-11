package com.meetingRooms.service;

import java.util.List;

import com.meetingRooms.dao.MeetingRoomsDaoInterface;
import com.meetingRooms.entity.Meeting;
import com.meetingRooms.entity.User;
import com.meetingRooms.entity.loginUserEntity;
import com.meetingRooms.utility.MeetingDaoFactory;


/**
 * Implementation for manager home feature
 * 
 * @author Akspreet Kaur
 *
 */
public class MeetingRoomsService implements MeetingRoomsServiceInterface{

	MeetingRoomsDaoInterface d=null;
	
	public MeetingRoomsService() {
		d= MeetingDaoFactory.createObject("admin dao");
	}
	
	
	/**
	 * 
	 * get the manager info
	 * 
	 * @param logged in manager user
	 * @return object with user details
	 *
	 */
	@Override
	public User managerInfoService(User u) {
		// TODO Auto-generated method stub
		return d.managerInfoDao(u);
	}
	
	/**
	 * get the list of scheduled meetings by the manager
	 * 
	 * @param user object
	 * @return list of scheduled meetings
	 *
	 */
	@Override
	public List<Meeting> listOfScheduledMeetingsService(User u) {
		// TODO Auto-generated method stub
		return d.listOfScheduledMeetingsDao(u);
	}
	
	
	
}
