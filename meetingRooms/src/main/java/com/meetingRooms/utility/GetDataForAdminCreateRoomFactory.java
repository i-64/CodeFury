package com.meetingRooms.utility;

import com.meetingRooms.dao.GetDataForAdminCreateRoomDao;
import com.meetingRooms.dao.GetDataForAdminCreateRoomDaoInterface;
import com.meetingRooms.service.GetDataForAdminCreateRoomService;
import com.meetingRooms.service.GetDataForAdminCreateRoomServiceInterface;

/**
 * Factory design pattern for object creation of Admin feature related classes
 * 
 * @author Ashutosh Danwe
 *
 */
public class GetDataForAdminCreateRoomFactory {
	
	private GetDataForAdminCreateRoomFactory () {};	
	
	/**
	 * creating oject of admin service layer class
	 * 
	 * @return object of admin service
	 */
	public static GetDataForAdminCreateRoomServiceInterface createObjectForService () {
		
		return new GetDataForAdminCreateRoomService ();
	}
	
	/**
	 * creating oject of admin dao layer class
	 * 
	 * @return object of admin dao
	 */
	public static GetDataForAdminCreateRoomDaoInterface createObjectForDAO () {
		
		return new GetDataForAdminCreateRoomDao ();
	}

	
}
