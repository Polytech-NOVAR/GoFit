package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

import com.novar.business.Registration;
import com.novar.exception.FalseFieldsException;
import com.novar.util.ConnectionUtil;

/**
 * this class is used for creating or loading a Registration on the jdbc 
 * @author othmane El kouahy
 * it is used in RegistrationManagerJdbc
 * @see RegistrationManagerJdbc
 */
public class RegistrationJdbc extends Registration{

	public RegistrationJdbc()
	{
		super(); 
	}

	public RegistrationJdbc(HashMap<String,Object> data) throws FalseFieldsException
	{
		super(data);
	}

	public void save()
	{

		try 
		{
			//Insertion d'une activite dans la table activity
			PreparedStatement insertRegistration = ConnectionUtil.connection.prepareStatement("INSERT INTO Register (eventID, pseudo, registrationDate)"
					+ "VALUES (?, ?, ?);");
			insertRegistration.setObject(1, getEventID(),Types.INTEGER);
			insertRegistration.setObject(2, getPseudo(),Types.VARCHAR);
			insertRegistration.setObject(3, getRegistrationDate(),Types.DATE);

			insertRegistration.executeUpdate();		
		}
		catch (SQLException e) 
		{

		}
	}

	public void saveRegistrationByMember()
	{

		try 
		{
			//Insertion d'une activite dans la table activity
			PreparedStatement insertRegistration = ConnectionUtil.connection.prepareStatement("INSERT INTO Register (eventID, pseudo, registrationDate)"
					+ "VALUES (?, ?, NOW());");
			insertRegistration.setObject(1, getEventID(),Types.INTEGER);
			insertRegistration.setObject(2, getPseudo(),Types.VARCHAR);


			insertRegistration.executeUpdate();		
		}
		catch (SQLException e) 
		{

		}
	}

	public void load()
	{
		PreparedStatement selectRegistration;
		try 
		{
			selectRegistration = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM Register "
					+ "WHERE eventID = ? "
					+ "AND pseudo = ? ");

			selectRegistration.setObject(1, getEventID(), Types.INTEGER);
			selectRegistration.setObject(2, this.getPseudo(), Types.VARCHAR);
			ResultSet res = selectRegistration.executeQuery();
			res.last();
			if(res.getRow() == 1)
			{

				this.setRegistrationDate(res.getDate("registrationDate"));

			}

		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update() {
		PreparedStatement updateRegistration;
		try {
			updateRegistration = ConnectionUtil.connection.prepareStatement("UPDATE Register "
					+ "SET pseudo = ? ,registrationDate = ? "
					+ "WHERE eventID = ? ");
			updateRegistration.setObject(1, getPseudo(), Types.VARCHAR);
			updateRegistration.setObject(2, this.getRegistrationDate(), Types.INTEGER);
			updateRegistration.setObject(3, getEventID());
			updateRegistration.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};

	public void delete(){
		PreparedStatement deleteRegistration;

		try {
			deleteRegistration = ConnectionUtil.connection.prepareStatement("DELETE FROM Register "
					+ "WHERE eventID = ? "
					+ "AND pseudo = ?");
			deleteRegistration.setObject(1, getEventID(), Types.INTEGER);
			deleteRegistration.setObject(2, this.getPseudo(),Types.VARCHAR);
			deleteRegistration.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
