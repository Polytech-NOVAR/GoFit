/**
 * This class is an abstract class used to make a NotificatioManager.
 * @author GUILMET Romain
 */
package com.novar.business;

import java.util.ArrayList;

public abstract class NotificationManager {

	/**
	 * This is the default constructor of a NotificationManager. 
	 * It is used to make an empty one.
	 */
	public NotificationManager()
	{
		
	}
	
	/**
	 * @param receiver, an User, the User who receive the Notifications
	 * @return an ArrayList of Notifications, all the Notifications of the receiver
	 */
	public abstract ArrayList<Notification> getAllNotifications(User receiver);
	
	/**
	 * @param receiver, an User, the User who receive the Notifications
	 * @return an int, the number of Notifications not seen by the receiver
	 */
	public abstract int countNewNotifs(User receiver);
}
