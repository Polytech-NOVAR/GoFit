package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

import com.novar.business.Category;
import com.novar.business.Product;
import com.novar.business.CategoryManager;
import com.novar.business.User;
import com.novar.exception.FalseFieldsException;
import com.novar.util.ConnectionUtil;

public class SubCategoryManagerJdbc extends CategoryManager 
{

	@Override
	public ArrayList<Category> getAllCategories() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Category> getCategoriesOfUser(User user) 
	{
		ArrayList<Category> categoryList = new ArrayList<Category>();
		try 
		{
			PreparedStatement selectCategories;
			selectCategories = ConnectionUtil.connection.prepareStatement("SELECT * FROM Product p, SubCategory s "
																		+ "WHERE p.catID = s.catID "
																		+ "AND p.pseudo = ?;");
			
			selectCategories.setObject(1, user.getPseudo(), Types.VARCHAR);
			ResultSet resCategories = selectCategories.executeQuery();
			
			
			while(resCategories.next())
			{
				HashMap<String,Object> mapcategory = new HashMap<String,Object>();
				mapcategory.put("catID", resCategories.getInt("catID"));
				Category cat = new SubCategoryJdbc(mapcategory);
				cat.load();
				categoryList.add(cat);
			}
				
		}
		catch (SQLException | FalseFieldsException e) 
		{
			System.out.println(e.getMessage());
		}
		return categoryList;
	}

}
