package com.novar.business;

import java.util.ArrayList;

/**
 * This abstract class provides specific method to create lists of products.
 * @author Antoine JOERG
 */

public abstract class ProductManager 
{
	
	// METHODS DESIGNED TO BE OVERRIDDEN BY CONCRETE SUBCLASSES, THEY MUST BE IMPLEMENTED ---------
	

	/**
	 * @return the list of all the products.
	 */
	public abstract ArrayList<Product> getAllProducts();

	/**
	 * @param category 
	 * @return the list of the products belonging to the category
	 */
	public abstract ArrayList<Product> getProducts(Category category);
	
	
	/**
	 * @param string 
	 * @return the list of the products corresponding to the string
	 */
	public abstract ArrayList<Product> getProducts(String string);
	
	
	/**
	 * @param category
	 * @param string 
	 * @return the list of the products belonging to the category and corresponding to the string
	 */
	public abstract ArrayList<Product> getProducts(Category category, String string);
	

	
}
