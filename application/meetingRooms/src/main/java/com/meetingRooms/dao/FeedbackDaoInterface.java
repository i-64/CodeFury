/**
 * 
 */
package com.meetingRooms.dao;

import java.sql.SQLException;
import java.util.List;

import com.meetingRooms.entity.Feedback;
import com.meetingRooms.entity.MeetingRoomEntity;

/**
 * Interface for Feedback data access class
 * 
 * @author Mrunal Ahire
 *
 */
public interface FeedbackDaoInterface {

	public boolean saveFeedbackDao (Feedback feedback) throws SQLException;

	public List<MeetingRoomEntity> getAllRooms();
}
