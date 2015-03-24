package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

import com.novar.business.Category;
import com.novar.business.MainCategory;
import com.novar.business.Product;
import com.novar.business.SubCategory;
import com.novar.business.User;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.LoginFailedException;
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
			selectCat = ConnectionUtil.connection.prepareStatement("SELECT mc.catID, mc.parentID, c.description AS descSub, c2.description AS descMain "
					+ "FROM SubCategory mc, Category c, Category c2 "
					+ "WHERE mc.catID = c.catID "
					+ "AND mc.parentID = c2.catID "
					+ "AND mc.catID = ?;");
					

			selectCat.setObject(1, getCatID(), Types.INTEGER);
			ResultSet res = selectCat.executeQuery();
			res.next();
			
			HashMap<String, Object> mapParent = new HashMap<String, Object>();
			mapParent.put("catID", res.getInt("parentID"));
			mapParent.put("description", res.getString("descMain"));
			
			try {
				MainCategory parent = new MainCategoryJdbc(mapParent);
				setParent(parent);
			} catch (FalseFieldsException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				setDescription(res.getString("descSub"));
			} catch (SyntaxException e) {
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
	
	public void loadProducts()
	{
		PreparedStatement selectProducts;
		try 
		{
			selectProducts = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM SubCategory mc, Product p "
					+ "WHERE mc.catID = p.catID "
					+ "AND mc.catID = ? ;");

			selectProducts.setObject(1, getCatID(), Types.VARCHAR);
			ResultSet resProducts = selectProducts.executeQuery();
			
			while(resProducts.next())
			{
				
				
				HashMap<String,Object> mapProduct = new HashMap<String,Object>();
				
				mapProduct.put("ProductID", resProducts.getInt("ProductID"));
				mapProduct.put("description", resProducts.getString("description"));
				mapProduct.put("price", resProducts.getDouble("price"));
				mapProduct.put("quantity", resProducts.getInt("quantity"));
				mapProduct.put("discountPrice", resProducts.getDouble("discountPrice"));
				
				Product prod = new ProductJdbc(mapProduct);
				
				this.addProduct(prod);
			}
		}
		catch (SQLException | FalseFieldsException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
