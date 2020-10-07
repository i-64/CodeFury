/**
 * 
 */
package com.meetingRooms.service;

import java.sql.SQLException;

import com.meetingRooms.entity.Feedback;

/**
 * Interface for the meeting room feedback service
 * 
 * @author Mrunal Ahire
 *
 */
public interface FeedbackServiceInterface {

	public boolean saveFeedbackService (Feedback feedback) throws SQLException;
}
