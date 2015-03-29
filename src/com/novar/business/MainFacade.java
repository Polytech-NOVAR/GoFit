package com.novar.business;

import java.util.ArrayList;
import java.util.HashMap;

import com.novar.exception.*;
import com.novar.persist.PersistKit;
import com.novar.util.StringUtil;

public class MainFacade
{
	private User theUser = null;
	private PersistKit kit = null;
	private RoomAccessoryFacade roomFacade;
	private NotificationFacade notificationFacade;
	private ActivityEventFacade ActEvFacade;
	
	public MainFacade(PersistKit kit)
	{
		this.kit = kit;
		this.roomFacade = new RoomAccessoryFacade(kit);
		this.notificationFacade = new NotificationFacade(kit);
		this.ActEvFacade= new ActivityEventFacade(kit);
	}
	
	public void register(HashMap<String,Object> dataUser) throws RegisterFailedException, FalseFieldsException
	{
		User userInRegistration = kit.makeUser(dataUser);
		userInRegistration.save();
	}
	
	public void login(HashMap<String,Object> dataUser) throws LoginFailedException, FalseFieldsException
	{
		if (theUser == null)
		{
			User userInLogin = kit.makeUser(dataUser);
			userInLogin.load();
			theUser = userInLogin;
		}
	}
	
	public User getUser()
	{
		return this.theUser;
	}
	
	public RoomAccessoryFacade getRoomFacade()
	{
		return this.roomFacade;
	}
	
	public NotificationFacade getNotificationFacade()
	{
		return this.notificationFacade;
	}
	

	public ActivityEventFacade getActEvFacade()
	{
		return this.ActEvFacade;
	}


}
