package com.novar.exception;

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
