package com.meetingRooms.controller;

import java.util.List;

import com.meetingRooms.entity.Meeting;
import com.meetingRooms.entity.loginUserEntity;
import com.meetingRooms.service.MemberScheduleServiceInterface;
import com.meetingRooms.utility.MemberScheduleServiceFactory;

public class MemberScheduleController implements MemberScheduleControllerInterface{

private MemberScheduleServiceInterface meeting_schedule = null;
	
	public MemberScheduleController() {
		
		meeting_schedule = MemberScheduleServiceFactory.createObject("create");
		
	}
	
	public List<Meeting> loadMeeting(loginUserEntity user) {
		
		List<Meeting> meetingList;
		
		
		meetingList = meeting_schedule.loadMeetingService(user);
		return meetingList;
	}
	
	
	public static void main(String args[]) {
		
		List<Meeting> meetingList;
		
		loginUserEntity user = new loginUserEntity();
		user.setUser_id("5001");
		
		MemberScheduleController mc= new MemberScheduleController();
		meetingList = mc.loadMeeting(user);
		
		for(Meeting m:meetingList) {
			System.out.println(m.getId());
			System.out.println(m.getDuration());
			System.out.println(m.getTitle());
			System.out.println(m.getMeetingDate());
			System.out.println(m.getStartTime());
			System.out.println(m.getOrganizedBy());
		}
	}
}
