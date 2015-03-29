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
import com.novar.business.Manager;
import com.novar.business.User;
import com.novar.persist.UserJdbc;

public class RegistrationsPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	private MainFacade facade;
	private User man;
	private ConnectedWindow mainFrame;
	private Event Ev;
	/**
	 * Create the panel.
	 */
	public RegistrationsPanel(ConnectedWindow frame, MainFacade facade) {
		this.facade = facade;
		this.mainFrame = frame;
		this.man=man;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		/*	JLabel lblName = new JLabel("Name");
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblName, mainFrame.getWidth()/4, SpringLayout.WEST, this);
		lblName.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblName);*/


		JLabel lblRooms = new JLabel("Registrations for events");
		springLayout.putConstraint(SpringLayout.NORTH, lblRooms, 30, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblRooms, mainFrame.getWidth()/2, SpringLayout.WEST, this);
		lblRooms.setFont(new Font("Calibri", Font.BOLD, 24));

		add(lblRooms);

		this.man = this.facade.getUser();
		ArrayList<Event> Events = facade.getActEvFacade().getAllEvents(man);
		for(int i=0; i<Events.size(); i++)
		{
			double multiplier = 1.5 + 0.5*i;
			this.Ev = Events.get(i);

			JLabel lblEvName = new JLabel(Ev.getEventName());
			springLayout.putConstraint(SpringLayout.NORTH, lblEvName, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblEvName, mainFrame.getWidth()/4, SpringLayout.WEST, this);
			lblEvName.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblEvName);

			JButton btnRegistrations = new JButton("Registrations");
			springLayout.putConstraint(SpringLayout.NORTH, btnRegistrations, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, btnRegistrations, mainFrame.getWidth()/4, SpringLayout.WEST, lblEvName);
			btnRegistrations.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnRegistrations.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seeMore();
				}
			});
			add(btnRegistrations);



		}
	}

	private void seeMore()
	{
		this.mainFrame.changePanel(new RegDetailsPanel(this.mainFrame,this.facade,this.Ev));
	}

	private void addOne(){

	}



	private void delete(Event Ev)
	{
		//	DeleteActDialog delete = new DeleteRegDialog(this.mainFrame, this.facade, Ev);
		//	delete.setVisible(true);
	}


}
