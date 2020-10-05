/**
 * 
 */
package com.meetingRooms.utility;

import com.meetingRooms.dao.OrganizeMeetingDao;
import com.meetingRooms.dao.OrganizeMeetingDaoInterface;

/**
 * Factory for instantiating OrganizeMeetingDao
 * 
 * @author Mrunal Ahire
 *
 */
public class OrganizeMeetingDaoFactory {
	
	private OrganizeMeetingDaoFactory () {}
	
	public static OrganizeMeetingDaoInterface createObject () {
		
		return (OrganizeMeetingDaoInterface) (new OrganizeMeetingDao());
	}

}