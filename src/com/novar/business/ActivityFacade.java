package com.novar.business;

import java.util.ArrayList;
import java.util.HashMap;

import com.novar.exception.FalseFieldsException;
import com.novar.persist.PersistKit;

public class ActivityFacade {

	private PersistKit kit = null;
	
	public ActivityFacade(PersistKit kit)
	{
		this.kit=kit;
	}
	public ArrayList<Activity> getAllActivities()
	{
		ActivityManager Am = kit.makeActivityManager();
		return Am.getAllActivities();
	}
	
	public void deleteActivity(Activity act)
	{
		act.delete();
	}
	public Activity createActivity(HashMap<String, Object> mapAct) throws FalseFieldsException
	{
		Activity act = kit.makeActivity(mapAct);
		act.save();
		return act;	
		
	}
	public Activity updateActivity(HashMap<String, Object> mapAct) throws FalseFieldsException 
	{
		Activity act = kit.makeActivity(mapAct);
		act.update();
		return act;	
		
	}
}
