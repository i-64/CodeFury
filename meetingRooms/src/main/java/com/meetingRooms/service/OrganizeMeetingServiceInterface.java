/**
 * 
 */
package com.meetingRooms.service;

import java.util.ArrayList;

import com.meetingRooms.entity.Meeting;
import com.meetingRooms.entity.MeetingRoom;
import com.meetingRooms.entity.User;

/**
 * Interface for service layer for Organize Meeting feature
 * 
 * @author Mrunal Ahire
 *
 */
public interface OrganizeMeetingServiceInterface {

	public ArrayList<MeetingRoom> filterRoomsService (Meeting meeting);
	public ArrayList<User> searchUserService (User user);
}