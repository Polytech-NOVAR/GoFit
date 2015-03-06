package com.novar.util;

import java.security.MessageDigest;

public class StringUtil
{
	public static String toCapitalizeCase(String string)
	{
		return Character.toUpperCase(string.charAt(0)) + string.substring(1);
	}

	public static String sha256(String string)
	{
		try
		{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(string.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int ind = 0; ind < hash.length; ind++)
			{
				String hex = Integer.toHexString(0xff & hash[ind]);
				
				if (hex.length() == 1)
				{
					hexString.append('0');
				}
				
				hexString.append(hex);
			}

			return hexString.toString();
		}
		catch (Exception ex)
		{
			throw new RuntimeException(ex);
		}
	}
}
