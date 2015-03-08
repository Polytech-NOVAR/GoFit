package com.novar.exception;

import java.util.ArrayList;

public class FalseFieldsException extends Exception
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<String> fieldsList;
	
	public FalseFieldsException(ArrayList<String> fieldsList)
	{
		super("Champs Incorrect");
		this.fieldsList = fieldsList;
	}
	
	public ArrayList<String> getFalseFields() {
		return fieldsList;
	}
}