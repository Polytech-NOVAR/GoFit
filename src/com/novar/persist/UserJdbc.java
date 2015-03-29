package com.novar.persist;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.novar.business.Administrator;
import com.novar.business.Basket;
import com.novar.business.Manager;
import com.novar.business.Product;
import com.novar.business.Speaker;
import com.novar.business.Member;
import com.novar.business.User;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.InvalidEmailException;
import com.novar.exception.LoginFailedException;
import com.novar.exception.RegisterFailedException;
import com.novar.exception.SyntaxException;
import com.novar.util.ConnectionUtil;

public class UserJdbc extends User
{	
	public UserJdbc()
	{
		super();
	}
	
	public UserJdbc(HashMap<String,Object> data) throws FalseFieldsException
	{
		super(data);
	}
	
	public void save() throws RegisterFailedException
	{
		try 
		{
			PreparedStatement insertUser = ConnectionUtil.connection.prepareStatement("INSERT INTO User (pseudo, password, lastName, firstName, phone, email, street, town, zipCode, country) "
																		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			insertUser.setObject(1, getPseudo(),Types.VARCHAR);
			insertUser.setObject(2, getPassword(),Types.VARCHAR);
			insertUser.setObject(3, getLastName(),Types.VARCHAR);
			insertUser.setObject(4, getFirstName(),Types.VARCHAR);
			insertUser.setObject(5, getPhone(),Types.VARCHAR);
			insertUser.setObject(6, getEmail(),Types.VARCHAR);
			insertUser.setObject(7, getStreet(),Types.VARCHAR);
			insertUser.setObject(8, getTown(),Types.VARCHAR);
			insertUser.setObject(9, getZipCode(), Types.VARCHAR);
			insertUser.setObject(10, getCountry(),Types.VARCHAR);
			insertUser.executeUpdate();		
		}
		catch (SQLException e) 
		{
			String message = e.getMessage();
			if(message.endsWith("'unEmail'"))
			{
				throw new RegisterFailedException("email2");
			}
			else if (message.endsWith("'PRIMARY'"))
			{
				throw new RegisterFailedException("pseudo2");
			}
		}
	}
	
	public void updateProfile() throws SQLException
	{
		PreparedStatement updateProfile;
		updateProfile = ConnectionUtil.connection.prepareStatement("UPDATE User "
				+ "SET "
				+ "lastName = ?,"
				+ "firstName = ?,"
				+ "phone = ?,"
				+ "email = ?,"
				+ "street = ?,"
				+ "town = ?,"
				+ "zipCode = ?,"
				+ "country = ? "
				+ "WHERE pseudo = ?;");
		
		updateProfile.setObject(1, getLastName(),Types.VARCHAR);
		updateProfile.setObject(2, getFirstName(),Types.VARCHAR);
		updateProfile.setObject(3, getPhone(),Types.VARCHAR);
		updateProfile.setObject(4, getEmail(),Types.VARCHAR);
		updateProfile.setObject(5, getStreet(),Types.VARCHAR);
		updateProfile.setObject(6, getTown(),Types.VARCHAR);
		updateProfile.setObject(7, getZipCode(), Types.VARCHAR);
		updateProfile.setObject(8, getCountry(),Types.VARCHAR);
		updateProfile.setObject(9, getPseudo(), Types.VARCHAR);
		updateProfile.executeUpdate();
		
		if (isSpeaker())
		{
			updateSpeaker();
		}
	}
	
	public void updateSpeaker() throws SQLException
	{
		PreparedStatement updateSpeaker;
		updateSpeaker = ConnectionUtil.connection.prepareStatement("UPDATE Speaker "
				+ "SET "
				+ "shortDescription = ?,"
				+ "detailedDescription = ? "
				+ "WHERE pseudoSpeaker = ?;");
		
		updateSpeaker.setObject(1, getSpeaker().getShortDescription(), Types.VARCHAR);
		updateSpeaker.setObject(2, getSpeaker().getDetailedDescription(), Types.VARCHAR);
		updateSpeaker.setObject(3, getPseudo(), Types.VARCHAR);
		updateSpeaker.executeUpdate();
	}
	
	public void updatePassword() throws SQLException
	{
		PreparedStatement updatePassword;
		updatePassword = ConnectionUtil.connection.prepareStatement("UPDATE User "
				+ "SET "
				+ "password = ?"
				+ "WHERE pseudo = ?;");
		
		updatePassword.setObject(1, getPassword(),Types.VARCHAR);
		updatePassword.setObject(2, getPseudo(), Types.VARCHAR);
		updatePassword.executeUpdate();
	}
	
	public void updateForgottenPassword() throws InvalidEmailException
	{
		
		PreparedStatement updatePassword;
		PreparedStatement email;
		
		try {
			email = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM User "
					+ "Where email = ?; ");
			email.setObject(1, getEmail(), Types.VARCHAR);
			ResultSet res = email.executeQuery();
			res.last();
			if(res.getRow() == 0)
					throw new InvalidEmailException();
			else
			{
				updatePassword = ConnectionUtil.connection.prepareStatement("UPDATE User "
						+ "SET password = ? "
						+ "WHERE email = ?");
				updatePassword.setObject(1, getPassword(),Types.VARCHAR);
				updatePassword.setObject(2, getEmail(), Types.VARCHAR);
				updatePassword.executeUpdate();
			}
		}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void delete() throws SQLException
	{
		PreparedStatement delete;
		delete = ConnectionUtil.connection.prepareStatement("DELETE FROM User "
				+ "WHERE pseudo = ?;");
		delete.setObject(1, getPseudo(), Types.VARCHAR);
		delete.executeUpdate();
		
	}
	
	public void load() throws LoginFailedException
	{
		PreparedStatement selectUser;
		try 
		{
			selectUser = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM User "
					+ "WHERE pseudo = ? "
					+ "AND password = ?;");

			selectUser.setObject(1, getPseudo(), Types.VARCHAR);
			selectUser.setObject(2, getPassword(),Types.VARCHAR);
			ResultSet res = selectUser.executeQuery();
			res.last();
			if(res.getRow() == 0)
				throw new LoginFailedException();
			else
			{
				try 
				{
					setEmail(res.getString("email"));
					setFirstName(res.getString("firstName"));
					setLastName(res.getString("lastName"));
					setPhone(res.getString("phone"));
					setStreet(res.getString("street"));
					setTown(res.getString("town"));
					setZipCode(res.getString("zipCode"));
					setCountry(res.getString("country"));
					
					loadRoles();	
				} 
				catch (SyntaxException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadRoles()
	{		
		try
		{
			PreparedStatement selectRoles;
			selectRoles = ConnectionUtil.connection.prepareStatement("SELECT * "
																	+ "FROM User u "
																	+ "LEFT JOIN Administrator a ON u.pseudo = a.pseudoAdministrator "
																	+ "LEFT JOIN Manager ma ON u.pseudo = ma.pseudoManager "
																	+ "LEFT JOIN Speaker s ON u.pseudo = s.pseudoSpeaker "
																	+ "LEFT JOIN Member m ON u.pseudo = m.pseudoMember "
																	+ "WHERE u.pseudo = ?");
			
			selectRoles.setObject(1, this.getPseudo(), Types.VARCHAR);
			ResultSet resRoles = selectRoles.executeQuery();
			
			resRoles.next();
			if( resRoles.getString("pseudoAdministrator") != null )
				setAdministrator(new Administrator());
			if( resRoles.getString("pseudoManager") != null )
				setManager(new Manager());
			if( resRoles.getString("pseudoSpeaker") != null )
				setSpeaker(new Speaker(resRoles.getString("shortDescription"), resRoles.getString("detailedDescription")));
			if( resRoles.getString("pseudoMember") != null )
				setMember(new Member());
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadAdmin()
	{
		PreparedStatement selectUser;
		try 
		{
			selectUser = ConnectionUtil.connection.prepareStatement("SELECT * FROM User WHERE pseudo = ? ");

			selectUser.setObject(1, getPseudo(), Types.VARCHAR);
			ResultSet res = selectUser.executeQuery();
			res.last();
				try 
				{
					setEmail(res.getString("email"));
					setFirstName(res.getString("firstName"));
					setLastName(res.getString("lastName"));
					setPhone(res.getString("phone"));
					setStreet(res.getString("street"));
					setTown(res.getString("town"));
					setZipCode(res.getString("zipCode"));
					setCountry(res.getString("country"));
					
					loadRoles();	
				} 
				catch (SyntaxException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void loadProducts() 
	{
		if(isMember())
		{
			PreparedStatement selectProducts;
			try 
			{
				selectProducts = ConnectionUtil.connection.prepareStatement("SELECT * "
						+ "FROM User u, Product p "
						+ "WHERE u.pseudo = p.pseudo "
						+ "AND p.pseudo = ? ;");
	
				selectProducts.setObject(1, getPseudo(), Types.VARCHAR);
				ResultSet resProducts = selectProducts.executeQuery();
				ArrayList<Product> products = new ArrayList<Product>();
				
				HashMap<String,Object> mapProduct = new HashMap<String,Object>();
				while(resProducts.next())
				{

					mapProduct.put("ProductID", resProducts.getInt("ProductID"));
					Product prod = new ProductJdbc(mapProduct);
					prod.load();
				
					products.add(prod);
				}
				this.getMember().setProducts(products);
			}
			catch (SQLException | FalseFieldsException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void loadInfo() 
	{
		PreparedStatement selectUser;
		try 
		{
			selectUser = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM User "
					+ "WHERE pseudo = ? ");

			selectUser.setObject(1, getPseudo(), Types.VARCHAR);
			ResultSet res = selectUser.executeQuery();
			res.last();
			try 
			{
				setEmail(res.getString("email"));
				setFirstName(res.getString("firstName"));
				setLastName(res.getString("lastName"));
				setPhone(res.getString("phone"));
				setStreet(res.getString("street"));
				setTown(res.getString("town"));
				setZipCode(res.getString("zipCode"));
				setCountry(res.getString("country"));
			} 
			catch (SyntaxException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			loadRoles();	
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void loadBasket() 
	{
		PreparedStatement selectBasket;
		PreparedStatement insertBasket;
		try 
		{
			selectBasket = ConnectionUtil.connection.prepareStatement("SELECT * "
					+ "FROM User u, Basket b "
					+ "WHERE u.pseudo = b.pseudo "
					+ "AND b.state = true "
					+ "AND u.pseudo = ? ;");
			
			selectBasket.setObject(1, getPseudo(), Types.VARCHAR);
			ResultSet res = selectBasket.executeQuery();
			Basket basket = new BasketJdbc();
			basket.setUser(this);
			
			res.last();
			if(res.getRow() == 0)
			{
				basket.save();
	            this.setBasket(basket);
			}
			else
			{
				basket.setBasketID(res.getInt("basketID"));
				this.setBasket(basket);
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

