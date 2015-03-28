package com.novar.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.novar.business.MainFacade;
import com.novar.business.Manager;
import com.novar.business.Member;
import com.novar.business.User;
import com.novar.business.UserManager;
import com.novar.exception.FalseFieldsException;
import com.novar.persist.UserJdbc;
import com.novar.persist.UserManagerJdbc;

public class MemberDetailsPanel extends JPanel {

	private MainFacade facade;
	private ConnectedWindow mainFrame;
	private User user = null;

	private JTextField textFieldName;
	
	/**
	 * Create the panel.
	 */
	public MemberDetailsPanel(ConnectedWindow frame, MainFacade facade, User user) {
		this.facade = facade;
		this.mainFrame = frame;
		this.user = user;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblMemberDetails = new JLabel("Member details");
		springLayout.putConstraint(SpringLayout.NORTH, lblMemberDetails, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblMemberDetails, mainFrame.getWidth()/3, SpringLayout.WEST, this);
		lblMemberDetails.setFont(new Font("Calibri", Font.BOLD, 24));
		add(lblMemberDetails);
		
		JLabel lblName = new JLabel("Name :");
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 100, SpringLayout.NORTH, lblMemberDetails);
		springLayout.putConstraint(SpringLayout.WEST, lblName, mainFrame.getWidth()/3, SpringLayout.WEST, this);
		lblName.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblName);
		
		textFieldName = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldName, 0, SpringLayout.NORTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, textFieldName, 6, SpringLayout.EAST, lblName);
		add(textFieldName);
		textFieldName.setColumns(10);
		
		if(user != null)
		{
			textFieldName.setText(user.getFirstName() + user.getLastName() );
			JButton btnUpdate = new JButton("Update");
			springLayout.putConstraint(SpringLayout.NORTH, btnUpdate, 30, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.EAST, btnUpdate, -80, SpringLayout.EAST, this);
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
			JButton btnCreate = new JButton("Create");
			springLayout.putConstraint(SpringLayout.NORTH, btnCreate, 30, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.EAST, btnCreate, -80, SpringLayout.EAST, this);
			btnCreate.setFont(new Font("Calibri", Font.PLAIN, 14));
			btnCreate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					create();
				}
			});
			add(btnCreate);
		}
		
		JButton btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 30, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnCancel, 80, SpringLayout.WEST, this);
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		add(btnCancel);
	}
	
	private void create(){
		HashMap<String,Object> dataUser = new HashMap<String,Object>();
		dataUser.put("pseudo", textFieldName.getText());
		try {
			user = new UserJdbc(dataUser);
		} catch (FalseFieldsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		facade.getAdminFacade().setMember(user);
		this.mainFrame.changePanel(new MemberPanel(this.mainFrame, this.facade));
	}
	
	private void update()
	{
		this.mainFrame.changePanel(new UpdateProfilePanel(this.mainFrame));;
	}
	
	private void cancel()
	{
		this.mainFrame.changePanel(new MemberPanel(this.mainFrame, this.facade));
	}

}
