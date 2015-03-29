package com.novar.business;

import java.util.ArrayList;

/**
 * This class  is used for Managing list Of activities
 * @author othmane El kouahy
 * it is used in JdbcKit class and AcitivityManagerJdbc 
 *@see JdbcKit 
 *@see ActivityManagerJdbc
 */
public abstract class ActivityManager {
	ArrayList<Activity> listeAct;


	public abstract ArrayList<Activity> getAllActivities();


}
