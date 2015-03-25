package com.novar.ui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.novar.business.Category;
import com.novar.business.MainCategory;
import com.novar.business.Product;
import com.novar.business.FacadeMain;
import com.novar.business.SubCategory;
import com.novar.exception.FalseFieldsException;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class ProductDetailsPanel extends JPanel {

	private FacadeMain facade;
	private ConnectedWindow mainFrame;
	private Product product = null;
	
	private JTextField textFieldId, textFieldPrice, textFieldQuantity, textFieldDiscountPrice;
	private JLabel lblErrorPrice, lblErrorDiscountPrice, lblErrorDescription, lblErrorQuantity, lblErrorParse;
	private JTextArea textAreaDescription ;
	private Border defaultBorder;
	private JComboBox<Category> comboBoxCategory;
	
	/**
	 * Create the panel.
	 */
	public ProductDetailsPanel(ConnectedWindow frame, FacadeMain facade, Product product) {
		this.facade = facade;
		this.mainFrame = frame;
		this.product = product;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblId = new JLabel("Id :");
		springLayout.putConstraint(SpringLayout.NORTH, lblId, 200, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblId, 352, SpringLayout.WEST, this);
		lblId.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblId);
		
		JLabel lblDescription = new JLabel("Description :");
		springLayout.putConstraint(SpringLayout.NORTH, lblDescription, 35, SpringLayout.SOUTH, lblId);
		springLayout.putConstraint(SpringLayout.EAST, lblDescription, 0, SpringLayout.EAST, lblId);
		lblDescription.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblDescription);
		
		JLabel lblPrice = new JLabel("Price :");
		springLayout.putConstraint(SpringLayout.NORTH, lblPrice, 125, SpringLayout.SOUTH, lblDescription);
		springLayout.putConstraint(SpringLayout.EAST, lblPrice, 0, SpringLayout.EAST, lblId);
		lblPrice.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblPrice);
		
		JLabel lblDiscountPrice = new JLabel("Discount Price (only for members) :");
		springLayout.putConstraint(SpringLayout.NORTH, lblDiscountPrice, 35, SpringLayout.SOUTH, lblPrice);
		springLayout.putConstraint(SpringLayout.EAST, lblDiscountPrice, 0, SpringLayout.EAST, lblId);
		lblDiscountPrice.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblDiscountPrice);
		
		JLabel lblQuantity = new JLabel("Quantity :");
		springLayout.putConstraint(SpringLayout.NORTH, lblQuantity, 35, SpringLayout.SOUTH, lblDiscountPrice);
		springLayout.putConstraint(SpringLayout.EAST, lblQuantity, 0, SpringLayout.EAST, lblId);
		lblQuantity.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblQuantity);
		
		JLabel lblCategory = new JLabel("Category :");
		springLayout.putConstraint(SpringLayout.NORTH, lblCategory, 35, SpringLayout.SOUTH, lblQuantity);
		springLayout.putConstraint(SpringLayout.EAST, lblCategory, 0, SpringLayout.EAST, lblId);
		lblCategory.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblCategory);
		

		textFieldId = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldId, -3, SpringLayout.NORTH, lblId);
		springLayout.putConstraint(SpringLayout.WEST, textFieldId, 6, SpringLayout.EAST, lblId);
		springLayout.putConstraint(SpringLayout.EAST, textFieldId, 265, SpringLayout.EAST, lblId);
		add(textFieldId);
		textFieldId.setColumns(10);
		textFieldId.setEditable(false);
		
		textAreaDescription = new JTextArea();
		springLayout.putConstraint(SpringLayout.NORTH, textAreaDescription, -5, SpringLayout.NORTH, lblDescription);
		springLayout.putConstraint(SpringLayout.WEST, textAreaDescription, 6, SpringLayout.EAST, lblDescription);
		springLayout.putConstraint(SpringLayout.EAST, textAreaDescription, -346, SpringLayout.EAST, this);
		textAreaDescription.setRows(6);
		add(textAreaDescription);
		textAreaDescription.setLineWrap(true);
		
		
		textFieldPrice = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldPrice, -3, SpringLayout.NORTH, lblPrice);
		springLayout.putConstraint(SpringLayout.WEST, textFieldPrice, 6, SpringLayout.EAST, lblPrice);
		springLayout.putConstraint(SpringLayout.EAST, textFieldPrice, -346, SpringLayout.EAST, this);
		add(textFieldPrice);
		textFieldPrice.setColumns(10);
		
		textFieldQuantity = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldQuantity, -3, SpringLayout.NORTH, lblQuantity);
		springLayout.putConstraint(SpringLayout.WEST, textFieldQuantity, 6, SpringLayout.EAST, lblQuantity);
		springLayout.putConstraint(SpringLayout.EAST, textFieldQuantity, -346, SpringLayout.EAST, this);
		add(textFieldQuantity);
		textFieldQuantity.setColumns(10);
	
		textFieldDiscountPrice = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldDiscountPrice, -3, SpringLayout.NORTH, lblDiscountPrice);
		springLayout.putConstraint(SpringLayout.WEST, textFieldDiscountPrice, 6, SpringLayout.EAST, lblDiscountPrice);
		springLayout.putConstraint(SpringLayout.EAST, textFieldDiscountPrice, -346, SpringLayout.EAST, this);
		add(textFieldDiscountPrice);
		textFieldDiscountPrice.setColumns(10);
		
		comboBoxCategory = new JComboBox<Category>();
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxCategory, -3, SpringLayout.NORTH, lblCategory);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxCategory, 6, SpringLayout.EAST, lblCategory);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxCategory, 0, SpringLayout.EAST, textFieldId);
		
		ArrayList<MainCategory> listMainCategory = facade.getCategoryFacade().getAllMainCategories();
		Category tempCat = null;
		for(int i=0;i<listMainCategory.size();i++)
		{
			comboBoxCategory.addItem(listMainCategory.get(i));
			ArrayList<SubCategory> listSubCategory = facade.getCategoryFacade().getSubCategories(listMainCategory.get(i));
			for(int j=0;j<listSubCategory.size();j++)
			{
				comboBoxCategory.addItem(listSubCategory.get(j));
				
				if(product != null && listSubCategory.get(j).getCatID() == product.getCategory().getCatID())
					tempCat = listSubCategory.get(j);
			}
			
			if(product != null && listMainCategory.get(i).getCatID() == product.getCategory().getCatID())
				tempCat = listMainCategory.get(i);
		}
		add(comboBoxCategory);
		
		lblErrorPrice = new JLabel("The price field must contains floats between 0.0 and 1 000 000.0");
		springLayout.putConstraint(SpringLayout.NORTH, lblErrorPrice, 1, SpringLayout.NORTH, lblPrice);
		springLayout.putConstraint(SpringLayout.WEST, lblErrorPrice, 6, SpringLayout.EAST, textFieldPrice);
		lblErrorPrice.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorPrice.setVisible(false);
		add(lblErrorPrice);
		
		lblErrorDiscountPrice = new JLabel("The discount price field must contains floats between 0.0 and 1 000 000.0");
		springLayout.putConstraint(SpringLayout.NORTH, lblErrorDiscountPrice, 1, SpringLayout.NORTH, lblDiscountPrice);
		springLayout.putConstraint(SpringLayout.WEST, lblErrorDiscountPrice, 6, SpringLayout.EAST, textFieldDiscountPrice);
		lblErrorDiscountPrice.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorDiscountPrice.setVisible(false);
		add(lblErrorDiscountPrice);
		
		lblErrorDescription = new JLabel("The description field must contains 1 to 256 letters or numbers.");
		springLayout.putConstraint(SpringLayout.WEST, lblErrorDescription, 6, SpringLayout.EAST, textAreaDescription);
		springLayout.putConstraint(SpringLayout.SOUTH, lblErrorDescription, -84, SpringLayout.NORTH, lblErrorPrice);
		lblErrorDescription.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorDescription.setVisible(false);
		add(lblErrorDescription);
		
		lblErrorQuantity = new JLabel("The price field must contains numbers between 0 and 1 000 000.");
		springLayout.putConstraint(SpringLayout.NORTH, lblErrorQuantity, 1, SpringLayout.NORTH, lblQuantity);
		springLayout.putConstraint(SpringLayout.WEST, lblErrorQuantity, 6, SpringLayout.EAST, textFieldQuantity);
		lblErrorQuantity.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorQuantity.setVisible(false);
		add(lblErrorQuantity);
		
		lblErrorParse = new JLabel("Warning, the price, the discount price or the quantity are not valid.");
		springLayout.putConstraint(SpringLayout.NORTH, lblErrorParse, 34, SpringLayout.SOUTH, comboBoxCategory);
		springLayout.putConstraint(SpringLayout.WEST, lblErrorParse, 0, SpringLayout.WEST, lblId);
		springLayout.putConstraint(SpringLayout.EAST, lblErrorParse, -211, SpringLayout.EAST, this);
		lblErrorParse.setFont(new Font("Calibri", Font.BOLD, 12));
		add(lblErrorParse);
		lblErrorParse.setVisible(false);
				
		defaultBorder = textFieldQuantity.getBorder();
		
		
		JButton btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, 0, SpringLayout.EAST, textFieldId);
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		add(btnCancel);
		
		
		if(product != null)
		{

			JLabel lblProductDetails = new JLabel("Product Details");
			springLayout.putConstraint(SpringLayout.SOUTH, lblProductDetails, -76, SpringLayout.NORTH, textFieldId);
			springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblProductDetails, 0, SpringLayout.HORIZONTAL_CENTER, this);
			lblProductDetails.setFont(new Font("Calibri", Font.BOLD, 24));
			add(lblProductDetails);
			
			textFieldId.setText(product.getProductID().toString());
			textFieldPrice.setText(product.getPrice().toString());
			textFieldDiscountPrice.setText(product.getDiscountPrice().toString());
			textAreaDescription.setText(product.getDescription());
			textFieldQuantity.setText(product.getQuantity().toString());
			comboBoxCategory.setSelectedItem(tempCat);
			
			JButton btnUpdate = new JButton("Update");
			springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 0, SpringLayout.NORTH, btnUpdate);
			springLayout.putConstraint(SpringLayout.WEST, btnUpdate, 0, SpringLayout.WEST, textFieldId);
			springLayout.putConstraint(SpringLayout.SOUTH, btnUpdate, -76, SpringLayout.SOUTH, this);
			btnUpdate.setFont(new Font("Calibri", Font.PLAIN, 14));
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					update();
				}
			});
			add(btnUpdate);
		}
		
		else
		{
			JLabel lblProductDetails = new JLabel("Add a Product");
			springLayout.putConstraint(SpringLayout.SOUTH, lblProductDetails, -76, SpringLayout.NORTH, textFieldId);
			springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblProductDetails, -15, SpringLayout.HORIZONTAL_CENTER, this);
			lblProductDetails.setFont(new Font("Calibri", Font.BOLD, 24));
			add(lblProductDetails);
			
			JButton btnCreate = new JButton("Create");
			springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 0, SpringLayout.NORTH, btnCreate);
			springLayout.putConstraint(SpringLayout.WEST, btnCreate, 0, SpringLayout.WEST, textFieldId);
			springLayout.putConstraint(SpringLayout.SOUTH, btnCreate, -76, SpringLayout.SOUTH, this);
			btnCreate.setFont(new Font("Calibri", Font.PLAIN, 14));
			btnCreate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					create();
				}
			});
			add(btnCreate);
		}
		
		
	}
	
	private void update()
	{
		hideErrors();	
		HashMap<String,Object> mapProduct = new HashMap<String,Object>();
		try
		{
			mapProduct.put("productID", Integer.parseInt(textFieldId.getText()));
			mapProduct.put("description", textAreaDescription.getText());
			mapProduct.put("price", Double.parseDouble(textFieldPrice.getText()));
			mapProduct.put("discountPrice", Double.parseDouble(textFieldDiscountPrice.getText()));
			mapProduct.put("quantity", Integer.parseInt(textFieldQuantity.getText()));
			mapProduct.put("category", comboBoxCategory.getSelectedItem());
			try 
			{
				facade.getProductFacade().updateProduct(product, mapProduct);
				this.mainFrame.changePanel(new ProductsPanel(this.mainFrame, this.facade));
			} 
			catch (FalseFieldsException e1) 
			{
				showErrors(e1);
			}
		}
		catch (NumberFormatException e)
		{
			lblErrorParse.setVisible(true);
			textFieldPrice.setText(product.getPrice().toString());
			textFieldDiscountPrice.setText(product.getDiscountPrice().toString());
			textFieldQuantity.setText(product.getQuantity().toString());
		}
	}
	
	private void create()
	{
		hideErrors();	
		HashMap<String,Object> mapProduct = new HashMap<String,Object>();
		try
		{
			mapProduct.put("description", textAreaDescription.getText());
			mapProduct.put("price", Double.parseDouble(textFieldPrice.getText()));
			mapProduct.put("discountPrice", Double.parseDouble(textFieldDiscountPrice.getText()));
			mapProduct.put("quantity", Integer.parseInt(textFieldQuantity.getText()));
			mapProduct.put("category", comboBoxCategory.getSelectedItem());
			mapProduct.put("user", facade.getUser());
			try 
			{
				facade.getProductFacade().createProduct(mapProduct);
				this.mainFrame.changePanel(new ProductsPanel(this.mainFrame, this.facade));
			} 
			catch (FalseFieldsException e1) 
			{
				showErrors(e1);
			}
		}
		catch (NumberFormatException e)
		{
			lblErrorParse.setVisible(true);
			textFieldPrice.setText("");
			textFieldDiscountPrice.setText("");
			textFieldQuantity.setText("");
		}
			
	}
	
	private void showErrors(FalseFieldsException e)
	{
		ArrayList<String> errors = e.getFalseFields();
		for(int i = 0 ; i <errors.size() ; i++)
		{
			switch(errors.get(i))
			{
				case "description" : textAreaDescription.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
									 textAreaDescription.setText("");
									 lblErrorDescription.setVisible(true);
				break;
				case "price" : textFieldPrice.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
							  textFieldPrice.setText("");
							  lblErrorPrice.setVisible(true);
				break;
				case "discountPrice" : textFieldDiscountPrice.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
									   textFieldDiscountPrice.setText("");
									   lblErrorDiscountPrice.setVisible(true);
				break;
				case "quantity" : textFieldQuantity.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
								  textFieldQuantity.setText("");
								  lblErrorQuantity.setVisible(true);
				break;

			}
		}
	}
	
	private void hideErrors()
	{
		textFieldPrice.setBorder(defaultBorder);
		textFieldDiscountPrice.setBorder(defaultBorder);
		textAreaDescription.setBorder(defaultBorder);
		textFieldQuantity.setBorder(defaultBorder);
		
		lblErrorPrice.setVisible(false);
		lblErrorDiscountPrice.setVisible(false);
		lblErrorDescription.setVisible(false);
		lblErrorQuantity.setVisible(false);
		lblErrorParse.setVisible(false);
	}
	
	private void cancel()
	{
		this.mainFrame.changePanel(new ProductsPanel(this.mainFrame, this.facade));
	}
}
