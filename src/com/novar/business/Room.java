package com.novar.business;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.novar.exception.FalseFieldsException;
import com.novar.exception.SyntaxException;
import com.novar.util.StringUtil;

public class Room {
	
	private int roomID;
	private String num;
	private int area;
	
	private String street;
	private String town;
	private String zipCode;
	private String country;
	
	public Room(HashMap<String,Object> data) throws FalseFieldsException
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
	
	public int getRoomID() {
		return roomID;
	}
	
	public void setRoomID(int roomID) {
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
	
	public void setArea(int area) {
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
	
	
}
