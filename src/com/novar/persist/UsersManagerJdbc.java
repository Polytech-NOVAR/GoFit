package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

import com.novar.business.User;
import com.novar.business.UsersManager;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.RegisterFailedException;
import com.novar.util.ConnectionUtil;

/**
 * This class allow us to manage users in the persistence. In a database SQL more precisely.
 * @author Valentin BERCOT-DUFLOS
 * @see JdbcKit
 * @see UsersManager
 */
public class UsersManagerJdbc extends UsersManager
{
	/**
	 * Method use to load users from database
	 * @return an array list of users from the database
	 */
	public ArrayList<User> loadUsers() throws SQLException, FalseFieldsException
	{
		PreparedStatement selectUsers;
		
		selectUsers = ConnectionUtil.connection.prepareStatement("SELECT * "
				+ "FROM User;");
		ResultSet resUsers = selectUsers.executeQuery();
		ArrayList<User> users = new ArrayList<User>();
		
		while(resUsers.next())
		{
			HashMap<String, Object> mapUsers = new HashMap<String, Object>();
			mapUsers.put("pseudo", resUsers.getObject("pseudo"));
			mapUsers.put("firstName", resUsers.getObject("firstName"));
			mapUsers.put("lastName", resUsers.getObject("lastName"));
			mapUsers.put("phone", resUsers.getObject("phone"));
			mapUsers.put("email", resUsers.getObject("email"));
			mapUsers.put("street", resUsers.getObject("street"));
			mapUsers.put("town", resUsers.getObject("town"));
			mapUsers.put("zipCode", resUsers.getObject("zipCode"));
			mapUsers.put("country", resUsers.getObject("country"));
			
			users.add(new UserJdbc(mapUsers));
		}
		
		return users;
	}
	
	/**
	 * Method use to save an user in database
	 */
	public void save(User user) throws SQLException, RegisterFailedException
	{
		PreparedStatement insert;

		insert = ConnectionUtil.connection.prepareStatement("INSERT INTO User (pseudo, password, lastName, firstName, phone, email, street, town, zipCode, country) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		insert.setObject(1, user.getPseudo(),Types.VARCHAR);
		insert.setObject(2, user.getPassword(),Types.VARCHAR);
		insert.setObject(3, user.getLastName(),Types.VARCHAR);
		insert.setObject(4, user.getFirstName(),Types.VARCHAR);
		insert.setObject(5, user.getPhone(),Types.VARCHAR);
		insert.setObject(6, user.getEmail(),Types.VARCHAR);
		insert.setObject(7, user.getStreet(),Types.VARCHAR);
		insert.setObject(8, user.getTown(),Types.VARCHAR);
		insert.setObject(9, user.getZipCode(), Types.VARCHAR);
		insert.setObject(10, user.getCountry(),Types.VARCHAR);
		insert.executeUpdate();	
	}
	
	/**
	 * Method use to update an user in database
	 */
	public void update(User user) throws SQLException
	{
		PreparedStatement update;
		update = ConnectionUtil.connection.prepareStatement("UPDATE User "
				+ "SET "
				+ "lastName = ?,"
				+ "firstName = ?,"
				+ "phone = ?,"
				+ "email = ?,"
				+ "street = ?,"
				+ "town = ?,"
				+ "zipCode = ?,"
				+ "country = ? "
				+ "WHERE pseudo = ?;");
		
		update.setObject(1, user.getLastName(),Types.VARCHAR);
		update.setObject(2, user.getFirstName(),Types.VARCHAR);
		update.setObject(3, user.getPhone(),Types.VARCHAR);
		update.setObject(4, user.getEmail(),Types.VARCHAR);
		update.setObject(5, user.getStreet(),Types.VARCHAR);
		update.setObject(6, user.getTown(),Types.VARCHAR);
		update.setObject(7, user.getZipCode(), Types.VARCHAR);
		update.setObject(8, user.getCountry(),Types.VARCHAR);
		update.setObject(9, user.getPseudo(), Types.VARCHAR);
		update.executeUpdate();
	}
	
	/**
	 * Method use to delete an user in database
	 */
	public void delete(User user) throws SQLException
	{
		PreparedStatement delete;
		delete = ConnectionUtil.connection.prepareStatement("DELETE FROM User "
				+ "WHERE pseudo = ?;");
		delete.setObject(1, user.getPseudo(), Types.VARCHAR);
		delete.executeUpdate();
	}
}
