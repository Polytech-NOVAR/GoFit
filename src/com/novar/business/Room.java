/**
 * This class is an abstract class used to make a Room.
 * @author GUILMET Romain
 */

package com.novar.business;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.novar.exception.FalseFieldsException;
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

	private TypeRoom type;

	/**
	 * This is the default constructor of a Room. 
	 * It is used to make an empty one.
	 */
	public Room()
	{
	}
	
	/**
	 * This is the constructor of a Room. 
	 * @param data, an HashMap
	 * It will create a new empty Room and it will use all the setter in the HashMap.
	 */
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
	
	/**
	 * @return roomID, an int, the ID of the Room
	 */
	public int getRoomID() {
		return roomID;
	}
	
	/**
	 * Set the ID of the Room
	 * @param roomID, an int
	 */
	public void setRoomID(Integer roomID) {
		this.roomID = roomID;
	}
	
	/**
	 * @return num, a String,the number of the Room
	 */
	public String getNum() {
		return num;
	}
	
	/**
	 * Set the number of the Room
	 * @param num, a String
	 */
	public void setNum(String num) {
		this.num = num;
	}
	
	/**
	 * @return area, an int, the area of the Room
	 */
	public int getArea() {
		return area;
	}
	
	/**
	 * Set the area of the room
	 * @param area, an int
	 */
	public void setArea(Integer area){
		this.area = area;
	}
	
	/**
	 * @return street, a String, the street of the Room
	 */
	public String getStreet()
	{
		return street;
	}
	
	/**
	 * Set the street of the Room
	 * @param street, a String
	 * @throws SyntaxException, if the param doesn't match the regexp
	 */
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
	
	/**
	 * @return town, a String, the town of the Room
	 */
	public String getTown()
	{
		return town;
	}
	
	/**
	 * Set the town of the Room
	 * @param town, a String
	 * @throws SyntaxException, if the param doesn't match the regexp
	 */
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
	
	/**
	 * @return zipCode, a String, the zipCode of the Room
	 */
	public String getZipCode()
	{
		return zipCode;
	}
	
	/**
	 * Set the zipCode of the Room
	 * @param zipCode, a String
	 * @throws SyntaxException, if the param doesn't match the regexp
	 */
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
	
	/**
	 * @return country, a String, the Country of the Room
	 */
	public String getCountry()
	{
		return country;
	}
	
	/**
	 * Set the country of the Room
	 * @param country, a String
	 * @throws SyntaxException, if the param doesn't match the regexp
	 */
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
	
	/**
	 * @return accessories, an ArrayList of Have, all the accessories which have this room and the quantity it have.
	 */
	public ArrayList<Have> getAccessories() {
		return accessories;
	}

	/**
	 * Set the accessories which have this Room and the quantity it have
	 * @param accesories, an ArrayList of Have
	 */
	public void setAccessories(ArrayList<Have> accessories) {
		this.accessories = accessories;
	}

	/**
	 * @return type, a TypeRoom, the type of the Room (i.e an Office or a ClassRoom)
	 */
	public TypeRoom getType() {
		return type;
	}

	/**
	 * Set the type of the Room to an Office
	 * @param type
	 */
	public void setType(Office type) {
		this.type = type;
	}
	
	/**
	 * Set the type of the Room to a ClassRoom
	 * @param type
	 */
	public void setType(ClassRoom type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Room [roomID=" + roomID + ", num=" + num + ", area=" + area
				+ ", street=" + street + ", town=" + town + ", zipCode="
				+ zipCode + ", country=" + country + ", accessories="
				+ accessories + ", type=" + type + "]";
	}
	
	//////////////HOOKS ////////////////
	/**
	 * Load the attributes of a Room from the persistence
	 */
	public abstract void load();
	
	/**
	 * Save the Room into the persistence
	 */
	public abstract void save();
	
	/**
	 * Update the Room in the persistence
	 */
	public abstract void update();
	
	/**
	 * Delete the Room from the persistence
	 */
	public abstract void delete();
	
	/**
	 * Load the Type of the Room from the persistence
	 */
	public abstract void loadType();
	
	/**
	 * Load the Have, all the accessories which have the Room and the quantity of them, from the persistence.
	 */
	public abstract void loadAccessories();
	
	/**
	 * Add an Accessory to this Room and save it into the persistence
	 * @param acc, an Accessory
	 * @param quantity, an int, the quantity of this Accessory
	 */
	public abstract void addAccessory(Accessory acc, int quantity);
	
}
