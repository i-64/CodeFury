package com.meetingRooms.service;

import java.util.List;

import com.meetingRooms.entity.AmenitiesEntity;
import com.meetingRooms.entity.MeetingTypes;

public interface GetDataForAdminCreateRoomServiceInterface {
	
	public List<AmenitiesEntity> getAmenities (); // to get amenities from database
	
	public List<MeetingTypes> getMeetingTypes (); // to get meeting types from database

}
