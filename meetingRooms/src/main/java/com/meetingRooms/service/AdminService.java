package com.meetingRooms.service;

import java.util.List;

import com.meetingRooms.dao.AdminDao;
import com.meetingRooms.dao.AdminDaoInterface;
import com.meetingRooms.entity.AmenitiesEntity;
import com.meetingRooms.entity.MeetingRoomEntity;
import com.meetingRooms.entity.loginUserEntity;

public class AdminService implements AdminServiceInterface {
	
  private AdminDaoInterface daoobj= new AdminDao();
   
	@Override
	public loginUserEntity fetchuserdata(loginUserEntity user) {
		return daoobj.fetchuserDao(user);
	}

	@Override
	public int createRoomService(AmenitiesEntity amenitiesEntity,loginUserEntity user) {
		return daoobj.createRoomDao(amenitiesEntity,user);
	}

	@Override
	public List<MeetingRoomEntity> listRoomsAdminService(loginUserEntity user) {
		return daoobj.listRoomsAdminDao(user);
	}

}
