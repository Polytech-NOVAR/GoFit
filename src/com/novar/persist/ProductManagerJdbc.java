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
import com.novar.business.Product;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.SyntaxException;
import com.novar.util.ConnectionUtil;

/**
 * This concrete subclass of ProductManager uses the Jdbc technology to perfom the methods defined in ProductManager.
 * @author Antoine JOERG
 *
 */
public class ProductManagerJdbc extends ProductManager
{

	@Override
	public ArrayList<Product> getAllProducts() 
	{
		PreparedStatement selectProducts;
		ArrayList<Product> products = new ArrayList<Product>();
		try 
		{
			selectProducts = ConnectionUtil.connection.prepareStatement("SELECT * FROM Product; ");

			ResultSet res = selectProducts.executeQuery();
			while(res.next())
			{
				Product product = new ProductJdbc();
				product.setProductID(res.getInt("productID"));
				product.load();
				
				UserJdbc user = new UserJdbc();
				try {
					user.setPseudo(res.getString("pseudo"));
				} catch (SyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				user.loadInfo();
				
				product.setUser(user);
				products.add(product);
			}
					
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return products;
	}
	
	@Override
	public ArrayList<Product> getProducts(Category category) 
	{
		PreparedStatement selectProducts;
		ArrayList<Product> products = new ArrayList<Product>();
		try 
		{
			selectProducts = ConnectionUtil.connection.prepareStatement("SELECT * FROM Product WHERE catID = ?; ");
			selectProducts.setObject(1, category.getCatID(), Types.VARCHAR);
			
			ResultSet res = selectProducts.executeQuery();
			while(res.next())
			{
				Product product = new ProductJdbc();
				product.setProductID(res.getInt("productID"));
				product.load();
				
				UserJdbc user = new UserJdbc();
				try {
					user.setPseudo(res.getString("pseudo"));
				} catch (SyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				user.loadInfo();
				
				product.setUser(user);
				products.add(product);
			}
					
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return products;
	}


	@Override
	public ArrayList<Product> getProducts(String string) 
	{
		
		PreparedStatement selectProducts;
		ArrayList<Product> products = new ArrayList<Product>();
		try 
		{
			selectProducts = ConnectionUtil.connection.prepareStatement("SELECT * FROM Product WHERE description LIKE ?; ");
			selectProducts.setObject(1, "%"+string+"%", Types.VARCHAR);
			
			ResultSet res = selectProducts.executeQuery();
			while(res.next())
			{
				Product product = new ProductJdbc();
				product.setProductID(res.getInt("productID"));
				product.load();
				
				UserJdbc user = new UserJdbc();
				try {
					user.setPseudo(res.getString("pseudo"));
				} catch (SyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				user.loadInfo();
				
				product.setUser(user);
				products.add(product);
			}
					
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return products;
		
	}
	
	@Override
	public ArrayList<Product> getProducts(Category category, String string) 
	{
		PreparedStatement selectProducts;
		ArrayList<Product> products = new ArrayList<Product>();
		try 
		{
			selectProducts = ConnectionUtil.connection.prepareStatement("SELECT * FROM Product WHERE CatID = ? AND description LIKE ?; ");
			selectProducts.setObject(1, category.getCatID(), Types.VARCHAR);
			selectProducts.setObject(2, "%"+string+"%", Types.VARCHAR);
			
			ResultSet res = selectProducts.executeQuery();
			while(res.next())
			{
				Product product = new ProductJdbc();
				product.setProductID(res.getInt("productID"));
				product.load();
				
				UserJdbc user = new UserJdbc();
				try {
					user.setPseudo(res.getString("pseudo"));
				} catch (SyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				user.loadInfo();
				
				product.setUser(user);
				products.add(product);
			}
					
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return products;
	}

}
