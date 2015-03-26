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
import com.novar.business.MainCategory;
import com.novar.business.SubCategory;
import com.novar.business.Category;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CategoriesPanel extends JPanel 
{
	private ConnectedWindow mainFrame;
	private FacadeMain facade;
	private ArrayList<MainCategory> mainCategories;
	private ArrayList<SubCategory> subCategories;

	/**
	 * Create the panel.
	 */
	public CategoriesPanel(ConnectedWindow frame,FacadeMain facade) 
	{
		this.facade = facade;
		this.mainFrame = frame;
		mainCategories = facade.getCategoryFacade().getAllMainCategories();

		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblCategoryID = new JLabel("Id");
		springLayout.putConstraint(SpringLayout.NORTH, lblCategoryID, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblCategoryID, (mainFrame.getWidth()/9)/2, SpringLayout.WEST, this);
		lblCategoryID.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblCategoryID);
		
		JLabel lblDesc = new JLabel("description");
		springLayout.putConstraint(SpringLayout.NORTH, lblDesc, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblDesc, mainFrame.getWidth()/9, SpringLayout.WEST, lblCategoryID);
		lblDesc.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblDesc);
		
		
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
		
		JLabel lblCategories = new JLabel("Categories");
		springLayout.putConstraint(SpringLayout.NORTH, lblCategories, 30, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblCategories, 0, SpringLayout.HORIZONTAL_CENTER, this);
		lblCategories.setFont(new Font("Calibri", Font.BOLD, 24));
		
		add(lblCategories);
		int cptligne = 1;
		for(int i=0; i<mainCategories.size(); i++)
		{
			
			MainCategory categoryi = mainCategories.get(i);
			
			JLabel lblcatIID = new JLabel(categoryi.getCatID().toString());
			springLayout.putConstraint(SpringLayout.NORTH, lblcatIID, cptligne*55, SpringLayout.NORTH, lblCategoryID);
			springLayout.putConstraint(SpringLayout.WEST, lblcatIID, (mainFrame.getWidth()/9)/2, SpringLayout.WEST, this);
			lblcatIID.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblcatIID);
			
			JLabel lblCatDesc;
			if(categoryi.getDescription().length() > 30)
				lblCatDesc= new JLabel(categoryi.getDescription().substring(0, 30)+"...");
			else
				lblCatDesc= new JLabel(categoryi.getDescription());
			
			springLayout.putConstraint(SpringLayout.NORTH, lblCatDesc, cptligne*55, SpringLayout.NORTH, lblCategoryID);
			springLayout.putConstraint(SpringLayout.WEST, lblCatDesc, mainFrame.getWidth()/9, SpringLayout.WEST, lblcatIID);
			lblCatDesc.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblCatDesc);
			
			JButton btnSeeMore = new JButton("See more");
			springLayout.putConstraint(SpringLayout.NORTH, btnSeeMore, cptligne*55, SpringLayout.NORTH, lblCategoryID);
			springLayout.putConstraint(SpringLayout.WEST, btnSeeMore, mainFrame.getWidth()/3, SpringLayout.WEST, lblCatDesc);
			btnSeeMore.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnSeeMore.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seeMore(categoryi);
				}
			});
			add(btnSeeMore);
			
			JButton btnDelete = new JButton("Delete");
			springLayout.putConstraint(SpringLayout.NORTH, btnDelete,cptligne*55, SpringLayout.NORTH, lblCategoryID);
			springLayout.putConstraint(SpringLayout.WEST, btnDelete, mainFrame.getWidth()/9, SpringLayout.WEST, btnSeeMore);
			btnDelete.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					delete(categoryi);
				}
			});
			add(btnDelete);
			
			cptligne++;

			subCategories = facade.getCategoryFacade().getSubCategories(mainCategories.get(i));
			for(int j=0; j<subCategories.size(); j++)
			{
				
				SubCategory categoryj = subCategories.get(j);
				
				lblcatIID = new JLabel("-> " + categoryj.getCatID().toString());
				springLayout.putConstraint(SpringLayout.NORTH, lblcatIID, cptligne*55, SpringLayout.NORTH, lblCategoryID);
				springLayout.putConstraint(SpringLayout.WEST, lblcatIID, (mainFrame.getWidth()/9)/2, SpringLayout.WEST, this);
				lblcatIID.setFont(new Font("Calibri", Font.PLAIN, 12));
				add(lblcatIID);
				
				if(categoryj.getDescription().length() > 30)
					lblCatDesc= new JLabel(categoryj.getDescription().substring(0, 30)+"...");
				else
					lblCatDesc= new JLabel(categoryj.getDescription());
				
				springLayout.putConstraint(SpringLayout.NORTH, lblCatDesc, cptligne*55, SpringLayout.NORTH, lblCategoryID);
				springLayout.putConstraint(SpringLayout.WEST, lblCatDesc, mainFrame.getWidth()/9, SpringLayout.WEST, lblcatIID);
				lblCatDesc.setFont(new Font("Calibri", Font.PLAIN, 12));
				add(lblCatDesc);
				
				btnSeeMore = new JButton("See more");
				springLayout.putConstraint(SpringLayout.NORTH, btnSeeMore, cptligne*55, SpringLayout.NORTH, lblCategoryID);
				springLayout.putConstraint(SpringLayout.WEST, btnSeeMore, mainFrame.getWidth()/3, SpringLayout.WEST, lblCatDesc);
				btnSeeMore.setFont(new Font("Calibri", Font.PLAIN, 12));
				btnSeeMore.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						seeMore(categoryj);
					}
				});
				add(btnSeeMore);
				
				btnDelete = new JButton("Delete");
				springLayout.putConstraint(SpringLayout.NORTH, btnDelete,cptligne*55, SpringLayout.NORTH, lblCategoryID);
				springLayout.putConstraint(SpringLayout.WEST, btnDelete, mainFrame.getWidth()/9, SpringLayout.WEST, btnSeeMore);
				btnDelete.setFont(new Font("Calibri", Font.PLAIN, 12));
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						delete(categoryj);
					}
				});
				add(btnDelete);
				cptligne++;
			}
			
		}
	}
	
	private void seeMore(Category category)
	{
		this.mainFrame.changePanel(new CategoryDetailsPanel(this.mainFrame, this.facade, category));
	}
	
	
	private void addOne(){
		this.mainFrame.changePanel(new CategoryDetailsPanel(this.mainFrame, this.facade, null));
	}
	
	private void delete(Category product)
	{
		DeleteCategoryDialog delete = new DeleteCategoryDialog(this.mainFrame, this.facade, product);
		delete.setVisible(true);
	}
}