package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.novar.business.Event;
import com.novar.business.Registration;
import com.novar.business.RegistrationManager;
import com.novar.business.User;
import com.novar.util.ConnectionUtil;

/**
 * 
 * @author othmane
 *
 */
public  class RegistrationManagerJdbc extends RegistrationManager {

	/**
	 * Method used to return all registration relative to 
	 * @param Event
	 * 
	 */
	public ArrayList<Registration> getAllRegistrations(Event Ev)
	{
		PreparedStatement selectRegistrations;
		ArrayList<Registration> Registrations = new ArrayList<Registration>();
		try 
		{
			selectRegistrations = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM Register "
					+ "WHERE eventID= ? ");

			selectRegistrations.setObject(1, Ev.getEventID(), Types.INTEGER);
			ResultSet res = selectRegistrations.executeQuery();
			res.first();
			if(res.getRow() > 0)
			{
				do
				{
					Registration Reg = new RegistrationJdbc();
					Reg.setEventID(res.getInt("eventID"));
					Reg.setPseudo(res.getString("pseudo"));
					Reg.load();

					Registrations.add(Reg);

				}while(res.next());
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return Registrations;
	}


	public ArrayList<Registration> getAllRegistrations(User mem)
	{
		PreparedStatement selectRegistrations;
		ArrayList<Registration> Registrations = new ArrayList<Registration>();
		try 
		{
			selectRegistrations = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM Register "
					+ "WHERE pseudo= ? ");

			selectRegistrations.setObject(1, mem.getPseudo(), Types.VARCHAR);
			ResultSet res = selectRegistrations.executeQuery();
			res.first();
			if(res.getRow() > 0)
			{
				do
				{
					Registration Reg = new RegistrationJdbc();
					Reg.setEventID(res.getInt("eventID"));
					Reg.setPseudo(res.getString("pseudo"));
					Reg.load();

					Registrations.add(Reg);

				}while(res.next());
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return Registrations;
	}



}


