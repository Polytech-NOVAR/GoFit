package com.novar.ui;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;

import com.novar.business.Category;
import com.novar.business.MainCategory;
import com.novar.business.MainFacade;
import com.novar.business.Product;
import com.novar.business.SubCategory;

import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;

public class ShopPanel extends JPanel {

	private ConnectedWindow mainFrame;
	private MainFacade facade;
	private ArrayList<Product> products = new ArrayList<Product>();
	private JTextField textField;
	private JLabel lblShop;
	private JComboBox<Category> comboBox;
	private JPanel panel;
	private JLabel lbProductID;
	private JLabel lblDescritpion;
	private JLabel lblPrice;
	private JLabel lblCategory;
	private JLabel lblProducti;
	private JLabel lblDesci;
	private JLabel lblCategoryi;
	private JLabel lblPricei;
	private SpringLayout springLayout;
	private JButton btnSearch;
	private JButton btnAdd;
	private JLabel lblSeller;
	private JLabel lblSelleri;
	private JLabel lblSorry;
	/**
	 * Create the panel.
	 */
	public ShopPanel(ConnectedWindow frame,MainFacade facade) 
	{
		this.facade = facade;
		this.mainFrame = frame;
		springLayout = new SpringLayout();
		setLayout(springLayout);
		
		facade.getUser().loadBasket();
		
		lblShop = new JLabel("Shop");
		lblShop.setFont(new Font("Calibri", Font.PLAIN, 24));
		springLayout.putConstraint(SpringLayout.NORTH, lblShop, 30, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblShop, -0, SpringLayout.HORIZONTAL_CENTER, this);
		add(lblShop);
		
		comboBox = new JComboBox<Category>();
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 20, SpringLayout.SOUTH, lblShop);
		springLayout.putConstraint(SpringLayout.WEST, comboBox, -188, SpringLayout.EAST, lblShop);
		springLayout.putConstraint(SpringLayout.EAST, comboBox, -40, SpringLayout.EAST, lblShop);
		
		comboBox.addItem(null);
		ArrayList<MainCategory> listMainCategory = facade.getCategoryFacade().getAllMainCategories();
		for(int i=0;i<listMainCategory.size();i++)
		{
			comboBox.addItem(listMainCategory.get(i));
			ArrayList<SubCategory> listSubCategory = facade.getCategoryFacade().getSubCategories(listMainCategory.get(i));
			for(int j=0;j<listSubCategory.size();j++)
			{
				comboBox.addItem(listSubCategory.get(j));
			}
		}
		add(comboBox);
		
		textField = new JTextField();
		textField.setText("");
		springLayout.putConstraint(SpringLayout.NORTH, textField, 0, SpringLayout.NORTH, comboBox);
		springLayout.putConstraint(SpringLayout.WEST, textField, 29, SpringLayout.EAST, comboBox);
		springLayout.putConstraint(SpringLayout.EAST, textField, 177, SpringLayout.EAST, comboBox);
		add(textField);
		textField.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				search();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnSearch, 115, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnSearch, -0, SpringLayout.HORIZONTAL_CENTER, this);
		add(btnSearch);
		
		
	}
	
	private void search()
	{
		panel = new JPanel();
		
		springLayout.putConstraint(SpringLayout.NORTH, panel, 6, SpringLayout.SOUTH, btnSearch);
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, panel, 970, SpringLayout.WEST, this);
		add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		

		
		if (comboBox.getSelectedItem() == null && textField.getText().equals(""))
			products = facade.getProductFacade().getAllProducts();
		else if (textField.getText().equals(""))
			products = facade.getProductFacade().getProducts((Category)comboBox.getSelectedItem());
		else if(comboBox.getSelectedItem() == null)
			products = facade.getProductFacade().getProducts(textField.getText());
		else 
			products = facade.getProductFacade().getProducts((Category)comboBox.getSelectedItem(), textField.getText());
		
		if(products.isEmpty())
		{
			lblSorry = new JLabel("Sorry, no results for this search");
			sl_panel.putConstraint(SpringLayout.NORTH, lblSorry, 63, SpringLayout.SOUTH, lblCategory);
			sl_panel.putConstraint(SpringLayout.EAST, lblSorry, -358, SpringLayout.EAST, panel);
			lblSorry.setFont(new Font("Tahoma", Font.BOLD, 15));
			panel.add(lblSorry);
		}
		else
		{
			lbProductID = new JLabel("ID");
			sl_panel.putConstraint(SpringLayout.NORTH, lbProductID, 27, SpringLayout.NORTH, panel);
			sl_panel.putConstraint(SpringLayout.WEST, lbProductID, 89, SpringLayout.WEST, panel);
			lbProductID.setFont(new Font("Calibri", Font.BOLD, 14));
			panel.add(lbProductID);
			
			lblDescritpion = new JLabel("Descritpion");
			sl_panel.putConstraint(SpringLayout.NORTH, lblDescritpion, 0, SpringLayout.NORTH, lbProductID);
			sl_panel.putConstraint(SpringLayout.WEST, lblDescritpion, 94, SpringLayout.EAST, lbProductID);
			lblDescritpion.setFont(new Font("Calibri", Font.BOLD, 14));
			panel.add(lblDescritpion);
			
			lblPrice = new JLabel("Price");
			sl_panel.putConstraint(SpringLayout.NORTH, lblPrice, 27, SpringLayout.NORTH, panel);
			sl_panel.putConstraint(SpringLayout.WEST, lblPrice, 706, SpringLayout.WEST, panel);
			lblPrice.setFont(new Font("Calibri", Font.BOLD, 14));
			panel.add(lblPrice);
			
			lblCategory = new JLabel("Category");
			sl_panel.putConstraint(SpringLayout.NORTH, lblCategory, 0, SpringLayout.NORTH, lbProductID);
			sl_panel.putConstraint(SpringLayout.WEST, lblCategory, 148, SpringLayout.EAST, lblDescritpion);
			lblCategory.setFont(new Font("Calibri", Font.BOLD, 14));
			panel.add(lblCategory);
			
			lblSeller = new JLabel("Seller");
			sl_panel.putConstraint(SpringLayout.SOUTH, lblSeller, 0, SpringLayout.SOUTH, lbProductID);
			sl_panel.putConstraint(SpringLayout.EAST, lblSeller, -87, SpringLayout.WEST, lblPrice);
			lblSeller.setFont(new Font("Calibri", Font.BOLD, 14));
			panel.add(lblSeller);
		}
		
		for(int i=0; i<products.size(); i++)
		{
			Product producti = products.get(i);
			
			
			lblProducti = new JLabel(producti.getProductID().toString());
			sl_panel.putConstraint(SpringLayout.NORTH, lblProducti, 45*(i+1), SpringLayout.SOUTH, lbProductID);
			sl_panel.putConstraint(SpringLayout.WEST, lblProducti, 0, SpringLayout.WEST, lbProductID);
			lblProducti.setFont(new Font("Calibri", Font.PLAIN,12));
			panel.add(lblProducti);
			
			if(producti.getDescription().length() > 25)
				lblDesci= new JLabel(producti.getDescription().substring(0, 25)+"...");
			else
				lblDesci= new JLabel(producti.getDescription());
			
			sl_panel.putConstraint(SpringLayout.NORTH, lblDesci, 45*(i+1), SpringLayout.SOUTH, lblDescritpion);
			sl_panel.putConstraint(SpringLayout.WEST, lblDesci, 0, SpringLayout.WEST, lblDescritpion);
			lblDesci.setFont(new Font("Calibri", Font.PLAIN,12));
			panel.add(lblDesci);
			
			lblCategoryi = new JLabel(producti.getCategory().getDescription());
			sl_panel.putConstraint(SpringLayout.NORTH, lblCategoryi, 45*(i+1), SpringLayout.SOUTH, lblCategory);
			sl_panel.putConstraint(SpringLayout.WEST, lblCategoryi, 0, SpringLayout.WEST, lblCategory);
			lblCategoryi.setFont(new Font("Calibri", Font.PLAIN,12));
			panel.add(lblCategoryi);
			
			if(facade.getUser().isMember())
				lblPricei = new JLabel(producti.getDiscountPrice().toString() +" euros");
			else
				lblPricei = new JLabel(producti.getPrice().toString() +" euros");
			sl_panel.putConstraint(SpringLayout.NORTH, lblPricei, 45*(i+1), SpringLayout.SOUTH, lblPrice);
			sl_panel.putConstraint(SpringLayout.WEST, lblPricei, 0, SpringLayout.WEST, lblPrice);
			lblPricei.setFont(new Font("Calibri", Font.PLAIN,12));
			panel.add(lblPricei);
			
			btnAdd = new JButton("Add to basket");
			sl_panel.putConstraint(SpringLayout.NORTH, btnAdd, -5, SpringLayout.NORTH, lblPricei);
			sl_panel.putConstraint(SpringLayout.EAST, btnAdd, -10, SpringLayout.EAST, panel);
			btnAdd.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					addToBasket(producti);
				}
			});
			panel.add(btnAdd);
			
			
			lblSelleri = new JLabel(producti.getUser().getPseudo());
			sl_panel.putConstraint(SpringLayout.NORTH, lblSelleri, 45*(i+1), SpringLayout.SOUTH, lblSeller);
			sl_panel.putConstraint(SpringLayout.WEST, lblSelleri, 0, SpringLayout.WEST, lblSeller);
			lblSelleri.setFont(new Font("Calibri", Font.PLAIN, 12));
			panel.add(lblSelleri);
			
		}
		

		panel.repaint();
		panel.revalidate();
	}

	private void addToBasket(Product producti) 
	{
		facade.getProductFacade().addToBasket(producti, facade.getUser());
	}
}
