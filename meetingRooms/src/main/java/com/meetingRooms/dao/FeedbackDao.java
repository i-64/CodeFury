/**
 * 
 */
package com.meetingRooms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.meetingRooms.entity.Feedback;
import com.meetingRooms.entity.MeetingRoomEntity;
import com.meetingRooms.utility.ConnectionManager;

/**
 * facilitate Data access for feedback feature
 * 
 * @author Mrunal Ahire
 *
 */
public class FeedbackDao implements FeedbackDaoInterface {
	
	public boolean saveFeedbackDao (Feedback feedback) throws SQLException {
		
		Connection con = null;
		try {
			
			con = ConnectionManager.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into FEEDBACK (meeting_room_id, user_id, rating) values (?, ?, ?)");
			ps.setString(1, feedback.getMeetingRoomId());
			ps.setString(2, feedback.getUserId());
			ps.setInt(3, feedback.getRating());
			
			return (ps.executeUpdate() == 1);
			
		}
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			ConnectionManager.close();
		}
		return false;
	}

	@Override
	public List<MeetingRoomEntity> getAllRooms() {
		// TODO Auto-generated method stub
		List<MeetingRoomEntity> meetingRoomList = new ArrayList<MeetingRoomEntity>();
		
	   	Connection con = null;
	    
    	try {
			con = ConnectionManager.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from meeting_room"); 
			ResultSet res = ps.executeQuery();
       
			while(res.next()) {
			
				MeetingRoomEntity meetingRoom=new MeetingRoomEntity ();
				
				meetingRoom.setUniqueName(res.getString(1));
				
				meetingRoom.setSeatingCapacity(res.getInt(2));
				
				meetingRoom.setPerHourCost(res.getInt(3));
				
				meetingRoom.setTotal_meetings_conducted(res.getInt(4));
			
				meetingRoomList.add(meetingRoom);
			}
	
		}
		
		catch(SQLException | ClassNotFoundException ee) {
			ee.printStackTrace();
		}
		finally {
			ConnectionManager.close();
		}
    	return meetingRoomList;
	}
}
