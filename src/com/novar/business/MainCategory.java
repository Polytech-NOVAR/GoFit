package com.novar.business;

import java.util.HashMap;
import com.novar.exception.FalseFieldsException;

/**
 * This abstract class represent a product's main category. 
 * It's a category which is at the top level of the hierarchy.
 * @author Antoine JOERG
 */

public abstract class MainCategory extends Category
{
	public MainCategory()
	{
		super();
	}
	
	public MainCategory(HashMap<String,Object> data) throws FalseFieldsException

	{
		super(data);
	}
	

}
