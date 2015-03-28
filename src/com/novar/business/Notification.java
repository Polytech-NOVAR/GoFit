/**
 * This class is an abstract class used to make a Notification.
 * @author GUILMET Romain
 */

package com.novar.business;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import com.novar.util.StringUtil;

public abstract class Notification {

	private int notifID;
	private User sender;
	private String message;
	private String date;
	
	/**
	 * This is the default constructor of a Notification. 
	 * It is used to make an empty one.
	 */
	public Notification()
	{
	}
	
	/**
	 * This is the constructor of a Notification. 
	 * @param sender, an User, 
	 * @param message, a message
	 * It will create a new empty Notification and it will set the sender and the message.
	 */
	public Notification(User sender, String message)
	{
		this.setSender(sender);
		this.setMessage(message);
	}
	
	/**
	 * @return notifID, an int, the ID of the Notification
	 */
	public int getNotifID() {
		return notifID;
	}

	/**
	 * Set the ID of the Notification
	 * @param notifID, an int
	 */
	public void setNotifID(int notifID) {
		this.notifID = notifID;
	}

	/**
	 * @return sender, an User, the sender of the Notification
	 */
	public User getSender() {
		return sender;
	}

	/**
	 * Set the sender of the Notification
	 * @param sender, an  User
	 */
	public void setSender(User sender) {
		this.sender = sender;
	}

	/**
	 * @return message, a String, the message of the Notification
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set the message of the Notification
	 * @param message, a String
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return date, the Date of the Notification
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Set the date of the Notification
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Notification [notifID=" + notifID + ", sender=" + sender
				+ ", message=" + message + ", date=" + date + "]";
	}
	
	//////////////HOOKS ////////////////
	/**
	 * Load the attributes of a Notification from the persistence
	 */
	public abstract void load();
	
	/**
	 * Save the Notification into the persistence
	 */
	public abstract void save();
	
	/**
	 * Delete the Notification from the persistence
	 */
	public abstract void delete();
}
