package com.meetingRooms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.meetingRooms.entity.Meeting;
import com.meetingRooms.entity.User;
import com.meetingRooms.entity.loginUserEntity;
import com.meetingRooms.utility.ConnectionManager;

public class MeetingRoomsDao implements MeetingRoomsDaoInterface {
	private static Connection con;
	//creating connection con for the whole class
	static {

			try {
				con = ConnectionManager.getConnection();
				System.out.println("connection created");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("unable to get db connection!");

			}

	}
	
	@Override
	public User managerInfoDao(User u) {
		// TODO Auto-generated method stub
		
		User user=null;
		
		//getting manager information
		
		PreparedStatement ps;
			try {
				 ps = con.prepareStatement ( "select * from users where user_id=?" );
				
				ps.setString(1, u.getUserId());
				
				ResultSet res=ps.executeQuery();
				while(res.next()) {
					//i=true;
					user =new User();
					user.setName(res.getString("name"));
					user.setPhone(res.getString("Phone"));
					user.setRole(res.getString("role"));
					user.setUserId(res.getString("user_id"));
					user.setEmail(res.getString("email"));	
					user.setCredits(res.getInt("credits"));
				}
				
			} 
			catch (SQLException e) {
			
				e.printStackTrace();
			
			}
					
		return user;
	
		}

	@Override
	public List<Meeting> listOfScheduledMeetingsDao(User u) {
		
		List<Meeting> meetingList=new ArrayList<Meeting>();
		//getting list of meetings scheduled by the manager 
		PreparedStatement ps;
		
			try {
				
				//meeting_types, meeting_room, meeting
				ps = con.prepareStatement("select a.id, title, organized_by, meeting_date, start_time, end_time, b.meeting_type, d.unique_name from meeting a INNER JOIN MEETING_TYPES b  ON a.meeting_type_id=b.id INNER JOIN MEETING_ROOM d ON d.created_by = a.organized_by where organized_by=?");
				ps.setString(1, u.getUserId());
			
				
				ResultSet res= ps.executeQuery();
				Meeting m=null;
				
				while(res.next())
				{
					m=new Meeting();
					m.setId(res.getInt(1));
					m.setTitle(res.getString(2));
					m.setOrganizedBy(res.getString(3));
					m.setMeetingDateDT(res.getDate(4));
					m.setStartTimeTM(res.getTime(5));
					m.setDuration(res.getInt(6));
					m.setMeetingTypeName(res.getString(7));
					m.setMeetingRoomName(res.getString(8));
					meetingList.add(m);
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
			return meetingList;
	
	
			
	}
	
	
	

	
	
	
}
