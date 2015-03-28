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

import com.novar.business.User;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.RegisterFailedException;
import com.novar.persist.UserJdbc;

public class AddUserPanel extends JPanel
{
	private JTextField txtPseudo;
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
	public AddUserPanel(ConnectedWindow frame)
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
		
		txtPseudo = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtPseudo, 0, SpringLayout.NORTH, lblPseudo);
		springLayout.putConstraint(SpringLayout.WEST, txtPseudo, 0, SpringLayout.EAST, lblUser);
		add(txtPseudo);
		txtPseudo.setColumns(10);
		
		txtFirstName = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtFirstName, 0, SpringLayout.NORTH, lblFirstName);
		springLayout.putConstraint(SpringLayout.WEST, txtFirstName, 0, SpringLayout.WEST, txtPseudo);
		add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtLastName = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtLastName, 0, SpringLayout.NORTH, lblLastName);
		springLayout.putConstraint(SpringLayout.WEST, txtLastName, 0, SpringLayout.WEST, txtPseudo);
		add(txtLastName);
		txtLastName.setColumns(10);
		
		txtPhone = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtPhone, 0, SpringLayout.NORTH, lblPhone);
		springLayout.putConstraint(SpringLayout.WEST, txtPhone, 0, SpringLayout.WEST, txtPseudo);
		add(txtPhone);
		txtPhone.setColumns(10);
		
		txtEmail = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtEmail, 0, SpringLayout.NORTH, lblEmail);
		springLayout.putConstraint(SpringLayout.WEST, txtEmail, 0, SpringLayout.WEST, txtPseudo);
		add(txtEmail);
		txtEmail.setColumns(10);
		
		txtStreet = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtStreet, 0, SpringLayout.NORTH, lblStreet);
		springLayout.putConstraint(SpringLayout.WEST, txtStreet, 0, SpringLayout.WEST, txtPseudo);
		add(txtStreet);
		txtStreet.setColumns(10);
		
		txtTown = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtTown, 0, SpringLayout.NORTH, lblTown);
		springLayout.putConstraint(SpringLayout.WEST, txtTown, 0, SpringLayout.WEST, txtPseudo);
		add(txtTown);
		txtTown.setColumns(10);
		
		txtZipCode = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtZipCode, 0, SpringLayout.NORTH, lblZipCode);
		springLayout.putConstraint(SpringLayout.WEST, txtZipCode, 0, SpringLayout.WEST, txtPseudo);
		add(txtZipCode);
		txtZipCode.setColumns(10);
		
		txtCountry = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtCountry, 0, SpringLayout.NORTH, lblCountry);
		springLayout.putConstraint(SpringLayout.WEST, txtCountry, 0, SpringLayout.WEST, txtPseudo);
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
				addUser(frame);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnSave, 0, SpringLayout.NORTH, btnCancel);
		springLayout.putConstraint(SpringLayout.WEST, btnSave, 0, SpringLayout.WEST, txtFirstName);
		add(btnSave);
	}
	
	private void addUser(ConnectedWindow frame)
	{
		HashMap<String, Object> dataUser = new HashMap<String, Object>();
		User user = null;
		
		dataUser.put("pseudo", txtPseudo.getText());
		dataUser.put("password", "000000");
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
			user = new UserJdbc(dataUser);
			frame.getFacade().getUsersFacade().add(user);
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
		catch (RegisterFailedException e)
		{
			e.printStackTrace();
		}
	}
}
