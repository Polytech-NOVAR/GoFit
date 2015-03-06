package com.novar.business;

import java.lang.reflect.Method;
import java.util.HashMap;

import com.novar.util.StringUtil;

public abstract class Address
{
	private Integer addressID;
	private String street;
	private String town;
	private String zipCode;
	private String country;
	
	public Address(HashMap<String,Object> data)
	{
		
		Class[] typeArg = new Class[1];
		Object[] arg = new Object[1];
		
		for (String mapKey : data.keySet())
		{
			String setterName = "set" + StringUtil.toCapitalizeCase(mapKey);
			typeArg[0] = data.get(mapKey).getClass();
			arg[0] = data.get(mapKey);
			
			try
			{
				Method setter = this.getClass().getMethod(setterName, typeArg);
				setter.invoke(this, arg);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public Integer getAddressID()
	{
		return addressID;
	}

	public void setAddressID(Integer addressID)
	{
		this.addressID = addressID;
	}

	public String getStreet()
	{
		return street;
	}
	
	public void setStreet(String street)
	{
		this.street = street;
	}
	
	public String getTown()
	{
		return town;
	}
	
	public void setTown(String town)
	{
		this.town = town;
	}
	
	public String getZipCode()
	{
		return zipCode;
	}
	
	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}
	
	public String getCountry()
	{
		return country;
	}
	
	public void setCountry(String country)
	{
		this.country = country;
	}
	
	public String toString()
	{
		return ("ID : " + this.getAddressID() + "\n"
				+ "Street : " + this.getStreet() + "\n"
				+ "Town : " + this.getTown() + "\n"
				+ "ZipCode : " + this.getZipCode() + "\n"
				+ "Country : " + this.getCountry());
	}
	
	public void insert(){}
}
