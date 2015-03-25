package com.novar.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.novar.exception.*;
import com.novar.persist.PersistKit;
import com.novar.util.SendMail;
import com.novar.util.StringUtil;

public class MainFacade
{
	private User theUser = null;
	private PersistKit kit = null;
	private RoomAccessoryFacade roomFacade;
	private NotificationFacade notificationFacade;
	private ProductFacade product = null;
	
	public MainFacade(PersistKit kit)
	{
		this.kit = kit;
		this.roomFacade = new RoomAccessoryFacade(kit);
		this.notificationFacade = new NotificationFacade(kit);
		//product = new FacadeProduct(kit);
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
	
	// Disconnected methods
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
	
	public void forgottenPassword(HashMap<String,Object> dataUser) throws FalseFieldsException, InvalidEmailException, AddressException, MessagingException
	{
		String password = new String();
		password = StringUtil.nextSessionId();
		dataUser.put("password", password);
		User userInForgottenPassword = kit.makeUser(dataUser);
		userInForgottenPassword.updateForgottenPassword();
		SendMail.generateAndSendEmail(userInForgottenPassword.getEmail(), password);
	}
	
	// Profile methods
	public void updateTheUserProfile(HashMap<String,Object> dataUser) throws FalseFieldsException, SQLException
	{
		theUser.setAll(dataUser);
		theUser.updateProfile();
	}
	
	public void updateTheUserPassword(HashMap<String,Object> dataUser) throws FalseFieldsException, SQLException
	{
		theUser.setAll(dataUser);
		theUser.updatePassword();
	}
	
	public void deleteTheUser() throws SQLException
	{
		theUser.delete();
		//logoff
	}
}