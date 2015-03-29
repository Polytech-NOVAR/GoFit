package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.novar.business.Basket;
import com.novar.business.BasketLine;
import com.novar.business.Product;
import com.novar.util.ConnectionUtil;
/**
 * This concrete subclass of Basket uses the Jdbc technology to perfom the methods defined in Basket.
 * @author Antoine JOERG
 *
 */
public class BasketJdbc extends Basket {

	@Override
	public void load() 
	{
		PreparedStatement selectBasket;
		try 
		{
			selectBasket = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM Basket b, BasketLine bl "
					+ "WHERE b.basketID = bl.basketID "
					+ "AND b.basketID = ? ");

			selectBasket.setObject(1, getBasketID(), Types.INTEGER);
			ResultSet res = selectBasket.executeQuery();
			ArrayList<BasketLine> lines = new ArrayList<BasketLine>();
			
			while(res.next())
			{
				
				Product product = new ProductJdbc();
				product.setProductID(res.getInt("productID"));
				product.load();
				
				BasketLine line = new BasketLineJdbc(product, this, res.getInt("quantity"));
				
				lines.add(line);
			}
			this.setLines(lines);
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void save() 
	{
		PreparedStatement insertBasket;
		try 
		{
				Basket basket = new BasketJdbc();
				
				insertBasket = ConnectionUtil.connection.prepareStatement("INSERT INTO Basket (Pseudo, state) VALUES (?, true)", PreparedStatement.RETURN_GENERATED_KEYS);
				insertBasket.setObject(1, getUser().getPseudo(), Types.VARCHAR);
				insertBasket.executeUpdate();
				ResultSet key = insertBasket.getGeneratedKeys();
	            if (key.next())
	            	basket.setBasketID(key.getInt(1));
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	public void update()
	{
		try 
		{
			PreparedStatement updateBasket = ConnectionUtil.connection.prepareStatement("UPDATE Basket "
					+ "SET state = ?, "
					+ "orderDate = ? "
					+ "WHERE basketID = ?");
			
			updateBasket.setObject(1, getState(),Types.INTEGER);
			updateBasket.setObject(2, getOrderDate(),Types.DATE);
			updateBasket.setObject(3, getBasketID(),Types.INTEGER);
			updateBasket.executeUpdate();	
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
