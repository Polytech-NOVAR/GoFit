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
	
	public Accessory()
	{
	}
	
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
