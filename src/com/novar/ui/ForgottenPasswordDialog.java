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

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
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

import com.novar.business.MainFacade;
import com.novar.util.SendMail;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.InvalidEmailException;
import com.novar.exception.LoginFailedException;
import com.novar.exception.RegisterFailedException;

import java.awt.Font;


public class ForgottenPasswordDialog extends JDialog {

	private JPanel contentPane;
	private JTextField emailTextField;
	private MainFacade facade;
	private Border defaultBorder;
	private JLabel lblErrorEmail;

	/**
	 * Create the frame.
	 */
	public ForgottenPasswordDialog(JFrame frame, MainFacade facade) {
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
		lblErrorEmail = new JLabel("Invalid email.");
		lblErrorEmail.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorEmail.setVisible(false);
		
		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.setFont(new Font("Calibri", Font.PLAIN, 11));
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				forgottenPassword();
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
			
			JLabel lblAnEmailWill = new JLabel("An email will be sent in your box");
		
		
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(81)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(lblAnEmailWill)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnCancel)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnOk))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblEmail)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblErrorEmail)))
						.addContainerGap(214, Short.MAX_VALUE))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(15)
						.addComponent(lblAnEmailWill)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblEmail)
							.addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblErrorEmail))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnCancel)
							.addComponent(btnOk))
						.addContainerGap(58, Short.MAX_VALUE))
			);
			contentPane.setLayout(gl_contentPane);
		}
		
	private void closeWindow(){
		this.dispose();
	}
	
	private void forgottenPassword() {
			emailTextField.setBorder(defaultBorder);
			lblErrorEmail.setVisible(false);
			
			HashMap<String,Object> mapUser = new HashMap<String,Object>();
			mapUser.put("email", emailTextField.getText());
			
				try {
					facade.forgottenPassword(mapUser);
					closeWindow();
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FalseFieldsException | InvalidEmailException e) {
					emailTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
					emailTextField.setText("");
					lblErrorEmail.setVisible(true);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		
		}
	}

