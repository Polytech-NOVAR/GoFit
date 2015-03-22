package com.novar.persist;

import java.util.ArrayList;
import java.util.HashMap;

import com.novar.business.*;
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
	 * This is the declaration of the method to make a user
	 * @param data of a user
	 * @return the user
	 * @throws FalseFieldsException
	 */
	public User makeUser(HashMap<String,Object> data) throws FalseFieldsException;
	
	/**
	 * This is the declaration of the method to make a room
	 * @param data of a room
	 * @return the room
	 * @throws FalseFieldsException
	 */
	public Room makeRoom(HashMap<String,Object> data) throws FalseFieldsException;
	/**
	 * This is the declaration of the method to make a accessory
	 * @param data of a accessory
	 * @return the accessory
	 * @throws FalseFieldsException
	 */
	public Accessory makeAccessory(HashMap<String,Object> data);
	
	/**
	 * This is the declaration of the method to make a have (RoomAccessory)
	 * @param data of a have
	 * @return the have
	 * @throws FalseFieldsException
	 */
	public Have makeHave(Room room, Accessory acc, int quantity);
	/**
	 * This is the declaration of the method to make a RoomManager
	 * @param data of a RoomManager
	 * @return the RoomManager
	 */
	public RoomManager makeRoomManager();
	
	/**
	 * This is the declaration of the method to make a AccessoryManager
	 * @param data of a AccessoryManager
	 * @return the AccessoryManager
	 */
	public AccessoryManager makeAccessoryManager();
}
