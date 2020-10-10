package com.meetingRooms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

import com.meetingRooms.entity.ImportUser;

public class ImportUserDao implements ImportUserDaoInterface{

	
	private Connection con; // connection object to establish connection
	
	
	public ImportUserDao () {
		
		try {
			
			// load driver
			
			Class.forName ( "org.apache.derby.jdbc.EmbeddedDriver" );
						
			// get connection to database
						
			con = DriverManager.getConnection ( "jdbc:derby:c:/database/meetingRoomsDB", "admin", "admin" );
			
		} catch ( SQLException | ClassNotFoundException e ) {
			
			e.printStackTrace ();
		}
		 
	} // end of constructor

	
	@Override
	public void DaoImport(ImportUser iu) {
		
		
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
		
		ps.executeUpdate();
		
		
		//adding user data into log table
		PreparedStatement p=con.prepareStatement("insert into LOG values(?,?,?)");
		p.setString(1,iu.getuid());
		p.setString(2,null);
		p.setString(3,iu.getuserpath());
		
		p.executeUpdate();
		
		
		if(iu.getrole().equals("manager"))
		{
		PreparedStatement credits=con.prepareStatement("insert into CREDIT_RENEWAL values(?,?)");
		credits.setString(1,iu.getuid());
		credits.setString(2,iu.getmondaydate());
		credits.executeUpdate();
			
			
		}
		
		}
		catch ( SQLException sql ) 
		{
			
			sql.printStackTrace ();
		}
		finally {
			
		}
		

	}

}
