package com.novar.ui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.novar.business.Activity;
import com.novar.business.MainFacade;
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

public class ActDetailsPanel extends JPanel {

	private MainFacade facade;
	private ConnectedWindow mainFrame;
	private Activity act = null;

	private JTextField textFieldName;
	private JTextField textFieldShortDesc;
	private JTextField textFieldDetailedDesc;
	private JTextField textFieldPseudo;

	/**
	 * Create the panel.
	 */
	public ActDetailsPanel(ConnectedWindow frame, MainFacade facade, Activity act) {
		this.facade = facade;
		this.mainFrame = frame;
		this.act = act;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		JLabel lblActDetails = new JLabel("Activity details");
		springLayout.putConstraint(SpringLayout.NORTH, lblActDetails, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblActDetails, mainFrame.getWidth()/3, SpringLayout.WEST, this);
		lblActDetails.setFont(new Font("Calibri", Font.BOLD, 24));
		add(lblActDetails);

		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblName);

		textFieldName = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textFieldName, 126, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblName, -10, SpringLayout.WEST, textFieldName);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldName, -5, SpringLayout.NORTH, lblName);
		add(textFieldName);
		textFieldName.setColumns(10);


		textFieldShortDesc = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldShortDesc, 18, SpringLayout.SOUTH, textFieldName);
		springLayout.putConstraint(SpringLayout.WEST, textFieldShortDesc, 126, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, textFieldShortDesc, -457, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, textFieldShortDesc, -255, SpringLayout.EAST, this);
		add(textFieldShortDesc);
		textFieldShortDesc.setColumns(10);

		JLabel lblShortDesc = new JLabel("Short description :");
		springLayout.putConstraint(SpringLayout.NORTH, lblShortDesc, 19, SpringLayout.SOUTH, lblName);
		springLayout.putConstraint(SpringLayout.EAST, lblShortDesc, -6, SpringLayout.WEST, textFieldShortDesc);
		add(lblShortDesc);

		JLabel lblDetailedDescription = new JLabel("Detailed description:");
		springLayout.putConstraint(SpringLayout.EAST, lblDetailedDescription, 0, SpringLayout.EAST, lblShortDesc);
		add(lblDetailedDescription);

		textFieldDetailedDesc = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, lblDetailedDescription, 0, SpringLayout.NORTH, textFieldDetailedDesc);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldDetailedDesc, 32, SpringLayout.SOUTH, textFieldShortDesc);
		springLayout.putConstraint(SpringLayout.WEST, textFieldDetailedDesc, 0, SpringLayout.WEST, textFieldName);
		springLayout.putConstraint(SpringLayout.SOUTH, textFieldDetailedDesc, -341, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, textFieldDetailedDesc, -133, SpringLayout.EAST, this);
		add(textFieldDetailedDesc);
		textFieldDetailedDesc.setColumns(10);

		JLabel lblManager = new JLabel("Manager:");
		springLayout.putConstraint(SpringLayout.NORTH, lblManager, 91, SpringLayout.SOUTH, lblDetailedDescription);
		springLayout.putConstraint(SpringLayout.EAST, lblManager, 0, SpringLayout.EAST, lblName);
		add(lblManager);

		textFieldPseudo = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldPseudo, 0, SpringLayout.NORTH, lblManager);
		springLayout.putConstraint(SpringLayout.WEST, textFieldPseudo, 0, SpringLayout.WEST, textFieldName);
		add(textFieldPseudo);
		textFieldPseudo.setColumns(10);

		if(act != null)
		{
			textFieldName.setText(act.getActName());
			textFieldShortDesc.setText(act.getActShortDescription());
			textFieldDetailedDesc.setText(act.getActDetailedDescription());
			textFieldPseudo.setText(act.getPseudo());
			JButton btnUpdate = new JButton("Update");
			springLayout.putConstraint(SpringLayout.NORTH, btnUpdate, 30, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.EAST, btnUpdate, -80, SpringLayout.EAST, this);
			btnUpdate.setFont(new Font("Calibri", Font.PLAIN, 14));
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						update();
					} catch (FalseFieldsException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
					try {
						create();
					} catch (FalseFieldsException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			add(btnCreate);
		}

		JButton btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 60, SpringLayout.SOUTH, btnCancel);
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

	private void create() throws FalseFieldsException{
		HashMap<String,Object> mapAct = new HashMap<String,Object>();
		mapAct.put("actName", textFieldName.getText());
		mapAct.put("actShortDescription", textFieldShortDesc.getText());
		mapAct.put("actDetailedDescription", textFieldDetailedDesc.getText());
		mapAct.put("pseudo", textFieldPseudo.getText());
		facade.getActEvFacade().createActivity(mapAct);
		this.mainFrame.changePanel(new ActivitiesPanel(this.mainFrame, this.facade));
	}

	private void update() throws FalseFieldsException
	{
		HashMap<String,Object> mapAct = new HashMap<String,Object>();
		mapAct.put("actID", act.getActID());
		mapAct.put("actName", textFieldName.getText());
		mapAct.put("actShortDescription", textFieldShortDesc.getText());
		mapAct.put("actDetailedDescription", textFieldDetailedDesc.getText());
		mapAct.put("pseudo", textFieldPseudo.getText());
		facade.getActEvFacade().updateActivity(mapAct);
		this.mainFrame.changePanel(new ActivitiesPanel(this.mainFrame, this.facade));
	}

	private void cancel()
	{
		this.mainFrame.changePanel(new ActivitiesPanel(this.mainFrame, this.facade));
	}
}
