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
	private ProductFacade product = null;
	private CategoryFacade category = null;
	
	public MainFacade(PersistKit kit)
	{
		this.kit = kit;
		product = new ProductFacade(kit);
		category = new CategoryFacade(kit);
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
	
	public ArrayList<Product> getUserProducts()
	{
		theUser.loadProducts();
		return theUser.getMember().getProducts();
	}
	
	public ProductFacade getProductFacade()
	{
		return product;
	}
	
	public CategoryFacade getCategoryFacade()
	{
		return category;
	}
}
