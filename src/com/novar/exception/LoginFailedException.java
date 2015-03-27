package com.novar.exception;
/**
 * This Exception is used for alert the UI when a login failed because the login of the password are invalid.
 * @author Antoine JOERG
 *
 */
public class LoginFailedException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LoginFailedException()
	{
		super("Wrong pseudo and/or password.");
	}

}
