package com.meetingRooms.dao;

import java.util.List;

import com.meetingRooms.entity.AmenitiesEntity;
import com.meetingRooms.entity.MeetingRoomEntity;
import com.meetingRooms.entity.MeetingTypes;

public interface GetDataForAdminCreateRoomDaoInterface {
	
	public List<AmenitiesEntity> getAmenities ();
	
	public List<MeetingTypes> getMeetingTypes ();
	
	public int createRoom ( MeetingRoomEntity entity );
	
	public List<MeetingRoomEntity> getMeetingRooms(String username); 

}
