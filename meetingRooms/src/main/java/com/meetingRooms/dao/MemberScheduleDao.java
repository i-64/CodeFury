package com.meetingRooms.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.meetingRooms.entity.Meeting;
import com.meetingRooms.entity.loginUserEntity;
import com.meetingRooms.utility.ConnectionManager;
import com.meetingRooms.utility.MemberScheduleDaoFactory;


/**
 * 
 * Dao to retrieve scheduled meetings for a member
 * @author Haritha Jayan
 * 
 */
public class MemberScheduleDao implements MemberScheduleDaoInterface {
	
	private Connection con;
	
	public MemberScheduleDao() {
		
			try {
						
			// get connection to database
			con = ConnectionManager.getConnection();
			
			} catch ( SQLException | ClassNotFoundException e ) {
			
			e.printStackTrace ();
		}
	}
	
	
	
	/**
	 * get list of meetings the user is invited for
	 * 
	 * @param user logged in
	 * @return list of meetings of the logged in user
	 * 
	 */
	@Override
	public List<Meeting> loadMeetingServiceDao(loginUserEntity user) {
		
		List<Meeting> meeting_schedule =new ArrayList<Meeting>();
		
			try {
			
			//getting all meeting ID's assigned to member using innerjoin to join meeting and attendees
			
			
			PreparedStatement ps = con.prepareStatement ( "select m.id, m.title, m.organized_by, m.meeting_date, m.start_time, m.end_time, m.meeting_room_id, rm.meeting_type from attendees a inner join meeting m inner join (select m.id as meet_id, meeting_type from meeting m inner join meeting_types mt on m.meeting_type_id=mt.id) rm on rm.meet_id = m.id on a.meeting_id = m.id where a.user_id=?" );
			ps.setString ( 1, user.getUser_id () );
			ResultSet rs = ps.executeQuery ();
			
			while(rs.next()) {
				// using meeting entity to store the data
				
				Meeting m1= new Meeting();
				m1.setId(Integer.parseInt(rs.getString(1)));
				m1.setTitle(rs.getString(2));
				m1.setOrganizedBy(rs.getString(3));
				
				m1.setMeetingDateDT(rs.getDate(4));
				m1.setStartTimeTM((rs.getTime(5)));
				m1.setEndTime(rs.getString(6));
				
				m1.setMeetingRoomId(rs.getString(7));
				m1.setMeetingTypeName(rs.getString(8));

				
				meeting_schedule.add(m1);
	
				//adding each meeting to the list of meeting
			}
			

			
			}catch(SQLException sql) {
				
				sql.printStackTrace();				
			}
			
			return meeting_schedule;
		
		
	}

}
