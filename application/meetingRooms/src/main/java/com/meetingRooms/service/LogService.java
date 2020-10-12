package com.meetingRooms.service;

import java.sql.Time;

import com.meetingRooms.dao.LogDaoInterface;
import com.meetingRooms.entity.User;
import com.meetingRooms.utility.LogDaoFactory;

/**
 * last logged in feature service layer implementation
 * 
 * @author Akspreet Kaur
 *
 */
public class LogService implements LogServiceInterface{

	LogDaoInterface d=null;
	public LogService() {
		d= LogDaoFactory.createObject();
	}
	
	/**
	 * get last logged in time
	 * 
	 * @param user
	 * @return last logged in time of the user
	 *
	 */
	@Override
	public Time displayLastLoginService(User u) {
		// TODO Auto-generated method stub
		return d.displayLastLoginDao(u) ;
	}

}
