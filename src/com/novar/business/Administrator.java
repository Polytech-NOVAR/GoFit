package com.novar.business;

import java.util.ArrayList;

import com.novar.persist.JdbcKit;

/**
 * This class show the Administrator Role
 * <br><br>
 * This class is use in every class which use User
 * @author Valentin BERCOT-DUFLOS
 * @see Role
 */
public  class Administrator implements Role
{
	public String toString()
	{
		return ("Administrator");
	}
}
