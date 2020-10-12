package com.meetingRooms.utility;

import com.meetingRooms.dao.ImportUserDao;
import com.meetingRooms.dao.ImportUserDaoInterface;

/**
 * Factory design pattern for object creation of Admin feature related classes
 * 
 * @author Ashutosh Danwe
 *
 */
public class ImportUserDaoFactory {
	
	private ImportUserDaoFactory() {}
	
	/**
	 * create object of import user dao class implementation
	 * 
	 * @param nn
	 * @return imported user dao layer object
	 */
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
