package com.meetingRooms.utility;

import com.meetingRooms.service.LogService;
import com.meetingRooms.service.LogServiceInterface;

public class LogServiceFactory {

	private LogServiceFactory() 
	{
		
	}
	
	public static LogServiceInterface createObject() 
	{
		LogServiceInterface s=new LogService();
		return s;
	}
}
