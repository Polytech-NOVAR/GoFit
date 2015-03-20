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
import com.novar.exception.SyntaxException;
import com.novar.util.ConnectionUtil;

public class ProductJdbc extends Product
{
	public ProductJdbc(HashMap<String,Object> data) throws FalseFieldsException
	{
		super(data);
	}
	
	public void save()
	{
		try 
		{
			PreparedStatement insertProd = ConnectionUtil.connection.prepareStatement("INSERT INTO Product (description, price, quantity, discountPrice, pseudo, catID) "
																		+ "VALUES (?,?,?,?,?,?);", PreparedStatement.RETURN_GENERATED_KEYS);
			insertProd.setObject(1, getDescription(),Types.VARCHAR);
			insertProd.setObject(2, getPrice(),Types.FLOAT);
			insertProd.setObject(3, getQuantity(),Types.INTEGER);
			insertProd.setObject(4, getDiscountPrice(),Types.FLOAT);
			/*insertProd.setObject(5, getSeller().getPseudo(),Types.VARCHAR);
			insertProd.setObject(6, getCategory().getCatID(),Types.INTEGER);*/
			
			insertProd.executeUpdate();		
			ResultSet key = insertProd.getGeneratedKeys();
            if (key.next())
            	setProductID(key.getInt(1));
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void load()
	{
		PreparedStatement selectProd;
		try 
		{
			selectProd = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM Product p "
					+ "WHERE p.productID = ? ;");

			selectProd.setObject(1, getProductID(), Types.VARCHAR);
			ResultSet res = selectProd.executeQuery();
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
}
