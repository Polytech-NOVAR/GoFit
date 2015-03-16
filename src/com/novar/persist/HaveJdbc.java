package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

import com.novar.business.Accessory;
import com.novar.business.Have;
import com.novar.business.Room;
import com.novar.exception.FalseFieldsException;
import com.novar.util.ConnectionUtil;

public class HaveJdbc extends Have{

	public HaveJdbc()
	{
		super();
	}
	
	public void save()
	{
		try 
		{
			PreparedStatement insertHave = ConnectionUtil.connection.prepareStatement("INSERT INTO Have (accID, roomID, quantity) "
																		+ "VALUES (?, ?, ?);");
			insertHave.setObject(1, getAcc().getAccID(), Types.VARCHAR);
			insertHave.setObject(2, getRoom().getRoomID(), Types.VARCHAR);
			insertHave.setObject(3, getQuantity(), Types.VARCHAR);
			insertHave.executeUpdate();		
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void load()
	{
		PreparedStatement selectHave;
		try 
		{
			selectHave = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM Have "
					+ "WHERE accID = ? "
					+ "AND roomID = ? ");

			selectHave.setObject(1, getAcc().getAccID(), Types.INTEGER);
			selectHave.setObject(2, getRoom().getRoomID(), Types.INTEGER);
			ResultSet res = selectHave.executeQuery();
			res.last();
			if(res.getRow() == 1)
			{
				setQuantity(res.getInt("quantity"));
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
