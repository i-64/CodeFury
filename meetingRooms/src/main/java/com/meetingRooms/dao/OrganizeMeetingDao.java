/**
 * 
 */
package com.meetingRooms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.meetingRooms.entity.Meeting;
import com.meetingRooms.entity.MeetingRoom;
import com.meetingRooms.utility.ConnectionManager;
import com.meetingRooms.utility.OrganizeMeetingDaoFactory;

/**
 * Data Access layer for Organize Meeting feature
 * 
 * @author Mrunal Ahire
 *
 */
public class OrganizeMeetingDao implements OrganizeMeetingDaoInterface {

	/**
	 * filter meeting rooms according to the meeting organised by manager
	 * 
	 * @param meeting as asked by the manager
	 * @return meetingRoomsList the list of available and suitable meeting rooms
	 */
	@Override
	public ArrayList<MeetingRoom> filterMeetingRoomsDao(Meeting meeting) {
		
		ArrayList<MeetingRoom> meetingRoomsList = new ArrayList<>();
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();

			PreparedStatement ps = con.prepareStatement("select * from MEETING_ROOMS where start_time<? and end_time<?");
			
			// TODO query
			// TODO resultset -> arraylist
			
		}
		catch (SQLException | ClassNotFoundException e) {
			// TODO log exception to error.log
			e.printStackTrace();
		}
		
		return meetingRoomsList;
	}
	
}