package com.novar.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import com.novar.business.FacadeMain;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.LoginFailedException;
import com.novar.exception.SyntaxException;
import com.novar.util.ConnectionUtil;
import com.novar.util.StringUtil;
import com.novar.persist.JdbcKit;
import com.novar.persist.PersistKit;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class LoginWindow
{
	
	final public static String IMAGE_PATH = "../img/";
	private JFrame frame;
	private JTextField pseudoTextField;
	private JPasswordField passwordField;
	private FacadeMain facade;
	private JLabel logo;
	
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
	public LoginWindow()
	{
		PersistKit kit = new JdbcKit();
		ConnectionUtil.start();
		facade = new FacadeMain(kit);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize()
	{
		frame = new JFrame("GoFit");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit(); 
		Dimension d = tk.getScreenSize();
		int hauteurEcran = d.height;
		int largeurEcran = d.width; 
		frame.setBounds(largeurEcran/3, hauteurEcran/5, 380, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{37, 103, 57, 128, 0};
		gridBagLayout.rowHeights = new int[]{38, 300, 39, 14, 20, 20, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		logo = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource(IMAGE_PATH+"logo.jpg")).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
		GridBagConstraints gbc_logo = new GridBagConstraints();
		gbc_logo.anchor = GridBagConstraints.NORTHWEST;
		gbc_logo.insets = new Insets(0, 0, 5, 0);
		gbc_logo.gridwidth = 3;
		gbc_logo.gridx = 1;
		gbc_logo.gridy = 1;
		frame.getContentPane().add(logo, gbc_logo);
		
		JLabel lblError = new JLabel("Error");
		lblError.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.gridwidth = 2;
		gbc_lblError.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblError.insets = new Insets(0, 0, 5, 5);
		gbc_lblError.gridx = 2;
		gbc_lblError.gridy = 3;
		frame.getContentPane().add(lblError, gbc_lblError);
		
		JLabel lblPseudo = new JLabel("Pseudo:");
		lblPseudo.setFont(new Font("Calibri", Font.PLAIN, 11));
		GridBagConstraints gbc_lblPseudo = new GridBagConstraints();
		gbc_lblPseudo.anchor = GridBagConstraints.EAST;
		gbc_lblPseudo.insets = new Insets(0, 0, 5, 5);
		gbc_lblPseudo.gridx = 1;
		gbc_lblPseudo.gridy = 4;
		frame.getContentPane().add(lblPseudo, gbc_lblPseudo);
		
			JButton btnLogin = new JButton("Login");
			btnLogin.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					HashMap<String,Object> mapUser = new HashMap<String,Object>();
					mapUser.put("pseudo", pseudoTextField.getText());
					mapUser.put("password", new String(passwordField.getPassword()));
					
					try 
					{
						facade.login(mapUser);
						ConnectedWindow connectWindow = new ConnectedWindow();
						connectWindow.setVisible(true);
						closeWindow();
					} 
					catch (FalseFieldsException e1) 
					{
						lblError.setForeground(Color.RED);
						lblError.setText(e1.getMessage()+ e1.getFalseFields());
					}
					catch (LoginFailedException e2) 
					{
						lblError.setForeground(Color.RED);
						lblError.setText(e2.getMessage());
					}
				}
			});
			
			pseudoTextField = new JTextField();
			pseudoTextField.setColumns(10);
			GridBagConstraints gbc_pseudoTextField = new GridBagConstraints();
			gbc_pseudoTextField.anchor = GridBagConstraints.NORTH;
			gbc_pseudoTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_pseudoTextField.insets = new Insets(0, 0, 5, 0);
			gbc_pseudoTextField.gridwidth = 2;
			gbc_pseudoTextField.gridx = 2;
			gbc_pseudoTextField.gridy = 4;
			frame.getContentPane().add(pseudoTextField, gbc_pseudoTextField);
			
			JLabel lblPassword = new JLabel("Password:");
			lblPassword.setFont(new Font("Calibri", Font.PLAIN, 11));
			GridBagConstraints gbc_lblPassword = new GridBagConstraints();
			gbc_lblPassword.anchor = GridBagConstraints.EAST;
			gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblPassword.gridx = 1;
			gbc_lblPassword.gridy = 5;
			frame.getContentPane().add(lblPassword, gbc_lblPassword);
			
			passwordField = new JPasswordField();
			GridBagConstraints gbc_passwordField = new GridBagConstraints();
			gbc_passwordField.anchor = GridBagConstraints.NORTH;
			gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
			gbc_passwordField.insets = new Insets(0, 0, 5, 0);
			gbc_passwordField.gridwidth = 2;
			gbc_passwordField.gridx = 2;
			gbc_passwordField.gridy = 5;
			frame.getContentPane().add(passwordField, gbc_passwordField);
			GridBagConstraints gbc_btnLogin = new GridBagConstraints();
			gbc_btnLogin.anchor = GridBagConstraints.NORTHWEST;
			gbc_btnLogin.insets = new Insets(0, 0, 0, 5);
			gbc_btnLogin.gridx = 2;
			gbc_btnLogin.gridy = 6;
			frame.getContentPane().add(btnLogin, gbc_btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterWindow register = new RegisterWindow(frame,facade);
				register.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnRegister = new GridBagConstraints();
		gbc_btnRegister.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnRegister.gridx = 3;
		gbc_btnRegister.gridy = 6;
		frame.getContentPane().add(btnRegister, gbc_btnRegister);
	}

	private void closeWindow(){
		frame.dispose();
	}
}
