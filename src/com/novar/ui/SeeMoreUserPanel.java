package com.novar.ui;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import com.novar.business.User;

public class SeeMoreUserPanel extends JPanel
{

	/**
	 * Create the panel.
	 */
	public SeeMoreUserPanel(ConnectedWindow frame, User user)
	{
		setBounds(new Rectangle(0, 0, 980, 800));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		springLayout.putConstraint(SpringLayout.NORTH, lblUser, 20, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblUser, 0, SpringLayout.HORIZONTAL_CENTER, this);
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblUser);
		
		JLabel lblPseudo = new JLabel("Pseudo :");
		springLayout.putConstraint(SpringLayout.NORTH, lblPseudo, 75, SpringLayout.NORTH, lblUser);
		springLayout.putConstraint(SpringLayout.EAST, lblPseudo, 0, SpringLayout.WEST, lblUser);
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
		springLayout.putConstraint(SpringLayout.NORTH, lblStreet, 50, SpringLayout.SOUTH, lblEmail);
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
		lblPseudoDynamic.setText(user.getPseudo());
		springLayout.putConstraint(SpringLayout.NORTH, lblPseudoDynamic, 0, SpringLayout.NORTH, lblPseudo);
		springLayout.putConstraint(SpringLayout.WEST, lblPseudoDynamic, 0, SpringLayout.EAST, lblUser);
		add(lblPseudoDynamic);
		
		JLabel lblFirstNameDynamic = new JLabel("");
		lblFirstNameDynamic.setText(user.getFirstName());
		springLayout.putConstraint(SpringLayout.NORTH, lblFirstNameDynamic, 0, SpringLayout.NORTH, lblFirstName);
		springLayout.putConstraint(SpringLayout.WEST, lblFirstNameDynamic, 0, SpringLayout.WEST, lblPseudoDynamic);
		add(lblFirstNameDynamic);
		
		JLabel lblLastNameDynamic = new JLabel("");
		lblLastNameDynamic.setText(user.getLastName());
		springLayout.putConstraint(SpringLayout.NORTH, lblLastNameDynamic, 0, SpringLayout.NORTH, lblLastName);
		springLayout.putConstraint(SpringLayout.WEST, lblLastNameDynamic, 0, SpringLayout.WEST, lblPseudoDynamic);
		add(lblLastNameDynamic);
		
		JLabel lblPhoneDynamic = new JLabel("");
		lblPhoneDynamic.setText(user.getPhone());
		springLayout.putConstraint(SpringLayout.NORTH, lblPhoneDynamic, 0, SpringLayout.NORTH, lblPhone);
		springLayout.putConstraint(SpringLayout.WEST, lblPhoneDynamic, 0, SpringLayout.WEST, lblPseudoDynamic);
		add(lblPhoneDynamic);
		
		JLabel lblEmailDynamic = new JLabel("");
		lblEmailDynamic.setText(user.getEmail());
		springLayout.putConstraint(SpringLayout.NORTH, lblEmailDynamic, 0, SpringLayout.NORTH, lblEmail);
		springLayout.putConstraint(SpringLayout.WEST, lblEmailDynamic, 0, SpringLayout.WEST, lblPseudoDynamic);
		add(lblEmailDynamic);
		
		JLabel lblStreetDynamic = new JLabel("");
		lblStreetDynamic.setText(user.getStreet());
		springLayout.putConstraint(SpringLayout.NORTH, lblStreetDynamic, 0, SpringLayout.NORTH, lblStreet);
		springLayout.putConstraint(SpringLayout.WEST, lblStreetDynamic, 0, SpringLayout.WEST, lblPseudoDynamic);
		add(lblStreetDynamic);
		
		JLabel lblTownDynamic = new JLabel("");
		lblTownDynamic.setText(user.getTown());
		springLayout.putConstraint(SpringLayout.NORTH, lblTownDynamic, 0, SpringLayout.NORTH, lblTown);
		springLayout.putConstraint(SpringLayout.WEST, lblTownDynamic, 0, SpringLayout.WEST, lblPseudoDynamic);
		add(lblTownDynamic);
		
		JLabel lblZipCodeDynamic = new JLabel("");
		lblZipCodeDynamic.setText(user.getZipCode());
		springLayout.putConstraint(SpringLayout.NORTH, lblZipCodeDynamic, 0, SpringLayout.NORTH, lblZipCode);
		springLayout.putConstraint(SpringLayout.WEST, lblZipCodeDynamic, 0, SpringLayout.WEST, lblPseudoDynamic);
		add(lblZipCodeDynamic);
		
		JLabel lblCountryDynamic = new JLabel("");
		lblCountryDynamic.setText(user.getCountry());
		springLayout.putConstraint(SpringLayout.NORTH, lblCountryDynamic, 0, SpringLayout.NORTH, lblCountry);
		springLayout.putConstraint(SpringLayout.WEST, lblCountryDynamic, 0, SpringLayout.WEST, lblPseudoDynamic);
		add(lblCountryDynamic);
	
	JButton btnCancel = new JButton("Cancel");
	btnCancel.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			frame.changePanel(new UsersPanel(frame));
		}
	});
	springLayout.putConstraint(SpringLayout.SOUTH, btnCancel, -20, SpringLayout.SOUTH, this);
	springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnCancel, 0, SpringLayout.HORIZONTAL_CENTER, this);
	add(btnCancel);
	}
}
