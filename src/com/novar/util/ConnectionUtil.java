package com.novar.util;

import java.sql.*;

/**
 * This class permit to connect, disconnect or make request to the application database
 * @author Antoine JOERG
 * @author Valentin BERCOT
 */
public class ConnectionUtil
{	
	// ===== STATIC VARIABLES =====
	
	/**
	 * This is the url of the database
	 */
	private final static String url = "jdbc:mysql://gofit.cb9xv2w6phgt.us-west-2.rds.amazonaws.com:3306/GOFIT";
	
	/**
	 * This is the user identifier of the database
	 */
	private final static String user = "gofit";
	
	/**
	 * This is the password identifier of the database
	 */
	private final static String pwd = "gofit5151";
	
	/**
	 * TODO antoine tu sauras mieux que moi quoi mettre ici !
	 * @see Connection
	 */
	public static Connection connection = null;
	
	// ===== STATIC METHODS =====
	
	/**
	 * This method permit to start the connection to the database
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
	 * This method permit to stop the connection to the database
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