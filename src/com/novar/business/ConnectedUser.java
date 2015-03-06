package com.novar.business;

import java.util.HashMap;

public class ConnectedUser extends User
{
	private static ConnectedUser theConnectedUser = null;
	
	private ConnectedUser(HashMap<String,Object> data)
	{
		super(data);
	}
	
	public static ConnectedUser create(HashMap<String,Object> data)
	{
		if (theConnectedUser == null)
		{
			theConnectedUser = new ConnectedUser(data);
		}
		
		return theConnectedUser;
	}
	
}
