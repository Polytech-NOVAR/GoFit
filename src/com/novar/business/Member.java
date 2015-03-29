package com.novar.business;

import java.sql.Date;
import java.util.ArrayList;

/**
 * This class show the Member Role
 * <br><br>
 * This class is use in every class which use User
 * @author Antoine JOERG
 * @see Role
 */
public class Member implements Role
{
	private String pseudoMember;
	private Date registrationDate;
	
	private ArrayList<Product> products = new ArrayList<Product>();
	
	public Date getRegistrationDate()
	{
		return registrationDate;
	}

	public String toString()
	{
		return ("Member. Date d'inscritpion : " + getRegistrationDate());
	}
	
	public void addProduct(Product p)
	{
		this.products.add(p);
	}
	
	public void removeProduct(Product p)
	{
		this.products.remove(p);
	}
	
	public ArrayList<Product> getProducts() 
	{
		return products;
	}

	public void setProducts(ArrayList<Product> products) 
	{
		this.products = products;
	}
	
	public String getPseudoAdministrator()
	{
		return pseudoMember;
	}
}
