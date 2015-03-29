package com.novar.exception;

/**
 * This Exception is used for alert the user that the address is invalid.
 * @author PELCE Nicolas
 *
 */
public class InvalidEmailException extends Exception {


	public InvalidEmailException()
	{
		super("Adresse invalide.");
	}
	
}
