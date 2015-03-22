package com.novar.ui;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

import com.novar.business.FacadeMain;
import com.novar.business.Notification;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelNotifications extends JPanel {

	private FacadeMain facade;
	
	private ConnectedWindow mainFrame;
	/**
	 * Create the panel.
	 */
	public PanelNotifications(ConnectedWindow frame, FacadeMain facade) {
		this.facade = facade;
		this.mainFrame = frame;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblSender = new JLabel("Sender");
		springLayout.putConstraint(SpringLayout.NORTH, lblSender, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblSender, mainFrame.getWidth()/3, SpringLayout.WEST, this);
		lblSender.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblSender);
		
		JLabel lblMessage = new JLabel("Message");
		springLayout.putConstraint(SpringLayout.NORTH, lblMessage, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblMessage, mainFrame.getWidth()/3, SpringLayout.WEST, lblSender);
		lblMessage.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblMessage);
		
		JLabel lblNotifications = new JLabel("Notifications");
		springLayout.putConstraint(SpringLayout.NORTH, lblNotifications, 30, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNotifications, mainFrame.getWidth()/2, SpringLayout.WEST, this);
		lblNotifications.setFont(new Font("Calibri", Font.BOLD, 24));		
		add(lblNotifications);
		
		ArrayList<Notification> notifications = facade.getNotificationFacade().getAllNotifications(this.facade.getUser());
		for(int i=0; i<notifications.size(); i++)
		{
			double multiplier = 1.5 + 0.5*i;
			Notification notifi = notifications.get(i);
			facade.getNotificationFacade().viewNotify(notifi, this.facade.getUser().getPseudo());
					
			JLabel lblSenderi = new JLabel(notifi.getSender().getPseudo());
			springLayout.putConstraint(SpringLayout.NORTH, lblSenderi, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblSenderi, mainFrame.getWidth()/3, SpringLayout.WEST, this);
			lblSenderi.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblSenderi);
	
			JLabel lblMessagei= new JLabel(notifi.getMessage());
			springLayout.putConstraint(SpringLayout.NORTH, lblMessagei, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblMessagei, mainFrame.getWidth()/3, SpringLayout.WEST, lblSenderi);
			lblMessagei.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblMessagei);
		}
	}
}
