package com.meetingRooms.utility;

import com.meetingRooms.service.ImportUserService;
import com.meetingRooms.service.ImportUserServiceInterface;

/**
 * object creation factory for instantiating import user service class
 * 
 * @author Kunal Rasam
 *
 */
public class ImportUserServiceFactory {
	
	private ImportUserServiceFactory()
	{
		
	}
	/**
	 * create object of import user service class
	 * 
	 * @param nn
	 * @return object of ImportUserService
	 */
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
