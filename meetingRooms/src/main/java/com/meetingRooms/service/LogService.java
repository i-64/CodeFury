package com.meetingRooms.service;

import java.sql.Time;

import com.meetingRooms.dao.LogDaoInterface;
import com.meetingRooms.entity.User;
import com.meetingRooms.utility.LogDaoFactory;

public class LogService implements LogServiceInterface{

	LogDaoInterface d=null;
	public LogService() {
		d= LogDaoFactory.createObject();
	}
	@Override
	public Time displayLastLoginService(User u) {
		// TODO Auto-generated method stub
		return d.displayLastLoginDao(u) ;
	}

}
