package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

import com.novar.business.Category;
import com.novar.business.MainCategory;
import com.novar.business.Product;
import com.novar.business.User;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.LoginFailedException;
import com.novar.exception.SyntaxException;
import com.novar.util.ConnectionUtil;

public class MainCategoryJdbc extends MainCategory{
	
	public MainCategoryJdbc(HashMap<String,Object> data) throws FalseFieldsException
	{
		super(data);
	}
	
	public void save()
	{
		try 
		{
			PreparedStatement insertCat = ConnectionUtil.connection.prepareStatement("INSERT INTO Category (description) "
																		+ "VALUES (?);", PreparedStatement.RETURN_GENERATED_KEYS);
			insertCat.setObject(1, getDescription(),Types.VARCHAR);
			insertCat.executeUpdate();		
			ResultSet key = insertCat.getGeneratedKeys();
            if (key.next())
            	setCatID(key.getInt(1));
            
			PreparedStatement insertSubCat = ConnectionUtil.connection.prepareStatement("INSERT INTO MainCategory (catID) "
					+ "VALUES (?);");
			insertSubCat.setObject(1, getCatID(),Types.INTEGER);
			insertSubCat.executeUpdate();		
            
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void load()
	{
		PreparedStatement selectCat;
		try 
		{
			selectCat = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM MainCategory mc, Category c "
					+ "WHERE c.catID = mc.catID "
					+ "AND mc.catID = ? ;");

			selectCat.setObject(1, getCatID(), Types.VARCHAR);
			ResultSet res = selectCat.executeQuery();
			res.last();
			try 
			{
				setDescription(res.getString("description"));

			} 
			catch (SyntaxException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void loadProducts() {
		// TODO Auto-generated method stub
		
	}

}
