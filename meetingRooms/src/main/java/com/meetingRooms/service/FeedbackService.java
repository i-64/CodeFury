/**
 * 
 */
package com.meetingRooms.service;

import java.sql.SQLException;

import com.meetingRooms.dao.FeedbackDaoInterface;
import com.meetingRooms.entity.Feedback;
import com.meetingRooms.utility.FeedbackDaoFactory;

/**
 * @author i-64
 *
 */
public class FeedbackService implements FeedbackServiceInterface {

	private FeedbackDaoInterface dao;
	
	public FeedbackService () {
		
		dao = FeedbackDaoFactory.createObject();
	}
	
	/**
	 * service to save feedback goven by user for the meeting room
	 * 
	 * @param the feedback given
	 * @return if the feedback was successfully saved
	 * @throws SQLException 
	 */
	@Override
	public boolean saveFeedbackService(Feedback feedback) throws SQLException  {
		
		return dao.saveFeedbackDao(feedback);
	}

}
