package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

import com.novar.business.Category;
import com.novar.business.SubCategory;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.RegisterFailedException;
import com.novar.exception.SyntaxException;
import com.novar.util.ConnectionUtil;

public class SubCategoryJdbc extends SubCategory
{
	public SubCategoryJdbc(HashMap<String,Object> data) throws FalseFieldsException
	{
		super(data);
	}
	
	public void setParent(MainCategoryJdbc parent) 
	{
		super.setParent(parent);
	}
	
	public void setParent(SubCategoryJdbc parent) 
	{
		super.setParent(parent);
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
            
			PreparedStatement insertSubCat = ConnectionUtil.connection.prepareStatement("INSERT INTO SubCategory (catID, parentID) "
					+ "VALUES (?,?);");
			insertSubCat.setObject(1, getCatID(),Types.INTEGER);
			insertSubCat.setObject(2, getParent().getCatID(),Types.INTEGER);
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
					+ "FROM SubCategory mc, Category c "
					+ "WHERE c.catID = mc.catID "
					+ "AND mc.catID = ? ;");

			selectCat.setObject(1, getCatID(), Types.INTEGER);
			ResultSet res = selectCat.executeQuery();
			res.last();
			
			try {
				setDescription(res.getString("description"));
			} catch (SyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			loadParent();
	
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadParent()
	{
		try 
		{
			PreparedStatement selectParentSubCat;
			PreparedStatement selectParentMainCat;
	
			selectParentSubCat = ConnectionUtil.connection
					.prepareStatement("SELECT c2.catID "
							+ "FROM SubCategory c1, SubCategory c2 "
							+ "WHERE c1.parentID = c2.catID "
							+ "AND c1.catID = ? ;");
	
			selectParentSubCat.setObject(1, getCatID(), Types.INTEGER);
	
			ResultSet resParentSub = selectParentSubCat.executeQuery();
			resParentSub.last();
	
			if (resParentSub.getRow() > 0) // Le parent est une SubCategory
			{
				HashMap<String, Object> mapCat = new HashMap<String, Object>();
				mapCat.put("catID", resParentSub.getInt("catID"));
				SubCategoryJdbc parent = null;
				try 
				{
					parent = new SubCategoryJdbc(mapCat);
				} 
				catch (FalseFieldsException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				parent.load();
				setParent(parent);
			} 
			else // Le parent est une MainCategory
			{
				selectParentMainCat = ConnectionUtil.connection
						.prepareStatement("SELECT c2.catID "
								+ "FROM SubCategory c1, MainCategory c2 "
								+ "WHERE c1.parentID = c2.catID "
								+ "AND c1.catID = ? ;");
	
				selectParentMainCat.setObject(1, getCatID(), Types.INTEGER);
				
				
				ResultSet resParentMain = selectParentMainCat.executeQuery();
				resParentMain.last();
				
				HashMap<String, Object> mapCat = new HashMap<String, Object>();
				mapCat.put("catID", resParentMain.getInt("catID"));
				MainCategoryJdbc parent = null;
				try 
				{
					parent = new MainCategoryJdbc(mapCat);
				} 
				catch (FalseFieldsException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				parent.load();
				setParent(parent);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	
}
