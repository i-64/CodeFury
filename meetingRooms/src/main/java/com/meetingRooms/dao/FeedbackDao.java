/**
 * 
 */
package com.meetingRooms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.meetingRooms.entity.Feedback;
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
}
