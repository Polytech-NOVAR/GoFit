package com.novar.business;

import java.util.ArrayList;
import java.util.HashMap;

import com.novar.exception.FalseFieldsException;
import com.novar.persist.PersistKit;

public class RoomAccessoryFacade {

	private PersistKit kit = null;
	
	public RoomAccessoryFacade(PersistKit kit)
	{
		this.kit = kit;
	}
	
	// RoomManager Method
	public ArrayList<Room> getAllRooms(){
		RoomManager rm = kit.makeRoomManager();
		return rm.getAllRooms();
	}
	
	// AccessoryManager Method
	public ArrayList<Accessory> getAllAccessories(){
		AccessoryManager rm = kit.makeAccessoryManager();
		return rm.getAllAccessories();
	}
	
	// Room Methods
	public Room getRoom(Room room){
		room.load();
		room.loadAccessories();
		return room;	
	}
	
	public Room createRoom(HashMap<String,Object> mapRoom) throws FalseFieldsException{
		Room room = kit.makeRoom(mapRoom);
		room.save();
		return room;	
	}
	
	public Room updateRoom(HashMap<String,Object> mapRoom) throws FalseFieldsException{
		Room room = kit.makeRoom(mapRoom);
		room.update();
		return room;	
	}
	
	public void deleteRoom(Room room){
		room.delete();
	}
	
	public Room addAccessory(Room room, Accessory acc, int quantity)
	{
		room.addAccessory(acc, quantity);
		return room;
	}
	
	// Accessory Methods
	public Accessory getAccessory(Accessory acc){
		acc.load();
		acc.loadRooms();
		return acc;	
	}
	
	public Accessory createAccessory(HashMap<String,Object> mapAcc){
		Accessory acc = kit.makeAccessory(mapAcc);
		acc.save();
		return acc;	
	}
	
	public Accessory updateAccessory(HashMap<String,Object> mapAcc){
		Accessory acc = kit.makeAccessory(mapAcc);
		acc.update();
		return acc;	
	}
	
	public void deleteAccessory(Accessory acc){
		acc.delete();
	}
	
	// RoomAccessory/Have Methods
	public void removeHave(Have have)
	{
		have.delete();
	}
	
	public Have updateHave(Have have, int quantity)
	{
		have = kit.makeHave(have.getRoom(), have.getAcc(), quantity);
		have.update();
		return have;
	}
}
