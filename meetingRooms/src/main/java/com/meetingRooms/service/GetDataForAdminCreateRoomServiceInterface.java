package com.meetingRooms.service;

import java.util.List;

import com.meetingRooms.entity.AmenitiesEntity;
import com.meetingRooms.entity.MeetingRoomEntity;
import com.meetingRooms.entity.MeetingTypes;

/**
 * Interface for admin features service layer
 * 
 * @author Ashutosh Danwe
 *
 */
public interface GetDataForAdminCreateRoomServiceInterface {
	
	public List<AmenitiesEntity> getAmenities (); // to get amenities from database
	
	public List<MeetingTypes> getMeetingTypes (); // to get meeting types from database

	public int createRoom ( MeetingRoomEntity entity ); // to insert data in database
	
	public List<MeetingRoomEntity> getMeetingRooms(String username);   //To get all the meetings created
	
	public MeetingRoomEntity getEditRoomInfo ( String meetingName ); // to get info for edit room
	
	public int editRoom ( MeetingRoomEntity entity ); // to edit meeting type data
	
	public int deleteRoom ( String meetingName ); // to delete meeting room
	
	public int getNameStatus ( String meetingName ); // to check for name status
	
}
