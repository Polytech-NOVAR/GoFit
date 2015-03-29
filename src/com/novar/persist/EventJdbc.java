package com.novar.persist;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

import com.novar.business.Event;
import com.novar.exception.FalseFieldsException;
import com.novar.util.ConnectionUtil;

/**
 * this class is used to load or create an Event On jdbc 
 * @author othmane
 * it is used in EventManagerJdbc
 * @see EventManagerJdbc
 */
public class EventJdbc extends Event {
	public EventJdbc()
	{
		super();
	}

	public EventJdbc(HashMap<String,Object> data)
	{
		super(data);
	}

	public void save()
	{

		try 
		{

			// insertion d'un evenement de type one time
			PreparedStatement insertEvent = ConnectionUtil.connection.prepareStatement("INSERT INTO Event (eventID, name, price, duration, roomID, actID, pseudo) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?);");

			insertEvent.setObject(1, getEventID(),Types.INTEGER);
			insertEvent.setObject(2, getEventName(),Types.VARCHAR);
			insertEvent.setObject(3, getEventPrice(),Types.FLOAT);
			insertEvent.setObject(4, getEventDuration(),Types.INTEGER);
			insertEvent.setObject(5, getRoomID(),Types.INTEGER);
			insertEvent.setObject(6, getActID(),Types.INTEGER);
			insertEvent.setObject(7, getPseudo(),Types.VARCHAR);

			insertEvent.executeUpdate();		





			//Insertion d'un evenement repetitive

		}
		catch (SQLException e) 
		{

		}
	}

	public void saveRepetitive()
	{

		PreparedStatement selectEvent;
		try 
		{
			selectEvent = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM Event "
					+ "WHERE eventID = LAST_INSERT_ID() ");

			ResultSet res = selectEvent.executeQuery();
			res.last();
			if(res.getRow() == 1)
			{
				PreparedStatement insertRepetitive =ConnectionUtil.connection.prepareStatement("INSERT INTO Repetitive (eventID, nbOccurrence, frequency, firstDate) "
						+ "VALUES (?, ?, ?, ?);");
				insertRepetitive.setObject(1, res.getInt("eventID"),Types.INTEGER);
				insertRepetitive.setObject(2, getOccurence(),Types.INTEGER);
				insertRepetitive.setObject(3, getFrequency(),Types.VARCHAR);
				insertRepetitive.setObject(4, getEventDate(),Types.DATE);
				insertRepetitive.executeUpdate();	 

			}



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveOneTime()
	{
		PreparedStatement insertOneTime,selectEvent;
		try{
			selectEvent = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM Event "
					+ "WHERE eventID = LAST_INSERT_ID() ");

			ResultSet res = selectEvent.executeQuery();
			res.last();
			if(res.getRow() == 1)
			{
				insertOneTime =ConnectionUtil.connection.prepareStatement("INSERT INTO OneTime (eventID, type, eventDate) "
						+ "VALUES (?, ?, ?);");
				insertOneTime.setObject(1, res.getInt("eventID"),Types.INTEGER);
				insertOneTime.setObject(2, getType(),Types.VARCHAR);
				insertOneTime.setObject(3, this.getEventDate(),Types.DATE);
				insertOneTime.executeUpdate();

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void load()
	{
		PreparedStatement selectEvent;
		PreparedStatement selectEventOneTime;
		PreparedStatement selectEventRepetitive;

		try 
		{
			selectEvent = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM Event "
					+ "WHERE eventID = ? ");

			selectEvent.setObject(1, getEventID(), Types.INTEGER);
			ResultSet res = selectEvent.executeQuery();
			res.last();
			if(res.getRow() == 1)
			{
				setEventName(res.getString("name"));
				setEventPrice(res.getInt("price"));
				setEventDuration(res.getInt("duration"));
				setRoomID(res.getInt("roomID"));
				setActID(res.getInt("actID"));
				setPseudo(res.getString("pseudo"));

				selectEventRepetitive = ConnectionUtil.connection.prepareStatement("SELECT * "
						+"FROM Repetitive "
						+"WHERE eventID = ? ");

				selectEventRepetitive.setObject(1, getEventID(), Types.INTEGER);
				ResultSet res2 = selectEventRepetitive.executeQuery();
				res2.last();
				if(res2.getRow()==1)
				{
					this.setType("Repetitive");
					this.setEventDate(res2.getDate("firstDate"));
					this.setOccurence(res2.getInt("nbOccurrence"));
					this.setFrequency(res2.getString("frequency"));
				}
				else 
				{
					selectEventOneTime = ConnectionUtil.connection.prepareStatement("SELECT * "
							+"FROM  OneTime "
							+"WHERE eventID = ? ");
					selectEventOneTime.setObject(1, getEventID(), Types.INTEGER);
					ResultSet res1 = selectEventOneTime.executeQuery();
					res1.last();
					if(res1.getRow()==1)
					{
						this.setType("One Time");
						this.setEventDate(res1.getDate("eventDate"));
						setType(res1.getString("type"));

					}
				}
			}


		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(){
		PreparedStatement deleteEvent,deleteEventOneTime,deleteEventRepetitive;

		try {
			deleteEvent = ConnectionUtil.connection.prepareStatement("DELETE FROM Event "
					+ "WHERE eventID = ? ");
			deleteEvent.setObject(1, getEventID(), Types.INTEGER);
			deleteEvent.executeUpdate();

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(int par)
	{

		PreparedStatement updateEvent,insertRepetitive,insertOneTime,deleteEventOneTime,deleteEventRepetitive;

		try {

			updateEvent = ConnectionUtil.connection.prepareStatement("UPDATE Event "
					+ "SET name = ? ,price = ? ,duration = ?,roomID = ? ,actID = ? ,pseudo = ? "
					+ "WHERE eventID = ?;");
			updateEvent.setObject(1, this.getEventName(), Types.VARCHAR);
			updateEvent.setObject(2, this.getEventPrice(), Types.INTEGER);
			updateEvent.setObject(3, this.getEventDuration(),Types.INTEGER);
			updateEvent.setObject(4, this.getRoomID(),Types.INTEGER);
			updateEvent.setObject(5, this.getActID(),Types.INTEGER);
			updateEvent.setObject(6, this.getPseudo(),Types.VARCHAR);
			updateEvent.setObject(7, this.getEventID(),Types.VARCHAR);
			updateEvent.executeUpdate();

			deleteEventOneTime= ConnectionUtil.connection.prepareStatement("DELETE FROM OneTime "
					+ "WHERE eventID = ? ");
			deleteEventOneTime.setObject(1, getEventID(), Types.INTEGER);
			deleteEventOneTime.executeUpdate();

			deleteEventRepetitive= ConnectionUtil.connection.prepareStatement("DELETE FROM Repetitive "
					+ "WHERE eventID = ?;");
			deleteEventRepetitive.setObject(1, getEventID(), Types.INTEGER);
			deleteEventRepetitive.executeUpdate();

			if(this.getType().equals("One Time"))
			{
				insertOneTime =ConnectionUtil.connection.prepareStatement("INSERT INTO OneTime (eventID, type, eventDate) "
						+ "VALUES (?, ?, ?);");
				insertOneTime.setObject(1, this.getEventID(),Types.INTEGER);
				insertOneTime.setObject(2, getType(),Types.VARCHAR);
				insertOneTime.setObject(3, this.getEventDate(),Types.DATE);
				insertOneTime.executeUpdate();
			}

			else {
				insertRepetitive =ConnectionUtil.connection.prepareStatement("INSERT INTO Repetitive (eventID, nbOccurrence, frequency, firstDate) "
						+ "VALUES (?, ?, ?, ?);");
				insertRepetitive.setObject(1, this.getEventID(),Types.INTEGER);
				insertRepetitive.setObject(2, getOccurence(),Types.INTEGER);
				insertRepetitive.setObject(3, getFrequency(),Types.VARCHAR);
				insertRepetitive.setObject(4, getEventDate(),Types.DATE);
				insertRepetitive.executeUpdate();	 


			}




		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
