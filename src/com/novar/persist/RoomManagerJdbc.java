package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.novar.business.Room;
import com.novar.business.RoomManager;
import com.novar.util.ConnectionUtil;

public class RoomManagerJdbc extends RoomManager{

	public RoomManagerJdbc()
	{
		
	}
	
	public ArrayList<Room> getAllRooms() {
		PreparedStatement selectRooms;
		ArrayList<Room> rooms = new ArrayList<Room>();
		try 
		{
			selectRooms = ConnectionUtil.connection.prepareStatement("SELECT * FROM Room");

			ResultSet res = selectRooms.executeQuery();
			res.first();
			if(res.getRow() > 0)
			{
				do
				{
					Room room = new RoomJdbc();
					room.setRoomID(res.getInt("roomID"));
					room.load();
					room.loadType();
					
					rooms.add(room);
					
				}while(res.next());
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return rooms;
	}
}
