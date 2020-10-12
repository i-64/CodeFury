package com.meetingRooms.utility;

import com.meetingRooms.service.MemberScheduleService;
import com.meetingRooms.service.MemberScheduleServiceInterface;

/**
 * object factory for member schedule service
 * 
 * @author Haritha Jayan
 *
 */
public class MemberScheduleServiceFactory {

	
	//private constructor
	
		private  MemberScheduleServiceFactory() {
			
		}

		/**
		 * create object of MemberScheduleService
		 * 
		 * @param str
		 * @return object of MemberScheduleService
		 */
		public static MemberScheduleServiceInterface createObject(String str) {
			
			MemberScheduleServiceInterface meeting_schedule= null;
			
			if(str.equals("create"))
				meeting_schedule = new MemberScheduleService();
				
			return meeting_schedule;
			
			
		}
		
		//object created and returned
}
