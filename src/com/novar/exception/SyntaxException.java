package com.novar.exception;

/**
 * This Exception is used in all the setters of Business classes. It is throw when an attribute is about to be setted whith a wrong parameter.
 * @author Antoine JOERG
 *
 */
public class SyntaxException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public SyntaxException(String champ)
	{
		super(champ);
	}
}
