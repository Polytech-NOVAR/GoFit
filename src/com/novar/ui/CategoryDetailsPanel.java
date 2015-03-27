package com.novar.ui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.novar.business.Category;
import com.novar.business.MainCategory;
import com.novar.business.MainFacade;
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

public class CategoryDetailsPanel extends JPanel {

	private MainFacade facade;
	private ConnectedWindow mainFrame;
	private Category category = null;
	
	private JTextField textFieldId;
	private JLabel lblErrorDescription;
	private JTextArea textAreaDescription ;
	private Border defaultBorder;
	private JComboBox<Category> comboBoxCategory;
	
	/**
	 * Create the panel.
	 */
	public CategoryDetailsPanel(ConnectedWindow frame, MainFacade facade, Category category) {

		this.facade = facade;
		this.mainFrame = frame;
		this.category = category;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblId = new JLabel("Id :");
		springLayout.putConstraint(SpringLayout.NORTH, lblId, 200, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblId, 352, SpringLayout.WEST, this);
		lblId.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblId);
		
		JLabel lblDescription = new JLabel("Description :");
		springLayout.putConstraint(SpringLayout.NORTH, lblDescription, 86, SpringLayout.SOUTH, lblId);
		springLayout.putConstraint(SpringLayout.EAST, lblDescription, 0, SpringLayout.EAST, lblId);
		lblDescription.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblDescription);
		
		
		textFieldId = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldId, -3, SpringLayout.NORTH, lblId);
		springLayout.putConstraint(SpringLayout.WEST, textFieldId, 6, SpringLayout.EAST, lblId);
		springLayout.putConstraint(SpringLayout.EAST, textFieldId, 359, SpringLayout.EAST, lblId);
		add(textFieldId);
		textFieldId.setColumns(10);
		textFieldId.setEditable(false);
		
		textAreaDescription = new JTextArea();
		springLayout.putConstraint(SpringLayout.NORTH, textAreaDescription, 29, SpringLayout.SOUTH, textFieldId);
		springLayout.putConstraint(SpringLayout.WEST, textAreaDescription, 6, SpringLayout.EAST, lblDescription);
		springLayout.putConstraint(SpringLayout.EAST, textAreaDescription, 0, SpringLayout.EAST, textFieldId);
		textAreaDescription.setRows(6);
		add(textAreaDescription);
		textAreaDescription.setLineWrap(true);
		

		
		if (!(category instanceof MainCategory))
		{
			JLabel lblCategory = new JLabel("Parent : ");
			springLayout.putConstraint(SpringLayout.EAST, lblCategory, 0, SpringLayout.EAST, lblId);
			lblCategory.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblCategory);
			
			comboBoxCategory = new JComboBox<Category>();
			springLayout.putConstraint(SpringLayout.NORTH, lblCategory, 3, SpringLayout.NORTH, comboBoxCategory);
			springLayout.putConstraint(SpringLayout.NORTH, comboBoxCategory, 21, SpringLayout.SOUTH, textAreaDescription);
			springLayout.putConstraint(SpringLayout.EAST, comboBoxCategory, 0, SpringLayout.EAST, textFieldId);
			springLayout.putConstraint(SpringLayout.WEST, comboBoxCategory, 375, SpringLayout.WEST, this);
			
			ArrayList<MainCategory> listMainCategory = facade.getCategoryFacade().getAllMainCategories();
			Category tempCat = null;
			
			if (category == null)
				comboBoxCategory.addItem(null);
			
			for(int i=0;i<listMainCategory.size();i++)
			{
				comboBoxCategory.addItem(listMainCategory.get(i));
				if(category instanceof SubCategory && listMainCategory.get(i).getCatID() == ((SubCategory)category).getParent().getCatID())
				{
					tempCat = listMainCategory.get(i);
					comboBoxCategory.setSelectedItem(tempCat);
				}
			}
			
			add(comboBoxCategory);
		}

		lblErrorDescription = new JLabel("The description field must contains 1 to 256 letters or numbers.");
		springLayout.putConstraint(SpringLayout.NORTH, lblErrorDescription, 117, SpringLayout.SOUTH, textAreaDescription);
		springLayout.putConstraint(SpringLayout.EAST, lblErrorDescription, -196, SpringLayout.EAST, this);
		lblErrorDescription.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorDescription.setVisible(false);
		add(lblErrorDescription);
		defaultBorder = textFieldId.getBorder();
		
		
		JButton btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, -196, SpringLayout.EAST, this);
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		add(btnCancel);
		
		
		if(category != null)
		{

			JLabel lblCategoryDetails = new JLabel("Category Details");
			springLayout.putConstraint(SpringLayout.SOUTH, lblCategoryDetails, -76, SpringLayout.NORTH, textFieldId);
			springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblCategoryDetails, 0, SpringLayout.HORIZONTAL_CENTER, this);
			lblCategoryDetails.setFont(new Font("Calibri", Font.BOLD, 24));
			add(lblCategoryDetails);
			
			textFieldId.setText(category.getCatID().toString());
			textAreaDescription.setText(category.getDescription());

			
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
			
			JLabel lblCategoryDetails = new JLabel("Add a Category");
			springLayout.putConstraint(SpringLayout.SOUTH, lblCategoryDetails, -76, SpringLayout.NORTH, textFieldId);
			springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblCategoryDetails, -15, SpringLayout.HORIZONTAL_CENTER, this);
			lblCategoryDetails.setFont(new Font("Calibri", Font.BOLD, 24));
			add(lblCategoryDetails);
			
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
		HashMap<String,Object> mapCategory = new HashMap<String,Object>();

		mapCategory.put("catID", Integer.parseInt(textFieldId.getText()));
		mapCategory.put("description", textAreaDescription.getText());
		if(comboBoxCategory != null)
			mapCategory.put("parent", comboBoxCategory.getSelectedItem());
		try 
		{
			facade.getCategoryFacade().updateCategory(category, mapCategory);
			this.mainFrame.changePanel(new CategoriesPanel(this.mainFrame, this.facade));
		} 
		catch (FalseFieldsException e1) 
		{
			showErrors(e1);
		}
	}
	
	private void create()
	{
		hideErrors();	
		HashMap<String,Object> mapCategory = new HashMap<String,Object>();

		mapCategory.put("description", textAreaDescription.getText());
		
		if(comboBoxCategory.getSelectedItem() != null)
		{
			mapCategory.put("parent", comboBoxCategory.getSelectedItem());
			try 
			{
				facade.getCategoryFacade().createSubCategory(mapCategory);
				this.mainFrame.changePanel(new CategoriesPanel(this.mainFrame, this.facade));
			} 
			catch (FalseFieldsException e1) 
			{
				showErrors(e1);
			}
		}
		else
		{
			try 
			{
				facade.getCategoryFacade().createMainCategory(mapCategory);
				this.mainFrame.changePanel(new CategoriesPanel(this.mainFrame, this.facade));
			} 
			catch (FalseFieldsException e1) 
			{
				showErrors(e1);
			}
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
			}
		}
	}
	
	private void hideErrors()
	{
		textAreaDescription.setBorder(defaultBorder);
		
		lblErrorDescription.setVisible(false);
	}
	
	private void cancel()
	{
		this.mainFrame.changePanel(new CategoriesPanel(this.mainFrame, this.facade));
	}
}
