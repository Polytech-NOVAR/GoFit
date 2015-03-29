package com.novar.business;

import java.util.HashMap;

import com.novar.exception.FalseFieldsException;
import com.novar.persist.JdbcKit;
import com.novar.persist.PersistKit;

import junit.framework.TestCase;

public class ProductTest extends TestCase 
{
	public void testCreateProduct()
	{
		PersistKit kit = new JdbcKit();
		
		HashMap<String, Object> dataProduct = new HashMap<String,Object>();
		dataProduct.put("description", "Tapis de yoga");
		dataProduct.put("price", 10.0);
		dataProduct.put("discountPrice",8.0);

		
		HashMap<String, Object> dataCategory = new HashMap<String,Object>();
		dataCategory.put("catID", 1);
		dataCategory.put("description", "Tapis");
		
		Category c = null;
		try 
		{
			c = kit.makeMainCategory(dataCategory);
		} 
		catch (FalseFieldsException e) 
		{
			fail("Le produit n'a pas pu être créé");
		}
		
		dataProduct.put("category", c);
		Product p = null;
		try 
		{
			p = kit.makeProduct(dataProduct);
		} 
		catch (FalseFieldsException e) 
		{
			fail("La category n'a pas pu être créé");
		}
		
		assertNotNull("Le produit est nul", p);
		assertEquals("La description n'est pas bonne",	"Tapis de yoga",	p.getDescription());
		assertEquals("Le prix n'est pas bon",	10.0,	p.getPrice());
		assertEquals("La prix discount n'est pas bon",	8.0,	p.getDiscountPrice());
		assertEquals("La categorie n'est pas bonne",	c,	p.getCategory());
	}
}
