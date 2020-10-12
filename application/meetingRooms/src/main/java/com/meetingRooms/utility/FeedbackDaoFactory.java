/**
 * 
 */
package com.meetingRooms.utility;

import com.meetingRooms.dao.FeedbackDao;
import com.meetingRooms.dao.FeedbackDaoInterface;

/**
 * Factory class for instantiating FeedbackDao class
 * 
 * @author Mrunal Ahire
 *
 */
public class FeedbackDaoFactory {
	
	private FeedbackDaoFactory () {}
	
	/** create object of FeedbackDao class using object factory design pattern
	 * 
	 * @return object of FeedbackDao class
	 */
	public static FeedbackDaoInterface createObject () {
		
		return (new FeedbackDao());
	}
}
