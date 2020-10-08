package com.meetingRooms.utility;

import com.meetingRooms.service.ImportUserService;
import com.meetingRooms.service.ImportUserServiceInterface;

public class ImportUserServiceFactory {
	
	private ImportUserServiceFactory()
	{
		
	}
	public static  ImportUserServiceInterface createobject(String nn)
	{
		ImportUserServiceInterface si = null;
		if(nn.equals("adminservice"))
		{
			si=new ImportUserService();
		}
		
		return si;
		
	}
	
	

}
