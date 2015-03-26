package com.novar.ui;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.novar.business.FacadeMain;
import com.novar.business.Category;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class DeleteCategoryDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private FacadeMain facade;
	private ConnectedWindow mainFrame;
	private Category category;
	
	/**
	 * Create the dialog.
	 */
	public DeleteCategoryDialog(ConnectedWindow frame, FacadeMain facade, Category category) 
	{
		super(frame, "Delete", Dialog.ModalityType.DOCUMENT_MODAL);
		this.mainFrame = frame;
		this.facade = facade;
		this.category = category;
		setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit(); 
		Dimension d = tk.getScreenSize();
		int screenHeight = d.height;
		int screenWidth = d.width; 
		setBounds(screenWidth/4, screenHeight/4, 400, 300);
		setAlwaysOnTop(true);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		String text = "Do you really want to delete the Category number "+category.getCatID()+" ?";
		JLabel labelConfirm = new JLabel(text);
		springLayout.putConstraint(SpringLayout.NORTH, labelConfirm, 34, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, labelConfirm, -42, SpringLayout.EAST, getContentPane());
		getContentPane().add(labelConfirm);
		
		JButton btnYes = new JButton("Yes");
		springLayout.putConstraint(SpringLayout.SOUTH, btnYes, -64, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnYes, 0, SpringLayout.EAST, labelConfirm);
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		getContentPane().add(btnYes);
		
		JButton btnNo = new JButton("No");
		springLayout.putConstraint(SpringLayout.WEST, btnNo, 0, SpringLayout.WEST, labelConfirm);
		springLayout.putConstraint(SpringLayout.SOUTH, btnNo, 0, SpringLayout.SOUTH, btnYes);
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		getContentPane().add(btnNo);
		
		JLabel lblAllTheProducts = new JLabel("All the products and subcategories of that category will be deleted too");
		lblAllTheProducts.setFont(new Font("Tahoma", Font.PLAIN, 10));
		springLayout.putConstraint(SpringLayout.NORTH, lblAllTheProducts, 6, SpringLayout.SOUTH, labelConfirm);
		springLayout.putConstraint(SpringLayout.EAST, lblAllTheProducts, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(lblAllTheProducts);
	}
	
	private void cancel()
	{
		this.dispose();
	}
	
	private void delete()
	{
		//facade.getProductFacade().deleteCategoriesProducts(category);
		facade.getCategoryFacade().deleteCategory(category);
		this.mainFrame.changePanel(new CategoriesPanel(this.mainFrame, this.facade));
		dispose();
	}

}
