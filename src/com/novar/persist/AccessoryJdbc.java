package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

import com.novar.business.Accessory;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.LoginFailedException;
import com.novar.exception.RegisterFailedException;
import com.novar.exception.SyntaxException;
import com.novar.util.ConnectionUtil;

public class AccessoryJdbc extends Accessory{

	public AccessoryJdbc()
	{
		super();
	}
	
	public void save()
	{
		try 
		{
			PreparedStatement insertAccessory = ConnectionUtil.connection.prepareStatement("INSERT INTO Accessory (name) "
																		+ "VALUES (?);");
			insertAccessory.setObject(1, getName(), Types.VARCHAR);
			insertAccessory.executeUpdate();		
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void load()
	{
		PreparedStatement selectRoom;
		try 
		{
			selectRoom = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM Accessory "
					+ "WHERE accID = ? ");

			selectRoom.setObject(1, getAccID(), Types.INTEGER);
			ResultSet res = selectRoom.executeQuery();
			res.last();
			if(res.getRow() == 1)
			{
				setName(res.getString("name"));
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadAll()
	{
		PreparedStatement selectRoom;
		try 
		{
			selectRoom = ConnectionUtil.connection.prepareStatement("SELECT * FROM Accessory ");

			selectRoom.setObject(1, getAccID(), Types.INTEGER);
			ResultSet res = selectRoom.executeQuery();
			res.last();
			if(res.getRow() == 1)
			{
				setName(res.getString("name"));
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(){
		
	}
	
	public void delete(){
		
	}
}
