package com.novar.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Rectangle;
import java.awt.Component;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProfilePanel extends JPanel
{	
	/**
	 * Create the panel.
	 */
	public ProfilePanel(ConnectedWindow frame)
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
		
		JLabel lblPseudo = new JLabel("Pseudo :");
		springLayout.putConstraint(SpringLayout.NORTH, lblPseudo, 100, SpringLayout.NORTH, lblProfile);
		springLayout.putConstraint(SpringLayout.EAST, lblPseudo, 0, SpringLayout.WEST, lblProfile);
		add(lblPseudo);
		
		JLabel lblFirstName = new JLabel("First name :");
		springLayout.putConstraint(SpringLayout.NORTH, lblFirstName, 20, SpringLayout.SOUTH, lblPseudo);
		springLayout.putConstraint(SpringLayout.EAST, lblFirstName, 0, SpringLayout.EAST, lblPseudo);
		add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last name :");
		springLayout.putConstraint(SpringLayout.NORTH, lblLastName, 20, SpringLayout.SOUTH, lblFirstName);
		springLayout.putConstraint(SpringLayout.EAST, lblLastName, 0, SpringLayout.EAST, lblPseudo);
		add(lblLastName);
		
		JLabel lblPhone = new JLabel("Phone :");
		springLayout.putConstraint(SpringLayout.NORTH, lblPhone, 20, SpringLayout.SOUTH, lblLastName);
		springLayout.putConstraint(SpringLayout.EAST, lblPhone, 0, SpringLayout.EAST, lblPseudo);
		add(lblPhone);
		
		JLabel lblEmail = new JLabel("Email :");
		springLayout.putConstraint(SpringLayout.NORTH, lblEmail, 20, SpringLayout.SOUTH, lblPhone);
		springLayout.putConstraint(SpringLayout.EAST, lblEmail, 0, SpringLayout.EAST, lblPseudo);
		add(lblEmail);
		
		JLabel lblStreet = new JLabel("Street :");
		springLayout.putConstraint(SpringLayout.NORTH, lblStreet, 100, SpringLayout.SOUTH, lblEmail);
		springLayout.putConstraint(SpringLayout.EAST, lblStreet, 0, SpringLayout.EAST, lblPseudo);
		add(lblStreet);
		
		JLabel lblTown = new JLabel("Town :");
		springLayout.putConstraint(SpringLayout.NORTH, lblTown, 20, SpringLayout.SOUTH, lblStreet);
		springLayout.putConstraint(SpringLayout.EAST, lblTown, 0, SpringLayout.EAST, lblPseudo);
		add(lblTown);
		
		JLabel lblZipCode = new JLabel("Zip code :");
		springLayout.putConstraint(SpringLayout.NORTH, lblZipCode, 20, SpringLayout.SOUTH, lblTown);
		springLayout.putConstraint(SpringLayout.EAST, lblZipCode, 0, SpringLayout.EAST, lblPseudo);
		add(lblZipCode);
		
		JLabel lblCountry = new JLabel("Country :");
		springLayout.putConstraint(SpringLayout.NORTH, lblCountry, 20, SpringLayout.SOUTH, lblZipCode);
		springLayout.putConstraint(SpringLayout.EAST, lblCountry, 0, SpringLayout.EAST, lblPseudo);
		add(lblCountry);
		
		JLabel lblPseudoDynamic = new JLabel("");
		lblPseudoDynamic.setText(frame.getFacade().getUser().getPseudo());
		springLayout.putConstraint(SpringLayout.NORTH, lblPseudoDynamic, 0, SpringLayout.NORTH, lblPseudo);
		springLayout.putConstraint(SpringLayout.WEST, lblPseudoDynamic, 0, SpringLayout.EAST, lblProfile);
		add(lblPseudoDynamic);
		
		JLabel lblFirstNameDynamic = new JLabel("");
		lblFirstNameDynamic.setText(frame.getFacade().getUser().getFirstName());
		springLayout.putConstraint(SpringLayout.NORTH, lblFirstNameDynamic, 0, SpringLayout.NORTH, lblFirstName);
		springLayout.putConstraint(SpringLayout.WEST, lblFirstNameDynamic, 0, SpringLayout.WEST, lblPseudoDynamic);
		add(lblFirstNameDynamic);
		
		JLabel lblLastNameDynamic = new JLabel("");
		lblLastNameDynamic.setText(frame.getFacade().getUser().getLastName());
		springLayout.putConstraint(SpringLayout.NORTH, lblLastNameDynamic, 0, SpringLayout.NORTH, lblLastName);
		springLayout.putConstraint(SpringLayout.WEST, lblLastNameDynamic, 0, SpringLayout.WEST, lblPseudoDynamic);
		add(lblLastNameDynamic);
		
		JLabel lblPhoneDynamic = new JLabel("");
		lblPhoneDynamic.setText(frame.getFacade().getUser().getPhone());
		springLayout.putConstraint(SpringLayout.NORTH, lblPhoneDynamic, 0, SpringLayout.NORTH, lblPhone);
		springLayout.putConstraint(SpringLayout.WEST, lblPhoneDynamic, 0, SpringLayout.WEST, lblPseudoDynamic);
		add(lblPhoneDynamic);
		
		JLabel lblEmailDynamic = new JLabel("");
		lblEmailDynamic.setText(frame.getFacade().getUser().getEmail());
		springLayout.putConstraint(SpringLayout.NORTH, lblEmailDynamic, 0, SpringLayout.NORTH, lblEmail);
		springLayout.putConstraint(SpringLayout.WEST, lblEmailDynamic, 0, SpringLayout.WEST, lblPseudoDynamic);
		add(lblEmailDynamic);
		
		JLabel lblStreetDynamic = new JLabel("");
		lblStreetDynamic.setText(frame.getFacade().getUser().getStreet());
		springLayout.putConstraint(SpringLayout.NORTH, lblStreetDynamic, 0, SpringLayout.NORTH, lblStreet);
		springLayout.putConstraint(SpringLayout.WEST, lblStreetDynamic, 0, SpringLayout.WEST, lblPseudoDynamic);
		add(lblStreetDynamic);
		
		JLabel lblTownDynamic = new JLabel("");
		lblTownDynamic.setText(frame.getFacade().getUser().getTown());
		springLayout.putConstraint(SpringLayout.NORTH, lblTownDynamic, 0, SpringLayout.NORTH, lblTown);
		springLayout.putConstraint(SpringLayout.WEST, lblTownDynamic, 0, SpringLayout.WEST, lblPseudoDynamic);
		add(lblTownDynamic);
		
		JLabel lblZipCodeDynamic = new JLabel("");
		lblZipCodeDynamic.setText(frame.getFacade().getUser().getZipCode());
		springLayout.putConstraint(SpringLayout.NORTH, lblZipCodeDynamic, 0, SpringLayout.NORTH, lblZipCode);
		springLayout.putConstraint(SpringLayout.WEST, lblZipCodeDynamic, 0, SpringLayout.WEST, lblPseudoDynamic);
		add(lblZipCodeDynamic);
		
		JLabel lblCountryDynamic = new JLabel("");
		lblCountryDynamic.setText(frame.getFacade().getUser().getCountry());
		springLayout.putConstraint(SpringLayout.NORTH, lblCountryDynamic, 0, SpringLayout.NORTH, lblCountry);
		springLayout.putConstraint(SpringLayout.WEST, lblCountryDynamic, 0, SpringLayout.WEST, lblPseudoDynamic);
		add(lblCountryDynamic);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.changePanel(new UpdateProfilePanel(frame));
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnUpdate, -20, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnUpdate, 0, SpringLayout.EAST, lblPseudo);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		springLayout.putConstraint(SpringLayout.NORTH, btnDelete, 0, SpringLayout.NORTH, btnUpdate);
		springLayout.putConstraint(SpringLayout.WEST, btnDelete, 0, SpringLayout.WEST, lblPseudoDynamic);
		add(btnDelete);
	}
}
