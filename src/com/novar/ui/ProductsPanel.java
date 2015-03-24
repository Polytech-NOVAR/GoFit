package com.novar.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.SpringLayout;

import com.novar.business.FacadeMain;
import com.novar.business.FacadeProduct;
import com.novar.business.Product;

import java.awt.Rectangle;
import java.util.ArrayList;

public class ProductsPanel extends JPanel 
{

	private FacadeMain facade;
	/**
	 * Create the panel.
	 */
	public ProductsPanel(FacadeMain f) 
	{
		setBounds(new Rectangle(0, 0, 980, 800));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		facade = f;
		
		ArrayList<Product> products = f.getUserProducts();
		
		JLabel lblProducts = new JLabel("Products");
		lblProducts.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		springLayout.putConstraint(SpringLayout.NORTH, lblProducts, 20, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblProducts, 0, SpringLayout.HORIZONTAL_CENTER, this);
		add(lblProducts);
		
		JLabel lblGoods = new JLabel("Goods");
		lblGoods.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		springLayout.putConstraint(SpringLayout.NORTH, lblGoods, 78, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblGoods, -300, SpringLayout.HORIZONTAL_CENTER, this);
		add(lblGoods);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		springLayout.putConstraint(SpringLayout.NORTH, lblPrice, 0, SpringLayout.NORTH, lblGoods);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblPrice, 300, SpringLayout.HORIZONTAL_CENTER, this);
		add(lblPrice);
		
		
		for(int i = 0; i < products.size(); i++)
		{
			JLabel lblThegood = new JLabel(products.get(i).getDescription());
			springLayout.putConstraint(SpringLayout.NORTH, lblThegood, 19*i, SpringLayout.SOUTH, lblGoods);
			springLayout.putConstraint(SpringLayout.WEST, lblThegood, 0, SpringLayout.WEST, lblGoods);
			add(lblThegood);
			
			JLabel lblTheprice = new JLabel(products.get(i).getPrice().toString() + " €");
			springLayout.putConstraint(SpringLayout.NORTH, lblTheprice, 0, SpringLayout.NORTH, lblThegood);
			springLayout.putConstraint(SpringLayout.WEST, lblTheprice, 0, SpringLayout.WEST, lblPrice);
			add(lblTheprice);
		}


	}

}