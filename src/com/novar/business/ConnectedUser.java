package com.novar.business;

import java.util.HashMap;

import com.novar.exception.FalseFieldsException;
import com.novar.exception.LoginFailedException;

public class ConnectedUser extends User
{
	private static ConnectedUser theConnectedUser = null;
	
	private ConnectedUser(HashMap<String,Object> data) throws FalseFieldsException
	{
		super(data);
	}
	
	public static ConnectedUser create(HashMap<String,Object> data)
	{
		if (theConnectedUser == null)
		{
			try {
				theConnectedUser = new ConnectedUser(data);
			} catch (FalseFieldsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return theConnectedUser;
	}

	@Override
	public void load() throws LoginFailedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
