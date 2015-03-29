

package com.novar.business;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class is an abstract class used to make a UserManager
 * @author PELCE Nicolas
 */
public abstract class UserManager {

	/**
	 * This is the default constructor of a UserManager. 
	 * It is used to make an empty one.
	 */
	public UserManager()
	{
		
	}
	
	/**
	 * Load the lists of all Users
	 */
	public abstract ArrayList<User> getAllUsers(User admin);
	
	/**
	 * Load the lists of all Admin
	 */
	public abstract ArrayList<User> getAllAdmin(User admin);
	
	/**
	 * Load the lists of all Manager
	 */
	public abstract ArrayList<User> getAllManager(User admin);
	
	/**
	 * Load the lists of all Member
	 */
	public abstract ArrayList<User> getAllMember(User admin);
	
	/**
	 * Fix the role of an Admin
	 */
	public abstract void setAdmin(User admin);
	
	/**
	 * Fix the role of a Manager
	 */
	public abstract void setManager(User manager);
	
	/**
	 * Fix the role of a Member
	 */
	public abstract void setMember(User member);

	
	/**
	 * Delete the role of an Admin
	 */
	public abstract void deleteAdmin(User admin) throws SQLException;
	
	/**
	 * Delete the role of a Manager
	 */
	public abstract void deleteManager(User manager) throws SQLException;
	
	/**
	 * Delete the role of a Member
	 */
	public abstract void deleteMember(User member) throws SQLException;
	
}
