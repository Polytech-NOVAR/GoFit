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
	
	public void setUser(UserJdbc user)
	{
		super.setUser(user);
	}
	
	public void setCategory(SubCategoryJdbc category)
	{
		super.setCategory(category);
	}

	
	public void setCategory(MainCategoryJdbc category)
	{
		super.setCategory(category);
	}
	
	@Override
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
			insertProd.setObject(5, getUser().getPseudo(),Types.VARCHAR);
			insertProd.setObject(6, getCategory().getCatID(),Types.INTEGER);
			
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
	
	@Override
	public void load()
	{
		PreparedStatement selectProd;
		try 
		{
			selectProd = ConnectionUtil.connection.prepareStatement("SELECT productID, p.description, p.price, p.discountPrice, p.quantity, p.catId, sc.parentID FROM Product p, Category c "
					+ "LEFT JOIN SubCategory sc on c.catID = sc.catID "
					+ "LEFT JOIN MainCategory mc on c.catID = mc.catID "
					+ "WHERE p.catID = c.catID "
					+ "AND p.productID = ?; ");

			selectProd.setObject(1, getProductID(), Types.VARCHAR);
			ResultSet res = selectProd.executeQuery();
			res.last();
			try 
			{
				setDescription(res.getString("description"));
				setPrice(res.getDouble("price"));
				setQuantity(res.getInt("quantity"));
				setDiscountPrice(res.getDouble("discountPrice"));
				
				Category category = null;
				if(res.getInt("parentID") == 0)
					category = new MainCategoryJdbc();
				else
					category = new SubCategoryJdbc();
				category.setCatID(res.getInt("catID"));
				category.load();
				setCategory(category);
				
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
	public void update()
	{
		try 
		{
			PreparedStatement insertProd = ConnectionUtil.connection.prepareStatement("UPDATE Product "
					+ "SET description = ?, "
					+ "price = ?, "
					+ "quantity = ?, "
					+ "discountPrice = ?, "
					+ "catID = ? "
					+ "WHERE productID = ?");
			
			insertProd.setObject(1, getDescription(),Types.VARCHAR);
			insertProd.setObject(2, getPrice(),Types.FLOAT);
			insertProd.setObject(3, getQuantity(),Types.INTEGER);
			insertProd.setObject(4, getDiscountPrice(),Types.FLOAT);
			insertProd.setObject(5, getCategory().getCatID(),Types.INTEGER);
			insertProd.setObject(6, getProductID(),Types.INTEGER);
			
			insertProd.executeUpdate();	
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void delete() {
		PreparedStatement deleteProduct;
		try {
			deleteProduct = ConnectionUtil.connection.prepareStatement("DELETE FROM Product "
						+ "WHERE productID = ? ");
			deleteProduct.setObject(1, getProductID(), Types.INTEGER);
			deleteProduct.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
