package com.novar.business;

import java.util.ArrayList;

/**
 * this class serve to manage list of Events
 * @author othmane El kouahy
 * 
 * It is used in EventManagerJdbc,PersistKit
 * @see EventManagerJdbc
 * @see PersistKit
 */
public abstract class EventManager {

	public void EventManager()
	{


	}

	public abstract ArrayList<Event> getAllEvents(User man);

	public abstract ArrayList<Event> getAllEvents(Activity act);

	public abstract ArrayList<Event> getAllEvents();

}
