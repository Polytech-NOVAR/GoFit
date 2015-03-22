package com.novar.business;

import java.util.ArrayList;
import java.util.HashMap;

import com.novar.exception.FalseFieldsException;
import com.novar.persist.PersistKit;

public class NotificationFacade {

	private PersistKit kit = null;
	
	public NotificationFacade(PersistKit kit)
	{
		this.kit = kit;
	}
	
	// NotificationManager Method
	public ArrayList<Notification> getAllNotifications(User receiver){
		NotificationManager nm = kit.makeNotificationManager();
		return nm.getAllNotifications(receiver);
	}
	
	public void notify(User sender, String message, ArrayList<String> receivers)
	{
		Notification notif = createNotification(sender, message);
		for(int i=0; i<receivers.size(); i++)
		{
			createNotify(notif, receivers.get(i));
		}
	}
	
	// Notification Methods
	public Notification getNotification(Notification notif){
		notif.load();
		return notif;	
	}

	public Notification createNotification(User sender, String message){
		Notification notif = kit.makeNotification(sender, message);
		notif.save();
		return notif;	
	}

	public void deleteNotification(Notification notif)
	{
		notif.delete();
	}
	
	// NotifyTo Methods
	public NotifyTo createNotify(Notification notif, String receiver)
	{
		NotifyTo notify = kit.makeNotifyTo(notif, receiver);
		notify.save();
		return notify;
	}

	public void deleteNotify(NotifyTo notify)
	{
		notify.delete();
	}
	
	public NotifyTo viewNotify(NotifyTo notify)
	{
		notify.setView(true);
		notify.update();
		return notify;
	}
}
