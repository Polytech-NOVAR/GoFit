package com.novar.business;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.novar.exception.FalseFieldsException;
import com.novar.util.StringUtil;
/**
 * This class manages all what is related to Registrations
 * @author othmane El kouahy
 * 
 * it is used in class  RegistrationManager,ActivityMainFacade,JdbcKit,RegistrationJdbc
 * @see RegistrationManager
 * @see ActivityMainFacade
 * @see JdbcKit 
 * @see RegistrationJdbc
 */
public abstract class Registration {

	private int eventID;
	private String pseudo;
	private Date registrationDate;

	public Registration()
	{

	}

	public Registration(HashMap<String,Object> data) throws FalseFieldsException
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



	public int getEventID() {
		return eventID;
	}

	public void setEventID(Integer eventID) {
		this.eventID = eventID;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public abstract void load();
	public abstract void save();
	public abstract void saveRegistrationByMember();
	public abstract void update();
	public abstract void delete();
}
