package com.novar.business;

import java.sql.Date;

public class Member implements Role
{
	private Date registrationDate;
	
	public String toString()
	{
		return ("Member. Date d'inscritpion : " + getRegistrationDate() + "\n");
	}
	
	public Date getRegistrationDate()
	{
		return registrationDate;
	}

}
