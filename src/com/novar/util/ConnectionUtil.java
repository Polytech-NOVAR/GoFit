package com.novar.util;
import java.sql.*;

/**
 * This Utility class is used to connect the application to a database.
 * @author All
 *
 */
public class ConnectionUtil
{	
	private final static String url = "jdbc:mysql://gofit.cb9xv2w6phgt.us-west-2.rds.amazonaws.com:3306/GOFIT";
	private final static String user = "gofit";
	private final static String pwd = "gofit5151";
	public static Connection connection = null;
	
	/**
	 * Static method which start the connection 
	 */
	public static void start()
	{
		try
		{
			//Chargement du pilote
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pwd);
		}
		catch (Exception e)
		{
			//Erreur de connexion
			e.printStackTrace();
		}
	}
	
	/**
	 * Static method which Stop the connection
	 */
	public static void stop()
	{
		try
		{
			connection.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	 
}