package com.novar.business;

import java.sql.SQLException;
import java.util.ArrayList;

import com.novar.exception.FalseFieldsException;
import com.novar.exception.RegisterFailedException;
import com.novar.persist.UsersManagerJdbc;

/**
 * This abstract class allow us to manage users and only users not these sub roles.
 * <br>
 * This real class use is UsersManagerJdbc and it s use in UsersFacade
 * <br>
 * @author Valentin BERCOT-DUFLOS
 * @see UsersFacade
 * @see UsersManagerJdbc
 */
public abstract class UsersManager
{
	// Hooks
	public abstract ArrayList<User>loadUsers() throws SQLException, FalseFieldsException;
	public abstract void save(User user) throws SQLException, RegisterFailedException;
	public abstract void update(User user) throws SQLException;
	public abstract void delete(User user) throws SQLException;
}
