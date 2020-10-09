package com.meetingRooms.utility;

import com.meetingRooms.service.AdminService;
import com.meetingRooms.service.AdminServiceInterface;

public class AdminServiceFactory {
	
	private AdminServiceFactory() {}

	public static AdminServiceInterface createObject(String nn) {
		
		AdminServiceInterface adminServiceFactory=null;
		if(nn.equals("adminservice")) {
			adminServiceFactory=new AdminService();
		}
		return adminServiceFactory;
	}

}
