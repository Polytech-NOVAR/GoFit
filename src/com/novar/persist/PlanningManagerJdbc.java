package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

import com.novar.business.Administrator;
import com.novar.business.Manager;
import com.novar.business.Member;
import com.novar.business.Planning;
import com.novar.business.PlanningManager;
import com.novar.business.Speaker;
import com.novar.business.User;
import com.novar.exception.FalseFieldsException;
import com.novar.util.ConnectionUtil;

public class PlanningManagerJdbc extends PlanningManager
{
	public ArrayList<Planning> loadPlanningsRepetitive() throws SQLException, FalseFieldsException
	{
		PreparedStatement selectPlanning;

		selectPlanning = ConnectionUtil.connection.prepareStatement("SELECT e.name AS eventName, "
				+ "a.name AS activityName, "
				+ "e.pseudo AS speakerPseudo, "
				+ "ro.num AS roomNumber, "
				+ "re.firstDate AS eventDate, "
				+ "re.frequency AS eventFrequency, "
				+ "re.nbOccurrence AS eventOccurrence "
				+ "FROM Event e, Activity a, Room ro, Repetitive re "
				+ "WHERE e.actID = a.actID "
				+ "AND e.roomID = ro.roomID "
				+ "AND e.eventID = re.eventID;");

		ResultSet resPlanning = selectPlanning.executeQuery();
		ArrayList<Planning> plannings = new ArrayList<Planning>();

		while (resPlanning.next())
		{
			HashMap<String, Object> mapPlanning = new HashMap<String, Object>();
			mapPlanning.put("event", resPlanning.getObject("eventName"));
			mapPlanning.put("activity", resPlanning.getObject("activityName"));
			mapPlanning.put("speaker", resPlanning.getObject("speakerPseudo"));
			mapPlanning.put("room", resPlanning.getObject("roomNumber"));
			mapPlanning.put("date", resPlanning.getObject("eventDate"));
			mapPlanning.put("frequency", resPlanning.getObject("eventFrequency"));
			mapPlanning.put("nbOccurrence", resPlanning.getObject("eventOccurrence"));
			
			plannings.add(new Planning(mapPlanning));
		}
		
		return plannings;
	}
	
	public ArrayList<Planning> loadPlanningsOneTime() throws SQLException, FalseFieldsException
	{
		PreparedStatement selectPlanning;

		selectPlanning = ConnectionUtil.connection.prepareStatement("SELECT e.name AS eventName, "
				+ "a.name AS activityName, "
				+ "e.pseudo AS speakerPseudo, "
				+ "ro.num AS roomNumber, "
				+ "o.eventDate AS eventDate "
				+ "FROM Event e, Activity a, Room ro, OneTime o "
				+ "WHERE e.actID = a.actID "
				+ "AND e.roomID = ro.roomID "
				+ "AND e.eventID = o.eventID;");

		ResultSet resPlanning = selectPlanning.executeQuery();
		ArrayList<Planning> plannings = new ArrayList<Planning>();

		while (resPlanning.next())
		{
			HashMap<String, Object> mapPlanning = new HashMap<String, Object>();
			mapPlanning.put("event", resPlanning.getObject("eventName"));
			mapPlanning.put("activity", resPlanning.getObject("activityName"));
			mapPlanning.put("speaker", resPlanning.getObject("speakerPseudo"));
			mapPlanning.put("room", resPlanning.getObject("roomNumber"));
			mapPlanning.put("date", resPlanning.getObject("eventDate"));
			
			plannings.add(new Planning(mapPlanning));
		}
		
		return plannings;
	}
}
