package com.novar.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.novar.business.Activity;
import com.novar.business.Event;
import com.novar.business.MainFacade;
import com.novar.business.Registration;
import com.novar.exception.FalseFieldsException;
import com.novar.persist.EventJdbc;
import com.toedter.calendar.JDateChooser;

public class PanelRegDetails extends JPanel {
	private MainFacade facade;
	private ConnectedWindow mainFrame;
	private JTextField textFieldName;
	private Registration Reg;
	private Event Ev;

	int position = 0;

	public PanelRegDetails(ConnectedWindow frame, MainFacade facade, Event Ev) {

		this.facade = facade;
		this.mainFrame = frame;
		this.Ev = Ev;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		setBounds(0, 0, 980, 800);

		JLabel lblRegDetails = new JLabel("Registrations details For Event:"+Ev.getEventName());
		springLayout.putConstraint(SpringLayout.NORTH, lblRegDetails, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblRegDetails, mainFrame.getWidth()/3, SpringLayout.WEST, this);
		lblRegDetails.setFont(new Font("Calibri", Font.BOLD, 24));
		add(lblRegDetails);

		JButton btnAddOne = new JButton("Add one");
		springLayout.putConstraint(SpringLayout.NORTH, btnAddOne, 30, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnAddOne, -80, SpringLayout.EAST, this);
		btnAddOne.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAddOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					create();
				} catch (FalseFieldsException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		add(btnAddOne);

		JLabel lblMemberBase = new JLabel("Member");
		springLayout.putConstraint(SpringLayout.NORTH, lblMemberBase, 75, SpringLayout.SOUTH, lblRegDetails);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblMemberBase, -200, SpringLayout.HORIZONTAL_CENTER, this);
		add(lblMemberBase);

		JLabel lblDateBase = new JLabel("Date");
		springLayout.putConstraint(SpringLayout.NORTH, lblDateBase, 0, SpringLayout.NORTH, lblMemberBase);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblDateBase, 0, SpringLayout.HORIZONTAL_CENTER, this);
		add(lblDateBase);

		JLabel lblBtnBase = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, lblBtnBase, 0, SpringLayout.NORTH, lblMemberBase);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblBtnBase, 200, SpringLayout.HORIZONTAL_CENTER, this);
		add(lblBtnBase);

		ArrayList<Registration> Registrations = facade.getActEvFacade().getAllRegistrations(Ev);
		for(int i=0; i<Registrations.size(); i++)
		{
			double multiplier = 1.5 + 0.5*i;
			Reg = Registrations.get(i);

			Ev= new EventJdbc();
			Ev.setEventID(Reg.getEventID());
			Ev.load();

			position += 30;

			JLabel lblEvName = new JLabel(Reg.getPseudo());
			springLayout.putConstraint(SpringLayout.NORTH, lblEvName, position, SpringLayout.SOUTH, lblMemberBase);
			springLayout.putConstraint(SpringLayout.WEST, lblEvName, 0, SpringLayout.WEST, lblMemberBase);
			lblEvName.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblEvName);

			JDateChooser dateChooser = new JDateChooser();
			dateChooser.setDate(Reg.getRegistrationDate());
			springLayout.putConstraint(SpringLayout.NORTH, dateChooser,  position, SpringLayout.NORTH, lblDateBase);
			springLayout.putConstraint(SpringLayout.WEST, dateChooser, 0, SpringLayout.WEST, lblDateBase);
			dateChooser.setEnabled(false);
			add(dateChooser);

			JButton btnDelete = new JButton("Delete");
			springLayout.putConstraint(SpringLayout.NORTH, btnDelete,position, SpringLayout.NORTH, lblBtnBase);
			springLayout.putConstraint(SpringLayout.WEST, btnDelete, 0, SpringLayout.WEST, lblBtnBase);
			btnDelete.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnDelete.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					delete(Reg);
				}
			});

			add(btnDelete);

			/*JLabel lblEvName = new JLabel(Reg.getPseudo());
		springLayout.putConstraint(SpringLayout.NORTH, lblEvName, (int)(90*multiplier), SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblEvName, 0, SpringLayout.WEST, this);
		lblEvName.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblEvName);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDate(Reg.getRegistrationDate());
		springLayout.putConstraint(SpringLayout.NORTH, dateChooser,  (int)(90*multiplier), SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, dateChooser, 0, SpringLayout.WEST, lblEvName);
		dateChooser.setEnabled(false);
		add(dateChooser);

		//Delete 
		JButton btnDelete = new JButton("Delete");
		springLayout.putConstraint(SpringLayout.NORTH, btnDelete,(int)(90*multiplier), SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnDelete, mainFrame.getWidth()/6, SpringLayout.WEST, dateChooser);
		btnDelete.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					delete(Reg);
				}
			});

		add(btnDelete);*/


		}
	}

	private void create() throws FalseFieldsException{
		this.mainFrame.changePanel(new PanelAddReg(this.mainFrame, this.facade, this.Ev));
	}

	private void delete(Registration reg)
	{	DeleteRegDialog delete = new DeleteRegDialog(this.mainFrame, this.facade, Ev,reg);
	delete.setVisible(true);
	this.mainFrame.changePanel(new PanelRegDetails(this.mainFrame, this.facade,Ev));

	}

	private void cancel()
	{/*
	this.mainFrame.changePanel(new PanelAccessories(this.mainFrame, this.facade));*/
	}
}
