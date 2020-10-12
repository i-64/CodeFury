package com.meetingRooms.utility;

import com.meetingRooms.service.MeetingRoomsService;
import com.meetingRooms.service.MeetingRoomsServiceInterface;


/**
 * MeetingService factory for creating objects
 * 
 * @author Akspreet Kaur
 *
 */
public class MeetingServiceFactory {
	
	private MeetingServiceFactory() {}
	
	/**
	 * create and get object of MeetingRoomsService class
	 * 
	 * @param t
	 * @return MeetingRoomsService object
	 */
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
