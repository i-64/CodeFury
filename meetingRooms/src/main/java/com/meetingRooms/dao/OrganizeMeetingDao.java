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
			
			String queryString = "select unique_name from meeting_room except (select meeting_room_id from meeting where (((start_time>=? and end_time<=?) or (start_time<=? and end_time>=?) or (start_time>=? and endtime_<=?)) and meeting_date=?)) INTERSECT ";

			PreparedStatement ps = con.prepareStatement(queryString);
			ResultSet busy = ps.executeQuery();
			
			// ========================================
			
			String str = "select mandatory_amenities from meeting_types where id=?";
			PreparedStatement ps1 = con.prepareStatement(str);
			ps1.setInt(1, meetingType.getMeetingTypeId());
			
			ResultSet rs = ps1.executeQuery();
			
			if (rs.next()) {
				
				
				
				String[] test = rs.getString(1).split(",");
				for (int i = 0; i < test.length; i++) {
					
					if (i < test.length - 1)
						queryString = queryString + "select meeting_room_id from ROOM_AMENITIES where amenity_id=" + test[i] + " INTERSECT ";
					else
						queryString = queryString + "select meeting_room_id from ROOM_AMENITIES where amenity_id=" + test[i];
				}
			}
			else {
				// TODO handle this case
			}
			
			String finalQuery = "select * from meeting_room INNER JOIN " + queryString;
			
			// ========================================
			
			ArrayList<String> availableRooms = new ArrayList<>();
			
			while (busy.next()) {
				
				availableRooms.add(busy.getString(1));
			}
			
			// TODO query
			// TODO resultset -> arraylist
			
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
	
}