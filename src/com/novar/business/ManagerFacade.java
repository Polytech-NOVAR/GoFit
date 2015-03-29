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
	 * This is the implementation of the method to getAllSpeaker
	 * @param a User
	 * @return an ArrayList of Users
	 */
	public  ArrayList<User> getAllSpeaker(User speaker)
	{
		ManagerManager um = kit.makeManagerManager();
		return um.getAllSpeaker(speaker);	
	}
	
	/**
	 * Give the Speaker's role
	 * @param a User
	 */
	public  void setSpeaker(User speaker)
	{
		ManagerManager um = kit.makeManagerManager();
		um.setSpeaker(speaker);
	}
		
	/**
	 * Delete the Speaker's role
	 * @param a User
	 */
	public void deleteSpeaker(User speaker) throws SQLException
	{
		ManagerManager um = kit.makeManagerManager();
		um.deleteSpeaker(speaker);
	}
}
	