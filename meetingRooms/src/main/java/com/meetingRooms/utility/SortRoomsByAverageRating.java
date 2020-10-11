/**
 * 
 */
package com.meetingRooms.utility;

import java.util.Comparator;

import com.meetingRooms.entity.MeetingRoom;

/**
 * Comparator for sorting meeting in decreaing order
 * of their average rating
 * 
 * @author Mrunal Ahire
 *
 */
public class SortRoomsByAverageRating implements Comparator<MeetingRoom> {

	@Override
	public int compare (MeetingRoom room1, MeetingRoom room2) {
		
		return -1 * Double.compare(room1.getAverageRating(), room2.getAverageRating());
	}

}