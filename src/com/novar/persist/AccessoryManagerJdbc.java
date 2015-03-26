package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.novar.business.Accessory;
import com.novar.business.AccessoryManager;
import com.novar.util.ConnectionUtil;

public class AccessoryManagerJdbc extends AccessoryManager{

	public AccessoryManagerJdbc()
	{
		
	}
	
	public ArrayList<Accessory> getAllAccessories() {
		PreparedStatement selectAccessories;
		ArrayList<Accessory> accessories = new ArrayList<Accessory>();
		try 
		{
			selectAccessories = ConnectionUtil.connection.prepareStatement("SELECT * FROM Accessory");

			ResultSet res = selectAccessories.executeQuery();
			res.first();
			if(res.getRow() > 0)
			{
				do
				{
					Accessory acc = new AccessoryJdbc();
					acc.setAccID(res.getInt("accID"));
					acc.load();
					
					accessories.add(acc);
					
				}while(res.next());
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return accessories;
	}
}