package com.novar.business;

import java.util.ArrayList;

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
	
}
