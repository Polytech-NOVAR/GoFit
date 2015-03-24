package com.novar.business;

import java.util.ArrayList;

public abstract class CategoryManager 
{

	public abstract ArrayList<Category> getAllCategories();
	public abstract ArrayList<MainCategory> getAllMainCategories();
	public abstract ArrayList<SubCategory> getAllSubCategories();
	public abstract ArrayList<SubCategory> getSubCategories(MainCategory parent);
	
}
