package com.meetingRooms.utility;

import com.meetingRooms.dao.GetDataForAdminCreateRoomDao;
import com.meetingRooms.dao.GetDataForAdminCreateRoomDaoInterface;
import com.meetingRooms.service.GetDataForAdminCreateRoomService;
import com.meetingRooms.service.GetDataForAdminCreateRoomServiceInterface;

public class GetDataForAdminCreateRoomFactory {
	
	private GetDataForAdminCreateRoomFactory () {};	
	
	public static GetDataForAdminCreateRoomServiceInterface createObjectForService () {
		
		return new GetDataForAdminCreateRoomService ();
	}
	
	public static GetDataForAdminCreateRoomDaoInterface createObjectForDAO () {
		
		return new GetDataForAdminCreateRoomDao ();
	}

}
