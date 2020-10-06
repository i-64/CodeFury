package com.meetingRooms.utility;

import com.meetingRooms.dao.AdminDao;
import com.meetingRooms.dao.AdminDaoInterface;

public class AdminDaoFactory {

	public static AdminDaoInterface createObject(String nn) {
		AdminDaoInterface adminDaoFactory=null;
		if(nn.equals("admindao")) {
			adminDaoFactory=new AdminDao();
		}
		return adminDaoFactory;
	}
}
