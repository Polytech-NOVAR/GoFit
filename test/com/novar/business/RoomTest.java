package com.novar.business;

import java.util.HashMap;

import com.novar.exception.FalseFieldsException;
import com.novar.persist.JdbcKit;
import com.novar.persist.PersistKit;

import junit.framework.TestCase;

public class RoomTest extends TestCase {
	
	public void testCreateRoom()
	{
		PersistKit kit = new JdbcKit();
		
		HashMap<String,Object> dataRoom = new HashMap<String,Object>();
		dataRoom.put("num", "A208");
		dataRoom.put("area", 25);
		dataRoom.put("street", "25 rue du test");
		dataRoom.put("town", "VilleDeTest");
		dataRoom.put("zipCode", "12345");
		dataRoom.put("country", "FRANCE");
	
		Room r = null;
		try 
		{
			r = kit.makeRoom(dataRoom);
		} 
		catch (FalseFieldsException e) 
		{
			fail("La salle n'a pas pu être créé");
		}
		
		assertNotNull("La salle est nulle.", r);
		assertEquals("Le numéro de salle n'est pas bon.", "A208", r.getNum());
		assertEquals("La superficie n'est pas bonne.", 25, r.getArea());
		assertEquals("La rue n'est pas bonne.", "25 rue du test", r.getStreet());
		assertEquals("La ville n'est pas bonne.", "VilleDeTest",  r.getTown());
		assertEquals("Le code postal n'est pas bon.", "12345",r.getZipCode());
		assertEquals("Le pays n'est pas bon.", "FRANCE", r.getCountry());
	}
}
