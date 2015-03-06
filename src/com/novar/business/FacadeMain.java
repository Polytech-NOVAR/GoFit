package com.novar.business;

import com.novar.exception.LoginFailedException;
import com.novar.persist.PersistKit;
import com.novar.util.StringUtil;

public class FacadeMain {
	
	private User theUser;
	private PersistKit kit;
	
	public FacadeMain(PersistKit kit)
	{
		this.kit = kit;
	}
	
	public void login(String pseudo, String password) throws LoginFailedException
	{
		theUser = kit.makeUser(pseudo,StringUtil.sha256(password));
	}

}
