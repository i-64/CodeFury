package com.meetingRooms.utility;

import com.meetingRooms.dao.MemberScheduleDao;
import com.meetingRooms.dao.MemberScheduleDaoInterface;
import com.meetingRooms.service.MemberScheduleService;
import com.meetingRooms.service.MemberScheduleServiceInterface;

/**
 * object factory for member schedule dao
 * 
 * @author Haritha Jayan
 *
 */
public class MemberScheduleDaoFactory {
	
	
	//private constructor
	
	private  MemberScheduleDaoFactory() {

	
	}

	/**
	 * create object of MemberScheduleDao
	 * 
	 * @param str
	 * @return object of MemberScheduleDao
	 */
	public static MemberScheduleDaoInterface createObject(String str) {
				
		MemberScheduleDaoInterface meeting_schedule= null;
				
		if(str.equals("create"))
		meeting_schedule = new MemberScheduleDao();
					
		return meeting_schedule;
				
				
	}
			
	//object created and returned

}
