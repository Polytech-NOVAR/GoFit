package com.novar.business;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import com.novar.exception.FalseFieldsException;
import com.novar.util.StringUtil;
/**
 * This class is an abstract class used to make a Have
 * Have is the link between the classes Accessory and Room.
 * @author GUILMET Romain
 */

public abstract class Have {

	private Room room;
	private Accessory acc;
	private int quantity;
	

	/**
	 * This is the default constructor of a Have. 
	 * It is used to make an empty one.
	 */
	public Have()
	{
	}
	
	/**
	 * This is the constructor of a Have. 
	 * @param an HashMap
	 * It will create a new empty Have and it will use all the setter in the HashMap.
	 */
	public Have(Room room, Accessory acc, int quantity)
	{
		this.setRoom(room);
		this.setAcc(acc);
		this.setQuantity(quantity);
	}
	
	/**
	 * @return room, A Room
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * Set the Room of the Have
	 * @param room
	 */
	public void setRoom(Room room) {
		this.room = room;
	}

	/**
	 * @return acc, an Accessory
	 */
	public Accessory getAcc() {
		return acc;
	}

	/**
	 * Set the Accessory of the Have
	 * @param acc
	 */
	public void setAcc(Accessory acc) {
		this.acc = acc;
	}

	/**
	 * @return quantity, an int, the quantity of the Accessory the Room have
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Set the quantity of the Have
	 * @param quantity, an int
	 */
	public void setQuantity(Integer quantity){
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Have [room=" + room + ", acc=" + acc + ", quantity=" + quantity + "]";
	}
	
	//////////////HOOKS ////////////////
	/**
	 * Load the attributes of a Have from the persistence
	 */
	public abstract void load();
	
	/**
	 * Save the Have into the persistence
	 */
	public abstract void save();
	
	/**
	 * Update the Have in the persistence
	 */
	public abstract void update();
	
	/**
	 * Delete the Have from the persistence
	 */
	public abstract void delete();
	
}
