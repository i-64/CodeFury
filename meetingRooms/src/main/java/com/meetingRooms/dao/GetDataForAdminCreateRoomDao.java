package com.meetingRooms.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.meetingRooms.entity.AmenitiesEntity;
import com.meetingRooms.entity.MeetingTypes;

/**
 * Servlet implementation class GetDataForAdminCreateRoomDao
 */
public class GetDataForAdminCreateRoomDao implements GetDataForAdminCreateRoomDaoInterface {

	public GetDataForAdminCreateRoomDao () {
		
		try {
			
			// load driver
			
			Class.forName ( "org.apache.derby.jdbc.EmbeddedDriver" );
		
		} catch (ClassNotFoundException e ) {
			
			e.printStackTrace ();
		}
		 
	} // end of constructor
	
	
	@Override
	public List<MeetingTypes> getMeetingTypes () {
		
		
		try ( Connection con = DriverManager.getConnection ( "jdbc:derby:c:/database/meetingRoomsDB", "admin", "admin" ) ) { // get connection to database
		
			// prepare query
			
			PreparedStatement ps = con.prepareStatement ( "select * from meeting_types" );
			
			ResultSet rs = ps.executeQuery ();	// get amenities
			
			List<MeetingTypes> type_list = new ArrayList<MeetingTypes>();
			
			while ( rs.next () ) {
				
				MeetingTypes e = new MeetingTypes ();
				
				e.setID ( rs.getInt (1) );
				e.setType ( rs.getString ( 2 ) );
				e.setAmenitites ( rs.getString ( 3 ) );
				
				type_list.add (e);
			}
			
			return type_list;
			
		} catch (SQLException e ) {
			
			e.printStackTrace ();
		}		
		
		return null;
		
	} // end of getMeetingTypes
	
	
	@Override
	public List<AmenitiesEntity> getAmenities() {
		
		try ( Connection con = DriverManager.getConnection ( "jdbc:derby:c:/database/meetingRoomsDB", "admin", "admin" ) ) { // get connection to database
			
			// prepare query
			
			PreparedStatement ps = con.prepareStatement ( "select * from amenities" );
			
			ResultSet rs = ps.executeQuery ();	// get amenities
			
			List <AmenitiesEntity> amenity_list = new ArrayList <AmenitiesEntity> (); 
			
			while ( rs.next () ) {
				
				AmenitiesEntity e = new AmenitiesEntity ();
				
				e.setID ( rs.getInt (1) );
				e.setAmenity ( rs.getString ( 2 ) );
				e.setCredits( rs.getInt ( 3 ) );
				
				amenity_list.add (e);
			}
			
			return amenity_list;
			
		} catch (SQLException e ) {
			
			e.printStackTrace ();
		}
						
		return null;
	}

}

