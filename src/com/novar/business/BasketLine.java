package com.novar.business;

public abstract class BasketLine 
{
	private Product product;
	private Integer quantity;
	private Basket basket;
	
	public BasketLine(Product product, Basket basket, Integer quantity)
	{
		this.product = product;
		this.basket = basket;
		this.quantity = quantity;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}
	
	
	// METHODS DESIGNED TO BE OVERRIDDEN BY CONCRETE SUBCLASSES, THEY MUST BE IMPLEMENTED ---------

	/**
	 * Save the BasketLine with all his attributes.
	 */
	public abstract void save();
	
	/**
	 * Update the BasketLine with all his attributes.
	 */
	public abstract void update();
	
	/**
	 * Delete the BasketLine with.
	 */
	public abstract void delete();
	
	
}
