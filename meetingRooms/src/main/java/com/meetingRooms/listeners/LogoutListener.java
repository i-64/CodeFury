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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meetingRooms.entity.UserLog;
import com.meetingRooms.utility.ConnectionManager;

/**
 * Listener for log out event
 * 
 * @author Akspreet Kaur
 *
 */
public class LogoutListener implements HttpSessionListener {
	
	private static final Logger LOGR = LoggerFactory.getLogger(LogoutListener.class);
	
	private static Connection con;
	//creating connection con for the whole class
	static {

			try {
				con = ConnectionManager.getConnection();
			} catch (Exception e) {
				
				LOGR.error(e.toString());
			}
	}
	
				
	
	
	/**
	 * @see HttpSessionListener.sessionDestroyed(HttpSessionEvent session)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent session) {
		
		HttpSession ctx = session.getSession();
		
		String userId=(String) ctx.getAttribute("user_id");

		Date d = new Date(ctx.getLastAccessedTime());  				//.getLastAccessedTime() returns time in milliseconds
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");		//converting milliseconds to date format 
		
		df.setTimeZone(TimeZone.getDefault());							//changing timezome from GMT to IST
	
			
		
		PreparedStatement ps;
		try {
			 ps = con.prepareStatement ( "insert into log values(?,?,?)" );
			
			 ps.setString(1,userId);
			 ps.setTimestamp(2,new Timestamp(d.getTime()));
			 ps.setString(3, null);
			
			 ps.executeUpdate();
			 }
		catch (SQLException e) {

			LOGR.error(e.toString());
		
		}
	}

	/**
	 * @see HttpSessionListener.sessionCreated(HttpSessionEvent session)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSessionListener.super.sessionCreated(se);
		HttpSession session = se.getSession();
		
	    session.setMaxInactiveInterval(60);//in seconds
	}
	
	
	
	
}
