package com.novar.persist;

import java.util.ArrayList;
import java.util.HashMap;

import com.novar.business.Product;
import com.novar.business.User;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.LoginFailedException;
import com.novar.persist.PersistKit;
import com.novar.persist.UserJdbc;

/**
 * This class permit for each method to return the specific Object of the persistance with methods that permit to load, save and more.
 * <br><br>
 * This class implements PersistKit and return the specific Object persistance that you want.
 * @author Antoine JOERG
 * @see PersistKit
 */
public class JdbcKit implements PersistKit
{
	// ===== DEFINITION METHODS =====
	/**
	 * This is the definition of the method to make a user
	 * @param data of a user
	 * @return the user with persistance methods
	 * @throws FalseFieldsException
	 */
	public UserJdbc makeUser(HashMap<String,Object> data) throws FalseFieldsException
	{
		return new UserJdbc(data);
	}
	
	/**
	 * This is the definition of the method to make a MainCategory
	 * @param data of a MainCategory
	 * @return the MainCategory with persistance methods
	 * @throws FalseFieldsException
	 */
	public MainCategoryJdbc makeMainCategory(HashMap<String,Object> data) throws FalseFieldsException
	{
		return new MainCategoryJdbc(data);
	}
	
	/**
	 * This is the definition of the method to make a SubCategory
	 * @param data of a SubCategory
	 * @return the SubCategory with persistance methods
	 * @throws FalseFieldsException
	 */
	public SubCategoryJdbc makeSubCategory(HashMap<String,Object> data) throws FalseFieldsException
	{
		return new SubCategoryJdbc(data);
	}
	
	/**
	 * This is the definition of the method to make a Product
	 * @param data of a Product
	 * @return the Product with persistance methods
	 * @throws FalseFieldsException
	 */
	public ProductJdbc makeProduct(HashMap<String,Object> data) throws FalseFieldsException
	{
		return new ProductJdbc(data);
	}

}
