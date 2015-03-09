package com.novar.persist;

import java.util.ArrayList;
import java.util.HashMap;

import com.novar.business.Address;
import com.novar.business.User;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.LoginFailedException;
import com.novar.persist.PersistKit;
import com.novar.persist.UserJdbc;

/**
 * This class permit for each method to return the specific Object of the persistance with methods that permit to load, save and more.
 * <br><br>
 * This class implements PersistKit and return the specific Object persistance that you want.
 * @author Antoine JOERG
 * @see PersistKit
 */
public class JdbcKit implements PersistKit
{
	// ===== DECLARATION METHODS =====
	
	/**
	 * This is the declaration of the method to make an address
	 * @param data of an address
	 * @return the address with persistance methods
	 * @see AddressJdbc
	 * @see HashMap
	 */
	public AddressJdbc makeAddress(HashMap<String,Object> data)
	{
		return new AddressJdbc(data);
	}
	
	/**
	 * This is the definition of the method to make a user
	 * @param data of a user
	 * @return the user with persistance methods
	 * @throws FalseFieldsException
	 * @see UserJdbc
	 * @see HashMap
	 */
	public UserJdbc makeUser(HashMap<String,Object> data) throws FalseFieldsException
	{
		return new UserJdbc(data);
	}
	
}
