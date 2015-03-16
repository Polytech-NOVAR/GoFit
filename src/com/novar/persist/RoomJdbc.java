package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

import com.novar.business.Room;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.LoginFailedException;
import com.novar.exception.RegisterFailedException;
import com.novar.exception.SyntaxException;
import com.novar.util.ConnectionUtil;

public class RoomJdbc extends Room{
	
	public RoomJdbc(HashMap<String,Object> data) throws FalseFieldsException
	{
		super(data);
	}
	
	public void save() throws RegisterFailedException
	{
		try 
		{
			PreparedStatement insertUser = ConnectionUtil.connection.prepareStatement("INSERT INTO Room (roomID, roomNumber, area, street, town, zipCode, country) "
																		+ "VALUES ('',?, ?, ?, ?, ?, ?);");
			insertUser.setObject(1, getNum(),Types.VARCHAR);
			insertUser.setObject(2, getArea(),Types.INTEGER);
			insertUser.setObject(3, getStreet(),Types.VARCHAR);
			insertUser.setObject(4, getTown(),Types.VARCHAR);
			insertUser.setObject(5, getZipCode(), Types.VARCHAR);
			insertUser.setObject(6, getCountry(),Types.VARCHAR);
			insertUser.executeUpdate();		
		}
		catch (SQLException e) 
		{
			String message = e.getMessage();
			if(message.endsWith("'unEmail'"))
			{
				throw new RegisterFailedException("email2");
			}
			else if (message.endsWith("'PRIMARY'"))
			{
				throw new RegisterFailedException("pseudo2");
			}
		}
	}
	
	public void load() throws LoginFailedException
	{
		PreparedStatement selectRoom;
		try 
		{
			selectRoom = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM Room "
					+ "WHERE roomID = ? ");

			selectRoom.setObject(1, getRoomID(), Types.INTEGER);
			ResultSet res = selectRoom.executeQuery();
			res.last();
			if(res.getRow() == 1)
			{
				try 
				{
					setNum(res.getString("num"));
					setArea(res.getInt("area"));
					setStreet(res.getString("street"));
					setTown(res.getString("town"));
					setZipCode(res.getString("zipCode"));
					setCountry(res.getString("country"));
				} 
				catch (SyntaxException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public void loadAll() throws LoginFailedException
	{
		PreparedStatement selectRooms;
		try 
		{
			selectRooms = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM Room ");

			ResultSet res = selectRooms.executeQuery();
			res.last();
			if(res.getRow() == 0)
				throw new LoginFailedException();
			else
			{
				try 
				{
					setNum(res.getString("num"));
					setArea(res.getInt("area"));
					setStreet(res.getString("street"));
					setTown(res.getString("town"));
					setZipCode(res.getString("zipCode"));
					setCountry(res.getString("country"));
				} 
				catch (SyntaxException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
