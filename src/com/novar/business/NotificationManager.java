package com.novar.business;

import java.util.ArrayList;

public abstract class NotificationManager {

	public NotificationManager()
	{
		
	}
	
	public abstract ArrayList<Notification> getAllNotifications(User receiver);
}
