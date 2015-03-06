package com.novar.business;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;

import com.novar.exception.SyntaxException;
import com.novar.util.StringUtil;

public class User
{
	private String pseudo;
	private String password;
	private String lastName;
	private String firstName;
	private String phone;
	private String email;
	private ArrayList<Address> address;
	
	public void setUp(HashMap<String,Object> data)
	{
		//TODO Faire le cas de la verif mdp. Login 1arg, register 2args.
		Class[] typeArg = new Class[1];
		Object[] arg = new Object[1];
		
		for (String mapKey : data.keySet())
		{
			String setterName = "set" + StringUtil.toCapitalizeCase(mapKey);
			typeArg[0] = data.get(mapKey).getClass();
			arg[0] = data.get(mapKey);
			
			try
			{
				Method setter = this.getClass().getMethod(setterName, typeArg);
				setter.invoke(this, arg);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public String getPseudo()
	{
		return pseudo;
	}
	
	public void setPseudo(String pseudo)
	{
		this.pseudo = pseudo;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = StringUtil.sha256(password);
	}
	
	public void setPassword(String password, String confirmPassword)
	{
		if (password == confirmPassword)
		{
			setPassword(password);
		}
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public void setLastName(String lastName) throws SyntaxException
	{
		Pattern pLast = Pattern.compile("^[a-zA-Z-]{1,51}");
		Matcher mLast = pLast.matcher(lastName);
		
		if(mLast.matches())
		{
			this.lastName = lastName;
		}
		else
			throw new SyntaxException("lastName");
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public void setFirstName(String firstName) throws SyntaxException
	{
		Pattern pFirst = Pattern.compile("^[a-zA-Z-]{1,51}");
		Matcher mFirst = pFirst.matcher(firstName);
		if(mFirst.matches())
		{
			this.firstName = firstName;
		}
		else
			throw new SyntaxException("firstName");
	}
	
	public String getPhone()
	{
		return phone;
	}
	
	public void setPhone(String phone) throws SyntaxException
	{
		Pattern pTel = Pattern.compile("^0[0-9]{9}$");
		Matcher mTel = pTel.matcher(phone);
		
		if(mTel.matches())
		{
			this.phone = phone;
		}
		else
			throw new SyntaxException("phone");
		
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email) throws SyntaxException
	{
		Pattern pEmail = Pattern.compile("^[a-zA-Z0-9._-]{1,64}@[a-zA-Z0-9-]{2,252}[.]{1}[a-zA-Z]{2,6}$");
		Matcher mEmail = pEmail.matcher(email);
		if(mEmail.matches())
		{
			this.email = email;
		}
		else
			throw new SyntaxException("email");
	}
	
	public ArrayList<Address> getAddress()
	{
		return address;
	}
	
	public void setAddress(ArrayList<Address> adds)
	{
		this.address = adds;
	}
	
	public String toString()
	{
		return ("Pseudo : " + this.getPseudo() + "\n"
				+ "LastName : " + this.getLastName() + "\n"
				+ "Password : " + this.getPassword() + "\n"
				+ "FirstName : " + this.getFirstName() + "\n"
				+ "Phone : " + this.getPhone() + "\n"
				+ "Email : " + this.getEmail() + "\n"
				+ "Adrress : " + this.getAddress());
	}
}
