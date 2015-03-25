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
import com.novar.exception.SyntaxException;
import com.novar.util.ConnectionUtil;

public class RoomJdbc extends Room{
	
	public RoomJdbc()
	{
		super();
	}
	
	public RoomJdbc(HashMap<String,Object> data) throws FalseFieldsException
	{
		super(data);
	}
	
	
	public void save()
	{
		try 
		{
			PreparedStatement insertRoom = ConnectionUtil.connection.prepareStatement("INSERT INTO Room (num, area, street, town, zipCode, country) "
																		+ "VALUES (?, ?, ?, ?, ?, ?);", PreparedStatement.RETURN_GENERATED_KEYS);
			insertRoom.setObject(1, getNum(),Types.VARCHAR);
			insertRoom.setObject(2, getArea(),Types.INTEGER);
			insertRoom.setObject(3, getStreet(),Types.VARCHAR);
			insertRoom.setObject(4, getTown(),Types.VARCHAR);
			insertRoom.setObject(5, getZipCode(), Types.VARCHAR);
			insertRoom.setObject(6, getCountry(),Types.VARCHAR);
			insertRoom.executeUpdate();
			ResultSet key = insertRoom.getGeneratedKeys();
			if(key.next()){
				setRoomID(key.getInt(1));
			}
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
					e.printStackTrace();
				}
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}	
	}
	
	public void loadAccessories()
	{
		PreparedStatement selectAccessories;
		ArrayList<Have> accessories = new ArrayList<Have>();
		try 
		{
			selectAccessories = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM Have "
					+ "WHERE roomID = ? ");

			selectAccessories.setObject(1, getRoomID(), Types.INTEGER);
			ResultSet res = selectAccessories.executeQuery();
			res.first();
			if(res.getRow() > 0)
			{
				do
				{
					Accessory acc = new AccessoryJdbc();
					acc.setAccID(res.getInt("accID"));
					acc.load();
					
					Have have = new HaveJdbc();
					have.setRoom(this);
					have.setAcc(acc);
					have.setQuantity(res.getInt("quantity"));
					accessories.add(have);
					
				}while(res.next());
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setAccessories(accessories);
	}
	
	public void update(){
		PreparedStatement updateRoom;
		try {
			updateRoom = ConnectionUtil.connection.prepareStatement("UPDATE Room "
						+ "SET num = ?, area = ?, street = ?, town = ?, zipCode = ?, country = ? "
						+ "WHERE roomID = ? ");
			updateRoom.setObject(1, getNum(), Types.VARCHAR);
			updateRoom.setObject(2, getArea(), Types.INTEGER);
			updateRoom.setObject(3, getStreet(), Types.VARCHAR);
			updateRoom.setObject(4, getTown(), Types.VARCHAR);
			updateRoom.setObject(5, getZipCode(), Types.VARCHAR);
			updateRoom.setObject(6, getCountry(), Types.VARCHAR);
			updateRoom.setObject(7, getRoomID(), Types.INTEGER);
			updateRoom.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(){
		PreparedStatement deleteRoom;
		this.loadAccessories();
		for(int acci=0; acci<this.getAccessories().size(); acci++)
		{
			getAccessories().get(acci).delete();
		}
		try {
			deleteRoom = ConnectionUtil.connection.prepareStatement("DELETE FROM Room "
						+ "WHERE roomID = ? ");
			deleteRoom.setObject(1, getRoomID(), Types.INTEGER);
			deleteRoom.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addAccessory(Accessory acc, int quantity)
	{
		Have have = new HaveJdbc();
		have.setRoom(this);
		have.setAcc(acc);
		have.setQuantity(quantity);
		have.save();
		this.loadAccessories();
	}
}