/**
 * 
 */
package com.meetingRooms.service;

import java.sql.SQLException;
import java.util.List;

import com.meetingRooms.entity.Feedback;
import com.meetingRooms.entity.MeetingRoomEntity;

/**
 * Interface for the meeting room feedback service
 * 
 * @author Mrunal Ahire
 *
 */
public interface FeedbackServiceInterface {

	public boolean saveFeedbackService (Feedback feedback) throws SQLException;
	public List<MeetingRoomEntity> getAllRooms();
}
