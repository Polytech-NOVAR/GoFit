package com.novar.persist;

import java.util.ArrayList;
import java.util.HashMap;

import com.novar.business.User;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.LoginFailedException;
import com.novar.persist.PersistKit;
import com.novar.persist.UserJdbc;

public class JdbcKit implements PersistKit
{

	public AddressJdbc makeAddress(HashMap<String,Object> data)
	{
		return new AddressJdbc(data);
	}
	
	public UserJdbc makeUser(HashMap<String,Object> data) throws FalseFieldsException
	{
		return new UserJdbc(data);
	}
	
}
