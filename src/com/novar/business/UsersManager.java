package com.novar.business;

import java.sql.SQLException;
import java.util.ArrayList;

import com.novar.exception.FalseFieldsException;
import com.novar.exception.RegisterFailedException;

public abstract class UsersManager
{
	public abstract ArrayList<User>loadUsers() throws SQLException, FalseFieldsException;
	public abstract void save(User user) throws SQLException, RegisterFailedException;
	public abstract void update(User user) throws SQLException;
	public abstract void delete(User user) throws SQLException;
}
