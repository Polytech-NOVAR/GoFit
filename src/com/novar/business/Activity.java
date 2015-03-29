package com.novar.business;

import java.lang.reflect.Method;


import java.util.ArrayList;
import java.util.HashMap;

import com.novar.exception.FalseFieldsException;
import com.novar.persist.ActivityJdbc;
import com.novar.persist.JdbcKit;
import com.novar.util.StringUtil;


/**
 * This class manage all what is relative to an Activity
 * <br><br>

 * This Class is used in ActivityManager, AcitivityEventFacadeJdbcKit and ActivityJdbc
 * @author othmane El Kouahy
 * @see ActivityManager
 * @see JdbcKit
 * @see ActivityEventFacade
 * @see ActivityJdbc
 */
public abstract class Activity 
{
	Integer actID;
	String actName;
	String actShortDescription;
	String actDetailedDescription;
	String pseudo;
	public Activity()
	{
	}


	public Activity(HashMap<String,Object> data) throws FalseFieldsException
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(!errors.isEmpty())
			throw new FalseFieldsException(errors);
	}


	public void setActID(Integer actID)
	{
		this.actID=actID;
	}


	public Integer getActID()
	{
		return this.actID;
	}

	public void setActName(String actName)
	{
		this.actName=actName;
	}
	public String getActName()
	{
		return this.actName;
	}

	public void setActShortDescription(String shortdesc)
	{
		this.actShortDescription=shortdesc;
	}

	public String getActShortDescription()
	{
		return this.actShortDescription;	
	}

	public void setActDetailedDescription(String detailedDesc)
	{
		this.actDetailedDescription=detailedDesc;
	}
	public String getActDetailedDescription()
	{
		return this.actDetailedDescription;
	}

	public void setPseudo(String pseudo)
	{
		this.pseudo=pseudo;
	}

	public String getPseudo()
	{
		return this.pseudo;
	}

	public abstract void load();
	public abstract void save();
	public abstract void update();
	public abstract void delete();
}
