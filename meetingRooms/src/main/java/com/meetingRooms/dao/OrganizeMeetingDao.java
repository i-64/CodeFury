/**
 * 
 */
package com.meetingRooms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.meetingRooms.entity.Meeting;
import com.meetingRooms.entity.MeetingRoom;
import com.meetingRooms.entity.MeetingType;
import com.meetingRooms.entity.User;
import com.meetingRooms.utility.ConnectionManager;

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
	public ArrayList<MeetingRoom> filterMeetingRoomsDao(Meeting meeting, MeetingType meetingType) {
		
		ArrayList<MeetingRoom> meetingRoomsList = new ArrayList<>();
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();
			
			// start build query
			// exclude overlapping meeting rooms
		//	String queryString = "(select unique_name from meeting_room except (select meeting_room_id from meeting where (((start_time>=? and end_time<=?) or (start_time<=? and end_time>=?) or (start_time>=? and end_time<=?)) and meeting_date=?))) INTERSECT ";
			String queryString = " (  select unique_name from meeting_room except (select meeting_room_id from meeting where (   ((start_time<=? and end_time>=?) or (start_time<=? and start_time>=?)) and meeting_date=?)) ) INTERSECT ";
			
			// fetch mandatory amenities for this meeting type
			String str = "select mandatory_amenities from meeting_types where id=?";
			PreparedStatement ps1 = con.prepareStatement(str);
			ps1.setInt(1, meetingType.getMeetingTypeId());
			ResultSet rs = ps1.executeQuery();
			
			
			if (rs.next()) {
				
				// intersect rooms with rooms containing the amenity required
				String[] amenity_ids = rs.getString(1).split(",");
				for (int i = 0; i < amenity_ids.length; i++) {
					
					if (i < amenity_ids.length - 1)
						queryString = queryString + "select meeting_room_id from ROOM_AMENITIES where amenity_id=" + amenity_ids[i] + " INTERSECT ";
					else
						queryString = queryString + "select meeting_room_id from ROOM_AMENITIES where amenity_id=" + amenity_ids[i];
				}
				
				
				// complete the query
				String finalQuery = "select * from meeting_room where unique_name in (" + queryString + ")";
				
				
				PreparedStatement ps = con.prepareStatement(finalQuery);
				
//System.out.println(meeting.getStartTime()+ "  " + meeting.getEndTime() + "  " + meeting.getMeetingDate()  );
				ps.setString(1, meeting.getStartTime());
				ps.setString(2, meeting.getStartTime());
				ps.setString(3, meeting.getEndTime());
				ps.setString(4, meeting.getStartTime());
				ps.setString(5, meeting.getMeetingDate());
				
				
//				ps.setString(1, meeting.getEndTime());
//				ps.setString(2, meeting.getEndTime());
//				ps.setString(3, meeting.getStartTime());
//				ps.setString(4, meeting.getStartTime());
//				ps.setString(5, meeting.getStartTime());
//				ps.setString(6, meeting.getEndTime());
//				ps.setString(7, meeting.getMeetingDate());
				
				
//				System.out.println(finalQuery + "            " + ps.toString());
				ResultSet availableRooms = ps.executeQuery();
				
				while (availableRooms.next()) {
					
					MeetingRoom room = new MeetingRoom();
					room.setRoomName(availableRooms.getString(1));
					room.setCostPerHour(availableRooms.getInt(3));
					room.setSeatingCapacity(availableRooms.getInt(2));
					meetingRoomsList.add(room);
				}
				
				return meetingRoomsList;
			}
			else {
				// TODO handle this case
				// TODO throw
				System.out.println("Invalid Meeting Type");
			}
			
		}
		catch (SQLException | ClassNotFoundException e) {
			
			// TODO log exception to error.log
			e.printStackTrace();
		}
		finally {
			ConnectionManager.close();
		}
		
		return meetingRoomsList;
	}
	
	
	/**
	 * search users with the role-member by name in the database
	 * 
	 * @param the user object containing search criteria
	 * @return list of users based on the criteria
	 */
	@Override
	public ArrayList<User> searchUserDao(User user) {
		
		ArrayList<User> users = new ArrayList<>();
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();

			PreparedStatement statement = con.prepareStatement("select * from USERS where role=?");
			statement.setString(1, "member");
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				
				if (rs.getString(3).contains(user.getName())) {
					
					User currUser = new User();
					currUser.setUserId(rs.getString(1));
					currUser.setName(rs.getString(3));
					users.add(currUser);
				}
			}
			
			return users;
		}
		catch (SQLException | ClassNotFoundException e) {
			
			// TODO log exception to error.log
			e.printStackTrace();
		}
		finally {
			ConnectionManager.close();
		}
		
		return null;
	}


	/**
	 * save meeting to the database confirming the meeting booking
	 * 
	 * @param meeting details to save
	 * @param members to invite to meeting
	 * @return if meeting saved or not
	 */
	@Override
	public boolean saveMeetingDao(Meeting meeting, ArrayList<User> members) {
		
		Connection con = null;
		try {
			con = ConnectionManager.getConnection();

			
			PreparedStatement statement = con.prepareStatement("insert into MEETING ");
			statement.setString(1, "member");
			return (statement.executeUpdate() == 1);
		}
		catch (SQLException | ClassNotFoundException e) {
			
			// TODO log exception to error.log
			e.printStackTrace();
		}
		finally {
			ConnectionManager.close();
		}
		return false;
	}
	
}