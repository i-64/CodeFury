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
			
			
			PreparedStatement ps = con.prepareStatement ( "select m.id, m.title, m.organized_by, m.meeting_date, m.start_time, m.duration, rm.meeting_type from attendees a inner join meeting m inner join (select m.id as meet_id, meeting_type from meeting m inner join meeting_types mt on m.meeting_type_id=mt.id) rm on rm.meet_id = m.id on a.meeting_id = m.id where a.user_id=?" );
			ps.setString ( 1, user.getUser_id () );
			ResultSet rs = ps.executeQuery ();
			
			while(rs.next()) {
				// using meeting entity to store the data
				
				Meeting m1= new Meeting();
				m1.setId(Integer.parseInt(rs.getString(1)));
				m1.setTitle(rs.getString(2));
				m1.setOrganizedBy(rs.getString(3));
				m1.setMeetingDateDT(rs.getTime(4));
				m1.setStartTimeTM((rs.getTime(5)));
				m1.setDuration(Integer.parseInt(rs.getString(6)));
				m1.setMeetingTypeName(rs.getString(7));
				
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
