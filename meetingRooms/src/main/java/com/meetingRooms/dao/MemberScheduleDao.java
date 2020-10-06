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
import com.meetingRooms.utility.MemberScheduleDaoFactory;

public class MemberScheduleDao implements MemberScheduleDaoInterface {
	
	private Connection con;
	
	public MemberScheduleDao() {
		
			try {
			// load driver
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
						
			// get connection to database
			con = DriverManager.getConnection("jdbc:derby:c:/database/meetingRoomsDB", "admin", "admin" );
			
			} catch ( SQLException | ClassNotFoundException e ) {
			
			e.printStackTrace ();
		}
	}
	
	
	
	@Override
	public List<Meeting> loadMeetingServiceDao(loginUserEntity user) {
		
		List<Meeting> meeting_schedule =new ArrayList<Meeting>();
		
			try {
			
			//getting all meeting ID's assigned to member using innerjoin to join meeting and attendees
			
			
			PreparedStatement ps = con.prepareStatement ( "select meeting.id, meeting.title, meeting.organized_by, meeting.meeting_date, meeting.start_time, meeting.duration, meeting.meeting_type_id from meeting inner join attendees on meeting.id= attendees.meeting_id where attendees.user_id = ?" );
			ps.setString ( 1, user.getUser_id () );
			ResultSet rs = ps.executeQuery ();
			
			while(rs.next()) {
				// using meeting entity to store the data
				
				Meeting m1= new Meeting();
				m1.setId(Integer.parseInt(rs.getString(1)));
				m1.setTitle(rs.getString(2));
				m1.setOrganizedBy(rs.getString(3));
				m1.setMeetingDate(rs.getDate(4));
				m1.setStartTime((rs.getTime(5)));
				m1.setDuration(Integer.parseInt(rs.getString(6)));
				m1.setMeetingType(rs.getString(7));
				
				meeting_schedule.add(m1);
	
				//adding each meeting to the list of meeting
			}
			
			//to check if retrieval from db and adding to list is happening-not for actual code
			for(Meeting m:meeting_schedule) {
				System.out.println(m.getId());
				System.out.println(m.getDuration());
				System.out.println(m.getTitle());
				System.out.println(m.getMeetingDate());
				System.out.println(m.getStartTime());
				System.out.println(m.getOrganizedBy());
			}
			
			}catch(SQLException sql) {
				
				sql.printStackTrace();				
			}
			
			return meeting_schedule;
		
		
	}

}
