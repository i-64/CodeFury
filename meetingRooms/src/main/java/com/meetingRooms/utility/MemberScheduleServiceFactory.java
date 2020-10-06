package com.meetingRooms.utility;

import com.meetingRooms.service.MemberScheduleService;
import com.meetingRooms.service.MemberScheduleServiceInterface;

public class MemberScheduleServiceFactory {

	
	//private constructor
	
		private  MemberScheduleServiceFactory() {
			
		}

		public static MemberScheduleServiceInterface createObject(String str) {
			
			MemberScheduleServiceInterface meeting_schedule= null;
			
			if(str.equals("create"))
				meeting_schedule = new MemberScheduleService();
				
			return meeting_schedule;
			
			
		}
		
		//object created and returned
}
