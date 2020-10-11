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

import com.meetingRooms.entity.loginUserEntity;
import com.meetingRooms.utility.ConnectionManager;

import com.meetingRooms.utility.DateTimeManipulation;

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
	
	
	@Override
	public loginUserEntity logInUser ( loginUserEntity user ) {
		
		try {
			
			// prepare query
			
			PreparedStatement ps = con.prepareStatement ( "select * from users where USER_ID = ? and PASSWORD = ?" );
			
			String hashpassword=Hashing(getSHA(user.getPassword ()));
			hashpassword=(hashpassword.substring(1, 26));
			
			System.out.println ( hashpassword);
			
			ps.setString ( 1, user.getUser_id () );
			//ps.setString ( 2, user.getPassword () );
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
			
		} catch ( SQLException sql ) {
			
			sql.printStackTrace ();
			
		} catch ( NoSuchAlgorithmException e ) {
			
			e.printStackTrace ();
		}
		finally {
			
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
