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

public class AccessoryJdbc extends Accessory{

	/**
	 * This is the default constructor of an AccessoryJdbc. 
	 * It is used to make an empty one.
	 */
	public AccessoryJdbc()
	{
		super();
	}
	
	/**
	 * This is the constructor of an AccessoryJdbc. 
	 * @param data, an HashMap
	 * It will create a new empty Accessory and it will use all the setter of Accessory present in the HashMap.
	 */
	public AccessoryJdbc(HashMap<String,Object> data)
	{
		super(data);
	}
	
	/**
	 * Save the AccessoryJdbc into the DB
	 */
	public void save()
	{
		try 
		{
			PreparedStatement insertAccessory = ConnectionUtil.connection.prepareStatement("INSERT INTO Accessory (name) "
																		+ "VALUES (?);", PreparedStatement.RETURN_GENERATED_KEYS);
			insertAccessory.setObject(1, getName(), Types.VARCHAR);
			insertAccessory.executeUpdate();
			ResultSet key = insertAccessory.getGeneratedKeys();
			if(key.next()){
				setAccID(key.getInt(1));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Load the attributes of an AccessoryJdbc from the DB
	 */
	public void load()
	{
		PreparedStatement selectAccessory;
		try 
		{
			selectAccessory = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM Accessory "
					+ "WHERE accID = ? ");

			selectAccessory.setObject(1, getAccID(), Types.INTEGER);
			ResultSet res = selectAccessory.executeQuery();
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
	
	/**
	 * Load the Have, all the rooms which have the AccessoryJdbc and the quantity of it they all have, from the DB
	 */
	public void loadRooms()
	{
		PreparedStatement selectRooms;
		ArrayList<Have> rooms = new ArrayList<Have>();
		try 
		{
			selectRooms = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM Have "
					+ "WHERE accID = ? ");

			selectRooms.setObject(1, getAccID(), Types.INTEGER);
			ResultSet res = selectRooms.executeQuery();
			res.first();
			if(res.getRow() > 0)
			{
				do
				{
					Room room = new RoomJdbc();
					room.setRoomID(res.getInt("roomID"));
					room.load();
					
					Have have = new HaveJdbc();
					have.setAcc(this);
					have.setRoom(room);
					have.setQuantity(res.getInt("quantity"));
					rooms.add(have);
					
				}while(res.next());
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setRooms(rooms);
	}
	
	/**
	 * Update the AccessoryJdbc in the DB
	 */
	public void update(){
		PreparedStatement updateAccessory;
		try {
			updateAccessory = ConnectionUtil.connection.prepareStatement("UPDATE Accessory "
						+ "SET name = ? "
						+ "WHERE accID = ? ");
			updateAccessory.setObject(1, getName(), Types.VARCHAR);
			updateAccessory.setObject(2, getAccID(), Types.INTEGER);
			updateAccessory.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete the AccessoryJdbc from the DB
	 */
	public void delete(){
		PreparedStatement deleteAccessory;
		this.loadRooms();
		for(int roomi=0; roomi<this.getRooms().size(); roomi++)
		{
			getRooms().get(roomi).delete();
		}
		try {
			deleteAccessory = ConnectionUtil.connection.prepareStatement("DELETE FROM Accessory "
						+ "WHERE accID = ? ");
			deleteAccessory.setObject(1, getAccID(), Types.INTEGER);
			deleteAccessory.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
