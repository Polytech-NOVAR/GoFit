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
import com.novar.exception.LoginFailedException;
import com.novar.exception.RegisterFailedException;
import com.novar.exception.SyntaxException;
import com.novar.util.ConnectionUtil;

public class RoomJdbc extends Room{
	
	public RoomJdbc()
	{
		super();
	}
	
	public void save()
	{
		try 
		{
			PreparedStatement insertRoom = ConnectionUtil.connection.prepareStatement("INSERT INTO Room (num, area, street, town, zipCode, country) "
																		+ "VALUES (?, ?, ?, ?, ?, ?);");
			insertRoom.setObject(1, getNum(),Types.VARCHAR);
			insertRoom.setObject(2, getArea(),Types.INTEGER);
			insertRoom.setObject(3, getStreet(),Types.VARCHAR);
			insertRoom.setObject(4, getTown(),Types.VARCHAR);
			insertRoom.setObject(5, getZipCode(), Types.VARCHAR);
			insertRoom.setObject(6, getCountry(),Types.VARCHAR);
			insertRoom.executeUpdate();		
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void load()
	{
		PreparedStatement selectRoom;
		PreparedStatement selectAccessories;
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
				
				/*selectAccessories = ConnectionUtil.connection.prepareStatement("SELECT * "
						+ "FROM Have "
						+ "WHERE roomID = ? ");
				selectAccessories.setObject(1, getRoomID(), Types.INTEGER);
				ResultSet res2 = selectAccessories.executeQuery();
				res2.first();
				if(res2.getRow() > 0)
				{
					do
					{
						Accessory acc = new AccessoryJdbc();
						acc.setAccID(res2.getInt("accID"));
						acc.load();
						
						Have have = new HaveJdbc();
						have.setRoom(this);
						have.setAcc(acc);
						have.setQuantity(res2.getInt("quantity"));
						this.accessories.add(have);
						
					}while(res2.next());
					
					//pour chaque ligne, créer un accessoire, créer un have et le remplir avec la quantité et le mettre dans room
				}*/
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}	
	}
	
	public void loadAll() throws LoginFailedException
	{
		PreparedStatement selectRooms;
		try 
		{
			selectRooms = ConnectionUtil.connection.prepareStatement("SELECT * FROM Room ");

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
			res.last();
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
	
	public static void main(String[] args) {
		ConnectionUtil.start();
		
		RoomJdbc room = new RoomJdbc();
		room.setRoomID(1);
		System.out.println(room);
		room.load();
		System.out.println(room);
		room.loadAccessories();
		System.out.println(room.getAccessories());
		ConnectionUtil.stop();
	}
}
