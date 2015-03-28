package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.novar.business.Accessory;
import com.novar.business.Have;
import com.novar.business.Notification;
import com.novar.business.NotifyTo;
import com.novar.business.User;
import com.novar.exception.SyntaxException;
import com.novar.util.ConnectionUtil;

public class NotificationJdbc extends Notification{

	/**
	 * This is the default constructor of a NotificationJdbc. 
	 * It is used to make an empty one.
	 */
	public NotificationJdbc()
	{
		super();
	}
	
	/**
	 * This is the constructor of a NotificationJdbc. 
	 * @param sender, an User, 
	 * @param message, a message
	 * It will create a new empty NotificationJdbc and it will set the sender and the message using the super constructor.
	 */
	public NotificationJdbc(User sender, String message)
	{
		super(sender, message);
	}

	/**
	 * Load the attributes of a NotificationJdbc from the DB
	 */
	@Override
	public void load()
	{
		PreparedStatement selectNotif;
		try 
		{
			selectNotif = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM Notification "
					+ "WHERE notifID = ? ");

			selectNotif.setObject(1, getNotifID(), Types.INTEGER);
			ResultSet res = selectNotif.executeQuery();
			res.last();
			if(res.getRow() == 1)
			{
				User sender = new UserJdbc();
				try {
					sender.setPseudo(res.getString("sender"));
				} catch (SyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setSender(sender);
				setMessage(res.getString("message"));
				setDate(res.getString("notifDate"));			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}	
	}

	/**
	 * Save the NotificationJdbc into the DB
	 */
	@Override
	public void save()
	{
		try 
		{
			PreparedStatement insertNotif = ConnectionUtil.connection.prepareStatement("INSERT INTO Notification (sender, message, notifDate) "
																		+ "VALUES (?, ?, ?);", PreparedStatement.RETURN_GENERATED_KEYS);
			insertNotif.setObject(1, getSender().getPseudo(),Types.VARCHAR);
			insertNotif.setObject(2, getMessage(),Types.VARCHAR);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			insertNotif.setObject(3, date,Types.DATE);
			insertNotif.executeUpdate();
			ResultSet key = insertNotif.getGeneratedKeys();
			if(key.next()){
				setNotifID(key.getInt(1));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Delete the NotificationJdbc from the DB
	 */
	@Override
	public void delete(){
		PreparedStatement deleteNotif;
		PreparedStatement deleteNotify;
		try {
			deleteNotify = ConnectionUtil.connection.prepareStatement("DELETE FROM NotifyTo "
						+ "WHERE notifID = ? ");
			deleteNotify.setObject(1, getNotifID(), Types.INTEGER);
			deleteNotify.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			deleteNotif = ConnectionUtil.connection.prepareStatement("DELETE FROM Notification "
						+ "WHERE notifID = ? ");
			deleteNotif.setObject(1, getNotifID(), Types.INTEGER);
			deleteNotif.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
