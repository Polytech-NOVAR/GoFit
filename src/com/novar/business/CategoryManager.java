package com.novar.business;

import java.util.ArrayList;

/**
 * This abstract class provides specific method to create lists of categories.
 * @author Antoine JOERG
 */

public abstract class CategoryManager 
{
	
	// METHODS DESIGNED TO BE OVERRIDDEN BY CONCRETE SUBCLASSES, THEY MUST BE IMPLEMENTED ---------
	
	
	/**
	 * 
	 * @return the list of all the MainCategories
	 */
	public abstract ArrayList<MainCategory> getAllMainCategories();
	
	/**
	 * @param parent the MainCategory
	 * @return the list of all the SubCategories of the MainCategory
	 */
	public abstract ArrayList<SubCategory> getSubCategories(MainCategory parent);
	
}
