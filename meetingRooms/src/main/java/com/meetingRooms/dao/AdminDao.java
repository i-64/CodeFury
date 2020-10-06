package com.meetingRooms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.meetingRooms.entity.AmenitiesEntity;
import com.meetingRooms.entity.MeetingRoomEntity;
import com.meetingRooms.entity.loginUserEntity;

public class AdminDao implements AdminDaoInterface{

	private Connection con;
	public AdminDao() {
		
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con=DriverManager.getConnection("jdbc:derby:c:/database/meetingRoomsDB;create=true","admin","admin");
			
		}
		catch(ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public loginUserEntity fetchuserDao(loginUserEntity user) {
		loginUserEntity user1 = new loginUserEntity();
		try {
			PreparedStatement ps=con.prepareStatement("select * from Users where user_id=?");
			ps.setString(1, user.getUser_id());
			
			ResultSet res=ps.executeQuery();
			if(res.next()) {
				user1.setName(res.getString(3));
				user1.setEmail(res.getString(4));
				user1.setPhone(res.getString(5));
				
			}
	
		}
		catch(SQLException ee) {
			ee.printStackTrace();
		}
		
		return user1;
	}

//----------------------------------------------CREATE ROOM----------------------------------------------------------------

		@Override
		public int createRoomDao(AmenitiesEntity amenitiesEntity, loginUserEntity user) {
			int i = 0 , j=0 , perHourCost=0 , seatingCapacity ;
			try {
				
				PreparedStatement ps=con.prepareStatement("insert into meeting_room values(?,?,?,?,?)");
				
				ps.setString(1, amenitiesEntity.getUniqueName());
				ps.setInt(2, amenitiesEntity.getSeatingCapacity());
				ps.setInt(3, perHourCost);    
				ps.setInt(4, 0); ////number of meetings conducted update later   
				ps.setString(5, user.getUser_id());
				
				 i=ps.executeUpdate();   // 1 if added 
				  
				 PreparedStatement ps1=con.prepareStatement("select id from amenities where name=?"); 
				 
				 if(amenitiesEntity.getProjector()!=null)    //////If projector check box is selected
				 {	
					 perHourCost+=5;
					
					 ps1.setString(1, amenitiesEntity.getProjector());
					 ResultSet res = ps1.executeQuery();
			        
			        if(res.next())
			        {
			        	amenitiesEntity.setID(res.getInt(1));
			    	 }
			       
			        PreparedStatement ps2=con.prepareStatement("insert into room_amenities values (?,?)");
					ps2.setString(1, amenitiesEntity.getUniqueName());
					ps2.setInt(2, amenitiesEntity.getID());
					
					 j=ps2.executeUpdate();     // 1 if executed
				  }
				 
				 if(amenitiesEntity.getWiFiConnection()!=null)   //////If wi-fi connection check box is selected
				 {  
					 perHourCost+=10;
					 
					 ps1.setString(1, amenitiesEntity.getWiFiConnection());
					 ResultSet res = ps1.executeQuery();
			        
			        if(res.next())
			        {
			        	amenitiesEntity.setID(res.getInt(1));
			    	 }
			       
			        PreparedStatement ps2=con.prepareStatement("insert into room_amenities values (?,?)");
					ps2.setString(1, amenitiesEntity.getUniqueName());
					ps2.setInt(2, amenitiesEntity.getID());
					
					 j=ps2.executeUpdate();     // 1 if executed
				  }
				 
				 if(amenitiesEntity.getConferenceCallFacility()!=null)
				 {   
					 perHourCost+=15;
					 ps1.setString(1, amenitiesEntity.getConferenceCallFacility());
					 ResultSet res = ps1.executeQuery();
			        
			        if(res.next())
			        {
			        	amenitiesEntity.setID(res.getInt(1));
			    	 }
			       
			        PreparedStatement ps2=con.prepareStatement("insert into room_amenities values (?,?)");
					ps2.setString(1, amenitiesEntity.getUniqueName());
					ps2.setInt(2, amenitiesEntity.getID());
					
					 j=ps2.executeUpdate();     // 1 if executed
				  }
				 
				 if(amenitiesEntity.getWhiteBoard()!=null)
				 {
					 perHourCost+=5;
					 ps1.setString(1, amenitiesEntity.getWhiteBoard());
					 ResultSet res = ps1.executeQuery();
			        
			        if(res.next())
			        {
			        	amenitiesEntity.setID(res.getInt(1));
			    	 }
			       
			        PreparedStatement ps2=con.prepareStatement("insert into room_amenities values (?,?)");
					ps2.setString(1, amenitiesEntity.getUniqueName());
					ps2.setInt(2, amenitiesEntity.getID());
					
					 j=ps2.executeUpdate();     // 1 if executed
				  }
				 
				 if(amenitiesEntity.getWaterDispenser()!=null)
				 {
					 perHourCost+=5;
					 ps1.setString(1, amenitiesEntity.getWaterDispenser());
					 ResultSet res = ps1.executeQuery();
			        
			        if(res.next())
			        {
			        	amenitiesEntity.setID(res.getInt(1));
			    	 }
			       
			        PreparedStatement ps2=con.prepareStatement("insert into room_amenities values (?,?)");
					ps2.setString(1, amenitiesEntity.getUniqueName());
					ps2.setInt(2, amenitiesEntity.getID());
					
					 j=ps2.executeUpdate();     // 1 if executed
				  }
				 
				 if(amenitiesEntity.getTV()!=null)
				 {
					 perHourCost+=10;
					 
					 ps1.setString(1, amenitiesEntity.getTV());
					 ResultSet res = ps1.executeQuery();
			        
			        if(res.next())
			        {
			        	amenitiesEntity.setID(res.getInt(1));
			    	 }
			       
			        PreparedStatement ps2=con.prepareStatement("insert into room_amenities values (?,?)");
					ps2.setString(1, amenitiesEntity.getUniqueName());
					ps2.setInt(2, amenitiesEntity.getID());
					
					 j=ps2.executeUpdate();     // 1 if executed
				  }
				 
				 if(amenitiesEntity.getCoffeeMachine()!=null)
				 {
					 perHourCost+=10;
					 
					 ps1.setString(1, amenitiesEntity.getCoffeeMachine());
					 ResultSet res = ps1.executeQuery();
			        
			        if(res.next())
			        {
			        	amenitiesEntity.setID(res.getInt(1));
			    	 }
			       
			        PreparedStatement ps2=con.prepareStatement("insert into room_amenities values (?,?)");
					ps2.setString(1, amenitiesEntity.getUniqueName());
					ps2.setInt(2, amenitiesEntity.getID());
					
					 j=ps2.executeUpdate();     // 1 if executed
				  }
				 
				 
                 ///Calculating seatingCapacity per Hour Cost
				 seatingCapacity = amenitiesEntity.getSeatingCapacity();
                  if(seatingCapacity <= 5)
                  {  
                	  perHourCost+=0;
                  }
                  
                 if(seatingCapacity > 5 && seatingCapacity <= 10 )
                 { 
                	 perHourCost+=10; 
                 }
                 
                 if(seatingCapacity >  10)
                  { 
                	 perHourCost+=20; 
                  }
                 
                 
               PreparedStatement ps3=con.prepareStatement("update meeting_room set per_hour_cost=? where unique_name=?");
 				ps3.setInt(1, perHourCost);    
 				ps3.setString(2, amenitiesEntity.getUniqueName());
 				i=ps3.executeUpdate();   // 1 if executed correctly
				 
			}
			
			catch(SQLException ee) {
				ee.printStackTrace();
			}
			
			  return (i&j);
}


	@Override
	public List<MeetingRoomEntity> listRoomsAdminDao(loginUserEntity user) {
			
			List<MeetingRoomEntity> list=new ArrayList<MeetingRoomEntity>();
			   
			try
			{
				PreparedStatement ps=con.prepareStatement("select * from meeting_room where created_by=?"); 
		        ps.setString(1, user.getUser_id());
		        ResultSet res = ps.executeQuery();
		       
				while(res.next()) {
					MeetingRoomEntity meetingRoom=new MeetingRoomEntity ();
					meetingRoom.setUniqueName(res.getString(1));
					meetingRoom.setSeatingCapacity(res.getInt(2));
					meetingRoom.setPerHourCost(res.getInt(3));
					meetingRoom.setTotal_meetings_conducted(res.getInt(4));
					
					list.add(meetingRoom);
				}
			
			}
		   
			catch(SQLException ee) {
				ee.printStackTrace();
			}
			
		return list;
			
			}
	
	

}
