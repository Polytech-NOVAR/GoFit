package com.novar.business;

import com.novar.exception.SyntaxException;
import com.novar.persist.UserJdbc;

import junit.framework.TestCase;

public class UserTest extends TestCase {

	public void testSetPseudo() throws SyntaxException {
		User user = new UserJdbc();
		String pseudo = new String("ffff");
		try
		{
			user.setPseudo(pseudo);
		}
		catch (SyntaxException e)
		{
			System.out.println("Test setPseudo : Ok, le pseudo ne peux pas faire moins de 5 caracteres");
		}
		pseudo = "ffffff";
		user.setPseudo(pseudo);
		assertEquals("ffffff", user.getPseudo());		
	}

	public void testSetEmail() throws SyntaxException {
		User user = new UserJdbc();
		String email = new String("aaaaaa");
		try
		{
			user.setEmail(email);
		}
		catch (SyntaxException e)
		{
			System.out.println("Test setEmail : Ok, l'email n'est pas bon");
		}
		email = "servicegofit@gmail.com";
		user.setEmail(email);
		
		assertEquals("servicegofit@gmail.com", user.getEmail());		
	}


	public void testSetZipCode() throws SyntaxException {
		User user = new UserJdbc();
		String zipCode = new String("123456");
		try
		{
			user.setZipCode(zipCode);
		}
		catch (SyntaxException e)
		{
			System.out.println("Test setZipCode : Le zip code ne fait pas 5 caracteres");
		}
		zipCode = "34090";
		user.setZipCode(zipCode);
		
		assertEquals("34090", user.getZipCode());		
	}
}
