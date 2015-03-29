package com.novar.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Component;

import javax.swing.JProgressBar;
import javax.swing.JSpinner;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JMenu;

import com.novar.business.Activity;
import com.novar.business.Event;
import com.novar.business.MainFacade;
import com.novar.business.Manager;
import com.novar.business.Registration;
import com.novar.business.User;
import com.novar.persist.RegistrationJdbc;
import com.novar.persist.UserJdbc;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;

import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JMenuItem;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;

public class ConnectedWindow extends JFrame {


	private MainFacade facade;

	private JPanel contentPane;

	private ConnectedWindow frame;
	private JMenuItem mnNotification;
	/**
	 * @wbp.nonvisual location=-29,199
	 */
	/**
	 * Create the frame.
	 */
	public ConnectedWindow(MainFacade facade) 
	{
		super("GoFit");
		frame = this;
		this.facade = facade;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit tk = Toolkit.getDefaultToolkit(); 
		Dimension d = tk.getScreenSize();
		int screenHeight = d.height;
		int screenWidth = d.width; 
		int height = 720;
		int width = 980;
		setBounds(screenWidth/6, screenHeight/10, width, height);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{624, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{14, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);

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

			JMenuItem mntmRooms = new JMenuItem("Rooms");
			mnAdministrator.add(mntmRooms);

			JMenuItem mntmAccessories = new JMenuItem("Accessories");
			mnAdministrator.add(mntmAccessories);


			JMenuItem mntmActivities= new JMenuItem("Activities");
			mnAdministrator.add(mntmActivities);

			mntmCategories.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					//changePanel(new JPanelCategory());
				}
			});

			mntmRooms.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					changePanel(new PanelRooms(frame, facade));
				}
			});

			mntmAccessories.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					changePanel(new PanelAccessories(frame, facade));
				}
			});		


			mntmActivities.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0)
				{
					changePanel(new PanelActivities(frame,facade));
				}
			});
		}

		if(facade.getUser().isManager())
		{
			JMenu mnManager = new JMenu("Manager");
			menuBar.add(mnManager);


			JMenuItem mntmEvents = new JMenuItem("Events");
			mnManager.add(mntmEvents);

			JMenuItem mntmRegistrations = new JMenuItem("Registrations");
			mnManager.add(mntmRegistrations);

			mntmRegistrations.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{ 	
					seeMore();
				}
			});
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

			JMenuItem mntmMyregistrations = new JMenuItem("My Registration");
			mnMember.add(mntmMyregistrations);

			mntmMyregistrations.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					changePanel(new PanelRegistrationsMember(frame,facade));
				}
			});
			mntmProducts.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					//changePanel(new JPanelProducts());
				}
			});
		}

		Component verticalStrut = Box.createVerticalStrut(20);
		menuBar.add(verticalStrut);

		mnNotification = new JMenuItem("Notification");
		mnNotification.setMaximumSize(new Dimension(1, 32767));
		mnNotification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				changePanel(new PanelNotifications(frame, facade));
			}
		});
		menuBar.add(mnNotification);

		JMenuItem mnBasket = new JMenuItem("Basket");
		mnBasket.setMaximumSize(new Dimension(1, 32767));
		menuBar.add(mnBasket);

		JMenuItem mnLogoff = new JMenuItem("Logoff");
		mnLogoff.setMaximumSize(new Dimension(1, 32767));
		menuBar.add(mnLogoff);

		loadNotifs();

		ArrayList<String> receivers = new ArrayList<String>();
		receivers.add("Antoine1");
		this.facade.getNotificationFacade().notify(this.facade.getUser(), "tergsguh", receivers);
	}
	public void seeMore()
	{
		Registration reg= new RegistrationJdbc();


		changePanel(new PanelRegistrations(frame,facade));
	}

	public void changePanel (JPanel panel)
	{
		contentPane = panel;
		setContentPane(contentPane);
		loadNotifs();
		validate();
	}

	public void loadNotifs()
	{
		int nbNotif = this.facade.getNotificationFacade().countNewNotifs(this.facade.getUser());
		String text = "Notification ("+Integer.toString(nbNotif)+")";
		mnNotification.setText(text);
	}
}
