package com.meetingRooms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

import com.meetingRooms.entity.loginUserEntity;
import com.meetingRooms.utility.ConnectionManager;

import com.meetingRooms.utility.DateTimeManipulation;

/**
 * Implementation of login feature
 * 
 * @author Ashutosh Danwe
 * @author Ravi Kachhadiya
 *
 */
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

	
	/**
	 * renew the credits of the manager users
	 * 
	 * @param user object
	 *
	 */
	@Override
	public void renewCredits(String user) {
		
		try {
			
			// prepare query
			
			PreparedStatement ps = con.prepareStatement ( "select next_Renewal_Date from credit_renewal where user_id = ?" );
			
			ps.setString ( 1, user );

    		ResultSet rs = ps.executeQuery();
    		
    		if ( rs.next() ) {
    			
    			Timestamp currentTimestamp = Timestamp.from(Instant.now()); // current date
    			
    			Timestamp renewalDate = Timestamp.valueOf( rs.getString (1) ); // get renewal date
    			
    			if ( currentTimestamp.after ( renewalDate ) ) { // check if current date is past renewal date
    				
    					// if passed renewal date update credits
    				
    				ps = con.prepareStatement ( "update users set credits = 2000 where user_id = ?" );
    				ps.setString ( 1, user );
    				
    				if ( ! (ps.executeUpdate() > 0) ) {
    					
    					return;
    				}
    				
    					// set next renewal date
    				
    				ps = con.prepareStatement ( "update CREDIT_RENEWAL set next_Renewal_Date = ? where user_id = ?" );
    				ps.setString ( 1, DateTimeManipulation.addDays ( renewalDate, 7 ).toString() );
    				ps.setString ( 2, user );
    				
    				if ( !(ps.executeUpdate() > 0) ) {
    					return;
    				}
    				
    			}
    		}
    		
		} catch ( SQLException sql ) {
			
			sql.printStackTrace ();
		}
		
	} // end of renewCredits function
	
	
	/**
	 * Log the user in to the system
	 * 
	 * @param user object
	 * @return user that was logged in
	 * 
	 */
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
