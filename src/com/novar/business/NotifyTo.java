/**
 * This class is an abstract class used to make a NotifyTo.
 * @author GUILMET Romain
 */

package com.novar.business;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import com.novar.util.StringUtil;

public abstract class NotifyTo {

	private Notification notif;
	private String receiver;
	private boolean view = false;

	/**
	 * This is the default constructor of a NotifyTo. 
	 * It is used to make an empty one.
	 */
	public NotifyTo()
	{
	}
	
	/**
	 * This is the constructor of a NotifyTo 
	 * @param notif, a Notification
	 * @param receiver, the receiver of this Notification
	 * It will create a new empty NotifyTo and it will set the notif and the receiver.
	 */
	public NotifyTo(Notification notif, String receiver)
	{
		this.setNotif(notif);
		this.setReceiver(receiver);
	}
	
	/**
	 * @return notif, the Notification
	 */
	public Notification getNotif() {
		return notif;
	}
	
	/**
	 * Set the Notification
	 * @param notif
	 */
	public void setNotif(Notification notif) {
		this.notif = notif;
	}
	
	/**
	 * @return receiver, an User who receive the Notification
	 */
	public String getReceiver() {
		return receiver;
	}
	
	/**
	 * Set the receiver of the Notification
	 * @param receiver, an User
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	/**
	 * @return view, a boolean (true if the Notification was viewed by the receiver)
	 */
	public boolean isView() {
		return view;
	}

	/**
	 * Set the boolean view
	 * @param view
	 */
	public void setView(boolean view) {
		this.view = view;
	}

	@Override
	public String toString() {
		return "NotifyTo [notif=" + notif + ", receiver=" + receiver
				+ ", view=" + view + "]";
	}
	
	//////////////HOOKS ////////////////
	/**
	 * Save the NotifyTo into the persistence
	 */
	public abstract void save();
	
	/**
	 * Update the NotifyTo in the persistence
	 */
	public abstract void update();
	
	/**
	 * Delete the NotifyTo from the persistence
	 */
	public abstract void delete();
}
