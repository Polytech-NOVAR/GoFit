package com.novar.business;

public class Manager implements Role
{
	private String pseudoManager;
	
	public Manager()
	{
		
	}
	
	public String toString()
	{
		return ("Manager");
	}
	
	public String getPseudoManager()
	{
		return pseudoManager;
	}
}
