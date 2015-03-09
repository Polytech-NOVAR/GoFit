package com.novar.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
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
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				HashMap<String,Object> mapUser = new HashMap<String,Object>();
				mapUser.put("pseudo", pseudoTextField.getText());
				mapUser.put("password", new String(passwordField.getPassword()));
				
				try 
				{
					facade.login(mapUser);
					System.out.println("Connexion reussie");
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
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterWindow register = new RegisterWindow(frame,facade);
				register.setVisible(true);
			}
		});
		
	    
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addComponent(logo)
					.addGap(43))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(68, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(14)
							.addComponent(lblPseudo)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblPassword)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnLogin)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRegister))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblError)
							.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
							.addComponent(pseudoTextField)))
					.addGap(63))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addComponent(logo)
					.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
					.addComponent(lblError)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPseudo)
						.addComponent(pseudoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogin)
						.addComponent(btnRegister))
					.addGap(20))
		);
		frame.getContentPane().setLayout(groupLayout);
	}

	private void closeWindow(){
		frame.dispose();
	}
}
