package com.novar.business;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.*;

import com.novar.exception.FalseFieldsException;
import com.novar.exception.LoginFailedException;
import com.novar.exception.RegisterFailedException;
import com.novar.exception.SyntaxException;
import com.novar.persist.JdbcKit;
import com.novar.persist.PersistKit;
import com.novar.util.ConnectionUtil;
import com.novar.util.StringUtil;

public abstract class User
{
	private String pseudo;
	private String password;
	private String lastName;
	private String firstName;
	private String phone;
	private String email;
	
	private String street;
	private String town;
	private String zipCode;
	private String country;

	private Administrator administrator = null;
	private Manager manager = null;
	private Speaker speaker = null;
	private Member member = null;
	
	public User()
	{
		
	}
	
	public User(HashMap<String,Object> data) throws FalseFieldsException
	{
		Class[] typeArg = new Class[1];
		Object[] arg = new Object[1];
		ArrayList<String> errors = new ArrayList<String>();
		
		for (String mapKey : data.keySet())
		{
			String setterName = "set" + StringUtil.toCapitalizeCase(mapKey);
			typeArg[0] = data.get(mapKey).getClass();
			arg[0] = data.get(mapKey);
			Method setter;
			try {
				setter = this.getClass().getMethod(setterName, typeArg);
				try
				{
					setter.invoke(this, arg);
				}
				catch (Exception e)
				{
					errors.add(e.getCause().getMessage());
				}
			} catch (NoSuchMethodException | SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(!errors.isEmpty())
			throw new FalseFieldsException(errors);
	}
	
	public String getPseudo()
	{
		return pseudo;
	}
	
	public void setPseudo(String pseudo) throws SyntaxException
	{
		Pattern pPseudo = Pattern.compile("^[a-zA-Z0-9-_]{6,51}$");
		Matcher mPseudo = pPseudo.matcher(pseudo);
		if(mPseudo.matches())
		{
			this.pseudo = pseudo;
		}
		else
			throw new SyntaxException("pseudo");
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password) throws SyntaxException
	{
		Pattern pPass = Pattern.compile("^[a-zA-Z0-9]{6,51}$");
		Matcher mPass = pPass.matcher(password);
		if(mPass.matches())
		{
			this.password = StringUtil.sha256(password);
		}
		else
			throw new SyntaxException("password");
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public void setLastName(String lastName) throws SyntaxException
	{
		Pattern pLast = Pattern.compile("^[a-zA-Z-]{1,51}$");
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
		Pattern pFirst = Pattern.compile("^[a-zA-Z-]{1,51}$");
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

	public String getStreet()
	{
		return street;
	}
	
	public void setStreet(String street) throws SyntaxException
	{
		Pattern pAddress = Pattern.compile("^[0-9]{1,3} [a-zA-Z- ]{2,48}$");
		Matcher mAddress = pAddress.matcher(street);
		if(mAddress.matches())
		{
			this.street = street;
		}
		else
			throw new SyntaxException("street");
	}
	
	public String getTown()
	{
		return town;
	}
	
	public void setTown(String town) throws SyntaxException
	{
		Pattern pTown = Pattern.compile("^[a-zA-Z-]{2,51}$");
		Matcher mTown = pTown.matcher(town);
		if(mTown.matches())
		{
			this.town = town;
		}
		else
			throw new SyntaxException("town");
	}
	
	public String getZipCode()
	{
		return zipCode;
	}
	
	public void setZipCode(String zipCode) throws SyntaxException
	{
		Pattern pZip = Pattern.compile("^[0-9]{5}$");
		Matcher mZip = pZip.matcher(zipCode);
		if(mZip.matches())
		{
			this.zipCode = zipCode;
		}
		else
			throw new SyntaxException("zipCode");
	}
	
	public String getCountry()
	{
		return country;
	}
	
	public void setCountry(String country) throws SyntaxException
	{
		Pattern pCountry = Pattern.compile("^[a-zA-Z-]{2,51}$");
		Matcher mCountry = pCountry.matcher(country);
		if(mCountry.matches())
		{
			this.country = country;
		}
		else
			throw new SyntaxException("country");
	}
	
	public Administrator getAdministrator()
	{
		return this.administrator;
	}
	
	public void setAdministrator(Administrator administrator)
	{
		if (this.administrator == null)
		{
			this.administrator = administrator;
		}
	}
	
	public boolean isAdministrator()
	{
		if(this.getAdministrator() == null)
			return false;
		else
			return true;	
	}
	
	public Manager getManager()
	{
		return this.manager;
	}
	
	public void setManager(Manager manager)
	{
		if (this.manager == null)
		{
			this.manager = manager;
		}
	}
	
	public boolean isManager()
	{
		if(this.getManager() == null)
			return false;
		else
			return true;	
	}
	
	public Speaker getSpeaker()
	{
		return this.speaker;
	}
	
	public void setSpeaker(Speaker speaker)
	{
		if (this.speaker == null)
		{
			this.speaker = speaker;
		}
	}
	
	public boolean isSpeaker()
	{
		if(this.getSpeaker() == null)
			return false;
		else
			return true;	
	}
	
	public Member getMember()
	{
		return this.member;
	}
	
	public void setMember(Member member)
	{
		if (this.member == null)
		{
			this.member = member;
		}
	}
	
	public boolean isMember()
	{
		if(this.getMember() == null)
			return false;
		else
			return true;	
	}
	
	public String toString()
	{
		String result = new String();
		
		result = "Pseudo : " + this.getPseudo() + "\n"
				+ "LastName : " + this.getLastName() + "\n"
				+ "Password : " + this.getPassword() + "\n"
				+ "FirstName : " + this.getFirstName() + "\n"
				+ "Phone : " + this.getPhone() + "\n"
				+ "Email : " + this.getEmail() + "\n"
				+ "Street : " + this.getStreet() + "\n"
				+ "City : " + this.getTown() + "\n"
				+ "ZipCode : " + this.getZipCode() + "\n"
				+ "Country : " + this.getCountry() + "\n";
		
		return result;	
	}
	
	////////////// HOOKS ////////////////
	public abstract void load() throws LoginFailedException;
	public abstract void save() throws RegisterFailedException;
	/*public abstract void update();
	public abstract void delete();*/
}
