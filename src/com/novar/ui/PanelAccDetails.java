package com.novar.ui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.novar.business.FacadeMain;
import com.novar.business.Accessory;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.LoginFailedException;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JTextField;

public class PanelAccDetails extends JPanel {

	private FacadeMain facade;
	private ConnectedWindow mainFrame;
	private Accessory acc = null;
	
	private JTextField textFieldName;
	
	/**
	 * Create the panel.
	 */
	public PanelAccDetails(ConnectedWindow frame, FacadeMain facade, Accessory acc) {
		this.facade = facade;
		this.mainFrame = frame;
		this.acc = acc;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblAccDetails = new JLabel("Accessory details");
		springLayout.putConstraint(SpringLayout.NORTH, lblAccDetails, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblAccDetails, mainFrame.getWidth()/3, SpringLayout.WEST, this);
		lblAccDetails.setFont(new Font("Calibri", Font.BOLD, 24));
		add(lblAccDetails);
		
		JLabel lblName = new JLabel("Name :");
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 100, SpringLayout.NORTH, lblAccDetails);
		springLayout.putConstraint(SpringLayout.WEST, lblName, mainFrame.getWidth()/3, SpringLayout.WEST, this);
		lblName.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblName);
		
		textFieldName = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldName, 0, SpringLayout.NORTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, textFieldName, 6, SpringLayout.EAST, lblName);
		add(textFieldName);
		textFieldName.setColumns(10);
		
		if(acc != null)
		{
			textFieldName.setText(acc.getName());
			JButton btnUpdate = new JButton("Update");
			springLayout.putConstraint(SpringLayout.NORTH, btnUpdate, 30, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.EAST, btnUpdate, -80, SpringLayout.EAST, this);
			btnUpdate.setFont(new Font("Calibri", Font.PLAIN, 14));
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					update();
				}
			});
			add(btnUpdate);
		}
		else
		{
			JButton btnCreate = new JButton("Create");
			springLayout.putConstraint(SpringLayout.NORTH, btnCreate, 30, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.EAST, btnCreate, -80, SpringLayout.EAST, this);
			btnCreate.setFont(new Font("Calibri", Font.PLAIN, 14));
			btnCreate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					create();
				}
			});
			add(btnCreate);
		}
		
		JButton btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 30, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnCancel, 80, SpringLayout.WEST, this);
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		add(btnCancel);
	}
	
	private void create(){
		HashMap<String,Object> mapAcc = new HashMap<String,Object>();
		mapAcc.put("name", textFieldName.getText());
		facade.getRoomFacade().createAccessory(mapAcc);
		this.mainFrame.changePanel(new PanelAccessories(this.mainFrame, this.facade));
	}
	
	private void update()
	{
		HashMap<String,Object> mapAcc = new HashMap<String,Object>();
		mapAcc.put("accID", acc.getAccID());
		mapAcc.put("name", textFieldName.getText());
		facade.getRoomFacade().updateAccessory(mapAcc);
		this.mainFrame.changePanel(new PanelAccessories(this.mainFrame, this.facade));
	}
	
	private void cancel()
	{
		this.mainFrame.changePanel(new PanelAccessories(this.mainFrame, this.facade));
	}

}
