package com.meetingRooms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.meetingRooms.entity.loginUserEntity;
import com.meetingRooms.utility.ConnectionManager;

public class loginDAO implements loginDAOInterface {

	private Connection con; // connection object to establish connection
	
	
	public loginDAO () {
		
		try {
			
			// load driver
			
			Class.forName ( "org.apache.derby.jdbc.EmbeddedDriver" );
						
			// get connection to database
						
			con = ConnectionManager.getConnection();
			
		} catch ( SQLException | ClassNotFoundException e ) {
			
			e.printStackTrace ();
		}
		 
	} // end of constructor

	
	@Override
	public loginUserEntity logInUser ( loginUserEntity user ) {
		
		try {
			
			// prepare query
			
			PreparedStatement ps = con.prepareStatement ( "select * from users where USER_ID = ? and PASSWORD = ?" );
			
			ps.setString ( 1, user.getUser_id () );
			ps.setString ( 2, user.getPassword () );
			
			ResultSet rs = ps.executeQuery ();
			
			if ( rs.next () ) {				
				
				user = new loginUserEntity ();
				
				user.setUser_id ( rs.getString ( 1 ) );
				
				user.setName ( rs.getString ( 3 ) );
				user.setEmail ( rs.getString ( 4 ) );
				
				user.setPhone ( rs.getString ( 5 ) );
				user.setRole ( rs.getString ( 7 ) );
				
				return user;
			}
			
		} catch ( SQLException sql ) {
			
			sql.printStackTrace ();
		}
		finally {
			
		}
		
		return null;
		
	} // end of logInUser class	
	
} // end of loginDAO class
