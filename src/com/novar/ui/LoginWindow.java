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
		btnLogin.setBackground(Color.WHITE);
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
							.addGap(37)
							.addComponent(logo))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(51)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPseudo)
										.addComponent(lblPassword))
									.addGap(25)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblError)
										.addComponent(pseudoTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
										.addComponent(passwordField, Alignment.LEADING)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnLogin)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnRegister)
									.addGap(10)))))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addComponent(logo)
					.addGap(18)
					.addComponent(lblError)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(pseudoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPseudo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogin)
						.addComponent(btnRegister))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}

	private void closeWindow(){
		frame.dispose();
	}
}
