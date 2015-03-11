package com.novar.exception;

public class RegisterFailedException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegisterFailedException(String msg)
	{
		super(msg);
	}
}
