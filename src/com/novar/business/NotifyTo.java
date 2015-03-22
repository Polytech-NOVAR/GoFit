package com.novar.business;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import com.novar.util.StringUtil;

public abstract class NotifyTo {

	private Notification notif;
	private String receiver;
	private boolean view = false;
	
	public NotifyTo()
	{
	}
	
	public NotifyTo(Notification notif, String receiver)
	{
		this.setNotif(notif);
		this.setReceiver(receiver);
	}
	
	public Notification getNotif() {
		return notif;
	}
	
	public void setNotif(Notification notif) {
		this.notif = notif;
	}
	
	public String getReceiver() {
		return receiver;
	}
	
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	public boolean isView() {
		return view;
	}

	public void setView(boolean view) {
		this.view = view;
	}

	@Override
	public String toString() {
		return "NotifyTo [notif=" + notif + ", receiver=" + receiver
				+ ", view=" + view + "]";
	}
	
	//////////////HOOKS ////////////////
	public abstract void save();
	public abstract void delete();
	public abstract void update();
}
