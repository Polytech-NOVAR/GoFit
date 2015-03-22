package com.novar.business;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import com.novar.util.StringUtil;

public abstract class Notification {

	private int notifID;
	private User sender;
	private String message;
	
	public Notification()
	{
	}
	
	public Notification(User sender, String message)
	{
		this.setSender(sender);
		this.setMessage(message);
	}
	
	public int getNotifID() {
		return notifID;
	}

	public void setNotifID(int notifID) {
		this.notifID = notifID;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Notification [notifID=" + notifID + ", sender=" + sender
				+ ", message=" + message + "]";
	}
	
	//////////////HOOKS ////////////////
	public abstract void load();
	public abstract void save();
	public abstract void delete();
}
