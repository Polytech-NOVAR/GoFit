package com.novar.ui;

import java.awt.Color;
import java.awt.EventQueue;
<<<<<<< HEAD
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.util.HashMap;
=======
>>>>>>> origin/uis

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

<<<<<<< HEAD
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
	
	final public static String IMAGE_PATH = "../images/";
	private JFrame frame;
	private JTextField pseudoTextField;
	private JPasswordField passwordField;
	private FacadeMain facade;
	private JLabel logo;
=======
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
>>>>>>> origin/uis
	
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
<<<<<<< HEAD
	public LoginWindow()
	{
		PersistKit kit = new JdbcKit();
		ConnectionUtil.start();
		facade = new FacadeMain(kit);
		
=======
	public LoginWindow() {
		business = new LoginBL();
>>>>>>> origin/uis
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
<<<<<<< HEAD
	private void initialize()
	{
		frame = new JFrame("GoFit");
=======
	private void initialize() {
		frame = new JFrame();
>>>>>>> origin/uis
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
				
				
				HashMap<String,Object> mapUser = new HashMap<String,Object>();
				mapUser.put("pseudo", pseudoTextField.getText());
				mapUser.put("password", new String(passwordField.getPassword()));
				
				try 
				{
					facade.login(mapUser);
					System.out.println("Connexion réussie");
				} 
				catch (FalseFieldsException e1) 
				{
					System.out.println(e1.getMessage()+ e1.getFalseFields());
				}
				catch (LoginFailedException e2) 
				{
					System.out.println(e2.getMessage());
				}
				
				/* Pour le Register
				HashMap<String,Object> map = new HashMap<String,Object>();
				map.put("pseudo", "pipyi");
				map.put("password", "popo");
				map.put("lastName", "JORG");
				map.put("firstName", "Antoine");
				map.put("phone", "0621940612");
				map.put("email", "ipp@kjh.fr");
				
				
				HashMap<String,Object> mapAddress = new HashMap<String,Object>();
				mapAddress.put("street", "Rue 1");
				mapAddress.put("town", "Ville 1");
				mapAddress.put("zipCode", "12345");
				mapAddress.put("country", "Pays 1");
				
				
				try 
				{
					facade.register(map, mapAddress);
				} 
				catch (FalseFieldsException e2) 
				{
					System.out.println(e2.getMessage()+ e2.getFalseFields());
				}
				catch (Exception e1) 
				{
					System.out.println(e1.getMessage());
				}*/
			}
		});
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterWindow register = new RegisterWindow(frame);
				register.setVisible(true);
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

	private void closeWindow(){
		frame.dispose();
	}
}
