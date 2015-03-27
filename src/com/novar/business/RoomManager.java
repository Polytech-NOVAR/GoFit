/**
 * This class is an abstract class used to make a RoomManager
 * @author GUILMET Romain
 */

package com.novar.business;

import java.util.ArrayList;

public abstract class RoomManager {
	
	/**
	 * This is the default constructor of a RoomManager. 
	 * It is used to make an empty one.
	 */
	public RoomManager()
	{
		
	}
	
	/**
	 * Load all the Room from the persistence
	 * @return an ArrayList of Room
	 */
	public abstract ArrayList<Room> getAllRooms();
}
