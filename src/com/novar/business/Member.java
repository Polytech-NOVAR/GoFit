package com.novar.business;

import java.sql.Date;

public class Member implements Role
{
	private Date registrationDate;
	
	public Date getRegistrationDate()
	{
		return registrationDate;
	}
	
	public String toString()
	{
		return ("Member. Date d'inscritpion : " + getRegistrationDate() + "\n");
	}
}
