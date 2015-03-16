package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

import com.novar.business.Category;
import com.novar.business.Product;
import com.novar.business.ProductManager;
import com.novar.business.User;
import com.novar.exception.FalseFieldsException;
import com.novar.util.ConnectionUtil;

public class ProductManagerJdbc extends ProductManager
{
	
	public ArrayList<Product> getUserProducts(User user)
	{

		ArrayList<Product> productList = new ArrayList<Product>();
		try 
		{
			PreparedStatement selectProducts;
			selectProducts = ConnectionUtil.connection.prepareStatement("SELECT * FROM Product p, "
																		+ "(SELECT c.catID, description, parentID FROM Category c "
																		+ "LEFT JOIN SubCategory sc ON sc.catID = c.catID "
																		+ "LEFT JOIN MainCategory mc ON mc.catID = c.catID) c "
																		+ "WHERE c.catID = p.catID "
																		+ "AND p.pseudo = ?;");
			
			selectProducts.setObject(1, user.getPseudo(), Types.VARCHAR);
			ResultSet resProducts = selectProducts.executeQuery();
			
			while(resProducts.next())
			{
				HashMap<String,Object> mapProduct = new HashMap<String,Object>();
				
				mapProduct.put("ProductID", resProducts.getInt("ProductID"));
				mapProduct.put("description", resProducts.getString("description"));
				mapProduct.put("price", resProducts.getDouble("price"));
				mapProduct.put("quantity", resProducts.getInt("quantity"));
				mapProduct.put("discountPrice", resProducts.getDouble("discountPrice"));
				mapProduct.put("seller", user);
				
				Category cat = null;
				if(resProducts.getInt("parentID")!=0)
				{				
					HashMap<String, Object> mapCat = new HashMap<String, Object>();
					mapCat.put("catID", resProducts.getInt("catID"));
					cat = new SubCategoryJdbc(mapCat);
					cat.load();
				}
				else
				{
					HashMap<String, Object> mapCat = new HashMap<String, Object>();
					mapCat.put("catID", resProducts.getInt("catID"));
					cat = new MainCategoryJdbc(mapCat);
					cat.load();
				}
				mapProduct.put("category", cat);
				
				productList.add(new ProductJdbc(mapProduct));
			}
				
		}
		catch (SQLException | FalseFieldsException e) 
		{
			System.out.println(e.getMessage());
		}
		return productList;
	}
}
