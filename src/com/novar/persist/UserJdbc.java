package com.novar.persist;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.novar.business.Address;
import com.novar.business.Administrator;
import com.novar.business.Manager;
import com.novar.business.Speaker;
import com.novar.business.Member;
import com.novar.business.User;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.LoginFailedException;
import com.novar.exception.SyntaxException;
import com.novar.util.ConnectionUtil;

public class UserJdbc extends User{
	
	
	public UserJdbc(HashMap<String,Object> data) throws FalseFieldsException
	{
		super(data);
	}
	
	public void save() throws SQLException
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
				getAddress().get(i).save();
				insertUserAdress.setObject(1, getAddress().get(i).getAddressID() ,Types.INTEGER);
				insertUserAdress.setObject(2, getPseudo(),Types.VARCHAR);
				insertUserAdress.executeUpdate();
			}
			
	}
	
	public void load() throws LoginFailedException
	{
		PreparedStatement selectUser;
		try 
		{
			selectUser = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM User "
					+ "WHERE pseudo = ? "
					+ "AND password = ?;");

			selectUser.setObject(1, getPseudo(), Types.VARCHAR);
			selectUser.setObject(2, getPassword(),Types.VARCHAR);
			ResultSet res = selectUser.executeQuery();
			res.last();
			if(res.getRow() == 0)
				throw new LoginFailedException();
			else
			{
				try 
				{
					setPseudo(res.getString("pseudo"));
					setPassword(res.getString("password"));
					setEmail(res.getString("email"));
					setFirstName(res.getString("firstName"));
					setLastName(res.getString("lastName"));
					setPhone(res.getString("phone"));
					
					loadAdress();
					loadRoles();
					
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

	public void loadAdress()
	{
		try {
			PreparedStatement selectAddress;
			selectAddress = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM UserAddress u, Address a "
					+ "WHERE u.addressID = a.addressID "
					+ "AND u.pseudo = ?;");
			
			selectAddress.setObject(1, getPseudo(), Types.VARCHAR);
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
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadRoles()
	{		
		try {
			PreparedStatement selectRoles;
			selectRoles = ConnectionUtil.connection.prepareStatement("SELECT * "
																	+ "FROM User u "
																	+ "LEFT JOIN Administrator a ON u.pseudo = a.pseudoAdministrator "
																	+ "LEFT JOIN Manager ma ON u.pseudo = ma.pseudoManager "
																	+ "LEFT JOIN Speaker s ON u.pseudo = s.pseudoSpeaker "
																	+ "LEFT JOIN Member m ON u.pseudo = m.pseudoMember "
																	+ "WHERE u.pseudo = ?");
			
			selectRoles.setObject(1, this.getPseudo(), Types.VARCHAR);
			ResultSet resRoles = selectRoles.executeQuery();
			
			resRoles.next();
			if( resRoles.getString("pseudoAdministrator") != null )
				setRoles(new Administrator());
			if( resRoles.getString("pseudoManager") != null )
				setRoles(new Manager());
			if( resRoles.getString("pseudoSpeaker") != null )
				setRoles(new Speaker());
			if( resRoles.getString("pseudoMember") != null )
				setRoles(new Member());
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
