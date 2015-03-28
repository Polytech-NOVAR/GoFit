package com.novar.business;

public abstract class BasketLine 
{
	private Product product;
	private Integer quantity;
	
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
