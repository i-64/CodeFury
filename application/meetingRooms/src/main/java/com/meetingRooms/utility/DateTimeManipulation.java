package com.meetingRooms.utility;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Manipulate Date Time
 * 
 * @author Ashutosh Danwe
 *
 */
public class DateTimeManipulation {
	
	private DateTimeManipulation () {}
	
	
	/**
	 * take the sum of days, and add to the given timestamp
	 * 
	 * @param date
	 * @param days
	 * @return resulting time
	 */
	public static Timestamp addDays (Timestamp date, int days) {
		
        Calendar cal = Calendar.getInstance();
        
        cal.setTime(date);// w ww.  j ava  2  s  .co m
        
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        
        return new Timestamp(cal.getTime().getTime());

    }

}
