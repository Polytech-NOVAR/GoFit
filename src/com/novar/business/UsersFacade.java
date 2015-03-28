package com.novar.business;

import java.sql.SQLException;
import java.util.ArrayList;

import com.novar.exception.FalseFieldsException;
import com.novar.exception.RegisterFailedException;
import com.novar.persist.PersistKit;
import com.novar.persist.UserJdbc;
import com.novar.persist.UsersManagerJdbc;

public class UsersFacade
{
	private PersistKit kit;
	private UsersManager manager;
	;

	public UsersFacade(PersistKit kit)
	{
		this.kit = kit;
		this.manager = new UsersManagerJdbc();
	}
	
	public ArrayList<User> getUsers() throws SQLException, FalseFieldsException
	{
		return manager.getAllUsers();
	}
	
	public void add(User user) throws SQLException, RegisterFailedException
	{
		manager.save(user);
	}
	
	public void update(User user) throws SQLException
	{
		manager.update(user);
	}
	
	public void delete(User user) throws SQLException
	{
		manager.delete(user);
	}
}
