/**
 * 
 */
package com.meetingRooms.service;

import java.util.ArrayList;

import com.meetingRooms.entity.Meeting;
import com.meetingRooms.entity.MeetingRoom;
import com.meetingRooms.entity.MeetingType;
import com.meetingRooms.entity.User;
import com.meetingRooms.exceptions.MeetingRoomAlreadyBookedException;
import com.meetingRooms.exceptions.NotEnoughCreditsException;

/**
 * Interface for service layer for Organize Meeting feature
 * 
 * @author Mrunal Ahire
 *
 */
public interface OrganizeMeetingServiceInterface {

	public ArrayList<MeetingRoom> filterRoomsService (Meeting meeting, MeetingType meetinType);
	public ArrayList<User> searchUserService (User user);
	public boolean saveMeetingService (Meeting meeting, ArrayList<User> members) throws NotEnoughCreditsException, MeetingRoomAlreadyBookedException;
	public int getCredits(User user);
}