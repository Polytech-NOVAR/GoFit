package com.novar.persist;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.novar.business.Address;
import com.novar.business.User;
import com.novar.exception.LoginFailedException;
import com.novar.exception.RegisterFailedException;
import com.novar.exception.SyntaxException;
import com.novar.util.ConnectionUtil;

public class UserJdbc extends User{
	
	public UserJdbc(HashMap<String,Object> data) throws RegisterFailedException
	{
		super(data);
	}
	
	public UserJdbc(String pseudo, String password) throws LoginFailedException
	{
		super(pseudo,password);
		PreparedStatement selectUser;
		try 
		{
			selectUser = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM User "
					+ "WHERE pseudo = ? "
					+ "AND password = ?;");

			selectUser.setObject(1, pseudo, Types.VARCHAR);
			selectUser.setObject(2, password,Types.VARCHAR);
			ResultSet res = selectUser.executeQuery();
			res.last();
			if(res.getRow() == 0)
				throw new LoginFailedException();
			else
			{
				try 
				{
					PreparedStatement selectAddress;
					selectAddress = ConnectionUtil.connection.prepareStatement("SELECT * "
							+ "FROM UserAddress u, Address a "
							+ "WHERE u.addressID = a.addressID "
							+ "AND u.pseudo = ?;");
					
					selectAddress.setObject(1, pseudo, Types.VARCHAR);
					ResultSet resAddress = selectAddress.executeQuery();
					JdbcKit fabric = new JdbcKit();
					ArrayList<Address> adds = new ArrayList<Address>();
					
					while(resAddress.next())
					{
						HashMap<String,Object> mapAddress = new HashMap<String,Object>();
						mapAddress.put("addressID", resAddress.getInt("addressID"));
						mapAddress.put("street", resAddress.getString("street"));
						mapAddress.put("town", resAddress.getString("town"));
						mapAddress.put("zipCode", resAddress.getString("zipCode"));
						mapAddress.put("country", resAddress.getString("country"));
						AddressJdbc ad1 = fabric.makeAddress(mapAddress);
						adds.add(ad1);
					}
					this.setAddress(adds);
					this.setPseudo(res.getString("pseudo"));
					this.setPassword(res.getString("password"));
					this.setEmail(res.getString("email"));
					this.setFirstName(res.getString("firstName"));
					this.setLastName(res.getString("lastName"));
					this.setPhone(res.getString("phone"));
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
	
	public void insert() throws SQLException
	{
			//Insertion de l'utilisateur dans la table User
			PreparedStatement insertUser = ConnectionUtil.connection.prepareStatement("INSERT INTO User (pseudo,password,lastName,firstName,phone,email) "
																		+ "VALUES (?, ?, ?, ?, ?, ?);");
			insertUser.setObject(1, getPseudo(),Types.VARCHAR);
			insertUser.setObject(2, getPassword(),Types.VARCHAR);
			insertUser.setObject(3, getLastName(),Types.VARCHAR);
			insertUser.setObject(4, getFirstName(),Types.VARCHAR);
			insertUser.setObject(5, getPhone(),Types.VARCHAR);
			insertUser.setObject(6, getEmail(),Types.VARCHAR);
			insertUser.executeUpdate();
			
			//Insertion des addresses dans la table Address et des couples (Pseudo,AdressID) dans la table UserAddress
			PreparedStatement insertUserAdress = ConnectionUtil.connection.prepareStatement("INSERT INTO UserAddress (addressID,pseudo) "
					+ "VALUES (?, ?);");
			for(int i = 0; i<getAddress().size(); i++)
			{
				getAddress().get(i).insert();
				insertUserAdress.setObject(1, getAddress().get(i).getAddressID() ,Types.INTEGER);
				insertUserAdress.setObject(2, getPseudo(),Types.VARCHAR);
				insertUserAdress.executeUpdate();
			}
			
	}

}
