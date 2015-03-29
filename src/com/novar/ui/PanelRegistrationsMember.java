package com.novar.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.novar.business.Activity;
import com.novar.business.Event;
import com.novar.business.MainFacade;
import com.novar.business.Accessory;
import com.novar.business.Registration;
import com.novar.persist.EventJdbc;
import com.toedter.calendar.JDateChooser;

public class PanelRegistrationsMember extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MainFacade facade;

	private ConnectedWindow mainFrame;
	private JLabel lblDurDyn;
	private JLabel lblspeakerDyn;

	private Activity act = null;
	/**
	 * Create the panel.
	 */
	public PanelRegistrationsMember(ConnectedWindow frame, MainFacade facade) {
		this.facade = facade;
		this.mainFrame = frame;

		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		JLabel lblName = new JLabel("Name");
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 100, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblName, 10, SpringLayout.WEST, this);
		lblName.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblName);


		/* Traitement avec variable local en attendant Describe Activity*/


		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
		springLayout.putConstraint(SpringLayout.NORTH, lblPrice, -1, SpringLayout.NORTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, lblPrice, 71, SpringLayout.EAST, lblName);
		add(lblPrice);

		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setFont(new Font("Tahoma", Font.BOLD, 13));
		springLayout.putConstraint(SpringLayout.NORTH, lblDuration, 0, SpringLayout.NORTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, lblDuration, 98, SpringLayout.EAST, lblPrice);
		add(lblDuration);

		JLabel lblSpeaker = new JLabel("Speaker");
		lblSpeaker.setFont(new Font("Tahoma", Font.BOLD, 13));
		springLayout.putConstraint(SpringLayout.NORTH, lblSpeaker, 0, SpringLayout.NORTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, lblSpeaker, 85, SpringLayout.EAST, lblDuration);
		add(lblSpeaker);

		JLabel lblRegistrationDate = new JLabel("Registration Date");
		lblRegistrationDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		springLayout.putConstraint(SpringLayout.NORTH, lblRegistrationDate, 0, SpringLayout.NORTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, lblRegistrationDate, 143, SpringLayout.EAST, lblSpeaker);
		add(lblRegistrationDate);

		ArrayList<Registration> Registrations = facade.getActEvFacade().getAllRegistrations(facade.getUser());
		for(int i=0; i<Registrations.size(); i++)
		{
			double multiplier = 1.5 + 0.5*i;
			Registration Reg = Registrations.get(i);


			Event Ev= new EventJdbc();
			Ev.setEventID(Reg.getEventID());
			Ev.load();
			JLabel lblRegName = new JLabel(Ev.getEventName());
			springLayout.putConstraint(SpringLayout.NORTH, lblRegName, (int)(90*multiplier), SpringLayout.SOUTH, lblName);
			springLayout.putConstraint(SpringLayout.WEST, lblRegName, 0, SpringLayout.WEST, lblName);
			lblRegName.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblRegName);

			JLabel lblPriceDyn = new JLabel(String.valueOf(Ev.getEventPrice()));
			springLayout.putConstraint(SpringLayout.NORTH, lblPriceDyn, (int)(90*multiplier), SpringLayout.SOUTH, lblPrice);
			springLayout.putConstraint(SpringLayout.WEST, lblPriceDyn, 0, SpringLayout.WEST, lblPrice);
			lblRegName.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblPriceDyn);

			JLabel lblDurDyn = new JLabel(String.valueOf(Ev.getEventDuration()));
			springLayout.putConstraint(SpringLayout.NORTH, lblDurDyn, (int)(90*multiplier), SpringLayout.SOUTH, lblDuration);
			springLayout.putConstraint(SpringLayout.WEST, lblDurDyn, 0, SpringLayout.WEST, lblDuration);
			lblRegName.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblDurDyn);

			JLabel lblSpeakerDyn = new JLabel();
			lblSpeakerDyn.setText(Ev.getPseudo());
			springLayout.putConstraint(SpringLayout.NORTH, lblSpeakerDyn, (int)(90*multiplier), SpringLayout.SOUTH, lblSpeaker);
			springLayout.putConstraint(SpringLayout.WEST, lblSpeakerDyn, 0, SpringLayout.WEST, lblSpeaker);
			lblRegName.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblSpeakerDyn);


			JDateChooser dateChooser = new JDateChooser();
			dateChooser.setDate(Reg.getRegistrationDate());
			springLayout.putConstraint(SpringLayout.NORTH, dateChooser, (int)(90*multiplier), SpringLayout.SOUTH, lblRegistrationDate);
			springLayout.putConstraint(SpringLayout.WEST, dateChooser, 0, SpringLayout.WEST, lblRegistrationDate);
			dateChooser.setEnabled(false);
			add(dateChooser);

		}


		JButton btnAddOne = new JButton("Registrate for event");
		springLayout.putConstraint(SpringLayout.NORTH, btnAddOne, 30, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnAddOne, -80, SpringLayout.EAST, this);
		btnAddOne.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAddOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addOne();
			}
		});
		add(btnAddOne);


	}


	private void seeMore(Event Ev){
		this.mainFrame.changePanel(new PanelEvDetails(this.mainFrame, this.facade, Ev,act));
	}

	private void addOne(){
		this.mainFrame.changePanel(new PanelListOfEvents(this.mainFrame, this.facade));
	}

	private void delete(Event Ev)
	{
		DeleteEvDialog delete = new DeleteEvDialog(this.mainFrame, this.facade, Ev,act);
		delete.setVisible(true);
	}
}


