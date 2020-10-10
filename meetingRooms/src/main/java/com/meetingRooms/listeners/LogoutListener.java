package com.meetingRooms.listeners;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.meetingRooms.entity.UserLog;
import com.meetingRooms.utility.ConnectionManager;

public class LogoutListener implements HttpSessionListener {
	
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
	public void sessionDestroyed(HttpSessionEvent session) {
		HttpSession ctx = session.getSession();
		String userId=(String) ctx.getAttribute("user_id");
		System.out.println(userId);
		Date d = new Date(ctx.getLastAccessedTime());  				//.getLastAccessedTime() returns time in milliseconds
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");		//converting milliseconds to date format 
		
		df.setTimeZone(TimeZone.getDefault());							//changing timezome from GMT to IST
		System.out.println(df.format(d));
		
		
		
		PreparedStatement ps;
		try {
			 ps = con.prepareStatement ( "insert into log values(?,?,?)" );
			
			 ps.setString(1,userId);
			 System.out.println("Timestamp feed");
			 System.out.println(new Timestamp(d.getTime()));
			 ps.setTimestamp(2,new Timestamp(d.getTime()));
			 ps.setString(3, null);
			
			 ps.executeUpdate();
			 }
		catch (SQLException e) {
			
			e.printStackTrace();
		
		}
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSessionListener.super.sessionCreated(se);
	}
	
	
	
	
}
