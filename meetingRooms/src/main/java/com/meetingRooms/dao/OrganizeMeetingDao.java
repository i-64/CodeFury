/**
 * 
 */
package com.meetingRooms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.meetingRooms.entity.Meeting;
import com.meetingRooms.entity.MeetingRoom;
import com.meetingRooms.entity.MeetingType;
import com.meetingRooms.entity.User;
import com.meetingRooms.exceptions.NotEnoughCreditsException;
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
			
			/*
			 *  start build query
			 *  exclude overlapping meeting rooms
			 */
			String queryString = "(select unique_name from meeting_room except (select meeting_room_id from meeting where ( ((start_time<=? and end_time>=?) or (start_time<=? and start_time>=?)) and meeting_date=?)) ) INTERSECT ";
			
			
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
				
				
				// finishig off the query
				String finalQuery = "select * from meeting_room where unique_name in (" + queryString + ")";
				
				
				PreparedStatement ps = con.prepareStatement(finalQuery);
				ps.setString(1, meeting.getStartTime());
				ps.setString(2, meeting.getStartTime());
				ps.setString(3, meeting.getEndTime());
				ps.setString(4, meeting.getStartTime());
				ps.setString(5, meeting.getMeetingDate());
				
				
				ResultSet availableRooms = ps.executeQuery();
				
				while (availableRooms.next()) {
					
					PreparedStatement ratingStatement = con.prepareStatement("");
					ResultSet ratingResultSet = ratingStatement.executeQuery();
					
					double averageRating = 0.0;
					
					MeetingRoom room = new MeetingRoom();
					room.setRoomName(availableRooms.getString(1));
					room.setCostPerHour(availableRooms.getInt(3));
					room.setSeatingCapacity(availableRooms.getInt(2));
					room.setAverageRating(averageRating);
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
		catch (Exception e) {
			
			// TODO log
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

			PreparedStatement statement = con.prepareStatement("select * from USERS where role=? or role=?");
			statement.setString(1, "member");
			statement.setString(2, "manager");
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
	 * @param list of members to invite to meeting
	 * @return if meeting saved or not
	 * @throws NotEnoughCreditsException 
	 */
	@Override
	public boolean saveMeetingDao(Meeting meeting, ArrayList<User> members) throws NotEnoughCreditsException {
		
		Connection con = null;
		try {
			
			con = ConnectionManager.getConnection();


			// check if the manager has enough credits
			
			int managerCredits = 0, meetingCost = 0;
			PreparedStatement managerCreditStatement = con.prepareStatement("select credits from USERS where user_id=?");
			managerCreditStatement.setString(1, meeting.getOrganizedBy());
			ResultSet managerCreditResult = managerCreditStatement.executeQuery();
			
			if (managerCreditResult.next()) {
				
				managerCredits = managerCreditResult.getInt(1);
				PreparedStatement roomCreditStatement = con.prepareStatement("select per_hour_cost from MEETING_ROOM where roounique_name=?");
				roomCreditStatement.setString(1, meeting.getMeetingRoomId());
				ResultSet roomCreditResult = roomCreditStatement.executeQuery();
				
				if (roomCreditResult.next()) {
					
					int roomCreditsPerHour = roomCreditResult.getInt(1);
					meetingCost = roomCreditsPerHour * meeting.getDurationInHours();
					
					if (meetingCost > managerCredits) {
						
						// Manager does not have enough credits to book the meeting
						// raise an exception and reject the booking request
						
						throw (new NotEnoughCreditsException());
					}
				}
			}
			
			
			// the manager has enough credits, meeting can be committed to database
			
			PreparedStatement statement = con.prepareStatement("insert into MEETING (title, organized_by, meeting_date, start_time, end_time, meeting_room_id, meeting_type_id) values (?,?,?,?,?,?,?)");
			statement.setString(1, meeting.getTitle());
			statement.setString(2, meeting.getOrganizedBy());
			statement.setString(3, meeting.getMeetingDate());
			statement.setString(4, meeting.getStartTime());
			statement.setString(5, meeting.getEndTime());
			statement.setString(6, meeting.getMeetingRoomId());
			statement.setInt(7, meeting.getMeetingTypeId());
			
			if (statement.executeUpdate() == 1) {
				
				PreparedStatement ps = con.prepareStatement("select MAX (id) from MEETING");
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					
					int meetingId = rs.getInt(1);
					
					for (User user: members) {
						
						PreparedStatement psAttendees = con.prepareStatement("insert into ATTENDEES values (?, ?)");
						psAttendees.setInt(1, meetingId);
						psAttendees.setString(2, user.getUserId());
						psAttendees.execute();
					}
					
					
					// meeting and attendees saved, safely charge credits to the manager
					
					PreparedStatement updateCreditsStatement = con.prepareStatement("update USERS set credits=? where user_id=?");
					updateCreditsStatement.setString(2, meeting.getOrganizedBy());
					updateCreditsStatement.setInt(1, managerCredits - meetingCost);
					return (updateCreditsStatement.executeUpdate() == 1);
				}
			}
			else {
				
				// TODO logr.error("Failed to insert meeting: " + meeting.toString());
			}
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

	
	/**
	 * get the credits of the manager
	 * 
	 * @param the user to get crdits
	 * @return the credits of the manager
	 */
	@Override
	public int getCredits(User user) {
		
		Connection con = null;
		try {
		
			con = ConnectionManager.getConnection();
			PreparedStatement ps = con.prepareStatement("select credits from USERS where user_id=?");
			ps.setString(1, user.getUserId());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				return rs.getInt(1);
			}
		}
		catch (SQLException | ClassNotFoundException e) {
			// TODO
		}
		finally {
			ConnectionManager.close();
		}
		return 0;
	}
	
}