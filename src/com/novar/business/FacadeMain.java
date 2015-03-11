package com.novar.business;

import java.util.ArrayList;
import java.util.HashMap;

import com.novar.exception.*;
import com.novar.persist.PersistKit;
import com.novar.util.StringUtil;

public class FacadeMain
{
	private User theUser = null;
	private PersistKit kit = null;
	
	public FacadeMain(PersistKit kit)
	{
		this.kit = kit;
	}
	
	public void register(HashMap<String,Object> dataUser) throws Exception
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

}
