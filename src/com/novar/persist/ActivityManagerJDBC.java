package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.novar.business.Accessory;
import com.novar.business.Activity;
import com.novar.business.ActivityManager;
import com.novar.persist.*;
import com.novar.util.ConnectionUtil;



public class ActivityManagerJDBC extends ActivityManager{

	 public ActivityManagerJDBC()
 {

	
 }
 
	public ArrayList<Activity> getAllActivities() {
		PreparedStatement selectActivities;
		ArrayList<Activity> activities = new ArrayList<Activity>();
		try 
		{
			selectActivities = ConnectionUtil.connection.prepareStatement("SELECT * FROM Activity");

			ResultSet res = selectActivities.executeQuery();
			res.first();
			if(res.getRow() > 0)
			{
				do
				{
					Activity act = new ActivityJdbc();
					act.setActID(res.getInt("actID"));
					act.load();
					
					activities.add(act);
					
				}while(res.next());
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return activities;
	}
}
