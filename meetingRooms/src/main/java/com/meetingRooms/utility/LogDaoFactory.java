package com.meetingRooms.utility;

import com.meetingRooms.dao.LogDao;
import com.meetingRooms.dao.LogDaoInterface;

public class LogDaoFactory {

	private LogDaoFactory() {
		
	}
	
	public static LogDaoInterface createObject() {
		
		LogDaoInterface d=new LogDao();
		return d;
		
	}
}
