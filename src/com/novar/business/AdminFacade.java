package com.novar.business;

import java.sql.SQLException;
import java.util.ArrayList;

import com.novar.persist.PersistKit;

/**
 * This class is an abstract class used to make an AdminFacade
 * @author PELCE Nicolas
 */
public class AdminFacade {


	private PersistKit kit = null;
	
	/**
	 * Construct a AdminFacade with the right persistKit and instantiate the manager.
	 * @param kit persistence engine
	 */
	public AdminFacade (PersistKit kit)
	{
		this.kit = kit;
	}
	
	public ArrayList<User> getAllUsers(User admin)
	{
		UserManager um = kit.makeUserManager();
		return um.getAllUsers(admin);
	}
	public  ArrayList<User> getAllAdmin(User admin)
	{
	
		UserManager um = kit.makeUserManager();
		return um.getAllAdmin(admin);
	}
	
	public  ArrayList<User> getAllManager(User admin)
	{
		UserManager um = kit.makeUserManager();
		return um.getAllManager(admin);
	}
	public  ArrayList<User> getAllMember(User admin)
	{
		UserManager um = kit.makeUserManager();
		return um.getAllMember(admin);
		
	}
	public void setAdmin(User admin)
	{
		UserManager um = kit.makeUserManager();
		um.setAdmin(admin);
	}
	public  void setManager(User manager)
	{
		UserManager um = kit.makeUserManager();
		um.setManager(manager);
	}
	public  void setMember(User member)
	{
		UserManager um = kit.makeUserManager();
		um.setMember(member);
	}

	
	public void deleteAdmin(User admin) throws SQLException
	{
		UserManager um = kit.makeUserManager();
		um.deleteAdmin(admin);
	}
	
	public void deleteManager(User manager) throws SQLException
	{
		UserManager um = kit.makeUserManager();
		um.deleteManager(manager);
	}
	
	public void deleteMember(User member) throws SQLException
	{
		UserManager um = kit.makeUserManager();
		um.deleteMember(member);
	}

}
