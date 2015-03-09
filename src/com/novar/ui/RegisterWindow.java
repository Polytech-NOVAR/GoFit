package com.novar.ui;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.novar.business.FacadeMain;
import com.novar.exception.FalseFieldsException;


public class RegisterWindow extends JDialog {

	private JPanel contentPane;
	private JTextField pseudoTextField;
	private JTextField firstTextField;
	private JTextField lastTextField;
	private JTextField emailTextField;
	private JPasswordField passwordField;
	private JPasswordField confirmField;
	private JTextField telTextField;
	private JTextField streetTextField;
	private JTextField cityTextField;
	private JTextField zipTextField;
	private JTextField countryTextField;
	private FacadeMain facade;
	private Border defaultBorder;

	/**
	 * Create the frame.
	 */
	public RegisterWindow(JFrame frame, FacadeMain facade) {
		super(frame, "Register", Dialog.ModalityType.DOCUMENT_MODAL);
		this.facade = facade;
		setResizable(false);
		setBounds(100, 100, 550, 504);
		setAlwaysOnTop(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblPseudo = new JLabel("Pseudo:");	
		JLabel lblFisrtname = new JLabel("FisrtName:");
		JLabel lblLastname = new JLabel("LastName:");
		JLabel lblEmail = new JLabel("Email:");
		JLabel lblConfirmpassword = new JLabel("Confirm password:");
		JLabel lblPassword = new JLabel("Password:");
		JLabel lblTel = new JLabel("Tel:");
		JLabel lblStreet = new JLabel("Street:");
		JLabel lblCity = new JLabel("City:");
		JLabel lblZipcode = new JLabel("ZipCode:");
		JLabel lblCountry = new JLabel("Country:");
		JLabel lblErrorPseudo = new JLabel("");
		JLabel lblErrorFirst = new JLabel("");
		JLabel lblErrorLast = new JLabel("");
		JLabel lblErrorEmail = new JLabel("");	
		JLabel lblErrorPass = new JLabel("");
		JLabel lblErrorTel = new JLabel("");
		JLabel lblErrorStreet = new JLabel("");
		JLabel lblErrorCity = new JLabel("");
		JLabel lblErrorZip = new JLabel("");
		JLabel lblErrorCountry = new JLabel("");
		
		pseudoTextField = new JTextField();
		pseudoTextField.setColumns(10);
		
		firstTextField = new JTextField();
		firstTextField.setColumns(10);
		
		lastTextField = new JTextField();
		lastTextField.setColumns(10);
		
		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		
		passwordField = new JPasswordField();
		
		confirmField = new JPasswordField();
		
		telTextField = new JTextField();
		telTextField.setColumns(10);
		
		streetTextField = new JTextField();
		streetTextField.setColumns(10);
		
		cityTextField = new JTextField();
		cityTextField.setColumns(10);
		
		zipTextField = new JTextField();
		zipTextField.setColumns(10);
		
		countryTextField = new JTextField();
		countryTextField.setColumns(10);
		
		defaultBorder = countryTextField.getBorder();
		
		JButton btnRegister = new JButton("Register");
		
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Arrays.equals(passwordField.getPassword(), confirmField.getPassword()))
				{
					pseudoTextField.setBorder(defaultBorder);
					firstTextField.setBorder(defaultBorder);
					lastTextField.setBorder(defaultBorder);
					passwordField.setBorder(defaultBorder);
					confirmField.setBorder(defaultBorder);
					emailTextField.setBorder(defaultBorder);
					telTextField.setBorder(defaultBorder);
					streetTextField.setBorder(defaultBorder);
					cityTextField.setBorder(defaultBorder);
					zipTextField.setBorder(defaultBorder);
					countryTextField.setBorder(defaultBorder);
					
					HashMap<String,Object> mapUser = new HashMap<String,Object>();
					mapUser.put("pseudo", pseudoTextField.getText());
					mapUser.put("password", new String(passwordField.getPassword()));
					mapUser.put("lastName", lastTextField.getText());
					mapUser.put("firstName", firstTextField.getText());
					mapUser.put("phone", telTextField.getText());
					mapUser.put("email", emailTextField.getText());
					
					HashMap<String,Object> mapAddress = new HashMap<String,Object>();
					mapAddress.put("street", streetTextField.getText());
					mapAddress.put("town", cityTextField.getText());
					mapAddress.put("zipCode", zipTextField.getText());
					mapAddress.put("country", countryTextField.getText());
					
					try 
					{
						facade.register(mapUser, mapAddress);
					} 
					catch (FalseFieldsException e2) 
					{
						ArrayList<String> errors = e2.getFalseFields();
						
						for(int i = 0 ; i <errors.size() ; i++)
						{
							switch(errors.get(i))
							{
								case "pseudo" : pseudoTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
												pseudoTextField.setText("");
								break;
								case "fisrtName" : firstTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
												   firstTextField.setText("");
								break;
								case "lastName" : lastTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
												  lastTextField.setText("");
								break;
								case "password" : passwordField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
												  passwordField.setText("");
								break;
								case "confirm" : confirmField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
												 confirmField.setText("");
								break;
								case "email" : emailTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
											   emailTextField.setText("");
								break;
								case "phone" : telTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
											   telTextField.setText("");
								break;
								case "street" : streetTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
												streetTextField.setText("");
								break;
								case "town" : cityTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
											  cityTextField.setText("");
								break;
								case "zipCode" : zipTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
												 zipTextField.setText("");
								break;
								case "country" : countryTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
												 countryTextField.setText("");
								break;
			
							}
						}
					}
					catch (Exception e1) 
					{
						System.out.println(e1.getMessage());
					}
					
					
					//if(Pas d'erreur)
					//	closeWindow();
				}
				else{
					lblErrorPass.setText("The 2 passwords are not equals.");
					passwordField.setBackground(Color.RED);
					confirmField.setBackground(Color.RED);
				}
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(88)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblCountry)
								.addComponent(lblZipcode)
								.addComponent(lblCity)
								.addComponent(lblStreet)
								.addComponent(lblTel)
								.addComponent(lblConfirmpassword)
								.addComponent(lblPassword)
								.addComponent(lblEmail)
								.addComponent(lblLastname)
								.addComponent(lblFisrtname)
								.addComponent(lblPseudo))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(pseudoTextField)
								.addComponent(firstTextField)
								.addComponent(lastTextField)
								.addComponent(emailTextField)
								.addComponent(telTextField)
								.addComponent(streetTextField)
								.addComponent(cityTextField)
								.addComponent(zipTextField)
								.addComponent(countryTextField)
								.addComponent(confirmField)
								.addComponent(passwordField))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnCancel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnRegister)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblErrorCountry)
						.addComponent(lblErrorZip)
						.addComponent(lblErrorCity)
						.addComponent(lblErrorStreet)
						.addComponent(lblErrorTel)
						.addComponent(lblErrorPass)
						.addComponent(lblErrorEmail)
						.addComponent(lblErrorLast)
						.addComponent(lblErrorFirst)
						.addComponent(lblErrorPseudo))
					.addGap(166))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPseudo)
						.addComponent(pseudoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblErrorPseudo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFisrtname)
						.addComponent(firstTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblErrorFirst))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLastname)
						.addComponent(lastTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblErrorLast))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblErrorEmail))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblErrorPass))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConfirmpassword)
						.addComponent(confirmField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTel)
						.addComponent(telTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblErrorTel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStreet)
						.addComponent(streetTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblErrorStreet))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCity)
						.addComponent(cityTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblErrorCity))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblZipcode)
						.addComponent(zipTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblErrorZip))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCountry)
						.addComponent(countryTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblErrorCountry))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnRegister))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void closeWindow(){
		this.dispose();
	}

}
