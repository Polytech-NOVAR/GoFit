package com.novar.persist;

import java.util.HashMap;
import java.util.ArrayList;
import java.sql.*;

import com.novar.business.Activity;
import com.novar.exception.ActivityLoadException;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.SyntaxException;
import com.novar.util.ConnectionUtil;

/**
 * This class give to the user the possibility to create an activity or to load it On/From Jdbc .
 * @author Othmane El Kouahy
 *
 * it is used in ActivityManagerJdbc 
 * @see ActivityManagerJdbc
 */
public class ActivityJdbc extends Activity{
	public ActivityJdbc()
	{
		super();
	}

	public ActivityJdbc(HashMap<String,Object> data) throws FalseFieldsException
	{
		super(data);
	}

	public void save()
	{

		try 
		{
			//Insertion d'une activite dans la table activity
			PreparedStatement insertActivity = ConnectionUtil.connection.prepareStatement("INSERT INTO Activity (actID, name, shortDescription, detailedDescription, pseudo) "
					+ "VALUES (?, ?, ?, ?, ?);");
			insertActivity.setObject(1, getActID(),Types.VARCHAR);
			insertActivity.setObject(2, getActName(),Types.VARCHAR);
			insertActivity.setObject(3, getActShortDescription(),Types.VARCHAR);
			insertActivity.setObject(4, getActDetailedDescription(),Types.VARCHAR);
			insertActivity.setObject(5, getPseudo(),Types.VARCHAR);
			insertActivity.executeUpdate();		
		}
		catch (SQLException e) 
		{

		}
	}
	public void load()
	{
		PreparedStatement selectActivity;
		try 
		{
			selectActivity = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM Activity "
					+ "WHERE actID = ? ");

			selectActivity.setObject(1, getActID(), Types.INTEGER);
			ResultSet res = selectActivity.executeQuery();
			res.last();
			if(res.getRow() == 1)
			{
				setActName(res.getString("name"));
				setActShortDescription(res.getString("shortDescription"));
				setActDetailedDescription(res.getString("detailedDescription"));
				setPseudo(res.getString("pseudo"));
			}

		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(){
		PreparedStatement deleteActivity;

		try {
			deleteActivity = ConnectionUtil.connection.prepareStatement("DELETE FROM Activity "
					+ "WHERE actID = ? ");
			deleteActivity.setObject(1, getActID(), Types.INTEGER);
			deleteActivity.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void update() {
		PreparedStatement updateActivity;
		try {
			updateActivity = ConnectionUtil.connection.prepareStatement("UPDATE Activity "
					+ "SET name = ? ,shortDescription = ? ,detailedDescription = ? ,pseudo = ?"
					+ "WHERE actID = ? ");
			updateActivity.setObject(1, getActName(), Types.VARCHAR);
			updateActivity.setObject(2, this.getActShortDescription(), Types.VARCHAR);
			updateActivity.setObject(3, this.getActDetailedDescription(),Types.VARCHAR);
			updateActivity.setObject(4, this.getPseudo(),Types.VARCHAR);
			updateActivity.setObject(5, this.getActID(),Types.VARCHAR);
			updateActivity.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
}

