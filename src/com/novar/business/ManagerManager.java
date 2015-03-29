package com.novar.business;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class ManagerManager {

		
	public ManagerManager()
	{
			
	}
	public abstract ArrayList<User> getAllSpeaker(User admin);
		
	public abstract void setSpeaker(User speaker);
		
	public abstract void deleteSpeaker(User speaker) throws SQLException;
		
}