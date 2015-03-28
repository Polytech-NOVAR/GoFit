package com.novar.exception;

import java.util.ArrayList;
/**
 * This Exception is used in all the constructors of Business classes. It is throw when on or more attribute is about to be setted whith a wrong parameter.
 * It is mainly used to alert the UI to tell it whats fields are wrong.
 * @author Antoine JOERG
 *
 */
public class FalseFieldsException extends Exception
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The list of all the wrong attributes.
	 */
	private ArrayList<String> fieldsList;
	
	public FalseFieldsException(ArrayList<String> fieldsList)
	{
		super("False field(s).");
		this.fieldsList = fieldsList;
	}
	
	public ArrayList<String> getFalseFields() 
	{
		return fieldsList;
	}
}