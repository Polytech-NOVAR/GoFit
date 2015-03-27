/**
 * This class is a facade to access to all the Notification and NotifyTo methods.
 * @author GUILMET Romain
 */

package com.novar.business;

import java.util.ArrayList;
import java.util.HashMap;

import com.novar.exception.FalseFieldsException;
import com.novar.persist.PersistKit;

public class NotificationFacade {

	private PersistKit kit = null;
	
	/**
	 * This is the default constructor of a NotificationFacade. 
	 * @param kit, the PersistKit selected 
	 */
	public NotificationFacade(PersistKit kit)
	{
		this.kit = kit;
	}
	
	// NotificationManager Methods
	
	/**
	 * @param receiver, an User, the User who receive the Notifications
	 * @return an int, the number of Notifications not seen by the receiver
	 */
	public int countNewNotifs(User receiver) {
		NotificationManager nm = kit.makeNotificationManager();
		return nm.countNewNotifs(receiver);
	}
	
	/**
	 * @param receiver, an User, the User who receive the Notifications
	 * @return an ArrayList of Notifications, all the Notifications of the receiver
	 */
	public ArrayList<Notification> getAllNotifications(User receiver){
		NotificationManager nm = kit.makeNotificationManager();
		return nm.getAllNotifications(receiver);
	}
	
	/**
	 * This method is used to create a Notification and to Notify multiple receivers
	 * @param sender, a User, the sender of the Notification
	 * @param message, a String, the message of the Notification
	 * @param receivers, an ArrayList of String, all the pseudo of the receivers of the Notification
	 */
	public void notify(User sender, String message, ArrayList<String> receivers)
	{
		Notification notif = createNotification(sender, message);
		for(int i=0; i<receivers.size(); i++)
		{
			createNotify(notif, receivers.get(i));
		}
	}
	
	// Notification Methods
	/**
	 * Get the Notification from the persistence
	 * @param notif, a Notification
	 * @return the Notification loaded from the persistence
	 */
	public Notification getNotification(Notification notif){
		notif.load();
		return notif;	
	}

	/**
	 * Create a Notification into the persistence
	 * @param sender, a User, the sender of the Notification
	 * @param message, a String, the message of the Notification
	 * @return the Notification saved into the persistence
	 */
	public Notification createNotification(User sender, String message){
		Notification notif = kit.makeNotification(sender, message);
		notif.save();
		return notif;	
	}

	/**
	 * Delete a Notification from the persistence
	 * @param notif, a Notification to delete
	 */
	public void deleteNotification(Notification notif)
	{
		notif.delete();
	}
	
	// NotifyTo Methods
	/**
	 * Create a NotifyTo into the persistence
	 * @param notif, the Notification
	 * @param receiver, an User who receive the Notification
	 * @return NotifyTo saved into the persistence
	 */
	public NotifyTo createNotify(Notification notif, String receiver)
	{
		NotifyTo notify = kit.makeNotifyTo(notif, receiver);
		notify.save();
		return notify;
	}

	/**
	 * Delete a NotifyTo from the persistence
	 * @param notify, a NotifyTo
	 */
	public void deleteNotify(NotifyTo notify)
	{
		notify.delete();
	}
	
	/**
	 * Update the view, boolean of an user for a Notification into the persistence
	 * @param notif, the new seen Notification
	 * @param receiver, the User who saw the Notification
	 * @return the NotifyTo updated in the persistence
	 */
	public NotifyTo viewNotify(Notification notif, String receiver)
	{
		NotifyTo notify = kit.makeNotifyTo(notif, receiver);
		notify.setView(true);
		notify.update();
		return notify;
	}
}
