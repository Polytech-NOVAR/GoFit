package com.novar.persist;

import java.util.ArrayList;
import java.util.HashMap;

import com.novar.business.Address;
import com.novar.business.User;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.LoginFailedException;

public interface PersistKit 
{
	public Address makeAddress(HashMap<String,Object> data);
	
	public User makeUser(HashMap<String,Object> data) throws FalseFieldsException;
	
}
