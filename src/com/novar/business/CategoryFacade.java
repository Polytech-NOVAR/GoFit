package com.novar.business;

import java.util.ArrayList;
import java.util.HashMap;

import com.novar.exception.FalseFieldsException;
import com.novar.persist.PersistKit;

public class CategoryFacade 
{
	private CategoryManager manager;
	
	private PersistKit kit = null;
	
	public CategoryFacade(PersistKit kit)
	{
		this.kit = kit;
		manager = kit.makeCategoryManager();
	}
	
	public ArrayList<MainCategory> getAllMainCategories()
	{
		return manager.getAllMainCategories();
	}
	
	public ArrayList<SubCategory> getSubCategories(MainCategory parent)
	{
		return manager.getSubCategories(parent);
	}
	
	public void deleteCategory(Category category)
	{
		category.delete();
	}
	
	public void createMainCategory(HashMap<String, Object> data) throws FalseFieldsException
	{
		MainCategory c = kit.makeMainCategory(data);
		c.save();
	}
	
	public void createSubCategory(HashMap<String, Object> data) throws FalseFieldsException
	{
		SubCategory c = kit.makeSubCategory(data);
		c.save();
	}
	
	public void updateCategory(Category c, HashMap<String, Object> data) throws FalseFieldsException
	{
		c.set(data);
		c.update();
	}
}
