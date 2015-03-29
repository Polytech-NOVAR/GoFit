package com.novar.util;

import java.math.BigInteger;
import java.security.SecureRandom;

import junit.framework.TestCase;

public class StringUtilTest extends TestCase {

	public void testToCapitalizeCase() {
		String test = StringUtil.toCapitalizeCase("nico");
		assertEquals("Nico", test);
		
	}

	public void testNextSessionId() {
		SecureRandom random = new SecureRandom();
		String a = new BigInteger(30, random).toString(32); 
		assertEquals(a.length(), 6);
	}

	public void testSha256() {
		String test = new String();
		test = StringUtil.sha256("a");
		assertEquals("ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e"
				+ "72b9807785afee48bb", test);
	}

}
