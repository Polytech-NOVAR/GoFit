package com.novar.ui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.SpringLayout;
import javax.swing.border.Border;

import com.novar.business.FacadeMain;
import com.novar.business.Product;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProductsPanel extends JPanel 
{
	private ConnectedWindow mainFrame;
	private FacadeMain facade;
	private ArrayList<Product> products;

	/**
	 * Create the panel.
	 */
	public ProductsPanel(ConnectedWindow frame,FacadeMain facade) 
	{
		this.facade = facade;
		this.mainFrame = frame;
		products = facade.getUserProducts();
		reload();
	}
	
	public void reload()
	{
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblRoomNumber = new JLabel("Id");
		springLayout.putConstraint(SpringLayout.NORTH, lblRoomNumber, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblRoomNumber, (mainFrame.getWidth()/9)/2, SpringLayout.WEST, this);
		lblRoomNumber.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblRoomNumber);
		
		JLabel lblArea = new JLabel("description");
		springLayout.putConstraint(SpringLayout.NORTH, lblArea, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblArea, mainFrame.getWidth()/9, SpringLayout.WEST, lblRoomNumber);
		lblArea.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblArea);
		
		JLabel lblStreet = new JLabel("price");
		springLayout.putConstraint(SpringLayout.NORTH, lblStreet, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblStreet, mainFrame.getWidth()/7, SpringLayout.WEST, lblArea);
		lblStreet.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblStreet);
		
		JLabel lblCity = new JLabel("discount price");
		springLayout.putConstraint(SpringLayout.NORTH, lblCity, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblCity, mainFrame.getWidth()/7, SpringLayout.WEST, lblStreet);
		lblCity.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblCity);
		
		JLabel lblZipcode = new JLabel("category");
		springLayout.putConstraint(SpringLayout.NORTH, lblZipcode, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblZipcode, mainFrame.getWidth()/7, SpringLayout.WEST, lblCity);
		lblZipcode.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblZipcode);
		
		JButton btnAddOne = new JButton("Add one");
		springLayout.putConstraint(SpringLayout.NORTH, btnAddOne, 30, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnAddOne, -80, SpringLayout.EAST, this);
		btnAddOne.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		btnAddOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addOne();
			}
		});
		add(btnAddOne);
		
		JLabel lblRooms = new JLabel("Products");
		springLayout.putConstraint(SpringLayout.NORTH, lblRooms, 30, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblRooms, 0, SpringLayout.HORIZONTAL_CENTER, this);
		lblRooms.setFont(new Font("Calibri", Font.BOLD, 24));
		
		add(lblRooms);
		
		for(int i=0; i<products.size(); i++)
		{
			double multiplier = 1.5 + 0.5*i;
			Product producti = products.get(i);
			
			JLabel lblproductiID = new JLabel(producti.getProductID().toString());
			springLayout.putConstraint(SpringLayout.NORTH, lblproductiID, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblproductiID, (mainFrame.getWidth()/9)/2, SpringLayout.WEST, this);
			lblproductiID.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblproductiID);
			
			JLabel lblproductiDesc;
			if(producti.getDescription().length() > 15)
				lblproductiDesc= new JLabel(producti.getDescription().substring(0, 15)+"...");
			else
				lblproductiDesc= new JLabel(producti.getDescription());
			
			springLayout.putConstraint(SpringLayout.NORTH, lblproductiDesc, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblproductiDesc, mainFrame.getWidth()/9, SpringLayout.WEST, lblproductiID);
			lblproductiDesc.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblproductiDesc);
			
			JLabel lblproductiPrice = new JLabel(producti.getPrice().toString() + " euros");
			springLayout.putConstraint(SpringLayout.NORTH, lblproductiPrice, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblproductiPrice, mainFrame.getWidth()/7, SpringLayout.WEST, lblproductiDesc);
			lblproductiPrice.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblproductiPrice);
			
			JLabel lblproductiDiscount = new JLabel(producti.getDiscountPrice().toString() + " euros");
			springLayout.putConstraint(SpringLayout.NORTH, lblproductiDiscount, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblproductiDiscount, mainFrame.getWidth()/7, SpringLayout.WEST, lblproductiPrice);;
			lblproductiDiscount.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblproductiDiscount);
			
			JLabel lblproductiCateg = new JLabel(producti.getCategory().getDescription());
			springLayout.putConstraint(SpringLayout.NORTH, lblproductiCateg, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblproductiCateg, mainFrame.getWidth()/7, SpringLayout.WEST, lblproductiDiscount);
			lblproductiCateg.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblproductiCateg);
			
			
			JButton btnSeeMore = new JButton("See more");
			springLayout.putConstraint(SpringLayout.NORTH, btnSeeMore, (int)(90*multiplier-4), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, btnSeeMore, mainFrame.getWidth()/7, SpringLayout.WEST, lblproductiCateg);
			btnSeeMore.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnSeeMore.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seeMore(producti);
				}
			});
			add(btnSeeMore);
			
			JButton btnDelete = new JButton("Delete");
			springLayout.putConstraint(SpringLayout.NORTH, btnDelete,(int)(90*multiplier-4), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, btnDelete, mainFrame.getWidth()/9, SpringLayout.WEST, btnSeeMore);
			btnDelete.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					delete(producti);
				}
			});
			add(btnDelete);
		}
	}
	
	private void seeMore(Product room){
		this.mainFrame.changePanel(new ProductDetailsPanel(this.mainFrame, this.facade, room));
	}
	
	
	private void addOne(){
		this.mainFrame.changePanel(new ProductDetailsPanel(this.mainFrame, this.facade, null));
	}
	
	private void delete(Product product)
	{
		DeleteProductDialog delete = new DeleteProductDialog(this.mainFrame, this.facade, product);
		delete.setVisible(true);
	}
}