package com.novar.ui;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
import com.novar.exception.RegisterFailedException;

import java.awt.Font;


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
	private JLabel lblErrorPseudo;
	private JLabel lblErrorPseudo2;
	private JLabel lblErrorFirst;
	private JLabel lblErrorLast;
	private JLabel lblErrorPass;
	private JLabel lblErrorConfirm;
	private JLabel lblErrorEmail;
	private JLabel lblErrorEmail2;
	private JLabel lblErrorTel;
	private JLabel lblErrorStreet;
	private JLabel lblErrorCity;
	private JLabel lblErrorZip;
	private JLabel lblErrorCountry;

	/**
	 * Create the frame.
	 */
	public RegisterWindow(JFrame frame, FacadeMain facade) {
		super(frame, "Register", Dialog.ModalityType.DOCUMENT_MODAL);
		this.facade = facade;
		setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit(); 
		Dimension d = tk.getScreenSize();
		int hauteurEcran = d.height;
		int largeurEcran = d.width; 
		setBounds(largeurEcran/4, hauteurEcran/4,600, 504);
		setAlwaysOnTop(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblPseudo = new JLabel("Pseudo:");	
		lblPseudo.setFont(new Font("Calibri", Font.PLAIN, 11));
		JLabel lblFisrtname = new JLabel("FisrtName:");
		lblFisrtname.setFont(new Font("Calibri", Font.PLAIN, 11));
		JLabel lblLastname = new JLabel("LastName:");
		lblLastname.setFont(new Font("Calibri", Font.PLAIN, 11));
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Calibri", Font.PLAIN, 11));
		JLabel lblConfirmpassword = new JLabel("Confirm password:");
		lblConfirmpassword.setFont(new Font("Calibri", Font.PLAIN, 11));
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 11));
		JLabel lblTel = new JLabel("Tel:");
		lblTel.setFont(new Font("Calibri", Font.PLAIN, 11));
		JLabel lblStreet = new JLabel("Street:");
		lblStreet.setFont(new Font("Calibri", Font.PLAIN, 11));
		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Calibri", Font.PLAIN, 11));
		JLabel lblZipcode = new JLabel("ZipCode:");
		lblZipcode.setFont(new Font("Calibri", Font.PLAIN, 11));
		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorPseudo = new JLabel("The pseudo field must contains 6 to 32 letters or numbers.");
		lblErrorPseudo.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorPseudo.setVisible(false);
		lblErrorPseudo2 = new JLabel("This pseudo is already taken.");
		lblErrorPseudo2.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorPseudo2.setVisible(false);
		lblErrorFirst = new JLabel("The pseudo field must contains 1 to 50 letters.");
		lblErrorFirst.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorFirst.setVisible(false);
		lblErrorLast = new JLabel("The pseudo field must contains 1 to 50 letters.");
		lblErrorLast.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorLast.setVisible(false);
		lblErrorEmail = new JLabel("Invalid email.");
		lblErrorEmail.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorEmail.setVisible(false);
		lblErrorEmail2 = new JLabel("This email is already taken..");
		lblErrorEmail2.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorEmail2.setVisible(false);
		lblErrorPass = new JLabel("The password must contains 6 to 50 letters or numbers.");
		lblErrorPass.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorPass.setVisible(false);
		lblErrorConfirm = new JLabel("The two passwords must be equals.");
		lblErrorConfirm.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorConfirm.setVisible(false);
		lblErrorTel = new JLabel("Invalid phone number.");
		lblErrorTel.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorTel.setVisible(false);
		lblErrorStreet = new JLabel("The street field must contains 1 to 50 letters or numbers.");
		lblErrorStreet.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorStreet.setVisible(false);
		lblErrorCity = new JLabel("The city field must contains 1 to 50 letters.");
		lblErrorCity.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorCity.setVisible(false);
		lblErrorZip = new JLabel("The zipCode field must contains 5 numbers.");
		lblErrorZip.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorZip.setVisible(false);
		lblErrorCountry = new JLabel("The country field must contains 2 to 50 letters.");
		lblErrorCountry.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorCountry.setVisible(false);
		
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
		btnRegister.setFont(new Font("Calibri", Font.PLAIN, 11));
		
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		
		/*KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher(){
			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					register();
				return false;
			}
		});*/
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(53)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
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
								.addComponent(passwordField)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnCancel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnRegister)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblErrorCountry)
						.addComponent(lblErrorZip)
						.addComponent(lblErrorCity)
						.addComponent(lblErrorStreet)
						.addComponent(lblErrorTel)
						.addComponent(lblErrorPass)
						.addComponent(lblErrorEmail)
						.addComponent(lblErrorEmail2)
						.addComponent(lblErrorLast)
						.addComponent(lblErrorFirst)
						.addComponent(lblErrorPseudo)
						.addComponent(lblErrorPseudo2)
						.addComponent(lblErrorConfirm))
					.addContainerGap(76, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPseudo)
						.addComponent(pseudoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblErrorPseudo)
						.addComponent(lblErrorPseudo2))
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
						.addComponent(lblErrorEmail)
						.addComponent(lblErrorEmail2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblErrorPass))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConfirmpassword)
						.addComponent(confirmField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblErrorConfirm))
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
					.addContainerGap(118, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void closeWindow(){
		this.dispose();
	}
	
	private void register(){
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
			lblErrorPseudo.setVisible(false);
			lblErrorPseudo2.setVisible(false);
			lblErrorFirst.setVisible(false);
			lblErrorLast.setVisible(false);
			lblErrorPass.setVisible(false);
			lblErrorConfirm.setVisible(false);
			lblErrorEmail.setVisible(false);
			lblErrorEmail2.setVisible(false);
			lblErrorTel.setVisible(false);
			lblErrorStreet.setVisible(false);
			lblErrorCity.setVisible(false);
			lblErrorZip.setVisible(false);
			lblErrorCountry.setVisible(false);
			
			HashMap<String,Object> mapUser = new HashMap<String,Object>();
			mapUser.put("pseudo", pseudoTextField.getText());
			mapUser.put("password", new String(passwordField.getPassword()));
			mapUser.put("lastName", lastTextField.getText());
			mapUser.put("firstName", firstTextField.getText());
			mapUser.put("phone", telTextField.getText());
			mapUser.put("email", emailTextField.getText());
			mapUser.put("street", streetTextField.getText());
			mapUser.put("town", cityTextField.getText());
			mapUser.put("zipCode", zipTextField.getText());
			mapUser.put("country", countryTextField.getText());
			
			try 
			{
				facade.register(mapUser);
				closeWindow();
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
										lblErrorPseudo.setVisible(true);
						break;
						case "firstName" : firstTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
										   firstTextField.setText("");
										   lblErrorFirst.setVisible(true);
						break;
						case "lastName" : lastTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
										  lastTextField.setText("");
										  lblErrorLast.setVisible(true);
						break;
						case "password" : passwordField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
										  passwordField.setText("");
										  lblErrorPass.setVisible(true);
						break;
						case "confirm" : confirmField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
										 confirmField.setText("");
						break;
						case "email" : emailTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
									   emailTextField.setText("");
									   lblErrorEmail.setVisible(true);
						break;
						case "phone" : telTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
									   telTextField.setText("");
									   lblErrorTel.setVisible(true);
						break;
						case "street" : streetTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
										streetTextField.setText("");
										lblErrorStreet.setVisible(true);
						break;
						case "town" : cityTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
									  cityTextField.setText("");
									  lblErrorCity.setVisible(true);
						break;
						case "zipCode" : zipTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
										 zipTextField.setText("");
										 lblErrorZip.setVisible(true);
						break;
						case "country" : countryTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
										 countryTextField.setText("");
										 lblErrorCountry.setVisible(true);
						break;
	
					}
				}
			}
			catch (RegisterFailedException e1) 
			{
				switch(e1.getMessage())
				{
					case "pseudo2" : pseudoTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
									pseudoTextField.setText("");
									lblErrorPseudo2.setVisible(true);
					break;
					case "email2" : emailTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
									emailTextField.setText("");
									lblErrorEmail2.setVisible(true);
					break;
				}
			}
			
			
			//if(Pas d'erreur)
			//	closeWindow();
		}
		else{
			passwordField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			passwordField.setText("");
			confirmField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			confirmField.setText("");
			lblErrorConfirm.setVisible(true);
		}
	}
}
