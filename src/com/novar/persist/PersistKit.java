package com.novar.persist;

import java.util.ArrayList;
import java.util.HashMap;

import com.novar.business.Address;
import com.novar.business.User;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.LoginFailedException;

/**
 * This interface permit to change persistance how the developer decides. For the moment the persistance is only JDBC.
 * <br><br>
 * The persistance can change easily whit this implementation, the developer just has to add a kit, like SrKit (for Serializable class) and in the facade, the developer has to change in the constructor the change of the persistance kit.
 * <br><br>
 * This interface is use in FacadeMain and JdbcKit implements this interface
 * @author Antoine JOERG
 * @see FacadeMain
 * @see JdbcKit
 */
public interface PersistKit
{
	// ===== DECLARATION METHODS =====
	
	/**
	 * This is the declaration of the method to make an address
	 * @param data of an address
	 * @return the address
	 * @see Address
	 * @see HashMap
	 */
	public Address makeAddress(HashMap<String,Object> data) throws FalseFieldsException;
	
	/**
	 * This is the declaration of the method to make a user
	 * @param data of a user
	 * @return the user
	 * @throws FalseFieldsException
	 * @see User
	 * @see HashMap
	 */
	public User makeUser(HashMap<String,Object> data) throws FalseFieldsException;
	
}
