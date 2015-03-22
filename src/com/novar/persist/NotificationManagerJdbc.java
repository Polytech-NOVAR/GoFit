package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.novar.business.Notification;
import com.novar.business.NotificationManager;
import com.novar.business.Room;
import com.novar.business.User;
import com.novar.util.ConnectionUtil;

public class NotificationManagerJdbc extends NotificationManager{


	public NotificationManagerJdbc()
	{
		
	}
	
	@Override
	public ArrayList<Notification> getAllNotifications(User receiver) {
		PreparedStatement selectNotifications;
		ArrayList<Notification> notifs = new ArrayList<Notification>();
		try 
		{
			selectNotifications = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM Notification n, NotifyTo nt "
					+ "WHERE n.notifID = nt.notifID "
					+ "AND receiver = ?");
			
			selectNotifications.setObject(1, receiver.getPseudo(), Types.VARCHAR);
			ResultSet res = selectNotifications.executeQuery();
			res.first();
			if(res.getRow() > 0)
			{
				do
				{
					Notification notif = new NotificationJdbc();
					notif.setNotifID(res.getInt("notifID"));
					notif.load();
					
					notifs.add(notif);
					
				}while(res.next());
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return notifs;
	}
	
	@Override
	public int countNewNotifs(User receiver) {
		PreparedStatement selectNotifications;
		ArrayList<Notification> notifs = new ArrayList<Notification>();
		try 
		{
			selectNotifications = ConnectionUtil.connection.prepareStatement("Select * "
					+ "FROM Notification n, NotifyTo nt "
					+ "WHERE n.notifID = nt.notifID "
					+ "AND receiver = ? "
					+ "AND view = 0" );
			
			selectNotifications.setObject(1, receiver.getPseudo(), Types.VARCHAR);
			ResultSet res = selectNotifications.executeQuery();
			res.first();
			if(res.getRow() > 0)
			{
				do
				{
					Notification notif = new NotificationJdbc();
					notif.setNotifID(res.getInt("notifID"));
					notif.load();
					
					notifs.add(notif);
					
				}while(res.next());
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return notifs.size();
	}
}
