package com.novar.business;

import java.sql.SQLException;
import java.util.ArrayList;

import com.novar.exception.FalseFieldsException;
import com.novar.persist.PlanningManagerJdbc;
import com.novar.persist.UsersManagerJdbc;

/**
 * This abstract class allow us to manage plannings.
 * <br>
 * This real class use is PlanningManagerJdbc and it s use in PlanningFacade
 * <br>
 * @author Valentin BERCOT-DUFLOS
 * @see PlanningManagerJdbc
 * @see PlanningFacade
 */
public abstract class PlanningManager
{
	// Hooks
	public abstract ArrayList<Planning> loadPlanningsRepetitive() throws SQLException, FalseFieldsException;
	public abstract ArrayList<Planning> loadPlanningsOneTime() throws SQLException, FalseFieldsException;
}
