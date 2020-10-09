/**
 * 
 */
package com.meetingRooms.service;

import java.util.ArrayList;
import java.util.Collections;

import com.meetingRooms.dao.OrganizeMeetingDaoInterface;
import com.meetingRooms.entity.Meeting;
import com.meetingRooms.entity.MeetingRoom;
import com.meetingRooms.entity.MeetingType;
import com.meetingRooms.entity.User;
import com.meetingRooms.utility.OrganizeMeetingDaoFactory;
import com.meetingRooms.utility.SortRoomsByAverageRating;

/**
 * Implementation of service methods for organize meeting feature
 * 
 * @author Mrunal Ahire
 *
 */
public class OrganizeMeetingService implements OrganizeMeetingServiceInterface {

	private OrganizeMeetingDaoInterface dao;
	
	public OrganizeMeetingService() {
		
		dao = OrganizeMeetingDaoFactory.createObject();
	}
	
	/**
	 * filter meeting rooms according to the criteria
	 * 
	 * @param meeting containing date, time, type of meeting
	 * @param type of meeting
	 * @return list of available meeting rooms
	 */
	@Override
	public ArrayList<MeetingRoom> filterRoomsService(Meeting meeting, MeetingType meetingType) {
		
		ArrayList<MeetingRoom> meetingRoomsList = dao.filterMeetingRoomsDao(meeting, meetingType);
		Collections.sort(meetingRoomsList, new SortRoomsByAverageRating());
		return meetingRoomsList;
	}

	/**
	 * search user of with role of a member by their names
	 * 
	 * @param user object containing name as the search criteria
	 * @return list of members matching the criteria
	 */
	@Override
	public ArrayList<User> searchUserService(User user) {
		
		ArrayList<User> users = dao.searchUserDao(user);
		return users;
	}

	/**
	 * service to save the members invited and meeting room booking details
	 * 
	 * @param meeting details to save
	 * @param list of members to invite for the meeting
	 * @return meeting was saved or not
	 */
	@Override
	public boolean saveMeetingService(Meeting meeting, ArrayList<User> members) {
		
		return (dao.saveMeetingDao(meeting, members));
	}
	
	
}