package com.novar.ui;


import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;



import com.novar.business.MainFacade;

import java.awt.Font;


public class NewCategoryDialog extends JDialog {

	private JPanel contentPane;
	private JTextField categoryTextField;
	private MainFacade facade;

	/**
	 * Create the frame.
	 */
	public NewCategoryDialog(JFrame frame, MainFacade facade) 
	{
		super(frame, "New Category", Dialog.ModalityType.DOCUMENT_MODAL);
		setResizable(false);
		this.facade = facade;
		Toolkit tk = Toolkit.getDefaultToolkit(); 
		Dimension d = tk.getScreenSize();
		int hauteurEcran = d.height;
		int largeurEcran = d.width; 
		setBounds(largeurEcran/4, hauteurEcran/4,582, 197);
		setAlwaysOnTop(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		categoryTextField = new JTextField();
		categoryTextField.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.setFont(new Font("Calibri", Font.PLAIN, 11));
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				askForAnewCategory();
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
			
			JLabel lblEnter = new JLabel("Enter the name of the category you want to be created");
		
		
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(81)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(lblEnter)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnCancel)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnOk))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(categoryTextField, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addContainerGap(214, Short.MAX_VALUE))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(15)
						.addComponent(lblEnter)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(categoryTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnCancel)
							.addComponent(btnOk))
						.addContainerGap(58, Short.MAX_VALUE))
			);
			contentPane.setLayout(gl_contentPane);
		}
		
	private void closeWindow()
	{
		this.dispose();
	}
	
	private void askForAnewCategory() 
	{
		//facade.getNotificationFacade().notify(facade.getUser(), "I would create a new category called " + this.categoryTextField.getText(), ListeDesAdmins);
		closeWindow();
	}
}
