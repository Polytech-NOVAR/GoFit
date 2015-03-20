package com.novar.persist;

import java.util.ArrayList;
import java.util.HashMap;

import com.novar.business.CategoryManager;
import com.novar.business.MainCategory;
import com.novar.business.Product;
import com.novar.business.SubCategory;
import com.novar.business.User;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.LoginFailedException;

/**
 * This interface permit to change persistance how the developer decides. For the moment the persistance is only JDBC.
 * <br><br>
 * The persistance can change easily whit this implementation, the developer just has to add a kit, like SrKit (for Serializable class) and in the facade, the developer has to change in the constructor the change of the persistance kit.
 * <br><br>
 * This interface is use in FacadeMain and JdbcKit implements this interface
 * @author Antoine JOERG
 * @see FacadeMain
 * @see JdbcKit
 */
public interface PersistKit
{
	// ===== DECLARATION METHODS =====
	
	/**
	 * This is the declaration of the method to make a user
	 * @param data of a user
	 * @return the user
	 * @throws FalseFieldsException
	 */
	public User makeUser(HashMap<String,Object> data) throws FalseFieldsException;
	
	/**
	 * This is the declaration of the method to make a MainCategory
	 * @param data of a Maincategory
	 * @return the Maincategory
	 * @throws FalseFieldsException
	 */
	public MainCategory makeMainCategory(HashMap<String,Object> data) throws FalseFieldsException;
	
	/**
	 * This is the declaration of the method to make a SubCategory
	 * @param data of a SubCategory
	 * @return the SubCategory
	 * @throws FalseFieldsException
	 */
	public SubCategory makeSubCategory(HashMap<String,Object> data) throws FalseFieldsException;
	
	/**
	 * This is the declaration of the method to make a Product
	 * @param data of a Product
	 * @return the product
	 * @throws FalseFieldsException
	 */
	public Product makeProduct(HashMap<String,Object> data) throws FalseFieldsException;

	
	public CategoryManager makeSubCategoryManager();
}
