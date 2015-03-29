package com.novar.business;


import java.util.Date;

import com.novar.exception.SyntaxException;
import com.novar.persist.PersistKit;
/**
 * This class provides methods used to manage the basket;
 * @author Antoine JOERG
 *
 */
public class BasketFacade 
{
	private PersistKit kit = null;
	
	/**
	 * Construct a BasketFacade with the right persistKit.
	 * @param kit persistence engine
	 */
	public BasketFacade(PersistKit kit)
	{
		this.kit = kit;
	}
	
	public void more(BasketLine line)
	{
		line.setQuantity(line.getQuantity()+1);
		line.update();
	}
	
	public void less(BasketLine line)
	{
		line.setQuantity(line.getQuantity()-1);
		line.update();
	}

	public void deleteLine(BasketLine linei)
	{
		linei.delete();
	}

	public void order(Basket basket) throws SyntaxException 
	{
		for(int i =0; i<basket.getLines().size();i++)
		{
			Product producti = basket.getLines().get(i).getProduct();
			if(producti.getProductID() == 2)
			{
				//setMembre
			}
			else
			{
				producti.setQuantity(producti.getQuantity() - basket.getLines().get(i).getQuantity());
				producti.update();
			}
		}
		basket.setState(0);
		basket.setOrderDate(new Date());
		basket.update();
	}
}
