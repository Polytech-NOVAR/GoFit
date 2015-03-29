package com.novar.business;

import java.util.Date;
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
	private User user;
	private ArrayList<BasketLine> lines = new ArrayList<BasketLine>();
	private Date orderDate;
	private Integer state;
	
	public Integer getBasketID() 
	{
		return basketID;
	}

	public void setBasketID(Integer basketID) 
	{
		this.basketID = basketID;
	}

	public User getUser() 
	{
		return user;
	}

	public void setUser(User user) 
	{
		this.user = user;
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
	
	
	// METHODS DESIGNED TO BE OVERRIDDEN BY CONCRETE SUBCLASSES, THEY MUST BE IMPLEMENTED ---------

	public Integer getState() 
	{
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * Load the basket with all his attributes. The basketID must be not null.
	 */
	public abstract void load();
	
	/**
	 * Save the basket with all his attributes.
	 */
	public abstract void save();
	
	/**
	 * Update the basket with all his attributes. The basketID must be not null.
	 */
	public abstract void update();
	
}
