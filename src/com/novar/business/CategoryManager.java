package com.novar.business;

import java.util.ArrayList;

public abstract class CategoryManager 
{
	public abstract ArrayList<Category> getAllCategories();
	public abstract ArrayList<Category> getCategoriesOfUser(User user);
}
