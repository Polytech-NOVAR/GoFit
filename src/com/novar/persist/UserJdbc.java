package com.novar.persist;

import java.sql.*;
import java.util.HashMap;

import com.novar.business.Administrator;
import com.novar.business.Manager;
import com.novar.business.Product;
import com.novar.business.Speaker;
import com.novar.business.Member;
import com.novar.business.User;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.LoginFailedException;
import com.novar.exception.RegisterFailedException;
import com.novar.exception.SyntaxException;
import com.novar.util.ConnectionUtil;

public class UserJdbc extends User{
	
	
	public UserJdbc(HashMap<String,Object> data) throws FalseFieldsException
	{
		super(data);
	}
	
	public void save() throws RegisterFailedException
	{
		try 
		{
			//Insertion de l'utilisateur dans la table User
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
		try {
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
				setSpeaker(new Speaker());
			if( resRoles.getString("pseudoMember") != null )
				setMember(new Member());
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
				
				while(resProducts.next())
				{
					
					HashMap<String,Object> mapProduct = new HashMap<String,Object>();
					
					mapProduct.put("ProductID", resProducts.getInt("ProductID"));
					mapProduct.put("description", resProducts.getString("description"));
					mapProduct.put("price", resProducts.getDouble("price"));
					mapProduct.put("quantity", resProducts.getInt("quantity"));
					mapProduct.put("discountPrice", resProducts.getDouble("discountPrice"));
					
					Product prod = new ProductJdbc(mapProduct);
					
					addProduct(prod);
				}
			}
			catch (SQLException | FalseFieldsException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
