package com.meetingRooms.dao;

import java.util.List;

import com.meetingRooms.entity.Meeting;
import com.meetingRooms.entity.loginUserEntity;

public interface MemberScheduleDaoInterface {

	List<Meeting> loadMeetingServiceDao(loginUserEntity user) ;

}
