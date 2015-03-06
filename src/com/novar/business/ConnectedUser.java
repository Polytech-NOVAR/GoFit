package com.novar.business;

import java.util.HashMap;

public class ConnectedUser extends User
{
	private static ConnectedUser theConnectedUser = null;
	
	public static ConnectedUser create(HashMap<String,Object> data)
	{
		if (theConnectedUser == null)
		{
			theConnectedUser = new ConnectedUser();
			theConnectedUser.setUp(data);
		}
		
		return theConnectedUser;
	}
	
}
