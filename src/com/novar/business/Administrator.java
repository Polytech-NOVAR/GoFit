package com.novar.business;

import java.util.ArrayList;

public  class Administrator implements Role
{
	private String pseudoAdministrator;
	
	public String toString()
	{
		return ("Administrator");
	}
		
	public String getPseudoAdministrator()
	{
		return pseudoAdministrator;
	}
	
}
