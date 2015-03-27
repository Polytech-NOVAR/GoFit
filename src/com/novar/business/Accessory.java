/**
 * This class is an abstract class used to make an Accessory.
 * @author GUILMET Romain
 */
package com.novar.business;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import com.novar.exception.FalseFieldsException;
import com.novar.util.StringUtil;

public abstract class Accessory {

	private int accID;
	private String name;
	
	private ArrayList<Have> rooms = new ArrayList<Have>();
	
	/**
	 * This is the default constructor of an Accessory. 
	 * It is used to make an empty one.
	 */
	public Accessory()
	{
	}
	
	/**
	 * This is the constructor of an Accessory. 
	 * @param data, an HashMap
	 * It will create a new empty Accessory and it will use all the setter in the HashMap.
	 */
	public Accessory(HashMap<String,Object> data)
	{
		Class[] typeArg = new Class[1];
		Object[] arg = new Object[1];
		ArrayList<String> errors = new ArrayList<String>();
		
		for (String mapKey : data.keySet())
		{
			String setterName = "set" + StringUtil.toCapitalizeCase(mapKey);
			typeArg[0] = data.get(mapKey).getClass();
			arg[0] = data.get(mapKey);
			Method setter;
			try {
				setter = this.getClass().getMethod(setterName, typeArg);
				try
				{
					setter.invoke(this, arg);
				}
				catch (Exception e)
				{
					errors.add(e.getCause().getMessage());
				}
			} catch (NoSuchMethodException | SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * @return accID, an int, the ID of an Accessory
	 */
	public int getAccID() {
		return accID;
	}
	
	/**
	 * Set the ID of an Accessory
	 * @param accID, an int
	 */
	public void setAccID(Integer accID) {
		this.accID = accID;
	}
	
	/**
	 * @return name, a String, the name of an Accessory
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the name of an Accessory
	 * @param name, a String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return rooms, an ArrayList of Have, all the rooms which have this accessory and the quantity they have.
	 */
	public ArrayList<Have> getRooms() {
		return rooms;
	}
	
	/**
	 * Set the rooms which have this Accessory and the quantity they have
	 * @param rooms, an ArrayList of Have
	 */
	public void setRooms(ArrayList<Have> rooms) {
		this.rooms = rooms;
	}
	
	@Override
	public String toString() {
		return "Accessory [accID=" + accID + ", name=" + name + "]";
	}
	
	//////////////HOOKS ////////////////
	/**
	 * Load the attributes of an Accessory from the persistence
	 */
	public abstract void load();
	
	/**
	 * Save the Accessory into the persistence
	 */
	public abstract void save();
	
	/**
	 * Update the Accessory in the persistence
	 */
	public abstract void update();
	
	/**
	 * Delete the Accessory from the persistence
	 */
	public abstract void delete();
	
	/**
	 * Load the Have, all the rooms which have the Accessory and the quantity of it they all have, from the persistence
	 */
	public abstract void loadRooms();
}
