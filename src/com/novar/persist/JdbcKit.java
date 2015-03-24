package com.novar.persist;

import java.util.ArrayList;
import java.util.HashMap;

import com.novar.business.Accessory;
import com.novar.business.AccessoryManager;
import com.novar.business.Notification;
import com.novar.business.NotificationManager;
import com.novar.business.NotifyTo;
import com.novar.business.Room;
import com.novar.business.RoomManager;

import com.novar.business.Product;

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
	// ===== DEFINITION METHODS =====
	/**
	 * This is the definition of the method to make a user
	 * @param data of a user
	 * @return the user with persistence methods
	 * @throws FalseFieldsException
	 */
	public UserJdbc makeUser(HashMap<String,Object> data) throws FalseFieldsException
	{
		return new UserJdbc(data);
	}
	
	/**
<<<<<<< HEAD
	 * This is the definition of the method to make a room
	 * @param data of a room
	 * @return the room with persistence methods
	 * @throws FalseFieldsException
	 */
	public RoomJdbc makeRoom(HashMap<String,Object> data) throws FalseFieldsException
	{
		return new RoomJdbc(data);
	}
	
	/**
	 * This is the definition of the method to make a accessory
	 * @param data of a accessory
	 * @return the accessory with persistence methods
	 * @throws FalseFieldsException
	 */
	public AccessoryJdbc makeAccessory(HashMap<String,Object> data)
	{
		return new AccessoryJdbc(data);
	}
	
	/**
	 * This is the definition of the method to make a have (a RoomAccessory)
	 * @param data of a have
	 * @return the have with persistence methods
	 * @throws FalseFieldsException
	 */
	public HaveJdbc makeHave(Room room, Accessory acc, int quantity)
	{
		return new HaveJdbc(room, acc ,quantity);
	}
	
	/**
	 * This is the definition of the method to make a RoomManager
	 * @param data of a RoomManager
	 * @return the RoomManager with persistence methods
	 */
	public RoomManager makeRoomManager()
	{
		return new RoomManagerJdbc();
	}
	
	/**
	 * This is the definition of the method to make a AccessoryManager
	 * @param data of a AccessoryManager
	 * @return the AccessoryManager with persistence methods
	 */
	public AccessoryManager makeAccessoryManager()
	{
		return new AccessoryManagerJdbc();
	}
	
	/**
	 * This is the definition of the method to make a NotificationManager
	 * @param data of a NotificationManager
	 * @return the NotificationManager
	 */
	public NotificationManager makeNotificationManager()
	{
		return new NotificationManagerJdbc();
	}
	/**
	 * This is the definition of the method to make a Notification
	 * @param data of a Notification
	 * @return the Notification
	 */
	public Notification makeNotification(User sender, String message)
	{
		return new NotificationJdbc(sender, message);
	}
	
	/**
	 * This is the definition of the method to make a NotifyTo
	 * @param data of a NotifyTo
	 * @return the NotifyTo
	 */
	public NotifyTo makeNotifyTo(Notification notif, String receiver)
	{
		return new NotifyToJdbc(notif, receiver);
	}
/*
	 * This is the definition of the method to make a MainCategory
	 * @param data of a MainCategory
	 * @return the MainCategory with persistance methods
	 * @throws FalseFieldsException
	 */
	public MainCategoryJdbc makeMainCategory(HashMap<String,Object> data) throws FalseFieldsException
	{
		return new MainCategoryJdbc(data);
	}
	
	/**
	 * This is the definition of the method to make a SubCategory
	 * @param data of a SubCategory
	 * @return the SubCategory with persistance methods
	 * @throws FalseFieldsException
	 */
	public SubCategoryJdbc makeSubCategory(HashMap<String,Object> data) throws FalseFieldsException
	{
		return new SubCategoryJdbc(data);
	}
	
	/**
	 * This is the definition of the method to make a Product
	 * @param data of a Product
	 * @return the Product with persistance methods
	 * @throws FalseFieldsException
	 */
	public ProductJdbc makeProduct(HashMap<String,Object> data) throws FalseFieldsException
	{
		return new ProductJdbc(data);
	}
}
