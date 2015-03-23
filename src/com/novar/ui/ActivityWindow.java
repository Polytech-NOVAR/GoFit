package com.novar.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;

import com.novar.business.FacadeMain;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ActivityWindow extends JFrame {
	private FacadeMain facade;

	public ActivityWindow(FacadeMain facade) {
		
		
		super("GoFit");
		this.facade = facade;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 800);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{624, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{14, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		
		JLabel lblNewLabel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 3;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JMenuItem mnProfile = new JMenuItem("Profile");
		mnProfile.setMaximumSize(new Dimension(1, 32767));
		menuBar.add(mnProfile);
		
		JMenuItem mnShop = new JMenuItem("Shop");
		mnShop.setMaximumSize(new Dimension(1, 32767));
		mnShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//changePanel(new JPanelShop());
			}
		});
		menuBar.add(mnShop);
		
		if(facade.getUser().isAdministrator())
		{
			JMenu mnAdministrator = new JMenu("Admin");
			menuBar.add(mnAdministrator);
			
			
			JMenuItem mntmCategories = new JMenuItem("Categories");
			mnAdministrator.add(mntmCategories);
			
			mntmCategories.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					//changePanel(new JPanelCategory());
				}
			});
		}
		
		if(facade.getUser().isManager())
		{
			JMenu mnManager = new JMenu("Manager");
			menuBar.add(mnManager);
		}
		
		if(facade.getUser().isSpeaker())
		{
			JMenu mnSpeaker = new JMenu("Speaker");
			menuBar.add(mnSpeaker);
		}
		
		if(facade.getUser().isMember())
		{
			JMenu mnMember = new JMenu("Member");
			menuBar.add(mnMember);
			
			JMenuItem mntmProducts = new JMenuItem("Products");
			mnMember.add(mntmProducts);
			
			mntmProducts.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					//changePanel(new JPanelProducts());
				}
			});
		}
		
		Component verticalStrut = Box.createVerticalStrut(20);
		menuBar.add(verticalStrut);
		
		JMenuItem mnNotification = new JMenuItem("Notification");
		mnNotification.setMaximumSize(new Dimension(1, 32767));
		menuBar.add(mnNotification);
		
		JMenuItem mnBasket = new JMenuItem("Basket");
		mnBasket.setMaximumSize(new Dimension(1, 32767));
		menuBar.add(mnBasket);
		
		JMenuItem mnLogoff = new JMenuItem("Logoff");
		mnLogoff.setMaximumSize(new Dimension(1, 32767));
		menuBar.add(mnLogoff);
	}
}
