/**
 * 
 */
package com.meetingRooms.dao;

import java.sql.SQLException;

import com.meetingRooms.entity.Feedback;

/**
 * Interface for Feedback data access class
 * 
 * @author Mrunal Ahire
 *
 */
public interface FeedbackDaoInterface {

	public boolean saveFeedbackDao (Feedback feedback) throws SQLException;
}
