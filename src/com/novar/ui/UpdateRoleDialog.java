package com.novar.ui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.novar.business.MainFacade;
import com.novar.business.User;

public class UpdateRoleDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private MainFacade facade;
	private ConnectedWindow mainFrame;
	private User user;
	
	/**
	 * Create the dialog.
	 */
	public UpdateRoleDialog(ConnectedWindow frame, MainFacade facade, User user) {
		super(frame, "Update", Dialog.ModalityType.DOCUMENT_MODAL);
		this.mainFrame = frame;
		this.facade = facade;
		this.user = user;
		setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit(); 
		Dimension d = tk.getScreenSize();
		int screenHeight = d.height;
		int screenWidth = d.width; 
		setBounds(screenWidth/4, screenHeight/4, 400, 300);
		setAlwaysOnTop(true);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		String text = "Do you really want to change the role of "+ user.getPseudo()+" ?";
		JLabel labelConfirm = new JLabel(text);
		springLayout.putConstraint(SpringLayout.NORTH, labelConfirm, 34, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, labelConfirm, -42, SpringLayout.EAST, getContentPane());
		getContentPane().add(labelConfirm);
		
		JButton btnYes = new JButton("Yes");
		springLayout.putConstraint(SpringLayout.SOUTH, btnYes, -64, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnYes, 0, SpringLayout.EAST, labelConfirm);
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
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
	}
	
	private void cancel()
	{
		this.dispose();
	}
	
	private void update()
	{
		this.facade.getAdminFacade().setManager(user);
		this.mainFrame.changePanel(new ManagerPanel(this.mainFrame, this.facade));
		this.dispose();
	}
}
