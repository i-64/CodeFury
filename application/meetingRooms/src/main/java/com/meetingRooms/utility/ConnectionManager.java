/**
 * 
 */
package com.meetingRooms.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Single Connection manager to facilitate connection with database
 * throughout the project
 * 
 * @author Mrunal Ahire
 *
 */
public class ConnectionManager {

	private final static String DRIVER_NAME = "org.apache.derby.jdbc.EmbeddedDriver";
	private final static String CONNECTION_STRING_LINUX = "jdbc:derby:/home/rkdgr8/Downloads/meetingRoomsDB;create=true";
	private final static String CONNECTION_STRING_WINDOWS = "jdbc:derby:c:/database/meetingRoomsDB;create=true";
	private final static String DB_USERNAME = "admin";
	private final static String DB_PASSWORD = "admin";
	
	private static Connection con;
	
	private ConnectionManager () {}
	
	
	/**
	 * @return Connection
	 * @throws Exception
	 */
	public static Connection getConnection () throws SQLException, ClassNotFoundException {
		
		try {
			
			Class.forName(DRIVER_NAME);
			if (System.getProperty("os.name").startsWith("Linux"))
				con = DriverManager.getConnection(CONNECTION_STRING_LINUX, DB_USERNAME, DB_PASSWORD);
			else
				con = DriverManager.getConnection(CONNECTION_STRING_WINDOWS, DB_USERNAME, DB_PASSWORD);
			
			return con;
		}
		catch (SQLException | ClassNotFoundException e) {
			
			// TODO log error to error.log
			throw e;
		}
	}
	
	/**
	 * @throws SQLException
	 */
	public static void close () {
		
		try {
			con.close();
		}
		catch (SQLException e) {
			
			// TODO log error to error.log
			e.printStackTrace();
			// throw e;
		}
	}
}