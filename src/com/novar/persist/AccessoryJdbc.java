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

	public AccessoryJdbc(HashMap<String,Object> data) throws FalseFieldsException
	{
		super(data);
	}
	
	public void save() throws RegisterFailedException
	{
		try 
		{
			PreparedStatement insertUser = ConnectionUtil.connection.prepareStatement("INSERT INTO User (accID, accName) "
																		+ "VALUES ('', ?);");
			insertUser.setObject(1, getName(),Types.VARCHAR);
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
					+ "FROM Accessory "
					+ "WHERE accID = ? ");

			selectRoom.setObject(1, getAccID(), Types.INTEGER);
			ResultSet res = selectRoom.executeQuery();
			res.last();
			if(res.getRow() == 1)
			{
				setName(res.getString("accName"));
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
