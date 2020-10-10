package com.meetingRooms.entity;

import java.sql.Time;

public class UserLog {
	
	private String filePath, userId;
	private Time logInTime;

	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Time getLogInTime() {
		return logInTime;
	}
	public void setLogInTime(Time logInTime) {
		this.logInTime = logInTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
