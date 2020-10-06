package com.meetingRooms.dao;

import java.util.List;

import com.meetingRooms.entity.AmenitiesEntity;
import com.meetingRooms.entity.MeetingRoomEntity;
import com.meetingRooms.entity.loginUserEntity;

public interface AdminDaoInterface {
    ///To Fetch User Infomation
	loginUserEntity fetchuserDao(loginUserEntity user);
	
	// To Create New Room
	int createRoomDao(AmenitiesEntity amenitiesEntity, loginUserEntity user);

	//To List Meeting Rooms created by the user
	List<MeetingRoomEntity> listRoomsAdminDao(loginUserEntity user);

}
