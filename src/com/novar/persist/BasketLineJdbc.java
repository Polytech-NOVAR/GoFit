package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.novar.business.Basket;
import com.novar.business.BasketLine;
import com.novar.business.Product;
import com.novar.util.ConnectionUtil;

public class BasketLineJdbc extends BasketLine {

	public BasketLineJdbc(Product product, Basket basket, Integer quantity) 
	{
		super(product, basket, quantity);
	}

	@Override
	public void save() 
	{
		try 
		{
			PreparedStatement insertBasketLine = ConnectionUtil.connection.prepareStatement("INSERT INTO BasketLine (productID, basketID, quantity) "
																		+ "VALUES (?, ?, ?);");
			insertBasketLine.setObject(1, getProduct().getProductID(), Types.INTEGER);
			insertBasketLine.setObject(2, getBasket().getBasketID(), Types.INTEGER);
			insertBasketLine.setObject(3, getQuantity(), Types.INTEGER);
			insertBasketLine.executeUpdate();		
		}
		catch (SQLException e) 
		{
			if(e instanceof MySQLIntegrityConstraintViolationException)
			{
				incrementQuantity();
			}
		}
	}
	
	public void incrementQuantity() 
	{
		PreparedStatement updateBasketLine;
		try {
			updateBasketLine = ConnectionUtil.connection.prepareStatement("UPDATE BasketLine "
						+ "SET quantity = quantity + 1 "
						+ "WHERE productID = ? AND basketID = ?");
			
			updateBasketLine.setObject(1, getProduct().getProductID(), Types.INTEGER);
			updateBasketLine.setObject(2, getBasket().getBasketID(), Types.INTEGER);

			updateBasketLine.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	@Override
	public void delete() 
	{
		PreparedStatement deleteBasketLine;
		try {
			deleteBasketLine = ConnectionUtil.connection.prepareStatement("DELETE FROM BasketLine "
						+ "WHERE productID = ? AND basketID = ?");
			deleteBasketLine.setObject(1, getProduct().getProductID(), Types.INTEGER);
			deleteBasketLine.setObject(2, getBasket().getBasketID(), Types.INTEGER);
			deleteBasketLine.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update() 
	{
		PreparedStatement updateBasketLine;
		try {
			updateBasketLine = ConnectionUtil.connection.prepareStatement("UPDATE BasketLine "
						+ "SET quantity = ? "
						+ "WHERE productID = ? AND basketID = ?");
			
			updateBasketLine.setObject(1, getQuantity(), Types.INTEGER);
			updateBasketLine.setObject(2, getProduct().getProductID(), Types.INTEGER);
			updateBasketLine.setObject(3, getBasket().getBasketID(), Types.INTEGER);

			updateBasketLine.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
