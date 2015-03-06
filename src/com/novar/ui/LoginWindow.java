package com.novar.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Canvas;

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
import com.novar.exception.LoginFailedException;
import com.novar.util.ConnectionUtil;
import com.novar.persist.JdbcKit;
import com.novar.persist.PersistKit;

public class LoginWindow
{
	
	final public static String IMAGE_PATH = "../images/";
	private JFrame frame;
	private JTextField pseudoTextField;
	private JPasswordField passwordField;
	private FacadeMain facade;
	private JLabel logo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					LoginWindow window = new LoginWindow();
					window.frame.setVisible(true);
				}
				catch (Exception e)
				{
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
		ConnectionUtil.start();
		PersistKit kit = new JdbcKit();
		facade = new FacadeMain(kit);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(400, 400, 380, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblError = new JLabel("");
		JLabel lblPseudo = new JLabel("Pseudo:");
		JLabel lblPassword = new JLabel("Password:");
		
		pseudoTextField = new JTextField();
		pseudoTextField.setColumns(10);
		
		passwordField = new JPasswordField();
		
		logo = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource(IMAGE_PATH+"logo.jpg")).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//Object connectedUser = business.login(pseudoTextField.getText(), new String(passwordField.getPassword()));
				
				/*if (connectedUser instanceof User)
				{
					ConnectedWindow connected  = new ConnectedWindow();
					connected.setVisible(true);
					closeWindow();
				}
				else
				{
					lblError.setForeground(Color.RED);
					lblError.setText((String) connectedUser);
				}*/
				//System.out.println(business.login(pseudoTextField.getText(), new String(passwordField.getPassword())));
				try {
					facade.login(pseudoTextField.getText(), new String(passwordField.getPassword()));
					System.out.println("Connexion réussie");
				} catch (LoginFailedException e1) {
					System.out.println(e1.getMessage());
				}
				
			}
		});
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				/*RegisterWindow register = new RegisterWindow(frame);
				register.setVisible(true);*/
			}
		});
		
	    
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

	private void closeWindow()
	{
		ConnectionUtil.stop();
		frame.dispose();
	}
}
