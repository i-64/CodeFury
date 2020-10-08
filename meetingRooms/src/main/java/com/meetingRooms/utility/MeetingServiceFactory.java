package com.meetingRooms.utility;

import com.meetingRooms.service.MeetingRoomsService;
import com.meetingRooms.service.MeetingRoomsServiceInterface;

public class MeetingServiceFactory {
	
	private MeetingServiceFactory() 
	{
	
	}
	
	public static MeetingRoomsServiceInterface createObject(String t) 
	{
	
		MeetingRoomsServiceInterface s=null;

		if(t.equals("admin service"))
		{
			s=new MeetingRoomsService();
		}
		return s;
		
	}

}
