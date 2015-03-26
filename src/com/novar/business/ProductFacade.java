package com.novar.business;

import java.util.ArrayList;
import java.util.HashMap;

import com.novar.exception.FalseFieldsException;
import com.novar.persist.PersistKit;

public class ProductFacade
{
	
	private PersistKit kit = null;

	//private ProductManager manager;
	
	public ProductFacade(PersistKit kit)
	{
		this.kit = kit;
	}
	
	public void createProduct(HashMap<String, Object> data) throws FalseFieldsException
	{
		Product p = kit.makeProduct(data);
		p.save();
	}
	
	public void deleteProduct(Product p)
	{
		p.delete();
	}
	
	public void updateProduct(Product p, HashMap<String, Object> data) throws FalseFieldsException
	{
		p.set(data);
		p.update();
	}
	
}
