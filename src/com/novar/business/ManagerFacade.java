package com.novar.business;

import java.sql.SQLException;
import java.util.ArrayList;

import com.novar.persist.PersistKit;

public class ManagerFacade {


	private PersistKit kit = null;
	
	public ManagerFacade (PersistKit kit)
	{
		this.kit = kit;
	}
}
	