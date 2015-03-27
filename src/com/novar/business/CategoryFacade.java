package com.novar.business;

import java.util.ArrayList;
import java.util.HashMap;

import com.novar.exception.FalseFieldsException;
import com.novar.persist.PersistKit;

/**
 * This class provides methods used to manage the categories. 
 * @author Antoine JOERG
 *
 */
public class CategoryFacade 
{
	private CategoryManager manager;
	
	private PersistKit kit = null;
	
	/**
	 * Construct a CategoryFacade with the right persistKit and instantiate the manager.
	 * @param kit persistence engine
	 */
	public CategoryFacade(PersistKit kit)
	{
		this.kit = kit;
		manager = kit.makeCategoryManager();
	}
	
	/**
	 * @return a list of all the MainCategories
	 */
	public ArrayList<MainCategory> getAllMainCategories()
	{
		return manager.getAllMainCategories();
	}
	

	/**
	 * @param parent the MainCategory
	 * @return a list of all the SubCategories of parent
	 */
	public ArrayList<SubCategory> getSubCategories(MainCategory parent)
	{
		return manager.getSubCategories(parent);
	}
	
	/**
	 * Create a MainCategory 
	 * @param data 
	 * @throws FalseFieldsException
	 */
	public void createMainCategory(HashMap<String, Object> data) throws FalseFieldsException
	{
		MainCategory c = kit.makeMainCategory(data);
		c.save();
	}
	
	/**
	 * Create a SubCategory
	 * @param data 
	 * @throws FalseFieldsException
	 */
	public void createSubCategory(HashMap<String, Object> data) throws FalseFieldsException
	{
		SubCategory c = kit.makeSubCategory(data);
		c.save();
	}
	
	/**
	 * Update the category
	 * @param c the category to update
	 * @param data new data of the category
	 * @throws FalseFieldsException
	 */
	public void updateCategory(Category c, HashMap<String, Object> data) throws FalseFieldsException
	{
		c.set(data);
		c.update();
	}
	
	
	/**
	 * Delete the category
	 * @param category the category to delete
	 */
	public void deleteCategory(Category category)
	{
		category.delete();
	}
}
