package com.novar.business;

import java.util.ArrayList;
import java.util.HashMap;

import com.novar.exception.*;
import com.novar.persist.PersistKit;
import com.novar.util.StringUtil;

public class FacadeMain {
	
	private User theUser;
	private PersistKit kit;
	
	public FacadeMain(PersistKit kit)
	{
		this.kit = kit;
	}
	
	public void register(HashMap<String,Object> dataUser, HashMap<String,Object> dataAddress) throws Exception
	{
		Address registrationAdress = kit.makeAddress(dataAddress);
		ArrayList<Address> listAdress = new ArrayList<Address>(); 
		listAdress.add(registrationAdress); //Une seule adresse pour l'inscription
		
		dataUser.put("address", listAdress);
		
		User userInRegistration = kit.makeUser(dataUser);
		userInRegistration.save();
	}
	
	public void login(HashMap<String,Object> dataUser) throws LoginFailedException, FalseFieldsException
	{
		theUser = kit.makeUser(dataUser);
		theUser.load();
	}

}
