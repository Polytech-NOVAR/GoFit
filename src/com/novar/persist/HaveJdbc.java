package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

import com.novar.business.Accessory;
import com.novar.business.Have;
import com.novar.business.Room;
import com.novar.exception.FalseFieldsException;
import com.novar.util.ConnectionUtil;

public class HaveJdbc extends Have{

	/**
	 * This is the default constructor of a HaveJdbc. 
	 * It is used to make an empty one.
	 */
	public HaveJdbc()
	{
		super();
	}
	
	/**
	 * This is the constructor of a Have. 
	 * @param an HashMap
	 * It will create a new empty Have and it will use all the setter of Have present in the HashMap.
	 */
	public HaveJdbc(Room room, Accessory acc, int quantity)
	{
		super(room, acc, quantity);
	}
	
	/**
	 * Save the HaveJdbc into the BD
	 */
	public void save()
	{
		try 
		{
			PreparedStatement insertHave = ConnectionUtil.connection.prepareStatement("INSERT INTO Have (accID, roomID, quantity) "
																		+ "VALUES (?, ?, ?);");
			insertHave.setObject(1, getAcc().getAccID(), Types.INTEGER);
			insertHave.setObject(2, getRoom().getRoomID(), Types.INTEGER);
			insertHave.setObject(3, getQuantity(), Types.INTEGER);
			insertHave.executeUpdate();		
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Load the attributes of a HaveJdbc from the BD
	 */
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
	
	/**
	 * Update the HaveJdbc in the BD
	 */
	public void update(){
		PreparedStatement updateHave;
		try {
			updateHave = ConnectionUtil.connection.prepareStatement("UPDATE Have "
						+ "SET quantity = ? "
						+ "WHERE roomID = ? AND accID = ?");
			updateHave.setObject(1, getQuantity(), Types.INTEGER);
			updateHave.setObject(2, getRoom().getRoomID(), Types.INTEGER);
			updateHave.setObject(3, getAcc().getAccID(), Types.INTEGER);
			updateHave.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete the HaveJdbc from the BD
	 */
	public void delete(){
		PreparedStatement deleteHAve;
		try {
			deleteHAve = ConnectionUtil.connection.prepareStatement("DELETE FROM Have "
						+ "WHERE roomID = ? AND accID = ?");
			deleteHAve.setObject(1, getRoom().getRoomID(), Types.INTEGER);
			deleteHAve.setObject(2, getAcc().getAccID(), Types.INTEGER);
			deleteHAve.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
