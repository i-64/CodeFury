package com.meetingRooms.utility;

import com.meetingRooms.dao.ImportUserDao;
import com.meetingRooms.dao.ImportUserDaoInterface;

public class ImportUserDaoFactory {
	
	private ImportUserDaoFactory()
	{
		
	}
	public static  ImportUserDaoInterface createobject(String nn)
	{
		ImportUserDaoInterface si = null;
		if(nn.equals("admindao"))
		{
			si=new ImportUserDao();
		}
		
		return si;
		
	}
	
	

}
