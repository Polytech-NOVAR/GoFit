package com.novar.business;

import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.novar.exception.FalseFieldsException;
import com.novar.exception.LoginFailedException;
import com.novar.exception.SyntaxException;
import com.novar.persist.JdbcKit;
import com.novar.persist.PersistKit;
import com.novar.util.ConnectionUtil;
import com.novar.util.StringUtil;

public abstract class Product 
{
	private Integer productID;
	
	/**
	  * maximal size : 256 characters
	  */
	private String description;	
	
	/**
	  * Greater than or equal to zero
	  * Lesser than 1 000 000
	  */
	private Double price;
	
	/**
	  * Greater than or equal to zero
	  * Lesser than 1 000 000
	  */
	private Integer quantity;
	
	/**
	  * Greater than or equal to zero
	  * Lesser than 1 000 000
	  */
	private Double discountPrice;
	
	/**
	  * Could be a SubCategory or mainCategory 
	  */
	private Category category;
	
	/**
	 * Must be a Member
	 */
	private User user;
	
	/**
	 * Create a Product whit data of the hashmap. The number of data of the hashmap is not fixed. 
	 * For each couple (key, value) the right setter is called.
	 * @param data Its keys are the names of instance's variables. Its values are the data to affect.
	 * @throws FalseFieldsException throw if one condition at least is not respected in the setters.
	 */
	public Product(HashMap<String,Object> data) throws FalseFieldsException
	{
		set(data);
	}
	
	/**
	 * Set a Product whit data of the hashmap. The number of data of the hashmap is not fixed. 
	 * For each couple (key, value) the right setter is called.
	 * @param data Its keys are the names of instance's variables. Its values are the data to affect.
	 * @throws FalseFieldsException throw if one condition at least is not respected in the setters.
	 */
	public void set(HashMap<String,Object> data) throws FalseFieldsException
	{
		Class[] typeArg = new Class[1];
		Object[] arg = new Object[1];
		ArrayList<String> errors = new ArrayList<String>();
		
		for (String mapKey : data.keySet())
		{
			String setterName = "set" + StringUtil.toCapitalizeCase(mapKey);
			typeArg[0] = data.get(mapKey).getClass();
			arg[0] = data.get(mapKey);
			Method setter;
			try {
				setter = this.getClass().getMethod(setterName, typeArg);
				try
				{
					setter.invoke(this, arg);
				}
				catch (Exception e)
				{
					errors.add(e.getCause().getMessage());
				}
			} catch (NoSuchMethodException | SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(!errors.isEmpty())
			throw new FalseFieldsException(errors);
	}

	public Integer getProductID() 
	{
		return productID;
	}

	public void setProductID(Integer productID) 
	{
		this.productID = productID;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) throws SyntaxException
	{
		Pattern pDesc = Pattern.compile("^[a-zA-Z-0-9- -._\n]{1,256}$");
		Matcher mDesc = pDesc.matcher(description);
		if(mDesc.matches())
		{
			this.description = description;
		}
		else
			throw new SyntaxException("description");

	}

	public Double getPrice() 
	{
		return price;
	}

	public void setPrice(Double price) throws SyntaxException
	{
		if(price >= 0 && price <= 1000000)
		{
			this.price = price;
		}
		else
			throw new SyntaxException("price");
	}

	public Integer getQuantity() 
	{
		return quantity;
	}

	public void setQuantity(Integer quantity) throws SyntaxException
	{
		if(quantity >= 0 && quantity <= 1000000)
		{
			this.quantity = quantity;
		}
		else
			throw new SyntaxException("quantity");
	}

	public Double getDiscountPrice() 
	{
		return discountPrice;
	}

	public void setDiscountPrice(Double discountPrice) throws SyntaxException
	{
		if(discountPrice >= 0 && discountPrice <= 1000000)
		{
			this.discountPrice = discountPrice;
		}
		else
			throw new SyntaxException("discountPrice");
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		if(user.isMember())
			this.user = user;
	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", description="
				+ description + ", price=" + price + ", quantity=" + quantity
				+ ", discountPrice=" + discountPrice + ", category=" + category
				+ "]";
	}

	////////////// HOOKS ////////////////
	public abstract void load();
	public abstract void save();
	public abstract void update();
	public abstract void delete();
	
	public static void main(String[] args) 
	{
		
		ConnectionUtil.start();
		PersistKit kit = new JdbcKit();
		for(int i=0;i<1;i++)
		{
			try {
				PreparedStatement insertProd = ConnectionUtil.connection.prepareStatement("INSERT INTO Product (description, price, quantity, discountPrice, pseudo, catID) VALUES ('Bonnet 1', 12.52, 10, 10.0, 'Antoine', 48);");
				insertProd.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		/*
		// ===== CREATE USER =====
		HashMap<String,Object> mapUser = new HashMap<String,Object>();
		mapUser.put("pseudo", "Antoine");
		mapUser.put("password", "123456");
		User user = null;
		
		try {
			user = kit.makeUser(mapUser);
			user.load();
			
		} catch (FalseFieldsException | LoginFailedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		// ===== CREATE PRODUCTS =====
		HashMap<String,Object> mapProduct = new HashMap<String,Object>();
		mapProduct.put("productID", 13);
		Product prod = null;
		try {
			prod = kit.makeProduct(mapProduct);
			
		} catch (FalseFieldsException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getFalseFields());
		}
		
		// ===== PERSISTENCE ====
		prod.load();
		System.out.println(prod);*/
		
	}


}
