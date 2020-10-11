package com.meetingRooms.dao;

import com.meetingRooms.entity.ImportUser;

/**
 * Interface for importing users through xml
 * 
 * @author Kunal Rasam
 *
 */
public interface ImportUserDaoInterface {

	int DaoImport(ImportUser iu);
}
