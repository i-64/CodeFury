package com.meetingRooms.service;

import java.util.List;

import com.meetingRooms.entity.AmenitiesEntity;
import com.meetingRooms.entity.MeetingRoomEntity;
import com.meetingRooms.entity.loginUserEntity;

/**
 * Interface for Admin features Service Layer
 * 
 * @author Aishwarya Sonawane
 *
 */
public interface AdminServiceInterface {
	
	/**
	 * To Fetch User Infomation
	 * 
	 * @param user
	 * @return object of logged in user
	 */
	public loginUserEntity fetchuserdata(loginUserEntity user);
	
	/**
	 * To Create New Room
	 * 
	 * @param amenitiesEntity
	 * @param user
	 * @return if room was created
	 */
	public int createRoomService(AmenitiesEntity amenitiesEntity,loginUserEntity user);
	
	/**
	 * To List Meeting Rooms created by the user
	 * 
	 * @param user
	 * @return list of meetinig rooms
	 */
	public List <MeetingRoomEntity> listRoomsAdminService(loginUserEntity user);
}
