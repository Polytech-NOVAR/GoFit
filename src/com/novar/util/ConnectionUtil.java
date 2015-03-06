package com.novar.util;
import java.sql.*;

public class ConnectionUtil
{	
	private final static String url = "jdbc:mysql://gofit.cb9xv2w6phgt.us-west-2.rds.amazonaws.com:3306/GOFIT";
	private final static String user = "gofit";
	private final static String pwd = "gofit5151";
	public static Connection connnection = null;
	
	public static void start()
	{
		try
		{
			//Chargement du pilote
			Class.forName("com.mysql.jdbc.Driver");
			connnection = DriverManager.getConnection(url, user, pwd);
		}
		catch (Exception e)
		{
			//Erreur de connexion
			e.printStackTrace();
		}
	}
	
	public static void stop()
	{
		try
		{
			connnection.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	 
}