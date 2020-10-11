package com.meetingRooms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import com.meetingRooms.entity.User;
import com.meetingRooms.entity.UserLog;
import com.meetingRooms.utility.ConnectionManager;

/**
 * Implementation for last logged in info feature
 * 
 * @author Akspreet Kaur
 *
 */
public class LogDao implements LogDaoInterface{
	
	private static Connection con;
	//creating connection con for the whole class
	static {

			try {
				con = ConnectionManager.getConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("unable to get db connection!");

			}

	}
	
	/**
	 * get the last logged in time of user
	 * 
	 * @param the object of user
	 * @return the login time of user
	 */
	@Override
	public Time displayLastLoginDao(User u) {
		
		UserLog user=new UserLog();
		PreparedStatement ps;
		try {
			 ps = con.prepareStatement ( "select last_login_time from log where user_id=?" );
			 ps.setString(1, u.getUserId());
			 
			 ResultSet res=ps.executeQuery();
			 while(res.next()) {
			 user.setLogInTime(new Time(res.getTimestamp(1).getTime()));
			 }
		}
			 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return user.getLogInTime();
	}
}

