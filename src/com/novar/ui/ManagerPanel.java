package com.novar.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.novar.business.MainFacade;
import com.novar.business.User;
import com.novar.persist.UserJdbc;

public class ManagerPanel extends JPanel {

private MainFacade facade;
	
	private ConnectedWindow mainFrame;
	/**
	 * Create the panel.
	 */
	public ManagerPanel(ConnectedWindow frame, MainFacade facade) {
		this.facade = facade;
		this.mainFrame = frame;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblPseudo = new JLabel("Pseudo");
		springLayout.putConstraint(SpringLayout.NORTH, lblPseudo, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblPseudo, mainFrame.getWidth()/8, SpringLayout.WEST, this);
		lblPseudo.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblPseudo);
		
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
		
		JLabel lblManager = new JLabel("Manager");
		springLayout.putConstraint(SpringLayout.NORTH, lblManager, 30, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblManager, mainFrame.getWidth()/2, SpringLayout.WEST, this);
		lblManager.setFont(new Font("Calibri", Font.BOLD, 24));
		
		add(lblManager);
		
		User manager = facade.getUser();
		ArrayList<User> users = facade.getAdminFacade().getAllAdmin(manager);
		for(int i=0; i<users.size(); i++)
		{
			double multiplier = 1.5 + 0.5*i;
			User admini = users.get(i);
			
			JLabel lblAdmini= new JLabel(admini.getPseudo());
			springLayout.putConstraint(SpringLayout.NORTH, lblAdmini, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblAdmini, mainFrame.getWidth()/8, SpringLayout.WEST, this);
			lblAdmini.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblAdmini);
			
			
			JButton btnSeeMore = new JButton("See more");
			springLayout.putConstraint(SpringLayout.NORTH, btnSeeMore, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, btnSeeMore, mainFrame.getWidth()/4, SpringLayout.WEST, lblAdmini);
			btnSeeMore.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnSeeMore.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seeMore(admini);
				}
			});
			add(btnSeeMore);
			
			JButton btnDelete = new JButton("Delete");
			springLayout.putConstraint(SpringLayout.NORTH, btnDelete,(int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, btnDelete, mainFrame.getWidth()/8, SpringLayout.WEST, btnSeeMore);
			btnDelete.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					delete(admini);
				}
			});
			add(btnDelete);
		}
	}
	
	private void seeMore(User user){
		this.mainFrame.changePanel(new ManagerDetailsPanel(this.mainFrame, this.facade, user));
	}
	
	private void addOne(){
		this.mainFrame.changePanel(new ManagerDetailsPanel(this.mainFrame, this.facade, null));
	}
	
	private void delete(User user)
	{
		DeleteManagerDialog delete = new DeleteManagerDialog(this.mainFrame, this.facade, user);
		delete.setVisible(true);
	}
	
}