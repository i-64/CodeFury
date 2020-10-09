package com.meetingRooms.service;

import java.util.List;

import com.meetingRooms.dao.GetDataForAdminCreateRoomDaoInterface;
import com.meetingRooms.entity.AmenitiesEntity;
import com.meetingRooms.entity.MeetingRoomEntity;
import com.meetingRooms.entity.MeetingTypes;
import com.meetingRooms.utility.GetDataForAdminCreateRoomFactory;

public class GetDataForAdminCreateRoomService implements GetDataForAdminCreateRoomServiceInterface {

	private GetDataForAdminCreateRoomDaoInterface dao;
	
	public GetDataForAdminCreateRoomService () {
		
		dao = GetDataForAdminCreateRoomFactory.createObjectForDAO ();		
	}
	
	
		// function to get meeting types
	
	@Override
	public List<MeetingTypes> getMeetingTypes () {
		
		return dao.getMeetingTypes ();
	}
	
		// function to get amenities
	
	@Override
	public List<AmenitiesEntity> getAmenities() {
		
		return dao.getAmenities ();
	}
	
	@Override
	public int createRoom ( MeetingRoomEntity entity ) {
		
		return dao.createRoom ( entity );
	}
	

}
