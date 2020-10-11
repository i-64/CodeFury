package com.meetingRooms.service;

import java.sql.Time;

import com.meetingRooms.entity.User;

/**
 * Interface for last logged in time of user feature
 * 
 * @author Akspreet Kaur
 *
 */
public interface LogServiceInterface {
	
	public Time displayLastLoginService(User u);
}
