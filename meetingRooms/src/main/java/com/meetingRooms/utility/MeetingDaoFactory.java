package com.meetingRooms.utility;

import com.meetingRooms.dao.MeetingRoomsDao;
import com.meetingRooms.dao.MeetingRoomsDaoInterface;

public class MeetingDaoFactory {

	private MeetingDaoFactory() 
	{
		
	}
	
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
