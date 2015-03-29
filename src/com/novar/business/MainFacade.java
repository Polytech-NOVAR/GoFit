package com.novar.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.novar.exception.*;
import com.novar.persist.PersistKit;
import com.novar.util.SendMail;
import com.novar.util.StringUtil;

/**
 * This class is the main facade of the application. This class store the user connected and allow us to choose a kit for the persistence.
 * <br>
 * Methods used before the user is connected and about is profile are here.
 * <br>
 * And this class returns every facade needed.
 * @author Antoine JOERG
 * @author Nicolas PELCE
 * @author Othmane EL KOUAHY
 * @author Romain GUILMET
 * @author Valentin BERCOT-DUFLOS
 * @see PersistKit
 */
public class MainFacade
{
	private User theUser = null;
	private PersistKit kit = null;
	private RoomAccessoryFacade roomFacade;
	private NotificationFacade notificationFacade;
	private ProductFacade product = null;
	private AdminFacade adminFacade;
	private ManagerFacade managerFacade;
	private ProductFacade productFacade = null;
	private CategoryFacade categoryFacade = null;
	private UsersFacade usersFacade;
	private PlanningFacade planningFacade;
	private BasketFacade basketFacade = null;

	
	public MainFacade(PersistKit kit)
	{
		this.kit = kit;
		this.roomFacade = new RoomAccessoryFacade(kit);
		this.notificationFacade = new NotificationFacade(kit);
		this.adminFacade = new AdminFacade(kit);
		this.managerFacade = new ManagerFacade(kit);
		roomFacade = new RoomAccessoryFacade(kit);
		notificationFacade = new NotificationFacade(kit);
		productFacade = new ProductFacade(kit);
		categoryFacade = new CategoryFacade(kit);
		usersFacade = new UsersFacade(kit);
		planningFacade = new PlanningFacade(kit);
		basketFacade = new BasketFacade(kit);

	}
	
	// Get User
	public User getUser()
	{
		return this.theUser;
	}
	
	
	// Get facades
	public RoomAccessoryFacade getRoomFacade()
	{
		return this.roomFacade;
	}
	
	public NotificationFacade getNotificationFacade()
	{
		return this.notificationFacade;
	}	
	
	public ProductFacade getProductFacade()
	{
		return productFacade;
	}
	
	public CategoryFacade getCategoryFacade()
	{
		return categoryFacade;
	}
	
	public UsersFacade getUsersFacade()
	{
		return usersFacade;
	}
	
	public PlanningFacade getPlanningFacade()
	{
		return planningFacade;
	}
	
	public AdminFacade getAdminFacade()
	{
		return this.adminFacade;
	}
	
	public ManagerFacade getManagerFacade()
	{
		return this.managerFacade;
	}

	public BasketFacade getBasketFacade()
	{
		return basketFacade;
	}

	// Disconnected methods
	/**
	 * Method use to register the user
	 * @param an hashmap with everything about a user
	 * @throws RegisterFailedException
	 * @throws FalseFieldsException
	 */
	public void register(HashMap<String,Object> dataUser) throws RegisterFailedException, FalseFieldsException
	{
		User userInRegistration = kit.makeUser(dataUser);
		userInRegistration.save();
	}
	
	/**
	 * Method use to log on the application the user
	 * @param an hashmap with a pseudo and a password
	 * @throws LoginFailedException
	 * @throws FalseFieldsException
	 */
	public void login(HashMap<String,Object> dataUser) throws LoginFailedException, FalseFieldsException
	{
		if (theUser == null)
		{
			User userInLogin = kit.makeUser(dataUser);
			userInLogin.load();
			theUser = userInLogin;
		}
	}
	
	/**
	 * Method use if the user forgot is password
	 * @param an hashmap with everything about the user
	 * @throws FalseFieldsException
	 * @throws InvalidEmailException
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void forgottenPassword(HashMap<String,Object> dataUser) throws FalseFieldsException, InvalidEmailException, AddressException, MessagingException
	{
		String password = new String();
		password = StringUtil.nextSessionId();
		dataUser.put("password", password);
		User userInForgottenPassword = kit.makeUser(dataUser);
		userInForgottenPassword.updateForgottenPassword();
		SendMail.generateAndSendEmail(userInForgottenPassword.getEmail(), password);
	}
	
	// Profile methods
	/**
	 * Method use to get the user products
	 * @return the user products
	 */
	public ArrayList<Product> getUserProducts()
	{
		theUser.loadProducts();
		return theUser.getMember().getProducts();
	}
	
	/**
	 * Method use to update the profile of the user connected
	 * @param an hashmap with everything about a user
	 * @throws FalseFieldsException
	 * @throws SQLException
	 */
	public void updateTheUserProfile(HashMap<String,Object> dataUser) throws FalseFieldsException, SQLException
	{
		theUser.setAll(dataUser);
		theUser.updateProfile();
	}
	
	/**
	 * Method use to update the password of the user connected
	 * @param an hashmap with everything about a user
	 * @throws FalseFieldsException
	 * @throws SQLException
	 */
	public void updateTheUserPassword(HashMap<String,Object> dataUser) throws FalseFieldsException, SQLException
	{
		theUser.setAll(dataUser);
		theUser.updatePassword();
	}
	
	/**
	 * Method use to delete the user connected and log off this one
	 * @throws SQLException
	 */
	public void deleteTheUser() throws SQLException
	{
		theUser.delete();
	}

	public Basket getBasket()
	{
		theUser.loadBasket();
		theUser.getBasket().load();
		return theUser.getBasket();
	}
}