package com.novar.business;

import java.sql.SQLException;
import java.util.ArrayList;

import com.novar.persist.PersistKit;

/**
 * This class is an abstract class used to make a ManagerFacade
 * @author PELCE Nicolas
 */
public class ManagerFacade {

	/**
	 * Construct a ManagerFacade with the right persistKit and instantiate the manager.
	 * @param kit persistence engine
	 */
	private PersistKit kit = null;
	
	public ManagerFacade (PersistKit kit)
	{
		this.kit = kit;
	}
	
	/**
	 * Take all Speaker
	 */
	public  ArrayList<User> getAllSpeaker(User speaker)
	{
		ManagerManager um = kit.makeManagerManager();
		return um.getAllSpeaker(speaker);	
	}
	
	/**
	 * Give the Speaker's role
	 */
	public  void setSpeaker(User speaker)
	{
		ManagerManager um = kit.makeManagerManager();
		um.setSpeaker(speaker);
	}
		
	/**
	 * Delete the Speaker's role
	 */
	public void deleteSpeaker(User speaker) throws SQLException
	{
		ManagerManager um = kit.makeManagerManager();
		um.deleteSpeaker(speaker);
	}
}
	