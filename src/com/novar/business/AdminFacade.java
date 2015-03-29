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
	
	/**
	 * This is the implementation of the method to getAllUsers
	 * @param a User
	 * @return an ArrayList of Users
	 */
	public ArrayList<User> getAllUsers(User admin)
	{
		UserManager um = kit.makeUserManager();
		return um.getAllUsers(admin);
	}
	
	/**
	 * This is the implementation of the method to getAllAdmin
	 * @param a User
	 * @return an ArrayList of Users
	 */
	public  ArrayList<User> getAllAdmin(User admin)
	{
	
		UserManager um = kit.makeUserManager();
		return um.getAllAdmin(admin);
	}
	
	/**
	 * This is the implementation of the method to getAllManager
	 * @param a User
	 * @return an ArrayList of Users
	 */
	public  ArrayList<User> getAllManager(User admin)
	{
		UserManager um = kit.makeUserManager();
		return um.getAllManager(admin);
	}
	
	/**
	 * This is the implementation of the method to getAllMember
	 * @param a User
	 * @return an ArrayList of Users
	 */
	public  ArrayList<User> getAllMember(User admin)
	{
		UserManager um = kit.makeUserManager();
		return um.getAllMember(admin);
		
	}
	
	/**
	 * This is the implementation for given the role of Admin
	 * @param a User
	 */
	public void setAdmin(User admin)
	{
		UserManager um = kit.makeUserManager();
		um.setAdmin(admin);
	}
	
	/**
	 * This is the implementation for given the role of Manager
	 * @param a User
	 */
	public  void setManager(User manager)
	{
		UserManager um = kit.makeUserManager();
		um.setManager(manager);
	}
	
	/**
	 * This is the implementation for given the role of a Member
	 * @param a User
	 */
	public  void setMember(User member)
	{
		UserManager um = kit.makeUserManager();
		um.setMember(member);
	}

	/**
	 * This is the implementation for delete an Admin
	 * @param a User
	 */
	public void deleteAdmin(User admin) throws SQLException
	{
		UserManager um = kit.makeUserManager();
		um.deleteAdmin(admin);
	}
	
	/**
	 * This is the implementation for delete a Manager
	 * @param a User
	 */
	public void deleteManager(User manager) throws SQLException
	{
		UserManager um = kit.makeUserManager();
		um.deleteManager(manager);
	}
	
	/**
	 * This is the implementation for delete a Member
	 * @param a User
	 */
	public void deleteMember(User member) throws SQLException
	{
		UserManager um = kit.makeUserManager();
		um.deleteMember(member);
	}

}
