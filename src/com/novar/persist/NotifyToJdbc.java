package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

import com.novar.business.Notification;
import com.novar.business.NotifyTo;
import com.novar.business.User;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.SyntaxException;
import com.novar.util.ConnectionUtil;

public class NotifyToJdbc extends NotifyTo{

	public NotifyToJdbc()
	{
		super();
	}
	
	public NotifyToJdbc(Notification notif, String receiver)
	{
		super(notif, receiver);
	}

	@Override
	public void save() {
		try 
		{	
			PreparedStatement insertNotify = ConnectionUtil.connection.prepareStatement("INSERT INTO NotifyTo (receiver, notifID) "
																		+ "VALUES (?, ?);");
			insertNotify.setObject(1, getReceiver(),Types.VARCHAR);
			insertNotify.setObject(2, getNotif().getNotifID(),Types.INTEGER);
			insertNotify.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void delete() {
		PreparedStatement deleteNotify;
		try {
			deleteNotify = ConnectionUtil.connection.prepareStatement("DELETE FROM NotifyTo "
						+ "WHERE receiver = ? "
						+ "AND notifID = ? ");
			deleteNotify.setObject(1, getReceiver(),Types.VARCHAR);
			deleteNotify.setObject(2, getNotif().getNotifID(),Types.INTEGER);
			deleteNotify.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		PreparedStatement updateNotify;
		try {
			updateNotify = ConnectionUtil.connection.prepareStatement("UPDATE NotifyTo "
					+ "SET view = ? "
					+ "WHERE receiver = ? "
					+ "AND notifID = ?");
			updateNotify.setObject(1, isView(),Types.BOOLEAN);
			updateNotify.setObject(2, getReceiver(),Types.VARCHAR);
			updateNotify.setObject(3, getNotif().getNotifID(),Types.INTEGER);
			updateNotify.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
