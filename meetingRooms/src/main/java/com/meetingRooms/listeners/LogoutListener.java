package com.meetingRooms.listeners;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class LogoutListener implements HttpSessionListener {
	
	
	@Override
	public void sessionDestroyed(HttpSessionEvent session) {
		HttpSession ctx = session.getSession();
		String userId=(String) ctx.getAttribute("user_id");
		System.out.println(userId);
		Date d = new Date(ctx.getLastAccessedTime());  				//.getLastAccessedTime() returns time in milliseconds
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");		//converting milliseconds to date format 
		
		df.setTimeZone(TimeZone.getDefault());							//changing timezome from GMT to IST
		System.out.println(df.format(d));
		
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSessionListener.super.sessionCreated(se);
	}
	
	
	
	
}
