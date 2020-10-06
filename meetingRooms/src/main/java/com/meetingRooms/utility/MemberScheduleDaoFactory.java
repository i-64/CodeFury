package com.meetingRooms.utility;

import com.meetingRooms.dao.MemberScheduleDao;
import com.meetingRooms.dao.MemberScheduleDaoInterface;
import com.meetingRooms.service.MemberScheduleService;
import com.meetingRooms.service.MemberScheduleServiceInterface;

public class MemberScheduleDaoFactory {
	
	
	//private constructor
	
	private  MemberScheduleDaoFactory() {

	
	}

	public static MemberScheduleDaoInterface createObject(String str) {
				
		MemberScheduleDaoInterface meeting_schedule= null;
				
		if(str.equals("create"))
		meeting_schedule = new MemberScheduleDao();
					
		return meeting_schedule;
				
				
	}
			
	//object created and returned

}
