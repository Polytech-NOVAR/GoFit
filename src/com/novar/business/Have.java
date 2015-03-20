package com.novar.business;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import com.novar.exception.FalseFieldsException;
import com.novar.util.StringUtil;

public abstract class Have {

	private Room room;
	private Accessory acc;
	private int quantity;
	
	public Have()
	{
	}
	
	public Have(HashMap<String,Object> data) throws FalseFieldsException
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
		if(!errors.isEmpty())
			throw new FalseFieldsException(errors);
	}
	
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Accessory getAcc() {
		return acc;
	}

	public void setAcc(Accessory acc) {
		this.acc = acc;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Have [room=" + room + ", acc=" + acc + ", quantity=" + quantity + "]";
	}
	
	//////////////HOOKS ////////////////
	public abstract void load();
	public abstract void save();
	public abstract void update();
	public abstract void delete();
	
}
