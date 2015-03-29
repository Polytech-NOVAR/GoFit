package com.novar.persist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import com.novar.business.MainFacade;
import com.novar.business.User;
import com.novar.business.UserManager;
import com.novar.exception.SyntaxException;
import com.novar.util.ConnectionUtil;


/**
 * This interface permit to change persistance how the developer decides. For the moment the persistance is only JDBC.
 * <br><br>
 * The persistance can change easily whit this implementation, the developer just has to add a kit, like SrKit (for Serializable class) and in the facade, the developer has to change in the constructor the change of the persistance kit.
 * <br><br>
 * This interface is use in FacadeMain and JdbcKit implements this interface
 * @author PELCE Nicolas
 * @see MainFacade
 * @see AdminFacade
 * @see UserManagerJdbc
 * @see JdbcKit
 */
public class UserManagerJdbc extends UserManager {

	public UserManagerJdbc()
	{
		
	}
	
	/**
	 * Load all the Users from the DB
	 * @return an ArrayList of Users
	 */
	public ArrayList<User> getAllUsers(User admin) {
		PreparedStatement selectuser;
		ArrayList<User> users = new ArrayList<User>();
		try 
		{
			selectuser = ConnectionUtil.connection.prepareStatement("SELECT * "
																	+ "FROM User u "
																	+ "WHERE pseudo != ? ");
			selectuser.setObject(1, admin.getPseudo(), Types.VARCHAR);
			ResultSet res = selectuser.executeQuery();
			res.first();
			if(res.getRow() > 0)
			{
				do
				{
					User user = new UserJdbc();
					try {
						user.setPseudo(res.getString("pseudo"));
					} catch (SyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					user.loadAdmin();		
					users.add(user);
					
				}while(res.next());
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return users;
	}
	
	/**
	 * Load all the Admin from the DB
	 * @return an ArrayList of User
	 */
	public ArrayList<User> getAllAdmin(User admin) {
		PreparedStatement selectadmin;
		ArrayList<User> users = new ArrayList<User>();
		try 
		{
			selectadmin = ConnectionUtil.connection.prepareStatement("SELECT * "
																	+ "FROM Administrator a "
																	+ "WHERE pseudoAdministrator != ? ");
			selectadmin.setObject(1, admin.getPseudo(), Types.VARCHAR);
			ResultSet res = selectadmin.executeQuery();
			res.first();
			if(res.getRow() > 0)
			{
				do
				{
					User user = new UserJdbc();
					try {
						user.setPseudo(res.getString("pseudoAdministrator"));
					} catch (SyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					user.loadAdmin();		
					users.add(user);
					
				}while(res.next());
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return users;
	}
	/**
	 * Load all the Managers from the DB
	 * @return an ArrayList of Users
	 */
	public ArrayList<User> getAllManager(User admin) {
		PreparedStatement selectmanager;
		ArrayList<User> users = new ArrayList<User>();
		try 
		{
			selectmanager = ConnectionUtil.connection.prepareStatement("SELECT * "
																	+ "FROM Manager ma ");
			ResultSet res = selectmanager.executeQuery();
			res.first();
			if(res.getRow() > 0)
			{
				do
				{
					User user = new UserJdbc();
					try {
						user.setPseudo(res.getString("pseudoManager"));
					} catch (SyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					user.loadAdmin();		
					users.add(user);
					
				}while(res.next());
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return users;
	}
	
	/**
	 * Load all the Members from the DB
	 * @return an ArrayList of Users
	 */
	public ArrayList<User> getAllMember(User admin) {
		PreparedStatement selectmember;
		ArrayList<User> users = new ArrayList<User>();
		try 
		{
			selectmember = ConnectionUtil.connection.prepareStatement("SELECT * "
																	+ "FROM Member me ");
			ResultSet res = selectmember.executeQuery();
			res.first();
			if(res.getRow() > 0)
			{
				do
				{
					User user = new UserJdbc();
					try {
						user.setPseudo(res.getString("pseudoMember"));
					} catch (SyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					user.loadAdmin();		
					users.add(user);
					
				}while(res.next());
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return users;
	}
	
	
	/**
	 * Fix the role of Admin for an User
	 * 
	 */
	
	public  void setAdmin(User admin)
	{
		try 
		{
			PreparedStatement insertUser = ConnectionUtil.connection.prepareStatement("INSERT INTO Administrator (pseudoAdministrator) "
																		+ "VALUES ( ? )");
			insertUser.setObject(1, admin.getPseudo(),Types.VARCHAR);
			insertUser.executeUpdate();		
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Fix the role of Manager for an User
	 * 
	 */
	
	public  void setManager(User manager)
	{
		try 
		{
			PreparedStatement insertUser = ConnectionUtil.connection.prepareStatement("INSERT INTO Manager (pseudoManager) "
																		+ "VALUES ( ? )");
			insertUser.setObject(1, manager.getPseudo(),Types.VARCHAR);
			insertUser.executeUpdate();		
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Fix the role of Member for an User
	 * 
	 */
	

	public  void setMember(User member)
	{

		Date d1 = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String d2 = formatter.format(d1);

		try 
		{
			PreparedStatement insertUser = ConnectionUtil.connection.prepareStatement("INSERT INTO Member (pseudoMember, registrationDate) "
																		+ "VALUES ( ?, ? )");
			insertUser.setObject(1, member.getPseudo(),Types.VARCHAR);
			insertUser.setObject(2, d2,Types.DATE);
			insertUser.executeUpdate();		
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete the role of Admin for an User
	 * 
	 */
	
	public void deleteAdmin(User admin) throws SQLException
	{
		PreparedStatement delete;
		delete = ConnectionUtil.connection.prepareStatement("DELETE FROM Administrator "
				+ "WHERE pseudoAdministrator = ?;");
		delete.setObject(1, admin.getPseudo(), Types.VARCHAR);
		delete.executeUpdate();
	}
	
	/**
	 * Delete the role of Manager for an User
	 * 
	 */
	
	public void deleteManager(User manager) throws SQLException
	{
		PreparedStatement delete;
		delete = ConnectionUtil.connection.prepareStatement("DELETE FROM Manager "
				+ "WHERE pseudoManager = ?;");
		delete.setObject(1, manager.getPseudo(), Types.VARCHAR);
		delete.executeUpdate();
	}
	
	/**
	 * Delete the role of Member for an User
	 * 
	 */
	
	
	public void deleteMember(User member) throws SQLException
	{
		PreparedStatement delete;
		delete = ConnectionUtil.connection.prepareStatement("DELETE FROM Member "
				+ "WHERE pseudoMember = ?;");
		delete.setObject(1, member.getPseudo(), Types.VARCHAR);
		delete.executeUpdate();
	}
		
	/*public static void main(String[] args) {
	
		ConnectionUtil.start();
		User user = new UserJdbc();
		try {
			user.setPseudo("jojojo1");
		} catch (SyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.loadAdmin();
		UserManager manager = new UserManagerJdbc();
		/*try {
			manager.deleteMember(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		manager.setMember(user);
		/*manager.setManager(user);
		manager.setSpeaker(user);
		manager.setAdmin(user);
		ArrayList<User> users =  manager.getAllAdmin(user);
		for(int i = 0; i < users.size(); i++)
		{
			System.out.println(users.get(i));
		
		}*/
}