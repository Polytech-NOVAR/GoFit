package com.novar.ui;


import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import com.novar.business.MainFacade;
import com.novar.util.ConnectionUtil;

import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JMenuItem;

import java.awt.Dimension;

public class ConnectedWindow extends JFrame
{	
	private MainFacade facade;
	private JPanel contentPane;
	private ConnectedWindow frame;
	private JMenuItem mnNotification;
	
	private JScrollPane scrollPane;
	/**
	 * Create the frame.
	 */
	public ConnectedWindow(MainFacade facade) 
	{
		super("GoFit");
		frame = this;
		this.facade = facade;
		this.frame = this;
		frame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit tk = Toolkit.getDefaultToolkit(); 
		Dimension d = tk.getScreenSize();
		int screenHeight = d.height;
		int screenWidth = d.width; 
		int height = 720;
		int width = 980;
		setBounds(screenWidth/6, screenHeight/10, width, height);
		
		setResizable(false);
		
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
		mnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePanel(new ProfilePanel(frame));
			}
		});
		menuBar.add(mnProfile);
		
		JMenuItem mnShop = new JMenuItem("Shop");
		mnShop.setMaximumSize(new Dimension(1, 32767));
		mnShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePanel(new ShopPanel(frame,facade));
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
			
			JMenuItem mntmMembers = new JMenuItem("Members");
			mnAdministrator.add(mntmMembers);
			
			JMenuItem mntmManagers = new JMenuItem("Managers");
			mnAdministrator.add(mntmManagers);
			
			JMenuItem mntmAccessories = new JMenuItem("Accessories");
			mnAdministrator.add(mntmAccessories);
			
			JMenuItem mntmUsers = new JMenuItem("Users");
			mnAdministrator.add(mntmUsers);
			
			mntmCategories.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					changePanel(new CategoriesPanel(frame,facade));
				}
			});
			
			mntmMembers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					changePanel(new MemberPanel(frame, facade));
				}
			});
			
			mntmManagers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					changePanel(new ManagerPanel(frame, facade));
				}
			});
			
			mntmRooms.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					changePanel(new RoomsPanel(frame, facade));
				}
			});
			
			mntmAccessories.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					changePanel(new AccessoriesPanel(frame, facade));
				}
			});
			
			mntmUsers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					changePanel(new UsersPanel(frame));
				}
			});
		}
		
		if(facade.getUser().isManager())
		{
			JMenu mnManager = new JMenu("Manager");
			menuBar.add(mnManager);
			
			JMenuItem mntmPlanning = new JMenuItem("Planning");
			mnManager.add(mntmPlanning);
			
			mntmPlanning.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					changePanel(new PlanningPanel(frame));
				}
			});
			JMenuItem mntmSpeakers = new JMenuItem("Speakers");
			mnManager.add(mntmSpeakers);
			
			mntmSpeakers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					changePanel(new SpeakerPanel(frame, facade));
				}
			});
			
		}
		
		if(facade.getUser().isSpeaker())
		{
			JMenu mnSpeaker = new JMenu("Speaker");
			menuBar.add(mnSpeaker);
			
			JMenuItem mntmPlanning = new JMenuItem("Planning");
			mnSpeaker.add(mntmPlanning);
			
			mntmPlanning.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					changePanel(new PlanningPanel(frame));
				}
			});
			
		}
		
		if(facade.getUser().isMember())
		{
			JMenu mnMember = new JMenu("Member");
			menuBar.add(mnMember);
			
			JMenuItem mntmProducts = new JMenuItem("Products");
			mnMember.add(mntmProducts);
			
			JMenuItem mntmPlanning = new JMenuItem("Planning");
			mnMember.add(mntmPlanning);
			
			mntmProducts.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					changePanel(new ProductsPanel(frame,facade));
				}
			});
			
			mntmPlanning.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					changePanel(new PlanningPanel(frame));
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
				changePanel(new NotificationsPanel(frame, facade));
			}
		});
		menuBar.add(mnNotification);
		
		JMenuItem mnBasket = new JMenuItem("Basket");
		mnBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePanel(new BasketPanel(frame, facade));
			}
		});
		mnBasket.setMaximumSize(new Dimension(1, 32767));
		menuBar.add(mnBasket);
		
		JMenuItem mnLogoff = new JMenuItem("Logoff");
		mnLogoff.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ConnectionUtil.stop();
				LoginWindow loginWindow = new LoginWindow();
				loginWindow.setVisible(true);
				closeWindow();
			}
		});
		mnLogoff.setMaximumSize(new Dimension(1, 32767));
		menuBar.add(mnLogoff);

		loadNotifs();

		changePanel(new ProfilePanel(frame));
	}
	
	public void changePanel (JPanel panel)
	{
		scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		setContentPane(scrollPane);
		loadNotifs();
		validate();
	}
	
	public void loadNotifs()
	{
		int nbNotif = this.facade.getNotificationFacade().countNewNotifs(this.facade.getUser());
		String text = "Notification ("+Integer.toString(nbNotif)+")";
		mnNotification.setText(text);
	}
	
	private void closeWindow(){
		dispose();
	}
	
	public MainFacade getFacade()
	{
		return this.facade;
	}
}
