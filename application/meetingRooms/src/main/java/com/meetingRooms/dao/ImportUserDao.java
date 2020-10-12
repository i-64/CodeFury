package com.meetingRooms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

import com.meetingRooms.entity.ImportUser;

/**
 * implementation of imprting users functionality, loading users into the database
 * 
 * @author Kunal Rasam
 *
 */
public class ImportUserDao implements ImportUserDaoInterface{
	
	private static final Logger LOGR = LoggerFactory.getLogger(ImportUserDao.class);

	int i=0;
	private Connection con; // connection object to establish connection	
	
	public ImportUserDao () {
		
		try {
			
			// load driver
			
			Class.forName ( "org.apache.derby.jdbc.EmbeddedDriver" );
						
			// get connection to database
						
			con = DriverManager.getConnection ( "jdbc:derby:c:/database/meetingRoomsDB", "admin", "admin" );
			
		} catch ( SQLException | ClassNotFoundException e ) {
			
			LOGR.error(e.toString());
		}
		 
	} // end of constructor

	
	/**
	 * import user functionality and loading into database
	 * 
	 * @param object of imported user entity
	 * @return the number of members saved
	 */
	@Override
	public int DaoImport(ImportUser iu) {
		
		
		try
		{
			
			//Adding user data into users table
			PreparedStatement ps=con.prepareStatement("insert into USERS values(?,?,?,?,?,?,?)");
			ps.setString(1,iu.getuid());
			ps.setString(2,iu.getpassword());
			ps.setString(3,iu.getname());
			ps.setString(4,iu.getemail());
			ps.setString(5,iu.getphone());
			ps.setInt(6,iu.getcredits());
			ps.setString(7,iu.getrole());
			
			i=ps.executeUpdate();
			
			
			
			
			//adding user data into log table
			PreparedStatement p=con.prepareStatement("insert into LOG values(?,?,?)");
			p.setString(1,iu.getuid());
			p.setString(2,Timestamp.from(Instant.now()).toString());
			p.setString(3,iu.getuserpath());
			
			p.executeUpdate();
			
			
			if(iu.getrole().equals("manager"))
			{
			PreparedStatement credits=con.prepareStatement("insert into CREDIT_RENEWAL values(?,?)");
			credits.setString(1,iu.getuid());
			credits.setString(2,iu.getmondaydate());
			credits.executeUpdate();
				
				
			}
			
			con.commit(); // commit transactions
			
		}
		catch ( SQLException e) 
		{

			LOGR.error(e.toString());
		}
		finally {
			
		}
		
		
		
		return i;
		

	}

}
