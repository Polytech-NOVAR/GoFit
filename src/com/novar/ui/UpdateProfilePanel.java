package com.novar.ui;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import com.novar.business.Speaker;
import com.novar.exception.FalseFieldsException;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JTextArea;

import java.awt.Dimension;

public class UpdateProfilePanel extends JPanel
{
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtStreet;
	private JTextField txtTown;
	private JTextField txtZipCode;
	private JTextField txtCountry;
	private JTextField txtShortDescription;
	private JTextArea txtDetailedDescription;
	
	/**
	 * Create the panel.
	 */
	public UpdateProfilePanel(ConnectedWindow frame)
	{
		setBounds(new Rectangle(0, 0, 980, 800));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblProfile = new JLabel("Profile");
		lblProfile.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		springLayout.putConstraint(SpringLayout.NORTH, lblProfile, 20, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblProfile, 0, SpringLayout.HORIZONTAL_CENTER, this);
		lblProfile.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblProfile);
		
		JLabel lblFirstName = new JLabel("First name :");
		springLayout.putConstraint(SpringLayout.NORTH, lblFirstName, 75, SpringLayout.NORTH, lblProfile);
		springLayout.putConstraint(SpringLayout.EAST, lblFirstName, 0, SpringLayout.WEST, lblProfile);
		add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last name :");
		springLayout.putConstraint(SpringLayout.NORTH, lblLastName, 20, SpringLayout.SOUTH, lblFirstName);
		springLayout.putConstraint(SpringLayout.EAST, lblLastName, 0, SpringLayout.EAST, lblFirstName);
		add(lblLastName);
		
		JLabel lblPhone = new JLabel("Phone :");
		springLayout.putConstraint(SpringLayout.NORTH, lblPhone, 20, SpringLayout.SOUTH, lblLastName);
		springLayout.putConstraint(SpringLayout.EAST, lblPhone, 0, SpringLayout.EAST, lblFirstName);
		add(lblPhone);
		
		JLabel lblEmail = new JLabel("Email :");
		springLayout.putConstraint(SpringLayout.NORTH, lblEmail, 20, SpringLayout.SOUTH, lblPhone);
		springLayout.putConstraint(SpringLayout.EAST, lblEmail, 0, SpringLayout.EAST, lblFirstName);
		add(lblEmail);
		
		JLabel lblStreet = new JLabel("Street :");
		springLayout.putConstraint(SpringLayout.NORTH, lblStreet, 50, SpringLayout.SOUTH, lblEmail);
		springLayout.putConstraint(SpringLayout.EAST, lblStreet, 0, SpringLayout.EAST, lblFirstName);
		add(lblStreet);
		
		JLabel lblTown = new JLabel("Town :");
		springLayout.putConstraint(SpringLayout.NORTH, lblTown, 20, SpringLayout.SOUTH, lblStreet);
		springLayout.putConstraint(SpringLayout.EAST, lblTown, 0, SpringLayout.EAST, lblFirstName);
		add(lblTown);
		
		JLabel lblZipCode = new JLabel("Zip code :");
		springLayout.putConstraint(SpringLayout.NORTH, lblZipCode, 20, SpringLayout.SOUTH, lblTown);
		springLayout.putConstraint(SpringLayout.EAST, lblZipCode, 0, SpringLayout.EAST, lblFirstName);
		add(lblZipCode);
		
		JLabel lblCountry = new JLabel("Country :");
		springLayout.putConstraint(SpringLayout.NORTH, lblCountry, 20, SpringLayout.SOUTH, lblZipCode);
		springLayout.putConstraint(SpringLayout.EAST, lblCountry, 0, SpringLayout.EAST, lblFirstName);
		add(lblCountry);
		
		txtFirstName = new JTextField();
		txtFirstName.setText(frame.getFacade().getUser().getFirstName());
		springLayout.putConstraint(SpringLayout.NORTH, txtFirstName, 0, SpringLayout.NORTH, lblFirstName);
		springLayout.putConstraint(SpringLayout.WEST, txtFirstName, 0, SpringLayout.EAST, lblProfile);
		add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setText(frame.getFacade().getUser().getLastName());
		springLayout.putConstraint(SpringLayout.NORTH, txtLastName, 0, SpringLayout.NORTH, lblLastName);
		springLayout.putConstraint(SpringLayout.WEST, txtLastName, 0, SpringLayout.WEST, txtFirstName);
		add(txtLastName);
		txtLastName.setColumns(10);
		
		txtPhone = new JTextField();
		txtPhone.setText(frame.getFacade().getUser().getPhone());
		springLayout.putConstraint(SpringLayout.NORTH, txtPhone, 0, SpringLayout.NORTH, lblPhone);
		springLayout.putConstraint(SpringLayout.WEST, txtPhone, 0, SpringLayout.WEST, txtFirstName);
		add(txtPhone);
		txtPhone.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setText(frame.getFacade().getUser().getEmail());
		springLayout.putConstraint(SpringLayout.NORTH, txtEmail, 0, SpringLayout.NORTH, lblEmail);
		springLayout.putConstraint(SpringLayout.WEST, txtEmail, 0, SpringLayout.WEST, txtFirstName);
		add(txtEmail);
		txtEmail.setColumns(10);
		
		txtStreet = new JTextField();
		txtStreet.setText(frame.getFacade().getUser().getStreet());
		springLayout.putConstraint(SpringLayout.NORTH, txtStreet, 0, SpringLayout.NORTH, lblStreet);
		springLayout.putConstraint(SpringLayout.WEST, txtStreet, 0, SpringLayout.WEST, txtFirstName);
		add(txtStreet);
		txtStreet.setColumns(10);
		
		txtTown = new JTextField();
		txtTown.setText(frame.getFacade().getUser().getTown());
		springLayout.putConstraint(SpringLayout.NORTH, txtTown, 0, SpringLayout.NORTH, lblTown);
		springLayout.putConstraint(SpringLayout.WEST, txtTown, 0, SpringLayout.WEST, txtFirstName);
		add(txtTown);
		txtTown.setColumns(10);
		
		txtZipCode = new JTextField();
		txtZipCode.setText(frame.getFacade().getUser().getZipCode());
		springLayout.putConstraint(SpringLayout.NORTH, txtZipCode, 0, SpringLayout.NORTH, lblZipCode);
		springLayout.putConstraint(SpringLayout.WEST, txtZipCode, 0, SpringLayout.WEST, txtFirstName);
		add(txtZipCode);
		txtZipCode.setColumns(10);
		
		txtCountry = new JTextField();
		txtCountry.setText(frame.getFacade().getUser().getCountry());
		springLayout.putConstraint(SpringLayout.NORTH, txtCountry, 0, SpringLayout.NORTH, lblCountry);
		springLayout.putConstraint(SpringLayout.WEST, txtCountry, 0, SpringLayout.WEST, txtFirstName);
		add(txtCountry);
		txtCountry.setColumns(10);
		
		if (frame.getFacade().getUser().isSpeaker())
		{
			JLabel lblShortDescription = new JLabel("Short description :");
			springLayout.putConstraint(SpringLayout.NORTH, lblShortDescription, 50, SpringLayout.SOUTH, lblCountry);
			springLayout.putConstraint(SpringLayout.EAST, lblShortDescription, 0, SpringLayout.EAST, lblFirstName);
			add(lblShortDescription);

			JLabel lblDetailedDescription = new JLabel("Detailed description :");
			springLayout.putConstraint(SpringLayout.NORTH, lblDetailedDescription, 20, SpringLayout.SOUTH, lblShortDescription);
			springLayout.putConstraint(SpringLayout.EAST, lblDetailedDescription, 0, SpringLayout.EAST, lblFirstName);
			add(lblDetailedDescription);

			txtShortDescription = new JTextField();
			txtShortDescription.setText(frame.getFacade().getUser().getSpeaker().getShortDescription());
			springLayout.putConstraint(SpringLayout.NORTH, txtShortDescription, 0, SpringLayout.NORTH, lblShortDescription);
			springLayout.putConstraint(SpringLayout.WEST, txtShortDescription, 0, SpringLayout.WEST, txtFirstName);
			add(txtShortDescription);
			txtShortDescription.setColumns(10);

			txtDetailedDescription = new JTextArea();
			springLayout.putConstraint(SpringLayout.SOUTH, txtDetailedDescription, 80, SpringLayout.NORTH, lblDetailedDescription);
			springLayout.putConstraint(SpringLayout.EAST, txtDetailedDescription, 360, SpringLayout.WEST, txtFirstName);
			txtDetailedDescription.setText(frame.getFacade().getUser().getSpeaker().getDetailedDescription());
			springLayout.putConstraint(SpringLayout.NORTH, txtDetailedDescription, 0, SpringLayout.NORTH, lblDetailedDescription);
			springLayout.putConstraint(SpringLayout.WEST, txtDetailedDescription, 0, SpringLayout.WEST, txtFirstName);
			add(txtDetailedDescription);
		}

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.changePanel(new ProfilePanel(frame));
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnCancel, -20, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, 0, SpringLayout.EAST, lblFirstName);
		add(btnCancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				updateProfile(frame);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnSave, 0, SpringLayout.NORTH, btnCancel);
		springLayout.putConstraint(SpringLayout.WEST, btnSave, 0, SpringLayout.WEST, txtFirstName);
		add(btnSave);
	}
	
	private void updateProfile(ConnectedWindow frame)
	{
		HashMap<String, Object> dataUser = new HashMap<String, Object>();
		
		dataUser.put("firstName", txtFirstName.getText());
		dataUser.put("lastName", txtLastName.getText());
		dataUser.put("phone", txtPhone.getText());
		dataUser.put("email", txtEmail.getText());
		dataUser.put("street", txtStreet.getText());
		dataUser.put("town", txtTown.getText());
		dataUser.put("zipCode", txtZipCode.getText());
		dataUser.put("country", txtCountry.getText());
		
		if (frame.getFacade().getUser().isSpeaker())
		{
			dataUser.put("speaker", new Speaker(txtShortDescription.getText(), txtDetailedDescription.getText()));
		}
		
		try
		{
			frame.getFacade().updateTheUserProfile(dataUser);
			frame.changePanel(new ProfilePanel(frame));
			
		}
		catch (FalseFieldsException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
