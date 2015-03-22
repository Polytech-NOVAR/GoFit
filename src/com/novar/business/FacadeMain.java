package com.novar.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.SQLException;

import com.novar.exception.*;
import com.novar.persist.PersistKit;
import com.novar.util.StringUtil;

public class FacadeMain
{
	private User theUser = null;
	private PersistKit kit = null;
	private FacadeProduct product = null;
	
	public FacadeMain(PersistKit kit)
	{
		this.kit = kit;
		//product = new FacadeProduct(kit);
	}
	
	public void register(HashMap<String,Object> dataUser) throws RegisterFailedException, FalseFieldsException
	{
		User userInRegistration = kit.makeUser(dataUser);
		userInRegistration.save();
	}
	
	public void forgottenPassword(HashMap<String,Object> dataUser) throws RegisterFailedException, FalseFieldsException, SQLException
	{
		User userInForgottenPassword = kit.makeUser(dataUser);
		userInForgottenPassword.updatePassword();
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
	
	public ArrayList<Product> getUserProducts()
	{
		theUser.loadProducts();
		return theUser.getMember().getProducts();
	}
}
