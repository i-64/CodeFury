package com.meetingRooms.utility;

import com.meetingRooms.dao.MeetingRoomsDao;
import com.meetingRooms.dao.MeetingRoomsDaoInterface;

/**
 * MeetingDao factory for creating objects
 * 
 * @author Akspreet Kaur
 *
 */
public class MeetingDaoFactory {

	private MeetingDaoFactory() 
	{
		
	}
	
	/**
	 * create and get object of MeetingRoomsService class
	 * 
	 * @param t
	 * @return MeetingRoomsService object
	 */
	public static MeetingRoomsDaoInterface createObject(String s) 
	{
		MeetingRoomsDaoInterface d=null;
		
		if(s.equals("admin dao"))
		{
			d= new MeetingRoomsDao();
		}
		
		return d;
	}
	
}
