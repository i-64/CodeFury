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
import com.meetingRooms.utility.ConnectionManager;

/**
 * Servlet implementation class GetDataForAdminCreateRoomDao
 * 
 * @author Sophia Tiwari
 * @author Ashutosh Danwe
 * @author Ravi Kachhadiya
 * 
 */
public class GetDataForAdminCreateRoomDao implements GetDataForAdminCreateRoomDaoInterface {
	
		// function to get meeting name status
	
	/**
	 * @param title of the meeting
	 */
	@Override
	public int getNameStatus ( String meetingName ) {
		
		PreparedStatement ps;
		Connection con=null;
		
		try  { 
			con = ConnectionManager.getConnection() ;
			
			ps = con.prepareStatement ( "select unique_name from MEETING_ROOM where unique_name = ?" );
			
			ps.setString ( 1, meetingName );
			
			ResultSet rs = ps.executeQuery ();
			
			if ( rs.next () ) {
				
				return 0; // invalid name				
			}
			
		} catch (SQLException | ClassNotFoundException e ) {
			
			e.printStackTrace ();
		}		
		finally{
			ConnectionManager.close();
		}
		
		return 1; // return on success
		
	} // end of getNameStatus function
	
	
	
	
	/**
	 * function to delete meeting room 
	 * 
	 * @param name of meeting
	 * @return if room was deleted
	 * 
	 */
	@Override
	public int deleteRoom ( String meetingName ) {
	
		PreparedStatement ps;
		Connection con;
		try  { 
			con = ConnectionManager.getConnection() ; // get connection to database

			ps = con.prepareStatement ( "delete from ROOM_AMENITIES where meeting_room_id = ?" );
			
			ps.setString ( 1, meetingName );
			
			if ( !( ps.executeUpdate () > 0) ) {
				
				return 0; // unsuccessful insertion				
			}
			
			ps = con.prepareStatement ( "delete from MEETING_ROOM where unique_name = ?" );
			
			ps.setString ( 1, meetingName );
			
			if ( !( ps.executeUpdate () > 0) ) {
				
				return 0; // unsuccessful insertion				
			}
			
			return 1; // successful deletion
			
		} catch (SQLException | ClassNotFoundException e ) {
			
			e.printStackTrace ();
		}
		finally {
			ConnectionManager.close();
		}
		
		
		return 0;
		
	} // end of deleteRoom function
	
	
	/**
	 * editing room functionality
	 * 
	 * @param meeting room object
	 * @return if room was edited successfully
	 */
	@Override
	public int editRoom ( MeetingRoomEntity entity ) {
		
		PreparedStatement ps;
		Connection con = null;
		try  { // get connection to database
			con = ConnectionManager.getConnection();
			int per_hour_cost = 0; // to calculate per hour cost
			
				// edit existing meeting room details
			
			ps = con.prepareStatement ( "update MEETING_ROOM set seating_capacity = ? where unique_name = ?" );
			
			ps.setInt ( 1, entity.getSeatingCapacity() );
			ps.setString ( 2, entity.getUniqueName () );
			
			if ( !( ps.executeUpdate () > 0) ) {
				
				return 0; // unsuccessful insertion				
			}
			
				// prepare query to delete data from room_amenities
			
			ps = con.prepareStatement ( "delete from ROOM_AMENITIES where meeting_room_id = ?" );
			
			ps.setString ( 1, entity.getUniqueName () );
			
			if ( !( ps.executeUpdate () > 0) ) {
				
				return 0; // successful insertion				
			}

				// insert new amenities
			
			for ( String temp : entity.getAmenityList() ) {
				
					// prepare query to enter data into ROOM AMENITIES database
				
				ps = con.prepareStatement ( "insert into ROOM_AMENITIES values (?, ?)" );
				
				ps.setString (1, entity.getUniqueName ());
				ps.setString (2, temp);	
				
				if ( !( ps.executeUpdate () > 0 ) ) {
					
					return 0; // return 0 if unsuccessful insertion
				}
			}
			
			
				// get cost from table for new amenities
			
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
				
				return 1; // return on success
			}
			
		} catch (SQLException | ClassNotFoundException e ) {
			
			e.printStackTrace ();
		}
		finally {
			ConnectionManager.close();
		}
		return 0;
	
	} // end of editRoom Function
	
	
	/**
	 * get the info for editing room
	 * 
	 * @param name if the meeting
	 * @return meeting room object 
	 * 
	 */
	@Override
	public MeetingRoomEntity getEditRoomInfo ( String meetingName ) {
		
		MeetingRoomEntity info = new MeetingRoomEntity ();		
		Connection con = null;
		try  { 
			con = ConnectionManager.getConnection() ;// get connection to database
				// create query for fetching room info
			
			PreparedStatement ps=con.prepareStatement("select * from meeting_room where unique_name=?");
			
			ps.setString(1, meetingName);
			
			ResultSet rs = ps.executeQuery();
			
			if ( rs.next () ) {
				
				info.setUniqueName ( rs.getString (1) );
				
				info.setSeatingCapacity ( rs.getInt (2) );
				
				info.setPerHourCost ( rs.getInt ( 3 ) );
				
				info.setTotal_meetings_conducted ( rs.getInt ( 4 ) );
				
				info.setCreatedBy ( rs.getString ( 5 ) );
				
			} else {
				
				return null;	// if unsuccessful
			}			
			
				// create query for fetching room amenities info
			
			ps=con.prepareStatement("select amenity_id from room_amenities where meeting_room_id=?");
			
			ps.setString(1, meetingName);
			
			rs = ps.executeQuery();
			
			List <String> list = new ArrayList <String> ();
			
			while ( rs.next () ) {
				
				list.add ( rs.getString ( 1 ) );
			}
			
			info.setAmenityList ( list ); 			
			
			return info;
			
		} catch (SQLException | ClassNotFoundException ee) {
			
			ee.printStackTrace();
		}
		finally {
			ConnectionManager.close();
		}
		
		return null;
		
	} // end of getEditRoomInfo function
	
	
	
    /**
     * get the list of meeting rooms
     *
     * @param the current user
     * @return list of meeting rooms
     * 
     */
    @Override
    public List<MeetingRoomEntity> getMeetingRooms(String username){
    	
    	List<MeetingRoomEntity> meetingRoomList= new ArrayList<MeetingRoomEntity>();
    	Connection con = null;
   
    	try {
			con = ConnectionManager.getConnection();
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
		
		catch(SQLException | ClassNotFoundException ee) {
			ee.printStackTrace();
		}
		finally {
			ConnectionManager.close();
		}

    	return meetingRoomList;
    	    	
    }
    

    /**
     * creates a new room admin feature
     * 
     * @param the meeting room object
     * @return if the room was successfully created
     * 
     */
    @Override
	public int createRoom ( MeetingRoomEntity entity ) {
		
		PreparedStatement ps;
		Connection con = null;
		try  { 
			con = ConnectionManager.getConnection(); // get connection to database
			int per_hour_cost = 0; // to calculate per hour cost			
			
				// prepare query to enter data into meeting_room database
			
			ps = con.prepareStatement ( "insert into MEETING_ROOM (unique_name, seating_capacity, per_hour_cost, total_meetings_conducted, created_by) values (?,?,?,?,?)" );
			
			ps.setString ( 1, entity.getUniqueName () );
			ps.setInt ( 2, entity.getSeatingCapacity() );
			
			ps.setInt ( 3, per_hour_cost );
			ps.setInt ( 4, 0 );
			
			ps.setString ( 5, entity.getCreatedBy () );			
			
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
			
			
		} catch (SQLException | ClassNotFoundException e ) {
			
			e.printStackTrace ();
		}
		finally {
			ConnectionManager.close();
		}
		
		return 0;
	}
	
	/**
	 * get the list of meeting types
	 * 
	 * @return list of meeting types
	 * 
	 */
	@Override
	public List<MeetingTypes> getMeetingTypes () {
		
		Connection con  = null;
		try  { // get connection to database
			con   = ConnectionManager.getConnection() ;
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
			
		} catch (SQLException | ClassNotFoundException e ) {
			
			e.printStackTrace ();
		}	
		finally {
			ConnectionManager.close();
		}	
		
		return null;
		
	} // end of getMeetingTypes
	
	
	/**
	 * get the list of amenities
	 * 
	 * @return the list of amenities
	 * 
	 */
	@Override
	public List<AmenitiesEntity> getAmenities() {
		Connection con = null;
		try  { // get connection to database
			con = ConnectionManager.getConnection();
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
			
		} catch (SQLException | ClassNotFoundException e ) {
			
			e.printStackTrace ();
		}
		finally {
			ConnectionManager.close();
		}
						
		return null;
	}

}

