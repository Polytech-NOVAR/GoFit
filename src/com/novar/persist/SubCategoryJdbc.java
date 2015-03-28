package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

import com.novar.business.MainCategory;
import com.novar.business.SubCategory;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.SyntaxException;
import com.novar.util.ConnectionUtil;

/**
 * This concrete subclass of SubCategory uses the Jdbc technology to perfom the methods defined in Category.
 * @author Antoine JOERG
 *
 */
public class SubCategoryJdbc extends SubCategory
{
	
	public SubCategoryJdbc()
	{
		super();
	}
	
	public SubCategoryJdbc(HashMap<String,Object> data) throws FalseFieldsException
	{
		super(data);
	}
	
	public void setParent(MainCategoryJdbc parent) 
	{
		super.setParent(parent);
	}
	
	@Override
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
	
	@Override
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
			
			MainCategory parent = new MainCategoryJdbc();
			parent.setCatID(res.getInt("parentID"));
			parent.load();
			setParent(parent);
			
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
	
	@Override
	public void delete() {
		PreparedStatement deleteCategory;
		try {
			deleteCategory = ConnectionUtil.connection.prepareStatement("DELETE FROM SubCategory "
						+ "WHERE catID = ? ; ");
			deleteCategory.setObject(1, getCatID(), Types.INTEGER);
			deleteCategory.executeUpdate();
			deleteCategory = ConnectionUtil.connection.prepareStatement("DELETE FROM Category "
					+ "WHERE catID = ? ; ");
			deleteCategory.setObject(1, getCatID(), Types.INTEGER);
			deleteCategory.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void update()
	{
		try 
		{
			PreparedStatement insertProd = ConnectionUtil.connection.prepareStatement("UPDATE SubCategory sc, Category c "
					+ "SET sc.parentID = ?, c.description = ? "
					+ "WHERE sc.catID = c.catID "
					+ "AND c.catID = ?; ");
			insertProd.setObject(1, getParent().getCatID(),Types.VARCHAR);
			insertProd.setObject(2, getDescription(),Types.VARCHAR);
			insertProd.setObject(3, getCatID(),Types.INTEGER);
			
			insertProd.executeUpdate();	
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
