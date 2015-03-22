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
import java.sql.SQLException;
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


public class ForgottenPassword extends JDialog {

	private JPanel contentPane;
	private JTextField emailTextField;
	private JPasswordField passwordField;
	private JPasswordField confirmField;
	private FacadeMain facade;
	private Border defaultBorder;
	private JLabel lblErrorPass;
	private JLabel lblErrorConfirm;
	private JLabel lblErrorEmail;

	/**
	 * Create the frame.
	 */
	public ForgottenPassword(JFrame frame, FacadeMain facade) {
		super(frame, "ForgottenPassword", Dialog.ModalityType.DOCUMENT_MODAL);
		setResizable(false);
		this.facade = facade;
		Toolkit tk = Toolkit.getDefaultToolkit(); 
		Dimension d = tk.getScreenSize();
		int hauteurEcran = d.height;
		int largeurEcran = d.width; 
		setBounds(largeurEcran/4, hauteurEcran/4,582, 197);
		setAlwaysOnTop(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Calibri", Font.PLAIN, 11));
		JLabel lblConfirmpassword = new JLabel("Confirm password:");
		lblConfirmpassword.setFont(new Font("Calibri", Font.PLAIN, 11));
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorEmail = new JLabel("Invalid email.");
		lblErrorEmail.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorEmail.setVisible(false);
		lblErrorPass = new JLabel("The password must contains 6 to 50 letters or numbers.");
		lblErrorPass.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorPass.setVisible(false);
		lblErrorConfirm = new JLabel("The two passwords must be equals.");
		lblErrorConfirm.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorConfirm.setVisible(false);
		
		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		
		passwordField = new JPasswordField();
		
		confirmField = new JPasswordField();
		
		JButton btnOk = new JButton("OK");
		btnOk.setFont(new Font("Calibri", Font.PLAIN, 11));
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					forgottenPassword();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		
		
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(22)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblConfirmpassword)
									.addComponent(lblPassword)
									.addComponent(lblEmail))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(confirmField)
									.addComponent(passwordField)
									.addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblErrorPass)
									.addComponent(lblErrorEmail)
									.addComponent(lblErrorConfirm)))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(83)
								.addComponent(btnCancel)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnOk)))
						.addContainerGap(26, Short.MAX_VALUE))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(20)
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
							.addComponent(confirmField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblErrorConfirm))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnCancel)
							.addComponent(btnOk))
						.addContainerGap(187, Short.MAX_VALUE))
			);
			contentPane.setLayout(gl_contentPane);
		}
		
	private void closeWindow(){
		this.dispose();
	}
	
	private void forgottenPassword() throws SQLException{
		if(Arrays.equals(passwordField.getPassword(), confirmField.getPassword()))
		{
			passwordField.setBorder(defaultBorder);
			confirmField.setBorder(defaultBorder);
			emailTextField.setBorder(defaultBorder);
			lblErrorPass.setVisible(false);
			lblErrorConfirm.setVisible(false);
			lblErrorEmail.setVisible(false);
			
			HashMap<String,Object> mapUser = new HashMap<String,Object>();
			mapUser.put("password", new String(passwordField.getPassword()));
			mapUser.put("email", emailTextField.getText());
			
			try 
			{
				facade.forgottenPassword(mapUser);
				closeWindow();
			} 
			catch (FalseFieldsException e2) 
			{
				ArrayList<String> errors = e2.getFalseFields();
				for(int i = 0 ; i <errors.size() ; i++)
				{
					switch(errors.get(i))
					{
						case "password" : passwordField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
										  passwordField.setText("");
										  lblErrorPass.setVisible(true);
						break;
						case "confirm" : confirmField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
										 confirmField.setText("");
										 confirmField.setVisible(true);
						break;
						case "email" : emailTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
									   emailTextField.setText("");
									   lblErrorEmail.setVisible(true);
	
					}
				}
			}
			catch (RegisterFailedException e1) 
			{
				switch(e1.getMessage())
				{
				
				}
			}			
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
