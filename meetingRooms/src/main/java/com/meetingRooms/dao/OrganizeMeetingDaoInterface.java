/**
 * 
 */
package com.meetingRooms.dao;

import java.util.ArrayList;

import com.meetingRooms.entity.Meeting;
import com.meetingRooms.entity.MeetingRoom;
import com.meetingRooms.entity.MeetingType;
import com.meetingRooms.entity.User;
import com.meetingRooms.exceptions.MeetingRoomAlreadyBookedException;
import com.meetingRooms.exceptions.NotEnoughCreditsException;

/**
 * Interface for Data Access for Organize Meeting feature
 * 
 * @author Mrunal Ahire
 *
 */
public interface OrganizeMeetingDaoInterface {
	
	public ArrayList<MeetingRoom> filterMeetingRoomsDao (Meeting meeting, MeetingType meetingType);
	public ArrayList<User> searchUserDao (User user);
	public boolean saveMeetingDao (Meeting meeting, ArrayList<User> members) throws NotEnoughCreditsException, MeetingRoomAlreadyBookedException;
	public int getCredits (User user);
}