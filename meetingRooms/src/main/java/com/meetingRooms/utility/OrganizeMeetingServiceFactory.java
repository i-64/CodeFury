/**
 * 
 */
package com.meetingRooms.utility;

import com.meetingRooms.service.OrganizeMeetingService;
import com.meetingRooms.service.OrganizeMeetingServiceInterface;

/**
 * Factory for instantiating OrganizeMeetingService
 * 
 * @author Mrunal Ahire
 *
 */
public class OrganizeMeetingServiceFactory {

	private OrganizeMeetingServiceFactory() {}
	
	/**
	 * @return object of OrganizeMeetingService
	 */
	public static OrganizeMeetingServiceInterface createObject () {
		
		return (new OrganizeMeetingService());
	}
}