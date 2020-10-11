package com.meetingRooms.service;

import java.util.List;

import com.meetingRooms.entity.AmenitiesEntity;
import com.meetingRooms.entity.MeetingRoomEntity;
import com.meetingRooms.entity.loginUserEntity;

public interface AdminServiceInterface {
	 ///To Fetch User Infomation
	loginUserEntity fetchuserdata(loginUserEntity user);
	
	// To Create New Room
	int createRoomService(AmenitiesEntity amenitiesEntity,loginUserEntity user);
	
	//To List Meeting Rooms created by the user
	List <MeetingRoomEntity> listRoomsAdminService(loginUserEntity user);
}
