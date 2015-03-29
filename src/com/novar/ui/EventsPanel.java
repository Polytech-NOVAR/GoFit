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

public class EventsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MainFacade facade;

	private ConnectedWindow mainFrame;
	private Activity act = null;
	private Event Ev;
	/**
	 * Create the panel.
	 */
	public EventsPanel(ConnectedWindow frame, MainFacade facade, Activity act) {
		this.facade = facade;
		this.mainFrame = frame;
		this.act=act;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		JLabel lblName = new JLabel("Name");
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblName, mainFrame.getWidth()/4, SpringLayout.WEST, this);
		lblName.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblName);


		JButton btnAddOne = new JButton("Add one");
		springLayout.putConstraint(SpringLayout.NORTH, btnAddOne, 30, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnAddOne, -80, SpringLayout.EAST, this);
		btnAddOne.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAddOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addOne();
			}
		});
		add(btnAddOne);
		/* Traitement avec variable local en attendant Describe Activity*/

		JLabel lblRooms = new JLabel();
		lblRooms.setText(act.getActName());
		springLayout.putConstraint(SpringLayout.NORTH, lblRooms, -5, SpringLayout.NORTH, btnAddOne);
		springLayout.putConstraint(SpringLayout.EAST, lblRooms, -49, SpringLayout.WEST, btnAddOne);
		lblRooms.setFont(new Font("Calibri", Font.BOLD, 24));

		add(lblRooms);

		ArrayList<Event> Events = facade.getActEvFacade().getAllEvents(act);
		for(int i=0; i<Events.size(); i++)
		{
			double multiplier = 1.5 + 0.5*i;
			Ev = Events.get(i);



			JLabel lblEvName = new JLabel(Ev.getEventName());
			springLayout.putConstraint(SpringLayout.NORTH, lblEvName, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblEvName, mainFrame.getWidth()/4, SpringLayout.WEST, this);
			lblEvName.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblEvName);

			JButton btnSeeMore = new JButton("See more");
			springLayout.putConstraint(SpringLayout.NORTH, btnSeeMore, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, btnSeeMore, mainFrame.getWidth()/4, SpringLayout.WEST, lblEvName);
			btnSeeMore.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnSeeMore.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seeMore(Ev);
				}
			});
			add(btnSeeMore);

			JButton btnDelete = new JButton("Delete");
			springLayout.putConstraint(SpringLayout.NORTH, btnDelete,(int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, btnDelete, mainFrame.getWidth()/4, SpringLayout.WEST, btnSeeMore);
			btnDelete.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					delete(Ev);
				}
			});
			add(btnDelete);
		}
	}

	private void seeMore(Event Ev){
		this.mainFrame.changePanel(new EvDetailsPanel(this.mainFrame, this.facade, this.Ev,act));
	}

	private void addOne(){
		this.mainFrame.changePanel(new EvDetailsPanel(this.mainFrame, this.facade, null,act));
	}

	private void delete(Event Ev)
	{
		DeleteEvDialog delete = new DeleteEvDialog(this.mainFrame, this.facade, this.Ev,act);
		delete.setVisible(true);


	}
}


