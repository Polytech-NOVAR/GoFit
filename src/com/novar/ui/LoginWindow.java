package com.novar.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import com.novar.business.LoginBL;
import com.novar.persistence.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Canvas;

public class LoginWindow{

	private JFrame frame;
	private JTextField pseudoTextField;
	private JPasswordField passwordField;
	private LoginBL business;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		business = new LoginBL();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblError = new JLabel("");
		
		JLabel lblPseudo = new JLabel("Pseudo:");
		
		JLabel lblPassword = new JLabel("Password:");
		
		pseudoTextField = new JTextField();
		pseudoTextField.setColumns(10);
		
		passwordField = new JPasswordField();
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object connectedUser = business.login(pseudoTextField.getText(), new String(passwordField.getPassword()));
				if(connectedUser instanceof User)
				{
					ConnectedWindow connected  = new ConnectedWindow();
					connected.setVisible(true);
					closeWindow();
				}
				else
				{
					lblError.setForeground(Color.RED);
					lblError.setText((String) connectedUser);
				}
				//System.out.println(business.login(pseudoTextField.getText(), new String(passwordField.getPassword())));
			}
		});
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterWindow register = new RegisterWindow(frame);
				register.setVisible(true);
			}
		});
		
		JLabel logo = new JLabel();
	    
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(134)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblPassword)
								.addComponent(lblPseudo))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnRegister)
								.addComponent(btnLogin)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(passwordField)
									.addComponent(pseudoTextField)
									.addComponent(lblError))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(37)
							.addComponent(logo)))
					.addContainerGap(170, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addComponent(logo)
					.addGap(24)
					.addComponent(lblError)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPseudo)
						.addComponent(pseudoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLogin)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRegister)
					.addContainerGap(86, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}

	private void closeWindow(){
		frame.dispose();
	}
}
