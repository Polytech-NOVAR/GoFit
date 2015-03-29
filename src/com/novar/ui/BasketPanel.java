package com.novar.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.novar.business.Basket;
import com.novar.business.BasketLine;
import com.novar.business.Category;
import com.novar.business.MainFacade;
import com.novar.business.Product;
import com.novar.exception.SyntaxException;

public class BasketPanel extends JPanel {
	
	private ConnectedWindow mainFrame;
	private MainFacade facade;
	private Basket basket;
	private JLabel lblShop;
	private JLabel lbProductID;
	private JLabel lblDescritpion;
	private JLabel lblPrice;
	private JLabel lblCategory;
	private JLabel lblProducti;
	private JLabel lblDesci;
	private JLabel lblCategoryi;
	private JLabel lblStocki;
	private JLabel lblPricei;
	private SpringLayout springLayout;
	private JButton btnDelete;
	private JLabel lblQuantity;
	private JLabel lblPrixtotal;
	private JButton buttonPlus;
	private JButton buttonMoins;
	private double total = 0;
	private JLabel lblQuantitInsuffisante;
	private JLabel lblStock;
	/**
	 * Create the 
	 */
	public BasketPanel(ConnectedWindow frame,MainFacade facade) 
	{
		this.facade = facade;
		this.mainFrame = frame;
		springLayout = new SpringLayout();
		setLayout(springLayout);
		
		lblShop = new JLabel("Basket");
		lblShop.setFont(new Font("Calibri", Font.PLAIN, 24));
		springLayout.putConstraint(SpringLayout.NORTH, lblShop, 30, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblShop, -0, SpringLayout.HORIZONTAL_CENTER, this);
		add(lblShop);
		
		lbProductID = new JLabel("ID");
		springLayout.putConstraint(SpringLayout.NORTH, lbProductID, 87, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lbProductID, 54, SpringLayout.WEST, this);
		lbProductID.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lbProductID);
		
		lblDescritpion = new JLabel("Descritpion");
		springLayout.putConstraint(SpringLayout.NORTH, lblDescritpion, 0, SpringLayout.NORTH, lbProductID);
		springLayout.putConstraint(SpringLayout.WEST, lblDescritpion, 57, SpringLayout.EAST, lbProductID);
		lblDescritpion.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblDescritpion);
		
		lblPrice = new JLabel("Price");
		springLayout.putConstraint(SpringLayout.NORTH, lblPrice, 0, SpringLayout.NORTH, lbProductID);
		lblPrice.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblPrice);
		
		lblCategory = new JLabel("Category");
		springLayout.putConstraint(SpringLayout.WEST, lblPrice, 87, SpringLayout.EAST, lblCategory);
		springLayout.putConstraint(SpringLayout.NORTH, lblCategory, 0, SpringLayout.NORTH, lbProductID);
		springLayout.putConstraint(SpringLayout.WEST, lblCategory, 159, SpringLayout.EAST, lblDescritpion);
		lblCategory.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblCategory);
		
		lblQuantity = new JLabel("Quantity");
		springLayout.putConstraint(SpringLayout.NORTH, lblQuantity, 87, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblQuantity, 180, SpringLayout.EAST, lblPrice);
		lblQuantity.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblQuantity);
		
		lblStock = new JLabel("Stock");
		springLayout.putConstraint(SpringLayout.WEST, lblStock, 72, SpringLayout.EAST, lblPrice);
		springLayout.putConstraint(SpringLayout.SOUTH, lblStock, 0, SpringLayout.SOUTH, lbProductID);
		lblStock.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblStock);
		

		
		basket = facade.getBasket();
		
		
		for(int i=0; i<basket.getLines().size(); i++)
		{

			BasketLine linei = basket.getLines().get(i);
			
			lblProducti = new JLabel(linei.getProduct().getProductID().toString());
			springLayout.putConstraint(SpringLayout.NORTH, lblProducti, 45*(i+1), SpringLayout.SOUTH, lbProductID);
			springLayout.putConstraint(SpringLayout.WEST, lblProducti, 0, SpringLayout.WEST, lbProductID);
			lblProducti.setFont(new Font("Calibri", Font.PLAIN,12));
			add(lblProducti);
			
			if(linei.getProduct().getDescription().length() > 25)
				lblDesci= new JLabel(linei.getProduct().getDescription().substring(0, 25)+"...");
			else
				lblDesci= new JLabel(linei.getProduct().getDescription());
			
			springLayout.putConstraint(SpringLayout.NORTH, lblDesci, 45*(i+1), SpringLayout.SOUTH, lblDescritpion);
			springLayout.putConstraint(SpringLayout.WEST, lblDesci, 0, SpringLayout.WEST, lblDescritpion);
			lblDesci.setFont(new Font("Calibri", Font.PLAIN,12));
			add(lblDesci);
			
			lblCategoryi = new JLabel(linei.getProduct().getCategory().getDescription());
			springLayout.putConstraint(SpringLayout.NORTH, lblCategoryi, 45*(i+1), SpringLayout.SOUTH, lblCategory);
			springLayout.putConstraint(SpringLayout.WEST, lblCategoryi, 0, SpringLayout.WEST, lblCategory);
			lblCategoryi.setFont(new Font("Calibri", Font.PLAIN,12));
			add(lblCategoryi);
			
			if(facade.getUser().isMember())
			{
				lblPricei = new JLabel(linei.getProduct().getDiscountPrice().toString() +" euros");
				total = total + linei.getProduct().getDiscountPrice() * linei.getQuantity();
			}
			else
			{
				lblPricei = new JLabel(linei.getProduct().getPrice().toString() +" euros");
				total = total + linei.getProduct().getPrice() * linei.getQuantity();
			}
			springLayout.putConstraint(SpringLayout.NORTH, lblPricei, 45*(i+1), SpringLayout.SOUTH, lblPrice);
			springLayout.putConstraint(SpringLayout.WEST, lblPricei, 0, SpringLayout.WEST, lblPrice);
			lblPricei.setFont(new Font("Calibri", Font.PLAIN,12));
			add(lblPricei);
			
			lblStocki = new JLabel(linei.getProduct().getQuantity().toString());
			springLayout.putConstraint(SpringLayout.NORTH, lblStocki, 45*(i+1), SpringLayout.SOUTH, lblStock);
			springLayout.putConstraint(SpringLayout.WEST, lblStocki, 0, SpringLayout.WEST, lblStock);
			lblStocki.setFont(new Font("Calibri", Font.PLAIN,12));
			add(lblStocki);
			
			btnDelete = new JButton("delete");
			springLayout.putConstraint(SpringLayout.NORTH, btnDelete, -8, SpringLayout.NORTH, lblPricei);
			springLayout.putConstraint(SpringLayout.EAST, btnDelete, -50, SpringLayout.EAST, this);
			btnDelete.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					delete(linei);
				}
			});
			add(btnDelete);
			
			JTextField quantityTextField = new JTextField();
			springLayout.putConstraint(SpringLayout.NORTH, quantityTextField, 45*(i+1), SpringLayout.SOUTH, lblQuantity);
			springLayout.putConstraint(SpringLayout.WEST, quantityTextField, 0, SpringLayout.WEST, lblQuantity);
			springLayout.putConstraint(SpringLayout.EAST, quantityTextField, 0, SpringLayout.EAST, lblQuantity);
			quantityTextField.setColumns(10);
			quantityTextField.setText(linei.getQuantity().toString());
			quantityTextField.setEditable(false);
			add(quantityTextField);

			
			buttonPlus = new JButton("+");
			springLayout.putConstraint(SpringLayout.NORTH, buttonPlus, 4, SpringLayout.NORTH, btnDelete);
			springLayout.putConstraint(SpringLayout.EAST, buttonPlus, -55, SpringLayout.WEST, btnDelete);
			buttonPlus.setFont(new Font("Tahoma", Font.PLAIN, 7));
			buttonPlus.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					more(linei, quantityTextField);
				}
			});
			add(buttonPlus);
			
			
			buttonMoins = new JButton("-");
			springLayout.putConstraint(SpringLayout.NORTH, buttonMoins, 4, SpringLayout.NORTH, btnDelete);
			springLayout.putConstraint(SpringLayout.EAST, buttonMoins, -15, SpringLayout.WEST, btnDelete);
			buttonMoins.setFont(new Font("Tahoma", Font.PLAIN, 7));
			buttonMoins.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					less(linei, quantityTextField);
				}
			});
			add(buttonMoins);
			
		}
		
		if(!basket.getLines().isEmpty())
		{

			lblPrixtotal = new JLabel(total + " Euros");
			springLayout.putConstraint(SpringLayout.NORTH, lblPrixtotal, 34, SpringLayout.SOUTH, lblPricei);
			springLayout.putConstraint(SpringLayout.WEST, lblPrixtotal, 0, SpringLayout.WEST, lblPricei);
			lblPrixtotal.setFont(new Font("Tahoma", Font.BOLD, 16));
			add(lblPrixtotal);
			
			JButton btnOrder = new JButton("Order");
			springLayout.putConstraint(SpringLayout.NORTH, btnOrder, 20, SpringLayout.SOUTH, lblPrixtotal);
			springLayout.putConstraint(SpringLayout.WEST, btnOrder, 0, SpringLayout.WEST, lblPricei);
			btnOrder.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					order(basket);
				}
			});
			add(btnOrder);
			
			lblQuantitInsuffisante = new JLabel("No enough quantity");
			lblQuantitInsuffisante.setFont(new Font("Tahoma", Font.BOLD, 15));
			springLayout.putConstraint(SpringLayout.NORTH, lblQuantitInsuffisante, 20, SpringLayout.SOUTH, btnOrder);
			springLayout.putConstraint(SpringLayout.WEST, lblQuantitInsuffisante, 0, SpringLayout.WEST, lblPricei);
			add(lblQuantitInsuffisante);
		
			lblQuantitInsuffisante.setVisible(false);
		}
		
		setPreferredSize(new Dimension(980, basket.getLines().size() * 45 + 250));
		
	}
	
	private void order(Basket basket) 
	{
		lblQuantitInsuffisante.setVisible(false);
		try 
		{
			this.facade.getBasketFacade().order(basket);
			for(int i = 0; i<basket.getLines().size();i++)
			{
				ArrayList<String> listReceiver = new ArrayList<String>();
				listReceiver.add(basket.getLines().get(i).getProduct().getUser().getPseudo());
				facade.getNotificationFacade().notify(facade.getUser(), basket.getLines().get(i).getQuantity() + " copies of your product n° " + basket.getLines().get(i).getProduct().getProductID() + " has been sold.", listReceiver);
			}
			this.mainFrame.changePanel(new BasketPanel(this.mainFrame, this.facade));
		} 
		catch (SyntaxException e) 
		{
			lblQuantitInsuffisante.setVisible(true);
		}
		
	}

	private void delete(BasketLine linei) 
	{
		this.facade.getBasketFacade().deleteLine(linei);
		this.mainFrame.changePanel(new BasketPanel(this.mainFrame, this.facade));
	}

	private void more(BasketLine linei, JTextField quantityTextField) 
	{
		facade.getBasketFacade().more(linei);
		quantityTextField.setText(linei.getQuantity().toString());

		if(facade.getUser().isMember())
			total = total + linei.getProduct().getDiscountPrice();
		else
			total = total + linei.getProduct().getPrice();
		lblPrixtotal.setText(total + " Euros");
	}

	private void less(BasketLine linei, JTextField quantityTextField)
	{
		facade.getBasketFacade().less(linei);
		quantityTextField.setText(linei.getQuantity().toString());
		if(facade.getUser().isMember())
			total = total - linei.getProduct().getDiscountPrice();
		else
			total = total - linei.getProduct().getPrice();
		lblPrixtotal.setText(total + " Euros");
	}
}
