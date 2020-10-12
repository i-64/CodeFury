/**
 * 
 */
package com.meetingRooms.utility;

import com.meetingRooms.service.FeedbackService;
import com.meetingRooms.service.FeedbackServiceInterface;

/**
 * Factory class for instantiating FeedbackService class using object factory design pattern
 * 
 * @author Mrunal Ahire
 *
 */
public class FeedbackServiceFactory {
	
	private FeedbackServiceFactory () {};
	
	/** create object of FeedbackService class using object factory design pattern
	 * 
	 * @return object of FeedbackService class
	 */
	public static FeedbackServiceInterface createObject () {
		
		return (new FeedbackService());
	}

}
