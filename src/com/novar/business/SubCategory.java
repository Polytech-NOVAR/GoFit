package com.novar.business;

import java.util.HashMap;
import com.novar.exception.FalseFieldsException;
/**
 * This abstract class represent a product's sub category. 
 * It's a category which have a parent. The parent is a MainCategory.
 * @author Antoine JOERG
 */

public abstract class SubCategory extends Category 
{
	
	private MainCategory parent;
	
	public SubCategory()
	{
		super();
	}
	
	public SubCategory(HashMap<String,Object> data) throws FalseFieldsException
	{
		super(data);
	}

	public Category getParent() 
	{
		return parent;
	}

	public void setParent(MainCategory parent) 
	{
		this.parent = parent;
	}
	
	

}
