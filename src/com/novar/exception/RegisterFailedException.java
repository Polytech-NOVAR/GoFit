package com.novar.exception;
/**
 * This Exception is used for alert the UI when a register failed because of the emai or the pseudo is already taken.
 * @author Antoine JOERG
 *
 */
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
