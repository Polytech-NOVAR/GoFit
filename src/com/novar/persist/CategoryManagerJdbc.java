package com.novar.persist;

import java.sql.PreparedStatement;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.novar.business.CategoryManager;
import com.novar.business.MainCategory;
import com.novar.business.SubCategory;
import com.novar.exception.SyntaxException;
import com.novar.util.ConnectionUtil;

/**
 * This concrete subclass of CategoryManager uses the Jdbc technology to perfom the methods defined in CategoryManager.
 * @author Antoine JOERG
 *
 */
public class CategoryManagerJdbc extends CategoryManager
{

	@Override
	public ArrayList<MainCategory> getAllMainCategories() {
		PreparedStatement selectCategories;
		ArrayList<MainCategory> categories = new ArrayList<MainCategory>();
		try 
		{
			selectCategories = ConnectionUtil.connection.prepareStatement("SELECT * "
																		+ "FROM MainCategory mc, Category c "
																		+ "WHERE mc.catID = c.catID ");

			ResultSet res = selectCategories.executeQuery();
			while(res.next())
			{
				MainCategory category = new MainCategoryJdbc();
				category.setCatID(res.getInt("catID"));
				category.setDescription(res.getString("description"));
				categories.add(category);
			}
		}
		catch (SQLException | SyntaxException e) 
		{
			e.printStackTrace();
		}
		return categories;
	}

	@Override
	public ArrayList<SubCategory> getSubCategories(MainCategory parent) {
		PreparedStatement selectCategories;
		ArrayList<SubCategory> categories = new ArrayList<SubCategory>();
		try 
		{
			selectCategories = ConnectionUtil.connection.prepareStatement("SELECT * "
																		+ "FROM SubCategory sc, Category c "
																		+ "WHERE sc.catID = c.catID "
																		+ "AND sc.parentID = ?");
			selectCategories.setObject(1, parent.getCatID(), Types.VARCHAR);
			
			ResultSet res = selectCategories.executeQuery();
			
			while(res.next())
			{
				SubCategory category = new SubCategoryJdbc();
				category.setCatID(res.getInt("catID"));
				category.setDescription(res.getString("description"));
				category.setParent(parent);
				
				categories.add(category);
			}
		}
		catch (SQLException | SyntaxException e) 
		{
			e.printStackTrace();
		}
		return categories;
	}

}
