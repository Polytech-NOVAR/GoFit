package com.novar.business;

import java.sql.SQLException;
import java.util.ArrayList;

import com.novar.exception.FalseFieldsException;
import com.novar.exception.RegisterFailedException;
import com.novar.persist.PersistKit;
import com.novar.persist.UserJdbc;
import com.novar.persist.UsersManagerJdbc;

/**
 * This class is the users facade and allow us to manage users.
 * @author Valentin BERCOT-DUFLOS
 * @see MainFacade
 * @see UsersManager
 */
public class UsersFacade
{
	private PersistKit kit;
	private UsersManager manager;
	;

	public UsersFacade(PersistKit kit)
	{
		this.kit = kit;
		this.manager = kit.makeUsersManager();
	}
	
	/**
	 * Method use to return all users
	 * @return an array list of users
	 * @throws SQLException
	 * @throws FalseFieldsException
	 */
	public ArrayList<User> getUsers() throws SQLException, FalseFieldsException
	{
		return manager.loadUsers();
	}
	
	/**
	 * Method use to add a user
	 * @param an user to add
	 * @throws SQLException
	 * @throws RegisterFailedException
	 */
	public void add(User user) throws SQLException, RegisterFailedException
	{
		manager.save(user);
	}
	
	/**
	 * Mthod use to update an user
	 * @param an user to update
	 * @throws SQLException
	 */
	public void update(User user) throws SQLException
	{
		manager.update(user);
	}
	
	/**
	 * Method use to delete an user
	 * @param an user to delete
	 * @throws SQLException
	 */
	public void delete(User user) throws SQLException
	{
		manager.delete(user);
	}
}
