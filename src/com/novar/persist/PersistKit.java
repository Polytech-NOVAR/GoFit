package com.novar.persist;

import java.util.HashMap;

import com.novar.business.Address;
import com.novar.business.User;
import com.novar.exception.LoginFailedException;
import com.novar.exception.RegisterFailedException;

public interface PersistKit 
{
	public Address makeAddress(HashMap<String,Object> data);
	
	public User makeUser(HashMap<String,Object> data) throws RegisterFailedException;
	
	public User makeUser(String pseudo, String password) throws LoginFailedException;
}
