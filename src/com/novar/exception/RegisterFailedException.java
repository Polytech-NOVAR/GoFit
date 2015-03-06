package com.novar.exception;

import java.util.ArrayList;

public class RegisterFailedException extends Exception
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<String> falseFields;
	
	public RegisterFailedException(ArrayList<String> falseFields)
	{
		super("Inscription impossible");
		this.falseFields = falseFields;
	}
	
	public ArrayList<String> getFalseFields() {
		return falseFields;
	}
}
