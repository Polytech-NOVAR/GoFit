package com.novar.business;

import java.util.ArrayList;
import java.util.HashMap;

import com.novar.exception.FalseFieldsException;
import com.novar.persist.PersistKit;
/**
 * This class provides methods used to manage the products. 
 * @author Antoine JOERG
 *
 */
public class ProductFacade
{
	
	private PersistKit kit = null;

	//private ProductManager manager;
	
	/**
	 * Construct a ProductFacade with the right persistKit and instantiate the manager.
	 * @param kit persistence engine
	 */
	public ProductFacade(PersistKit kit)
	{
		this.kit = kit;
	}
	
	/**
	 * Create a product
	 * @param data
	 * @throws FalseFieldsException
	 */
	public void createProduct(HashMap<String, Object> data) throws FalseFieldsException
	{
		Product p = kit.makeProduct(data);
		p.save();
	}
	
	/**
	 * delete the product
	 * @param p the product to delete
	 */
	public void deleteProduct(Product p)
	{
		p.delete();
	}
	
	/**
	 * Update the product
	 * @param c the product to update
	 * @param data new data of the product
	 * @throws FalseFieldsException
	 */
	public void updateProduct(Product p, HashMap<String, Object> data) throws FalseFieldsException
	{
		p.set(data);
		p.update();
	}
	
}
