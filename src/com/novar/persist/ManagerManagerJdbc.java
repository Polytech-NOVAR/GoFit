package com.novar.persist;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.novar.business.Administrator;
import com.novar.business.ManagerManager;
import com.novar.business.User;
import com.novar.business.UserManager;
import com.novar.exception.SyntaxException;
import com.novar.util.ConnectionUtil;

public class ManagerManagerJdbc extends ManagerManager {

	public ManagerManagerJdbc()
	{
		
	}

	@Override
	public ArrayList<User> getAllSpeaker(User speaker) 
	{
		PreparedStatement selectSpeaker;
		ArrayList<User> users = new ArrayList<User>();
		try 
		{
			selectSpeaker = ConnectionUtil.connection.prepareStatement("SELECT * "
																	+ "FROM Speaker u ");
			ResultSet res = selectSpeaker.executeQuery();
			res.first();
			if(res.getRow() > 0)
			{
				do
				{
					User user = new UserJdbc();
					try {
						user.setPseudo(res.getString("pseudoSpeaker"));
					} catch (SyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					user.loadAdmin();		
					users.add(user);
						
				}
				while(res.next());
				}
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			return users;
		}

	@Override
	public  void setSpeaker(User speaker)
	{
		try 
		{
			PreparedStatement insertUser = ConnectionUtil.connection.prepareStatement("INSERT INTO Speaker (pseudoSpeaker) "
																		+ "VALUES ( ? )");
			insertUser.setObject(1, speaker.getPseudo(),Types.VARCHAR);
			insertUser.executeUpdate();		
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void deleteSpeaker(User speaker) throws SQLException
	{
		PreparedStatement delete;
		delete = ConnectionUtil.connection.prepareStatement("DELETE FROM Speaker "
				+ "WHERE pseudoSpeaker = ?;");
		delete.setObject(1, speaker.getPseudo(), Types.VARCHAR);
		delete.executeUpdate();
	
	}
	
	/*public static void main(String[] args) {
	
	ConnectionUtil.start();
	User user = new UserJdbc();
	try {
		user.setPseudo("jojojo");
	} catch (SyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	user.loadAdmin();
	ManagerManager speaker = new ManagerManagerJdbc();
	speaker.setSpeaker(user);

	ArrayList<User> users =  speaker.getAllSpeaker(user);
	for(int i = 0; i < users.size(); i++)
	{
		System.out.println(users.get(i));
	
	}
	}*/
}




