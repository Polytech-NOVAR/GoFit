package com.novar.business;

import java.sql.Date;
import java.util.ArrayList;
/**
 * This abstract class represent a basket. It is composed of a list of BasketLine.
 * @author Antoine JOERG
 * @see MainCategory
 * @see SubCategory
 */

public abstract class Basket 
{
	
	private Integer basketID;
	private ArrayList<BasketLine> lines;
	private Date orderDate;
	
	public Integer getBasketID() 
	{
		return basketID;
	}

	public void setBasketID(Integer basketID) 
	{
		this.basketID = basketID;
	}

	public ArrayList<BasketLine> getLines() 
	{
		return lines;
	}

	public void setLines(ArrayList<BasketLine> lines) 
	{
		this.lines = lines;
	}

	public Date getOrderDate() 
	{
		return orderDate;
	}

	public void setOrderDate(Date orderDate) 
	{
		this.orderDate = orderDate;
	}

	/**
	 * Load the basket with all his attributes. The basketID must be not null.
	 */
	public abstract void load();
	
	/**
	 * Save the basket with all his attributes. The basketID must be not null.
	 */
	public abstract void save();
	
	/**
	 * Update the basket with all his attributes. The basketID must be not null.
	 */
	public abstract void update();
	
	/**
	 * Delete the basket with. The basketID must be not null.
	 */
	public abstract void delete();
	
}
