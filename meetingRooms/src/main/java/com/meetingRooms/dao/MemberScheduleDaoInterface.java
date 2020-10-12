package com.meetingRooms.dao;

import java.util.List;

import com.meetingRooms.entity.Meeting;
import com.meetingRooms.entity.loginUserEntity;

/**
 * Interface for displaying member schedule
 * 
 * @author Haritha Jayan
 *
 */
public interface MemberScheduleDaoInterface {

	List<Meeting> loadMeetingServiceDao(loginUserEntity user) ;

}
