package com.meetingRooms.dao;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.ArrayList;


import com.meetingRooms.entity.DataDisplayForIndex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
public class LoginDAO implements LoginDAOInterface {
	
	private static final Logger LOGR = LoggerFactory.getLogger(LoginDAO.class);

	private Connection con; // connection object to establish connection
	
	
	public LoginDAO () {
		
		try {
						
			// get connection to database
						
			con = ConnectionManager.getConnection();
			
		} catch ( SQLException | ClassNotFoundException e ) {

			LOGR.error(e.toString());
		}
		 
	} // end of constructor

	
	@Override
	public List<DataDisplayForIndex> getWelcomePageData () {
		
		List<DataDisplayForIndex> list = new ArrayList <DataDisplayForIndex> ();
		
		try {
		
			PreparedStatement ps = con.prepareStatement ( "select m.unique_name, m.seating_capacity, m.total_meetings_conducted, f.rating from meeting_room m left outer join ( select meeting_room_id, SUM(rating)/COUNT(rating) as rating from feedback group by meeting_room_id ) f on m.unique_name = f.meeting_room_id" );
		
			ResultSet set_1 = ps.executeQuery ();
			
			while ( set_1.next () ) {
				
				DataDisplayForIndex data = new DataDisplayForIndex ();
				
				data.setName ( set_1.getString(1));
				data.setSeating_capacity ( set_1.getString(2));
				data.setTotal_meetings_conducted ( set_1.getString(3));
				
				if ( set_1.getString(4) == null ) {
					
					data.setRatings ( "No Ratings" );
					
				} else {
					
					data.setRatings ( set_1.getString(4));
				}
				
				list.add(data);
			}
		
			return list;
			
		}catch ( SQLException sql ) {
			
			sql.printStackTrace ();
			
		}
		
		finally {
			
			ConnectionManager.close();			
		}
		
		
		return null;		
		
	} // end of getWelcomePageData function
	
	
	/**
	 * renew the credits of the manager users
	 * 
	 * @param user object
	 *
	 */
	@Override
	public void renewCredits(String user) {
		
		try {
			
			con.setAutoCommit(false); // initiate transaction
			
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
    					
    					con.rollback(); // roll back uncommitted changes
    					
    					return;
    				}
    				
    					// set next renewal date
    				
    				ps = con.prepareStatement ( "update CREDIT_RENEWAL set next_Renewal_Date = ? where user_id = ?" );
    				ps.setString ( 1, DateTimeManipulation.addDays ( renewalDate, 7 ).toString() );
    				ps.setString ( 2, user );
    				
    				if ( !(ps.executeUpdate() > 0) ) {
    					
    					con.rollback(); // roll back uncommitted changes
    					
    					return;
    				}
    				
    			}
    		}
    		
    		con.commit(); // commit transactions
    		

		} catch ( SQLException sql ) {
			
			sql.printStackTrace ();
		} 
		
		finally {
			
			ConnectionManager.close();			

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
			
			String hashpassword=Hashing(getSHA(user.getPassword ()));
			hashpassword=(hashpassword.substring(1, 26));
			
			ps.setString ( 1, user.getUser_id () );
			ps.setString ( 2, hashpassword );
			
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
			
		} catch ( SQLException e ) {

			LOGR.error(e.toString());
			
		} catch ( NoSuchAlgorithmException e ) {

			LOGR.error(e.toString());
		}
		finally {
			
			ConnectionManager.close();			
		}
		
		return null;
		
	} // end of logInUser class
	
	
		// utility functions
	
	private byte[] getSHA(String password) throws NoSuchAlgorithmException {
		
        	// Static getInstance method is called with hashing SHA
		
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
  
        // digest() method called  
        // to calculate message digest of an input  
        // and return array of byte 
        return md.digest(password.getBytes(StandardCharsets.UTF_8));
        
	} // end of getSHA(String password) function

	
	private String Hashing(byte[] password) {
		
		   // Convert byte array into signum representation  
        BigInteger number = new BigInteger(1, password);  
  
        // Convert message digest into hex value  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
  
        // Pad with leading zeros 
        while (hexString.length() < 32)  
        {  
            hexString.insert(0, '0');  
        }  
  
        return hexString.toString();  
        
	} // end of String Hashing functions
	
} // end of loginDAO class
