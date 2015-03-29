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
	private ProductFacade productFacade = null;
	private CategoryFacade categoryFacade = null;
	private UsersFacade usersFacade;
	private PlanningFacade planningFacade;
	
	public MainFacade(PersistKit kit)
	{
		this.kit = kit;
		roomFacade = new RoomAccessoryFacade(kit);
		notificationFacade = new NotificationFacade(kit);
		productFacade = new ProductFacade(kit);
		categoryFacade = new CategoryFacade(kit);
		usersFacade = new UsersFacade(kit);
		planningFacade = new PlanningFacade(kit);
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
	
	public ProductFacade getProductFacade()
	{
		return productFacade;
	}
	
	public CategoryFacade getCategoryFacade()
	{
		return categoryFacade;
	}
	
	public UsersFacade getUsersFacade()
	{
		return usersFacade;
	}
	
	public PlanningFacade getPlanningFacade()
	{
		return planningFacade;
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
	public ArrayList<Product> getUserProducts()
	{
		theUser.loadProducts();
		return theUser.getMember().getProducts();
	}

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