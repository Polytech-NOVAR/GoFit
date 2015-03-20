package com.novar.business;

import java.util.ArrayList;

public abstract class Accessory {
	
	private int accID;
	private String name;
	
	private ArrayList<Have> rooms = new ArrayList<Have>();
	
	public Accessory()
	{
	}
	
	public int getAccID() {
		return accID;
	}
	
	public void setAccID(Integer accID) {
		this.accID = accID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Accessory [accID=" + accID + ", name=" + name + "]";
	}
	
	public ArrayList<Have> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<Have> rooms) {
		this.rooms = rooms;
	}
	
	//////////////HOOKS ////////////////
	public abstract void load();
	public abstract void save();
	public abstract void update();
	public abstract void delete();
	public abstract void loadRooms();
}
