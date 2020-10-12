package com.meetingRooms.dao;

import java.sql.Time;

import com.meetingRooms.entity.User;
import com.meetingRooms.entity.UserLog;

/**
 * Interface for getting last logged in time feature
 * 
 * @author Akspreet Kaur
 *
 */
public interface LogDaoInterface {

	public Time displayLastLoginDao(User u);
	
}
