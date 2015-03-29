package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.novar.business.Accessory;
import com.novar.business.Activity;
import com.novar.business.Event;
import com.novar.util.ConnectionUtil;
import com.novar.business.*;


/**
 * this class is used for managing list of events 
 * @author othmane El kouahy
 *
 */
public class EventManagerJdbc extends EventManager
{

	public void EventManagerJdbc()
	{

	}
	public ArrayList<Event> getAllEvents(Activity act) {
		PreparedStatement selectEvents;
		ArrayList<Event> Events = new ArrayList<Event>();
		try 
		{

			selectEvents = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM Event "
					+ "WHERE actID= ? ");

			selectEvents.setObject(1, act.getActID(), Types.INTEGER);

			ResultSet res = selectEvents.executeQuery();
			res.first();
			if(res.getRow() > 0)
			{
				do
				{
					Event Ev = new EventJdbc();
					Ev.setEventID(res.getInt("eventID"));
					Ev.load();

					Events.add(Ev);

				}while(res.next());
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return Events;
	}

	public ArrayList<Event> getAllEvents() {
		PreparedStatement selectEvents;
		ArrayList<Event> Events = new ArrayList<Event>();
		try 
		{

			selectEvents = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM Event " );




			ResultSet res = selectEvents.executeQuery();
			res.first();
			if(res.getRow() > 0)
			{
				do
				{
					Event Ev = new EventJdbc();
					Ev.setEventID(res.getInt("eventID"));
					Ev.load();

					Events.add(Ev);

				}while(res.next());
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return Events;
	}
	public ArrayList<Event> getAllEvents(User man) {
		PreparedStatement selectEvents;
		ArrayList<Event> Events = new ArrayList<Event>();
		try 
		{

			selectEvents = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM Event "
					+ "WHERE pseudo= ? ");

			selectEvents.setObject(1, man.getPseudo(), Types.VARCHAR);


			ResultSet res = selectEvents.executeQuery();
			res.first();
			if(res.getRow() > 0)
			{
				do
				{
					Event Ev = new EventJdbc();
					Ev.setEventID(res.getInt("eventID"));
					Ev.load();

					Events.add(Ev);

				}while(res.next());
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return Events;
	}

}
