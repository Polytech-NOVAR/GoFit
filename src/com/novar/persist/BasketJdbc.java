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

public class BasketJdbc extends Basket {

	@Override
	public void load() 
	{
		PreparedStatement selectBasket;
		try 
		{
			selectBasket = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM Basket b, BasketLines bl "
					+ "WHERE b.basketID = bl.basketID "
					+ "AND b.basketID = ? ");

			selectBasket.setObject(1, getBasketID(), Types.INTEGER);
			ResultSet res = selectBasket.executeQuery();
			ArrayList<BasketLine> lines = new ArrayList<BasketLine>();
			
			while(res.next())
			{
				BasketLine line = new BasketLineJdbc();
				
				Product product = new ProductJdbc();
				product.setProductID(res.getInt("productID"));
				product.load();
				line.setProduct(product);
				
				line.setQuantity(res.getInt("quantity"));
				
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
	public void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

}
