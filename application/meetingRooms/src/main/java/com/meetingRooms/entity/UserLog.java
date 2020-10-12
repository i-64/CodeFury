package com.meetingRooms.entity;

import java.sql.Time;

/**
 * Entity class for logs of User
 * 
 * @author Kunal Rasam
 *
 */
public class UserLog {
	
	private String filePath, userId;
	private Time logInTime;

	
	/**
	 * @return path to user log
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * @return log in time of user
	 */
	public Time getLogInTime() {
		return logInTime;
	}
	/**
	 * @param logInTime
	 */
	public void setLogInTime(Time logInTime) {
		this.logInTime = logInTime;
	}
	/**
	 * @return id of user
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
