package com.novar.business;

import java.lang.reflect.Method;
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
	  * maximal size : 257 characters
	  */
	private String description;	
	
	/**
	  * Greater than or equal to zero
	  */
	private Double price;
	
	/**
	  * Greater than or equal to zero
	  */
	private Integer quantity;
	
	/**
	  * Greater than or equal to zero
	  */
	private Double discountPrice;
	
	
	/**
	 * Constructs a Product whit data of the hashmap. The number of data of the hashmap is not fixed. 
	 * For each couple (key, value) the right setter is called.
	 * @param data Its keys are the names of instance's variables. Its values are the data to affect.
	 * @throws FalseFieldsException thorw if one condition at least is not respected in the setters.
	 */
	public Product(HashMap<String,Object> data) throws FalseFieldsException
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
		Pattern pDesc = Pattern.compile("^[a-zA-Z-0-9- -._]{1,256}$");
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
		if(price >= 0)
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

	public void setQuantity(Integer remainingQuantity) throws SyntaxException
	{
		if(remainingQuantity >= 0)
		{
			this.quantity = remainingQuantity;
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
		if(discountPrice >= 0)
		{
			this.discountPrice = discountPrice;
		}
		else
			throw new SyntaxException("discountPrice");
	}


	@Override
	public String toString() 
	{
		return "Product [productID=" + productID + ", description="
				+ description + ", price=" + price + ", quantity="
				+ quantity + ", discountPrice=" + discountPrice+"]\n";
	}
	
	////////////// HOOKS ////////////////
	public abstract void load();
	public abstract void save();
	/*public abstract void update();
	public abstract void delete();*/
	
	public static void main(String[] args) 
	{
		
		ConnectionUtil.start();
		PersistKit kit = new JdbcKit();
		
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
		
		
		
		/*// ===== CREATE CATEGORIES =====
		HashMap<String,Object> mapCat = new HashMap<String,Object>();
		mapCat.put("description", "Gant");
		HashMap<String,Object> mapCat2 = new HashMap<String,Object>();
		mapCat2.put("description", "Moufle");
		MainCategory cat1 = null;
		SubCategory cat2 = null;
		try {
			cat1 = kit.makeMainCategory(mapCat);
			
			mapCat2.put("parent", cat1);
			cat2 = kit.makeSubCategory(mapCat2);
			
		} catch (FalseFieldsException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getFalseFields());
		}

		cat1.save();
		cat2.save();

		System.out.println(cat1);
		System.out.println(cat2);
		
		HashMap<String,Object> mapCat = new HashMap<String,Object>();
		mapCat.put("catID", 49);
		SubCategory cat1 = null;
		try {
			cat1 = kit.makeSubCategory(mapCat);
			cat1.load();
			
		} catch (FalseFieldsException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getFalseFields());
		}
		
		System.out.println(cat1);
		
		// ===== CREATE PRODUCTS =====
		HashMap<String,Object> mapProduct = new HashMap<String,Object>();
		mapProduct.put("description", "Moufle 1");
		mapProduct.put("price", 15.28);
		mapProduct.put("quantity", 10);
		mapProduct.put("discountPrice", 14.0);
		mapProduct.put("seller", user);
		mapProduct.put("category", cat1);
		Product prod = null;
		try {
			prod = kit.makeProduct(mapProduct);
			
		} catch (FalseFieldsException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getFalseFields());
		}
		
		===== PERSISTENCE ====
		prod.save();
		System.out.println(prod);*/
		
		
		user.loadProducts();
		System.out.println(user.getMember().getProducts());
		
	}


}
