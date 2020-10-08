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
import com.meetingRooms.entity.MeetingRoomEntity;
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
    public List<MeetingRoomEntity> getMeetingRooms(String username){
    	
    	List<MeetingRoomEntity> meetingRoomList= new ArrayList<MeetingRoomEntity>();
    	
   
    	try ( Connection con = DriverManager.getConnection ( "jdbc:derby:c:/database/meetingRoomsDB", "admin", "admin" ) ) // get connection to database

		{
			PreparedStatement ps=con.prepareStatement("select * from meeting_room where created_by=?"); 
			
			ps.setString(1, username);
			
			ResultSet res = ps.executeQuery();
       
			while(res.next()) {
			
				MeetingRoomEntity meetingRoom=new MeetingRoomEntity ();
				
				meetingRoom.setUniqueName(res.getString(1));
				
				meetingRoom.setSeatingCapacity(res.getInt(2));
				
				meetingRoom.setPerHourCost(res.getInt(3));
				
				meetingRoom.setTotal_meetings_conducted(res.getInt(4));
			
				meetingRoomList.add(meetingRoom);
			}
	
		}
		
		catch(SQLException ee) {
			ee.printStackTrace();
		}

    	return meetingRoomList;
    	    	
    }
    
    
    
	@Override
	public int createRoom ( MeetingRoomEntity entity ) {
		
		PreparedStatement ps;
		
		try ( Connection con = DriverManager.getConnection ( "jdbc:derby:c:/database/meetingRoomsDB", "admin", "admin" ) ) { // get connection to database
			
			int per_hour_cost = 0; // to calculate per hour cost			
			
				// prepare query to enter data into meeting_room database
			
			ps = con.prepareStatement ( "insert into MEETING_ROOM (unique_name, seating_capacity, per_hour_cost, total_meetings_conducted, created_by) values (?,?,?,?,?)" );
			
			ps.setString ( 1, entity.getUniqueName () );
			ps.setInt ( 2, entity.getSeatingCapacity() );
			
			ps.setInt ( 3, per_hour_cost );
			ps.setInt ( 4, 0 );
			
			ps.setString ( 5, entity.getCreated_by () );			
			
			if ( !( ps.executeUpdate () > 0) ) {
			
				return 0; // successful insertion				
			}
			
				// insert into room_amenities all amenities
			
			for ( String temp : entity.getAmenityList() ) {
				
					// prepare query to enter data into ROOM AMENITIES database
				
				ps = con.prepareStatement ( "insert into ROOM_AMENITIES values (?, ?)" );
				
				ps.setString (1, entity.getUniqueName ());
				ps.setString (2, temp);	
				
				if ( !( ps.executeUpdate () > 0 ) ) {
					
					return 0; // return 0 if unsuccessful insertion
				}
			}
			
				// get cost from table
			
			int size = entity.getAmenityList().size ();
			size--;
			
			String query = "select SUM(credit) from amenities where ";
			
			for ( String temp : entity.getAmenityList() ) {
				
				query = query + "id = " + temp;
				
				if ( size != 0 ) {
				
					query = query + " or ";
					size--;
				}
			}
			
			ps = con.prepareStatement ( query );
			
			ResultSet rs = ps.executeQuery ();	// get cost
			
			if ( rs.next () ) {
				
				per_hour_cost = rs.getInt ( 1 );
				
			} else {
				
				return 0;
			}
			
			if ( entity.getSeatingCapacity () > 5 && entity.getSeatingCapacity () <= 10 ) {
				
				per_hour_cost += 10;
			
			} else if ( entity.getSeatingCapacity () > 10 ) {
				
				per_hour_cost += 20;
			}
			
				// 	prepare query to enter data into ROOM AMENITIES database
			
			ps = con.prepareStatement ( "update MEETING_ROOM set per_hour_cost = " + per_hour_cost + "  where unique_name = '" + entity.getUniqueName () + "'" );
		
			if ( ps.executeUpdate () > 0 ) {
				
				return 1;
			}
			
			
		} catch (SQLException e ) {
			
			e.printStackTrace ();
		}
		
		return 0;
	}
	
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

