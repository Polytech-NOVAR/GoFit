package com.novar.business;

import java.sql.SQLException;
import java.util.ArrayList;

import com.novar.exception.FalseFieldsException;

public abstract class PlanningManager
{
	public abstract ArrayList<Planning> loadPlanningsRepetitive() throws SQLException, FalseFieldsException;
	public abstract ArrayList<Planning> loadPlanningsOneTime() throws SQLException, FalseFieldsException;
}
