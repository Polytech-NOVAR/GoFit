package com.novar.business;

import java.sql.SQLException;
import java.util.ArrayList;

import com.novar.persist.PersistKit;

public class ManagerFacade {


	private PersistKit kit = null;
	
	public ManagerFacade (PersistKit kit)
	{
		this.kit = kit;
	}
	
	public  ArrayList<User> getAllSpeaker(User speaker)
	{
		ManagerManager um = kit.makeManagerManager();
		return um.getAllSpeaker(speaker);	
	}
	
	public  void setSpeaker(User speaker)
	{
		ManagerManager um = kit.makeManagerManager();
		um.setSpeaker(speaker);
	}
		
	public void deleteSpeaker(User speaker) throws SQLException
	{
		ManagerManager um = kit.makeManagerManager();
		um.deleteSpeaker(speaker);
	}
}
	