package com.meetingRooms.utility;

import com.meetingRooms.dao.LogDao;
import com.meetingRooms.dao.LogDaoInterface;

/**
 * object factory for Last logged in feature
 * 
 * @author Akspreet Kaur
 *
 */
public class LogDaoFactory {

	private LogDaoFactory() {
		
	}
	
	/**
	 * @return LogDao object
	 */
	public static LogDaoInterface createObject() {
		
		LogDaoInterface d=new LogDao();
		return d;
		
	}
}
