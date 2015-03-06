package com.novar.exception;

public class SyntaxException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public SyntaxException(String champ)
	{
		super(champ);
	}
}
