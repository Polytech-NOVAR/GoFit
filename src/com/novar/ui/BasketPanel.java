package com.novar.ui;

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
	private JLabel lblPricei;
	private SpringLayout springLayout;
	private JButton btnDelete;
	private JLabel lblQuantity;
	private JTextField quantityTextField;
	private JLabel lblPrixtotal;
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
		springLayout.putConstraint(SpringLayout.WEST, lbProductID, 92, SpringLayout.WEST, this);
		lbProductID.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lbProductID);
		
		lblDescritpion = new JLabel("Descritpion");
		springLayout.putConstraint(SpringLayout.NORTH, lblDescritpion, 0, SpringLayout.NORTH, lbProductID);
		springLayout.putConstraint(SpringLayout.WEST, lblDescritpion, 92, SpringLayout.EAST, lbProductID);
		lblDescritpion.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblDescritpion);
		
		lblPrice = new JLabel("Price");
		springLayout.putConstraint(SpringLayout.NORTH, lblPrice, 0, SpringLayout.NORTH, lbProductID);
		lblPrice.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblPrice);
		
		lblCategory = new JLabel("Category");
		springLayout.putConstraint(SpringLayout.WEST, lblPrice, 144, SpringLayout.EAST, lblCategory);
		springLayout.putConstraint(SpringLayout.NORTH, lblCategory, 0, SpringLayout.NORTH, lbProductID);
		springLayout.putConstraint(SpringLayout.WEST, lblCategory, 139, SpringLayout.EAST, lblDescritpion);
		lblCategory.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblCategory);
		
		lblQuantity = new JLabel("Quantity");
		springLayout.putConstraint(SpringLayout.WEST, lblQuantity, 114, SpringLayout.EAST, lblPrice);
		springLayout.putConstraint(SpringLayout.SOUTH, lblQuantity, 0, SpringLayout.SOUTH, lbProductID);
		lblQuantity.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblQuantity);
		
		
		basketLines = facade.getBasketLines();
		
		for(int i=0; i<basketLines.size(); i++)
		{
			BasketLine linei = basketLines.get(i);
			
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
				lblPricei = new JLabel(linei.getProduct().getDiscountPrice().toString() +" euros");
			else
				lblPricei = new JLabel(linei.getProduct().getPrice().toString() +" euros");
			springLayout.putConstraint(SpringLayout.NORTH, lblPricei, 45*(i+1), SpringLayout.SOUTH, lblPrice);
			springLayout.putConstraint(SpringLayout.WEST, lblPricei, 0, SpringLayout.WEST, lblPrice);
			lblPricei.setFont(new Font("Calibri", Font.PLAIN,12));
			add(lblPricei);
			
			btnDelete = new JButton("delete");
			springLayout.putConstraint(SpringLayout.NORTH, btnDelete, -5, SpringLayout.NORTH, lblPricei);
			springLayout.putConstraint(SpringLayout.EAST, btnDelete, -10, SpringLayout.EAST, this);
			btnDelete.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					delete(linei);
				}
			});
			add(btnDelete);
			
			quantityTextField = new JTextField();
			springLayout.putConstraint(SpringLayout.NORTH, quantityTextField, 24, SpringLayout.SOUTH, lblQuantity);
			springLayout.putConstraint(SpringLayout.WEST, quantityTextField, 0, SpringLayout.WEST, lblQuantity);
			springLayout.putConstraint(SpringLayout.EAST, quantityTextField, 0, SpringLayout.EAST, lblQuantity);
			add(quantityTextField);
			quantityTextField.setColumns(10);
			quantityTextField.setText(linei.getQuantity().toString());
			
		}
		
		if (!basketLines.isEmpty())
		{
			lblPrixtotal = new JLabel("prixTotal");
			springLayout.putConstraint(SpringLayout.WEST, lblPrixtotal, 0, SpringLayout.WEST, lblPricei);
			lblPrixtotal.setFont(new Font("Tahoma", Font.BOLD, 16));
			add(lblPrixtotal);
			
			JButton btnOrder = new JButton("Order");
			springLayout.putConstraint(SpringLayout.SOUTH, lblPrixtotal, 0, SpringLayout.SOUTH, btnOrder);
			springLayout.putConstraint(SpringLayout.NORTH, btnOrder, 47, SpringLayout.SOUTH, quantityTextField);
			springLayout.putConstraint(SpringLayout.EAST, btnOrder, 0, SpringLayout.EAST, quantityTextField);
			add(btnOrder);
		}
	}
}
