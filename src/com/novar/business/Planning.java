package com.novar.business;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.HashMap;

import com.novar.exception.FalseFieldsException;
import com.novar.persist.UserJdbc;
import com.novar.util.StringUtil;

/**
 * This class describe a planning, this class is use in PlanningManager
 * @author Valentin BERCOT-DUFLOS
 * @see PlanningManager
 * @see PlanningFacade
 */
public class Planning
{
	private String activity;
	private String event;
	private String speaker;
	private String room;
	private Date date;
	private int frequency;
	private int nbOccurrence;
	
	public Planning(HashMap<String, Object> data) throws FalseFieldsException
	{
		setAll(data);
	}
	
	/**
	 * This method is an hydratate one, it allow to auto call setters with the name in key of the hashmap
	 * @param data
	 * @throws FalseFieldsException
	 */
	public void setAll(HashMap<String,Object> data) throws FalseFieldsException
	{
		Class[] typeArg = new Class[1];
		Object[] arg = new Object[1];
		ArrayList<String> errors = new ArrayList<String>();
		
		for (String mapKey : data.keySet())
		{
			String setterName = "set" + StringUtil.toCapitalizeCase(mapKey);
			typeArg[0] = data.get(mapKey).getClass();
			arg[0] = data.get(mapKey);
			Method setter;
			try {
				setter = this.getClass().getMethod(setterName, typeArg);
				try
				{
					setter.invoke(this, arg);
				}
				catch (Exception e)
				{
					errors.add(e.getCause().getMessage());
				}
			} catch (NoSuchMethodException | SecurityException e1) {
				e1.printStackTrace();
			}
		}
		if(!errors.isEmpty())
			throw new FalseFieldsException(errors);
	}

	public String getActivity()
	{
		return activity;
	}

	public void setActivity(String activity)
	{
		this.activity = activity;
	}

	public String getEvent()
	{
		return event;
	}

	public void setEvent(String event)
	{
		this.event = event;
	}

	public String getSpeaker()
	{
		return speaker;
	}

	public void setSpeaker(String speaker)
	{
		this.speaker = speaker;
	}

	public String getRoom()
	{
		return room;
	}

	public void setRoom(String room)
	{
		this.room = room;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public String getFrequency()
	{
		return String.valueOf(frequency);
	}

	public void setFrequency(int frequency)
	{
		this.frequency = frequency;
	}

	public String getNbOccurrence()
	{
		return String.valueOf(nbOccurrence);
	}

	public void setNbOccurrence(int nbOccurence)
	{
		this.nbOccurrence = nbOccurence;
	}
	
	
}
