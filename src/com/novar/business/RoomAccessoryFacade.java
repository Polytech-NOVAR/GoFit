/**
 * This class is a facade to access to all the Room and Accessory methods.
 * @author GUILMET Romain
 */

package com.novar.business;

import java.util.ArrayList;
import java.util.HashMap;

import com.novar.exception.FalseFieldsException;
import com.novar.persist.PersistKit;

public class RoomAccessoryFacade {

	private PersistKit kit = null;
	
	/**
	 * This is the default constructor of a RoomAccessoryFacade. 
	 * @param kit, the PersistKit selected 
	 */
	public RoomAccessoryFacade(PersistKit kit)
	{
		this.kit = kit;
	}
	
	// RoomManager Method
	/**
	 * Load all the Room from the persistence
	 * @return an ArrayList of Room
	 */
	public ArrayList<Room> getAllRooms(){
		RoomManager rm = kit.makeRoomManager();
		return rm.getAllRooms();
	}
	
	// AccessoryManager Method
	/**
	 * Load all the Accessory from the persistence
	 * @return an ArrayList of Accessory
	 */
	public ArrayList<Accessory> getAllAccessories(){
		AccessoryManager rm = kit.makeAccessoryManager();
		return rm.getAllAccessories();
	}
	
	// Room Methods
	/**
	 * Load a Room and it's accessories from the persistence
	 * @param room, a Room
	 * @return room, the Room loaded from the persistence
	 */
	public Room getRoom(Room room){
		room.load();
		room.loadAccessories();
		return room;	
	}
	
	/**
	 * Create a Room in the persistence
	 * @param mapRoom, a Hashmap
	 * @return room, the Room saved into the persistence
	 * @throws FalseFieldsException, if one regexp doesn't match
	 */
	public Room createRoom(HashMap<String,Object> mapRoom) throws FalseFieldsException{
		Room room = kit.makeRoom(mapRoom);
		room.save();
		return room;	
	}
	
	/**
	 * Update a Room in the persistence
	 * @param mapRoom, a Hashmap
	 * @return room, the Room updated into the persistence
	 * @throws FalseFieldsException, if one regexp doesn't match
	 */
	public Room updateRoom(HashMap<String,Object> mapRoom) throws FalseFieldsException{
		Room room = kit.makeRoom(mapRoom);
		room.update();
		return room;	
	}
	
	/**
	 * Delete a Room from the persistence
	 * @param room, the Room to delete
	 */
	public void deleteRoom(Room room){
		room.delete();
	}
	
	/**
	 * Add an Accessory and it's quantity to a Room
	 * @param room, the Room which have a new Accessory
	 * @param acc, the Accessory
	 * @param quantity, an int, the quantity of this Accessory
	 * @return room, the Room updated in the persistence
	 */
	public Room addAccessory(Room room, Accessory acc, int quantity)
	{
		room.addAccessory(acc, quantity);
		return room;
	}
	
	// Accessory Methods
	/**
	 * Load an Accessory and it's rooms from the persistence
	 * @param acc, an Accessory
	 * @return acc, the Accessory loaded from the persistence
	 */
	public Accessory getAccessory(Accessory acc){
		acc.load();
		acc.loadRooms();
		return acc;	
	}
	
	/**
	 * Create an Accessory in the persistence
	 * @param mapRoom, a Hashmap
	 * @return acc, the Accessory saved into the persistence
	 */
	public Accessory createAccessory(HashMap<String,Object> mapAcc){
		Accessory acc = kit.makeAccessory(mapAcc);
		acc.save();
		return acc;	
	}
	
	/**
	 * Update an Accessory in the persistence
	 * @param mapRoom, a Hashmap
	 * @return acc, the Accessory updated into the persistence
	 */
	public Accessory updateAccessory(HashMap<String,Object> mapAcc){
		Accessory acc = kit.makeAccessory(mapAcc);
		acc.update();
		return acc;	
	}

	/**
	 * Delete an Accessory from the persistence
	 * @param acc, the Accessory to delete
	 */
	public void deleteAccessory(Accessory acc){
		acc.delete();
	}
	
	// RoomAccessory/Have Methods
	/**
	 * Delete a Have from the persistence
	 * @param have, the Have to delete
	 */
	public void removeHave(Have have)
	{
		have.delete();
	}
	
	/**
	 * Update a Have in the persistence
	 * @param have, the Have to update
	 * @param quantity, an int, the new quantity
	 * @return have, the Have updated
	 */
	public Have updateHave(Have have, int quantity)
	{
		have = kit.makeHave(have.getRoom(), have.getAcc(), quantity);
		have.update();
		return have;
	}
}
