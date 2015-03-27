/**
 * This class is an abstract class used to make an AccessoryManager.
 * @author GUILMET Romain
 */

package com.novar.business;

import java.util.ArrayList;

public abstract class AccessoryManager {

	/**
	 * This is the default constructor of an AccessoryManager. 
	 * It is used to make an empty one.
	 */
	public AccessoryManager()
	{
		
	}
	
	/**
	 * Load all the Accessory from the persistence
	 * @return an ArrayList of Accessory
	 */
	public abstract ArrayList<Accessory> getAllAccessories();
}
