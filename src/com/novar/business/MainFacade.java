package com.novar.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.novar.exception.*;
import com.novar.persist.PersistKit;
import com.novar.util.StringUtil;

public class MainFacade
{
	private User theUser = null;
	private PersistKit kit = null;
	
	public MainFacade(PersistKit kit)
	{
		this.kit = kit;
	}
	
	public User getUser()
	{
		return this.theUser;
	}
	
	// Disconnected methods
	public void register(HashMap<String,Object> dataUser) throws RegisterFailedException, FalseFieldsException
	{
		User userInRegistration = kit.makeUser(dataUser);
		userInRegistration.save();
	}
	
	public void login(HashMap<String,Object> dataUser) throws LoginFailedException, FalseFieldsException
	{
		if (theUser == null)
		{
			User userInLogin = kit.makeUser(dataUser);
			userInLogin.load();
			theUser = userInLogin;
		}
	}
	
	// Profile methods
	public void updateTheUserProfile(HashMap<String,Object> dataUser) throws FalseFieldsException, SQLException
	{
		theUser.setAll(dataUser);
		theUser.updateProfile();
	}
	
	public void updateTheUserPassword(HashMap<String,Object> dataUser) throws FalseFieldsException, SQLException
	{
		theUser.setAll(dataUser);
		theUser.updatePassword();
	}
	
	public void deleteTheUser() throws SQLException
	{
		theUser.delete();
		//logoff
	}
}
