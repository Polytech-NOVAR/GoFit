package com.novar.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import com.novar.business.Speaker;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.LoginFailedException;

public class UpdatePasswordPanel extends JPanel
{
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmPassword;
	private JLabel lblError;
	
	/**
	 * Create the panel.
	 * @param frame 
	 */
	public UpdatePasswordPanel(ConnectedWindow frame)
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
		
		JLabel lblPassword = new JLabel("Confirm password :");
		springLayout.putConstraint(SpringLayout.NORTH, lblPassword, 75, SpringLayout.NORTH, lblProfile);
		springLayout.putConstraint(SpringLayout.EAST, lblPassword, 0, SpringLayout.WEST, lblProfile);
		add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Password :");
		springLayout.putConstraint(SpringLayout.NORTH, lblConfirmPassword, 20, SpringLayout.SOUTH, lblPassword);
		springLayout.putConstraint(SpringLayout.EAST, lblConfirmPassword, 0, SpringLayout.EAST, lblPassword);
		add(lblConfirmPassword);
		
		txtPassword = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, txtPassword, 0, SpringLayout.NORTH, lblPassword);
		springLayout.putConstraint(SpringLayout.WEST, txtPassword, 0, SpringLayout.EAST, lblProfile);
		add(txtPassword);
		txtPassword.setColumns(10);
		
		txtConfirmPassword = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, txtConfirmPassword, 0, SpringLayout.NORTH, lblConfirmPassword);
		springLayout.putConstraint(SpringLayout.WEST, txtConfirmPassword, 0, SpringLayout.WEST, txtPassword);
		add(txtConfirmPassword);
		txtConfirmPassword.setColumns(10);
		
		lblError = new JLabel("The two passwords must be equals.");
		lblError.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblError.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, lblError, 40, SpringLayout.NORTH, lblConfirmPassword);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblError, 0, SpringLayout.HORIZONTAL_CENTER, this);
		add(lblError);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.changePanel(new ProfilePanel(frame));
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnCancel, -20, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, 0, SpringLayout.EAST, lblPassword);
		add(btnCancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				updatePassword(frame);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnSave, 0, SpringLayout.NORTH, btnCancel);
		springLayout.putConstraint(SpringLayout.WEST, btnSave, 0, SpringLayout.WEST, txtPassword);
		add(btnSave);
	}
	
	private void updatePassword(ConnectedWindow frame)
	{
		lblError.setVisible(false);
		
		if(Arrays.equals(txtPassword.getPassword(), txtConfirmPassword.getPassword()))
		{
			HashMap<String, Object> dataUser = new HashMap<String, Object>();

			dataUser.put("password", new String(txtPassword.getPassword()));

			try
			{
				frame.getFacade().updateTheUserPassword(dataUser);
				frame.changePanel(new ProfilePanel(frame));
			}
			catch (FalseFieldsException | SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			txtPassword.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			txtPassword.setText("");
			txtConfirmPassword.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			txtConfirmPassword.setText("");
			lblError.setVisible(true);
		}
	}
}
