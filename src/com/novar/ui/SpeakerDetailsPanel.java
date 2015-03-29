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
import com.novar.business.ManagerManager;
import com.novar.business.Speaker;
import com.novar.business.User;
import com.novar.business.UserManager;
import com.novar.exception.FalseFieldsException;
import com.novar.persist.ManagerManagerJdbc;
import com.novar.persist.UserJdbc;
import com.novar.persist.UserManagerJdbc;

public class SpeakerDetailsPanel extends JPanel {

	private MainFacade facade;
	private ConnectedWindow mainFrame;
	private User user = null;

	private JTextField textFieldName;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtStreet;
	private JTextField txtTown;
	private JTextField txtZipCode;
	private JTextField txtCountry;
	
	
	/**
	 * Create the panel.
	 */
	public SpeakerDetailsPanel(ConnectedWindow frame, MainFacade facade, User user) {
		this.facade = facade;
		this.mainFrame = frame;
		this.user = user;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblManagerDetails = new JLabel("Speakers details");
		springLayout.putConstraint(SpringLayout.NORTH, lblManagerDetails, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblManagerDetails, mainFrame.getWidth()/3, SpringLayout.WEST, this);
		lblManagerDetails.setFont(new Font("Calibri", Font.BOLD, 24));
		add(lblManagerDetails);
		
		JLabel lblName = new JLabel("Pseudo :");
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 100, SpringLayout.NORTH, lblManagerDetails);
		springLayout.putConstraint(SpringLayout.WEST, lblName, mainFrame.getWidth()/3, SpringLayout.WEST, this);
		lblName.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblName);
		
		JLabel lblFirstName = new JLabel("FName :");
		springLayout.putConstraint(SpringLayout.NORTH, lblFirstName, 45, SpringLayout.NORTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, lblFirstName, mainFrame.getWidth()/3, SpringLayout.WEST, this);
		lblFirstName.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblFirstName);
		
		JLabel lblLastName = new JLabel("LName :");
		springLayout.putConstraint(SpringLayout.NORTH, lblLastName, 30, SpringLayout.SOUTH, lblFirstName);
		springLayout.putConstraint(SpringLayout.WEST, lblLastName, mainFrame.getWidth()/3, SpringLayout.WEST, this);
		lblLastName.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblLastName);
		
		JLabel lblPhone = new JLabel("Phone :");
		springLayout.putConstraint(SpringLayout.NORTH, lblPhone, 30, SpringLayout.SOUTH, lblLastName);
		springLayout.putConstraint(SpringLayout.WEST, lblPhone, mainFrame.getWidth()/3, SpringLayout.WEST, this);
		lblPhone.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblPhone);
		
		JLabel lblEmail = new JLabel("Email :");
		springLayout.putConstraint(SpringLayout.NORTH, lblEmail, 30, SpringLayout.SOUTH, lblPhone);
		springLayout.putConstraint(SpringLayout.WEST, lblEmail, mainFrame.getWidth()/3, SpringLayout.WEST, this);
		lblEmail.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblEmail);
		
		JLabel lblStreet = new JLabel("Street :");
		springLayout.putConstraint(SpringLayout.NORTH, lblStreet, 30, SpringLayout.SOUTH, lblEmail);
		springLayout.putConstraint(SpringLayout.WEST, lblStreet, mainFrame.getWidth()/3, SpringLayout.WEST, this);
		lblStreet.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblStreet);
		
		JLabel lblTown = new JLabel("Town :");
		springLayout.putConstraint(SpringLayout.NORTH, lblTown, 30, SpringLayout.SOUTH, lblStreet);
		springLayout.putConstraint(SpringLayout.WEST, lblTown, mainFrame.getWidth()/3, SpringLayout.WEST, this);
		lblTown.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblTown);
		
		JLabel lblZipCode = new JLabel("ZCode :");
		springLayout.putConstraint(SpringLayout.NORTH, lblZipCode, 30, SpringLayout.SOUTH, lblTown);
		springLayout.putConstraint(SpringLayout.WEST, lblZipCode, mainFrame.getWidth()/3, SpringLayout.WEST, this);
		lblZipCode.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblZipCode);
		
		JLabel lblCountry = new JLabel("Country :");
		springLayout.putConstraint(SpringLayout.NORTH, lblCountry, 30, SpringLayout.SOUTH, lblZipCode);
		springLayout.putConstraint(SpringLayout.WEST, lblCountry, mainFrame.getWidth()/3, SpringLayout.WEST, this);
		lblCountry.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblCountry);
		
		textFieldName = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldName, 0, SpringLayout.NORTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, textFieldName, 20, SpringLayout.EAST, lblName);
		add(textFieldName);
		textFieldName.setColumns(10);
		
		txtFirstName = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtFirstName, 0, SpringLayout.NORTH, lblFirstName);
		springLayout.putConstraint(SpringLayout.WEST, txtFirstName, 21, SpringLayout.EAST, lblFirstName);
		add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtLastName = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtLastName, 0, SpringLayout.NORTH, lblLastName);
		springLayout.putConstraint(SpringLayout.WEST, txtLastName, 22, SpringLayout.EAST, lblLastName);
		add(txtLastName);
		txtLastName.setColumns(10);
		
		txtPhone = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtPhone, 0, SpringLayout.NORTH, lblPhone);
		springLayout.putConstraint(SpringLayout.WEST, txtPhone, 25, SpringLayout.EAST, lblPhone);
		add(txtPhone);
		txtPhone.setColumns(10);
		
		txtEmail = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtEmail, 0, SpringLayout.NORTH, lblEmail);
		springLayout.putConstraint(SpringLayout.WEST, txtEmail, 25, SpringLayout.EAST, lblEmail);
		add(txtEmail);
		txtEmail.setColumns(10);
		
		txtStreet = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtStreet, 0, SpringLayout.NORTH, lblStreet);
		springLayout.putConstraint(SpringLayout.WEST, txtStreet, 25, SpringLayout.EAST, lblStreet);
		add(txtStreet);
		txtStreet.setColumns(10);
		
		txtTown = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtTown, 0, SpringLayout.NORTH, lblTown);
		springLayout.putConstraint(SpringLayout.WEST, txtTown, 26, SpringLayout.EAST, lblTown);
		add(txtTown);
		txtTown.setColumns(10);
		
		txtZipCode = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtZipCode, 0, SpringLayout.NORTH, lblZipCode);
		springLayout.putConstraint(SpringLayout.WEST, txtZipCode, 20, SpringLayout.EAST, lblZipCode);
		add(txtZipCode);
		txtZipCode.setColumns(10);
		
		txtCountry = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtCountry, 0, SpringLayout.NORTH, lblCountry);
		springLayout.putConstraint(SpringLayout.WEST, txtCountry, 18, SpringLayout.EAST, lblCountry);
		add(txtCountry);
		txtCountry.setColumns(10);
		
		if(user != null)
		{
			textFieldName.setText(user.getPseudo());
			txtFirstName.setText(user.getFirstName());
			txtLastName.setText(user.getLastName());
			txtPhone.setText(user.getPhone());
			txtEmail.setText(user.getEmail());
			txtStreet.setText(user.getStreet());
			txtTown.setText(user.getTown());
			txtZipCode.setText(user.getZipCode());
			txtCountry.setText(user.getCountry());
		}
		else
		{
			JButton btnCreate = new JButton("Add");
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
		facade.getManagerFacade().setSpeaker(user);
		this.mainFrame.changePanel(new SpeakerPanel(this.mainFrame, this.facade));
	}
	
	private void cancel()
	{
		this.mainFrame.changePanel(new SpeakerPanel(this.mainFrame, this.facade));
	}

}