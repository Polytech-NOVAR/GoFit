package com.novar.business;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import com.novar.exception.FalseFieldsException;
import com.novar.exception.LoginFailedException;
import com.novar.exception.RegisterFailedException;
import com.novar.util.StringUtil;

public abstract class Have {

	private Room room;
	private Accessory acc;
	private int quantity;
	
	public Have()
	{
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
	/*public abstract void update();
	public abstract void delete();*/
	
}
