package com.meetingRooms.service;

import java.util.List;

import com.meetingRooms.dao.GetDataForAdminCreateRoomDaoInterface;
import com.meetingRooms.entity.AmenitiesEntity;
import com.meetingRooms.entity.MeetingRoomEntity;
import com.meetingRooms.entity.MeetingTypes;
import com.meetingRooms.utility.GetDataForAdminCreateRoomFactory;

/**
 * implementation for admin features service layer
 * 
 * @author Ashutosh Danwe
 * @author Sophia Tiwari
 *
 */
public class GetDataForAdminCreateRoomService implements GetDataForAdminCreateRoomServiceInterface {

	private GetDataForAdminCreateRoomDaoInterface dao;
	
	public GetDataForAdminCreateRoomService () {
		
		dao = GetDataForAdminCreateRoomFactory.createObjectForDAO ();		
	}
	
	/**
	 * get the meeting name status
	 * 
	 * @param meeting name
	 * @return status of the name
	 */
	@Override
	public int getNameStatus ( String meetingName ) {
		
		return dao.getNameStatus(meetingName);
		
	}
	
	
	/**
	 * function to delete meeting room
	 *
	 * @param name of meeting room
	 * @return if the meeting room was deleted
	 * 
	 */
	@Override
	public int deleteRoom ( String meetingName ) {
		
		return dao.deleteRoom (meetingName);
	}	
	
	/**
	 * edit the room details
	 * 
	 * @param meeting room entity
	 * @return if the room edit was saved
	 * 
	 */
	@Override
	public int editRoom ( MeetingRoomEntity entity ) {
		
		return dao.editRoom ( entity );
	}
		
	
	/**
	 * function to get info for edit meeting 
	 * 
	 * @param meeting name
	 * @return the meeting room entity
	 * 
	 */
	@Override
	public MeetingRoomEntity getEditRoomInfo ( String meetingName ) {
		
		return dao.getEditRoomInfo (meetingName);
	}
	
	
	/**
	 * function to get meeting types
	 * 
	 * @return list of meeting types
	 *
	 */
	@Override
	public List<MeetingTypes> getMeetingTypes () {
		
		return dao.getMeetingTypes ();
	}
	
	
	/**
	 * fetch the amenities from database
	 * 
	 * @return list of amenities
	 * 
	 */
	@Override
	public List<AmenitiesEntity> getAmenities() {
		
		return dao.getAmenities ();
	}
	
	/**
	 * service to create a new meeting room
	 * 
	 * @param details of the meeting room
	 * @return if the room was saved
	 *
	 */
	@Override
	public int createRoom ( MeetingRoomEntity entity ) {
		
		return dao.createRoom ( entity );
	}


	/**
	 * get the list of meeting rooms
	 * 
	 * @param logged in user
	 * @return list of meeting rooms
	 *
	 */
	@Override
	public List<MeetingRoomEntity> getMeetingRooms(String username) {
		
		return dao.getMeetingRooms(username);
	}
	

}
