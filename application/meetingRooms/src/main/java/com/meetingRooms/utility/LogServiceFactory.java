package com.meetingRooms.utility;

import com.meetingRooms.service.LogService;
import com.meetingRooms.service.LogServiceInterface;

/**
 * factory for object dcreation of last logged in 
 * 
 * @author Akspreet Kaur
 *
 */
public class LogServiceFactory {

	private LogServiceFactory() 
	{
		
	}
	
	/**
	 * @return object of LogService class
	 */
	public static LogServiceInterface createObject() 
	{
		LogServiceInterface s=new LogService();
		return s;
	}
}
