package com.novar.business;

import java.awt.EventQueue;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.novar.exception.FalseFieldsException;
import com.novar.exception.LoginFailedException;
import com.novar.exception.RegisterFailedException;
import com.novar.exception.SyntaxException;
import com.novar.persist.JdbcKit;
import com.novar.persist.PersistKit;
import com.novar.ui.LoginWindow;
import com.novar.util.ConnectionUtil;
import com.novar.util.StringUtil;

/**
 * This abstract class represent a product's category. 
 * It could be a MainCategory or a SubCategory. The design pattern General Hierarchy is used to implements the hierarchy of categories.
 * @author Antoine JOERG
 * @see MainCategory
 * @see SubCategory
 */

public abstract class Category 
{
	
	private Integer catID;
	
	/**
	  * maximal size : 257 characters
	  */
	private String description;	
	
	
	public Category()
	{
		
	}
	
	/**
	 * Constructs a Category whit data of the hashmap. The number of data of the hashmap is not fixed. 
	 * For each couple (key, value) the right setter is called.
	 * @param data Its keys are the names of instance's variables. Its values are the data to affect.
	 * @throws FalseFieldsException throw if one condition at least is not respected in the setters.
	 */
	public Category(HashMap<String,Object> data) throws FalseFieldsException
	{
		set(data);
	}
	
	/**
	 * sets a Category whit data of the hashmap. The number of data of the hashmap is not fixed. 
	 * For each couple (key, value) the right setter is called.
	 * @param data Its keys are the names of instance's variables. Its values are the data to affect.
	 * @throws FalseFieldsException throw if one condition at least is not respected in the setters.
	 */
	public void set(HashMap<String,Object> data) throws FalseFieldsException
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
	
	public Integer getCatID() 
	{
		return catID;
	}
	
	public void setCatID(Integer catID) 
	{
		this.catID = catID;
	}

	public String getDescription() 
	{
		return description;
	}
	
	public void setDescription(String description) throws SyntaxException
	{
		Pattern pDesc = Pattern.compile("^[a-zA-Z-0-9- -._]{1,256}$");
		Matcher mDesc = pDesc.matcher(description);
		if(mDesc.matches())
		{
			this.description = description;
		}
		else
			throw new SyntaxException("description");

	}
	

	@Override
	public String toString() {
		return  description;
	}
	
	
	// METHODS DESIGNED TO BE OVERRIDDEN BY CONCRETE SUBCLASSES, THEY MUST BE IMPLEMENTED ---------

	/**
	 * Load the category with all his attributes. The catID must be not null.
	 */
	public abstract void load();
	
	/**
	 * Save the category with all his attributes.
	 */
	public abstract void save();
	
	/**
	 * Update the category with all his attributes. The catID must be not null.
	 */
	public abstract void update();
	
	/**
	 * Delete the category with. The catID must be not null.
	 */
	public abstract void delete();
	
	
	
}
