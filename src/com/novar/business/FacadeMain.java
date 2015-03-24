package com.novar.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.novar.exception.*;
import com.novar.persist.PersistKit;
import com.novar.util.SendMail;
import com.novar.util.StringUtil;

public class FacadeMain
{
	private User theUser = null;
	private PersistKit kit = null;
	private RoomAccessoryFacade roomFacade;
	private NotificationFacade notificationFacade;
	private FacadeProduct product = null;
	
	public FacadeMain(PersistKit kit)
	{
		this.kit = kit;
		this.roomFacade = new RoomAccessoryFacade(kit);
		this.notificationFacade = new NotificationFacade(kit);
		//product = new FacadeProduct(kit);
	}
	
	public void register(HashMap<String,Object> dataUser) throws RegisterFailedException, FalseFieldsException
	{
		User userInRegistration = kit.makeUser(dataUser);
		userInRegistration.save();
	}
	
	public void forgottenPassword(HashMap<String,Object> dataUser) throws FalseFieldsException, InvalidEmailException, AddressException, MessagingException
	{
		String password = new String();
		password = StringUtil.nextSessionId();
		dataUser.put("password", password);
		User userInForgottenPassword = kit.makeUser(dataUser);
		userInForgottenPassword.updatePassword();
		SendMail.generateAndSendEmail(userInForgottenPassword.getEmail(), password);
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
	public ArrayList<Product> getUserProducts()
	{
		theUser.loadProducts();
		return theUser.getMember().getProducts();
	}
}
