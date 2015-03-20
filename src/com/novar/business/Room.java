package com.novar.business;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.novar.exception.FalseFieldsException;
import com.novar.exception.LoginFailedException;
import com.novar.exception.RegisterFailedException;
import com.novar.exception.SyntaxException;
import com.novar.util.StringUtil;

public abstract class Room {
	
	private int roomID;
	private String num;
	private int area;
	
	private String street;
	private String town;
	private String zipCode;
	private String country;
	
	private ArrayList<Have> accessories = new ArrayList<Have>();

	public Room()
	{
	}
	
	public int getRoomID() {
		return roomID;
	}
	
	public void setRoomID(Integer roomID) {
		this.roomID = roomID;
	}
	
	public String getNum() {
		return num;
	}
	
	public void setNum(String num) {
		this.num = num;
	}
	
	public int getArea() {
		return area;
	}
	
	public void setArea(Integer area) {
		this.area = area;
	}
	
	public String getStreet()
	{
		return street;
	}
	
	public void setStreet(String street) throws SyntaxException
	{
		Pattern pAddress = Pattern.compile("^[0-9]{1,3} [a-zA-Z- ]{2,48}$");
		Matcher mAddress = pAddress.matcher(street);
		if(mAddress.matches())
		{
			this.street = street;
		}
		else
			throw new SyntaxException("street");
	}
	
	public String getTown()
	{
		return town;
	}
	
	public void setTown(String town) throws SyntaxException
	{
		Pattern pTown = Pattern.compile("^[a-zA-Z-]{2,51}$");
		Matcher mTown = pTown.matcher(town);
		if(mTown.matches())
		{
			this.town = town;
		}
		else
			throw new SyntaxException("town");
	}
	
	public String getZipCode()
	{
		return zipCode;
	}
	
	public void setZipCode(String zipCode) throws SyntaxException
	{
		Pattern pZip = Pattern.compile("^[0-9]{5}$");
		Matcher mZip = pZip.matcher(zipCode);
		if(mZip.matches())
		{
			this.zipCode = zipCode;
		}
		else
			throw new SyntaxException("zipCode");
	}
	
	public String getCountry()
	{
		return country;
	}
	
	public void setCountry(String country) throws SyntaxException
	{
		Pattern pCountry = Pattern.compile("^[a-zA-Z-]{2,51}$");
		Matcher mCountry = pCountry.matcher(country);
		if(mCountry.matches())
		{
			this.country = country;
		}
		else
			throw new SyntaxException("country");
	}
	
	public ArrayList<Have> getAccessories() {
		return accessories;
	}
	
	public void setAccessories(ArrayList<Have> accessories) {
		this.accessories = accessories;
	}

	@Override
	public String toString() {
		return "Room [roomID=" + roomID + ", num=" + num + ", area=" + area
				+ ", street=" + street + ", town=" + town + ", zipCode="
				+ zipCode + ", country=" + country +  "]";
	}
	
	//////////////HOOKS ////////////////
	public abstract void load();
	public abstract void save();
	public abstract void update();
	public abstract void delete();
	public abstract void loadAccessories();
	
}
