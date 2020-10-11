package com.meetingRooms.service;

import com.meetingRooms.dao.ImportUserDaoInterface;
import com.meetingRooms.entity.ImportUser;
import com.meetingRooms.utility.ImportUserDaoFactory;



/**
 * Implementation for importing users service layer
 * 
 * @author Kunal Rasam
 *
 */
public class ImportUserService implements ImportUserServiceInterface {

	private ImportUserDaoInterface di;
	
	public ImportUserService()
	{
		di=ImportUserDaoFactory.createobject("admindao");
	}
	
	
	/**
	 * import users service
	 * 
	 * @param imported user object to save
	 * @return if the user was saved
	 *
	 */
	@Override
	public int ServiceImport(ImportUser iu) {
		// TODO Auto-generated method stub
		
		
		int j= di.DaoImport(iu);
		return j;
		
	}

}
