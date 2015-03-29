package com.novar.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.novar.business.Activity;
import com.novar.business.Event;
import com.novar.business.MainFacade;
import com.novar.business.Registration;
import com.novar.exception.FalseFieldsException;
import com.novar.persist.EventJdbc;
import com.toedter.calendar.JDateChooser;

public class ListOfEventsPanel extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MainFacade facade;

	private ConnectedWindow mainFrame;
	private ArrayList<Event> Events;
	private JComboBox comboBoxEvent;
	private JLabel lblDurDyn;
	private JLabel lblspeakerDyn;
	private Event Ev;

	private Activity act = null;
	/**
	 * Create the panel.
	 */
	public ListOfEventsPanel(ConnectedWindow frame, MainFacade facade) {
		this.facade = facade;
		this.mainFrame = frame;

		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		JButton btnAddOne = new JButton("Add Registration");
		springLayout.putConstraint(SpringLayout.NORTH, btnAddOne, 30, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnAddOne, -80, SpringLayout.EAST, this);
		btnAddOne.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAddOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addOne();
			}
		});
		add(btnAddOne);
		JLabel lblEvent = new JLabel("Event");
		springLayout.putConstraint(SpringLayout.NORTH, lblEvent, 72, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblEvent, 64, SpringLayout.WEST, this);
		add(lblEvent);

		comboBoxEvent = new JComboBox();
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxEvent, 17, SpringLayout.SOUTH, btnAddOne);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxEvent, 103, SpringLayout.EAST, lblEvent);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxEvent, 204, SpringLayout.EAST, lblEvent);
		add(comboBoxEvent);

		this.Events= facade.getActEvFacade().getAllEvents();
		for(int i=0; i<Events.size(); i++)
		{
			double multiplier = 1.5 + 0.5*i;
			Ev = Events.get(i);
			comboBoxEvent.addItem(Ev.getEventName());



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
	private void cancel()
	{

		this.mainFrame.changePanel(new RegistrationsMemberPanel(this.mainFrame, this.facade));
	}
	private void addOne() 
	{

		HashMap<String,Object> mapReg = new HashMap<String,Object>();
		this.Ev = Events.get(comboBoxEvent.getSelectedIndex());
		mapReg.put("eventID", this.Ev.getEventID());
		mapReg.put("pseudo", facade.getUser().getPseudo());

		try {
			facade.getActEvFacade().createRegistrationMember(mapReg);
		} catch (FalseFieldsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.mainFrame.changePanel(new ListOfEventsPanel(this.mainFrame, this.facade));

	}

}
