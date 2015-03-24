package com.novar.business;

import java.sql.Date;
import java.util.ArrayList;

public class Member implements Role
{
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
}
