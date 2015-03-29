package com.novar.business;

import java.sql.SQLException;
import java.util.ArrayList;

import com.novar.exception.FalseFieldsException;
import com.novar.exception.RegisterFailedException;
import com.novar.persist.PersistKit;

public class PlanningFacade
{
	private PersistKit kit;
	private PlanningManager manager;

	public PlanningFacade(PersistKit kit)
	{
		this.kit = kit;
		this.manager = kit.makePlanningManager();
	}
	
	public ArrayList<Planning> getPlanningRepetitive() throws SQLException, FalseFieldsException
	{
		return manager.loadPlanningsRepetitive();
	}
	
	public ArrayList<Planning> getPlanningOneTime() throws SQLException, FalseFieldsException
	{
		return manager.loadPlanningsOneTime();
	}
}
