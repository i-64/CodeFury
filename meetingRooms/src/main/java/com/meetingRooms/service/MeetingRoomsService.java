package com.meetingRooms.service;

import java.util.List;

import com.meetingRooms.dao.MeetingRoomsDaoInterface;
import com.meetingRooms.entity.Meeting;
import com.meetingRooms.entity.loginUserEntity;
import com.meetingRooms.utility.MeetingDaoFactory;


public class MeetingRoomsService implements MeetingRoomsServiceInterface{

	MeetingRoomsDaoInterface d=null;
	public MeetingRoomsService() {
		d= MeetingDaoFactory.createObject("admin dao");
	}
	@Override
	public loginUserEntity managerInfoService(loginUserEntity u) {
		// TODO Auto-generated method stub
		return d.managerInfoDao(u);
	}
	@Override
	public List<Meeting> listOfScheduledMeetingsService(loginUserEntity u) {
		// TODO Auto-generated method stub
		return d.listOfScheduledMeetingsDao(u);
	}
	
	
	
}