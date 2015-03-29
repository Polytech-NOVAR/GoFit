package com.novar.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.novar.business.Activity;
import com.novar.business.Event;
import com.novar.business.MainFacade;
import com.novar.exception.FalseFieldsException;
import com.toedter.calendar.JDateChooser;

public class PanelAddReg extends JPanel {

	private MainFacade facade;
	private ConnectedWindow mainFrame;
	private JTextField textFieldMember;

	private JDateChooser dateChooser;


	private Event Ev=null;
	private JTextField textFieldNbOccur;

	/**
	 * Create the panel.
	 */
	public PanelAddReg(ConnectedWindow frame, MainFacade facade, Event Ev) {
		this.facade = facade;
		this.mainFrame = frame;
		this.Ev=Ev;

		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		JLabel lblActDetails = new JLabel("Add registration");
		springLayout.putConstraint(SpringLayout.NORTH, lblActDetails, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblActDetails, mainFrame.getWidth()/3, SpringLayout.WEST, this);
		lblActDetails.setFont(new Font("Calibri", Font.BOLD, 24));
		add(lblActDetails);

		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblName);


		textFieldMember = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 5, SpringLayout.NORTH, textFieldMember);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldMember, 110, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, textFieldMember, 212, SpringLayout.WEST, this);
		add(textFieldMember);
		textFieldMember.setColumns(10);



		JLabel lblDate = new JLabel("Date :");
		springLayout.putConstraint(SpringLayout.WEST, lblDate, 38, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, lblName, 0, SpringLayout.WEST, lblDate);
		add(lblDate);




		dateChooser = new JDateChooser();
		springLayout.putConstraint(SpringLayout.SOUTH, lblDate, 0, SpringLayout.SOUTH, dateChooser);
		springLayout.putConstraint(SpringLayout.EAST, lblDate, -136, SpringLayout.WEST, dateChooser);
		springLayout.putConstraint(SpringLayout.NORTH, dateChooser, 14, SpringLayout.SOUTH, textFieldMember);
		springLayout.putConstraint(SpringLayout.WEST, dateChooser, 0, SpringLayout.WEST, textFieldMember);
		springLayout.putConstraint(SpringLayout.EAST, dateChooser, 0, SpringLayout.EAST, textFieldMember);
		add(dateChooser);




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

	private void create() 
	{

		HashMap<String,Object> mapReg = new HashMap<String,Object>();
		mapReg.put("eventID", Ev.getEventID());
		mapReg.put("pseudo", textFieldMember.getText());
		mapReg.put("registrationDate", this.dateChooser.getDate());
		try {
			facade.getActEvFacade().createRegistration(mapReg);
		} catch (FalseFieldsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.mainFrame.changePanel(new PanelRegDetails(this.mainFrame, this.facade,Ev));

	}



	private void cancel()
	{
		this.mainFrame.changePanel(new PanelRegDetails(this.mainFrame, this.facade,Ev));
	}
}




