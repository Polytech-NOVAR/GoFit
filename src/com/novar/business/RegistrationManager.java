package com.novar.business;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.novar.persist.ActivityJdbc;
import com.novar.persist.RegistrationJdbc;
import com.novar.util.ConnectionUtil;

/**
 * this class manages list Of registrations 
 * @author Othmane El kouahy	
 * it is used in 
 * @see JdbcKit
 * @see RegistrationsManagerJdbc 
 */
public abstract class RegistrationManager {
	ArrayList<Activity> listeReg;


	public abstract  ArrayList<Registration> getAllRegistrations(Event Ev);
	public abstract  ArrayList<Registration> getAllRegistrations(User mem);

}
