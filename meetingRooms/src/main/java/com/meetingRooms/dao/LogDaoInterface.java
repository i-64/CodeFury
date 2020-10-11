package com.meetingRooms.dao;

import java.sql.Time;

import com.meetingRooms.entity.User;
import com.meetingRooms.entity.UserLog;

public interface LogDaoInterface {

	public Time displayLastLoginDao(User u);
	
}
