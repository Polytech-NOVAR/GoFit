package com.novar.business;

import java.util.ArrayList;

import com.novar.persist.PersistKit;

public class RoomAccessoryFacade {

	private PersistKit kit = null;
	
	public RoomAccessoryFacade(PersistKit kit)
	{
		this.kit = kit;
	}
	
	public ArrayList<Room> getAllRooms(){
		RoomManager rm = kit.makeRoomManager();
		return rm.getAllRooms();
	}
	
	public ArrayList<Accessory> getAllAccessories(){
		AccessoryManager rm = kit.makeAccessoryManager();
		return rm.getAllAccessories();
	}
}
