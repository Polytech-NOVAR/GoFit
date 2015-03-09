package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashMap;

import com.novar.business.Address;
import com.novar.util.ConnectionUtil;

public class AddressJdbc extends Address
{
	public AddressJdbc(HashMap<String,Object> data)
	{
		super(data);
	}
	
	public void save()
	{
		try {
			PreparedStatement insertQuery = ConnectionUtil.connection.prepareStatement("INSERT INTO Address (street, town, zipCode, country) "
																		+ "VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			insertQuery.setObject(1, getStreet(),Types.VARCHAR);
			insertQuery.setObject(2, getTown(),Types.VARCHAR);
			insertQuery.setObject(3, getZipCode(), Types.VARCHAR);
			insertQuery.setObject(4, getCountry(),Types.VARCHAR);
			insertQuery.executeUpdate();
			ResultSet key = insertQuery.getGeneratedKeys();
            if (key.next())
            	setAddressID(key.getInt(1));
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
