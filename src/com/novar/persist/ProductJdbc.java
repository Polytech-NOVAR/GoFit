package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

import com.novar.business.Product;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.SyntaxException;
import com.novar.util.ConnectionUtil;

public class ProductJdbc extends Product
{
	public ProductJdbc(HashMap<String,Object> data) throws FalseFieldsException
	{
		super(data);
	}
	
	public void setCategory(MainCategoryJdbc category) 
	{
		super.setCategory(category);
	}
	
	public void setCategory(SubCategoryJdbc category) 
	{
		super.setCategory(category);
	}
	
	public void setSeller(UserJdbc seller) throws SyntaxException
	{
		super.setSeller(seller);
	}
	
	public void save()
	{
		try 
		{
			PreparedStatement insertCat = ConnectionUtil.connection.prepareStatement("INSERT INTO Product (description, price, quantity, discountPrice, pseudo, catID) "
																		+ "VALUES (?,?,?,?,?,?);", PreparedStatement.RETURN_GENERATED_KEYS);
			insertCat.setObject(1, getDescription(),Types.VARCHAR);
			insertCat.setObject(2, getPrice(),Types.FLOAT);
			insertCat.setObject(3, getQuantity(),Types.INTEGER);
			insertCat.setObject(4, getDiscountPrice(),Types.FLOAT);
			insertCat.setObject(5, getSeller().getPseudo(),Types.VARCHAR);
			insertCat.setObject(6, getCategory().getCatID(),Types.INTEGER);
			
			insertCat.executeUpdate();		
			ResultSet key = insertCat.getGeneratedKeys();
            if (key.next())
            	setProductID(key.getInt(1));
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
