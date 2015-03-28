package com.novar.business;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class UserManager {

	
	public UserManager()
	{
		
	}
	
	public abstract ArrayList<User> getAllUsers(User admin);
	public abstract ArrayList<User> getAllAdmin(User admin);
	public abstract ArrayList<User> getAllManager(User admin);
	public abstract ArrayList<User> getAllMember(User admin);
	public abstract ArrayList<User> getAllSpeaker(User admin);
	
	public abstract void setAdmin(User admin);
	public abstract void setManager(User manager);
	public abstract void setMember(User member);
	public abstract void setSpeaker(User speaker);
	
	public abstract void deleteAdmin(User admin) throws SQLException;
	public abstract void deleteManager(User manager) throws SQLException;
	public abstract void deleteMember(User member) throws SQLException;
	public abstract void deleteSpeaker(User speaker) throws SQLException;
	
}
