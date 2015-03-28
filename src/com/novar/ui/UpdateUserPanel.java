package com.novar.ui;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import com.novar.business.Speaker;
import com.novar.business.User;
import com.novar.exception.FalseFieldsException;

public class UpdateUserPanel extends JPanel
{
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
	public UpdateUserPanel(ConnectedWindow frame, User user)
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
		
		JLabel lblFirstName = new JLabel("First name :");
		springLayout.putConstraint(SpringLayout.NORTH, lblFirstName, 75, SpringLayout.NORTH, lblUser);
		springLayout.putConstraint(SpringLayout.EAST, lblFirstName, 0, SpringLayout.WEST, lblUser);
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
		txtFirstName.setText(user.getFirstName());
		springLayout.putConstraint(SpringLayout.NORTH, txtFirstName, 0, SpringLayout.NORTH, lblFirstName);
		springLayout.putConstraint(SpringLayout.WEST, txtFirstName, 0, SpringLayout.EAST, lblUser);
		add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setText(user.getLastName());
		springLayout.putConstraint(SpringLayout.NORTH, txtLastName, 0, SpringLayout.NORTH, lblLastName);
		springLayout.putConstraint(SpringLayout.WEST, txtLastName, 0, SpringLayout.WEST, txtFirstName);
		add(txtLastName);
		txtLastName.setColumns(10);
		
		txtPhone = new JTextField();
		txtPhone.setText(user.getPhone());
		springLayout.putConstraint(SpringLayout.NORTH, txtPhone, 0, SpringLayout.NORTH, lblPhone);
		springLayout.putConstraint(SpringLayout.WEST, txtPhone, 0, SpringLayout.WEST, txtFirstName);
		add(txtPhone);
		txtPhone.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setText(user.getEmail());
		springLayout.putConstraint(SpringLayout.NORTH, txtEmail, 0, SpringLayout.NORTH, lblEmail);
		springLayout.putConstraint(SpringLayout.WEST, txtEmail, 0, SpringLayout.WEST, txtFirstName);
		add(txtEmail);
		txtEmail.setColumns(10);
		
		txtStreet = new JTextField();
		txtStreet.setText(user.getStreet());
		springLayout.putConstraint(SpringLayout.NORTH, txtStreet, 0, SpringLayout.NORTH, lblStreet);
		springLayout.putConstraint(SpringLayout.WEST, txtStreet, 0, SpringLayout.WEST, txtFirstName);
		add(txtStreet);
		txtStreet.setColumns(10);
		
		txtTown = new JTextField();
		txtTown.setText(user.getTown());
		springLayout.putConstraint(SpringLayout.NORTH, txtTown, 0, SpringLayout.NORTH, lblTown);
		springLayout.putConstraint(SpringLayout.WEST, txtTown, 0, SpringLayout.WEST, txtFirstName);
		add(txtTown);
		txtTown.setColumns(10);
		
		txtZipCode = new JTextField();
		txtZipCode.setText(user.getZipCode());
		springLayout.putConstraint(SpringLayout.NORTH, txtZipCode, 0, SpringLayout.NORTH, lblZipCode);
		springLayout.putConstraint(SpringLayout.WEST, txtZipCode, 0, SpringLayout.WEST, txtFirstName);
		add(txtZipCode);
		txtZipCode.setColumns(10);
		
		txtCountry = new JTextField();
		txtCountry.setText(user.getCountry());
		springLayout.putConstraint(SpringLayout.NORTH, txtCountry, 0, SpringLayout.NORTH, lblCountry);
		springLayout.putConstraint(SpringLayout.WEST, txtCountry, 0, SpringLayout.WEST, txtFirstName);
		add(txtCountry);
		txtCountry.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.changePanel(new UsersPanel(frame));
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
				updateUser(frame, user);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnSave, 0, SpringLayout.NORTH, btnCancel);
		springLayout.putConstraint(SpringLayout.WEST, btnSave, 0, SpringLayout.WEST, txtFirstName);
		add(btnSave);
	}
	
	private void updateUser(ConnectedWindow frame, User user)
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
		
		try
		{
			user.setAll(dataUser);
			frame.getFacade().getUsersFacade().update(user);
			frame.changePanel(new UsersPanel(frame));
		}
		catch (FalseFieldsException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
