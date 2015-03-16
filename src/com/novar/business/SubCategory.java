package com.novar.business;

import java.util.HashMap;
import com.novar.exception.FalseFieldsException;
/**
 * This abstract class represent a product's sub category. 
 * It's a category which have a parent. The parent could be a SubCategory or a MainCategory.
 * @author Antoine JOERG
 */

public abstract class SubCategory extends Category {
	
	private Category parent;
	
	public SubCategory(HashMap<String,Object> data) throws FalseFieldsException
	{
		super(data);
	}

	public Category getParent() 
	{
		return parent;
	}

	public void setParent(Category parent) 
	{
		this.parent = parent;
	}

	@Override
	public String toString() 
	{
		return "SubCategory [parent=" + parent + ", CatID=" + getCatID()
				+ ", description=" + getDescription() + "]\n";
	}
	
	

}
