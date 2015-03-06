package com.novar.persist;

import java.util.HashMap;

import com.novar.exception.LoginFailedException;
import com.novar.persist.PersistKit;
import com.novar.persist.UserJdbc;

public class JdbcKit implements PersistKit
{

	public AddressJdbc makeAddress(HashMap<String,Object> data)
	{
		return new AddressJdbc(data);
	}
	
	public UserJdbc makeUser(HashMap<String,Object> data)
	{
		return new UserJdbc(data);
	}
	
	public UserJdbc makeUser(String pseudo, String password) throws LoginFailedException
	{
		UserJdbc user = new UserJdbc(pseudo, password);
		return user;
	}
}
