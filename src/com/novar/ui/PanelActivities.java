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
import com.novar.business.MainFacade;
import com.novar.business.Accessory;

public class PanelActivities extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MainFacade facade;

	private ConnectedWindow mainFrame;
	/**
	 * Create the panel.
	 */
	public PanelActivities(ConnectedWindow frame, MainFacade facade) {
		this.facade = facade;
		this.mainFrame = frame;
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

		JLabel lblRooms = new JLabel("Activities");
		springLayout.putConstraint(SpringLayout.NORTH, lblRooms, 30, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblRooms, mainFrame.getWidth()/2, SpringLayout.WEST, this);
		lblRooms.setFont(new Font("Calibri", Font.BOLD, 24));

		add(lblRooms);

		ArrayList<Activity> acts = facade.getActEvFacade().getAllActivities();
		for(int i=0; i<acts.size(); i++)
		{
			double multiplier = 1.5 + 0.5*i;
			Activity act = acts.get(i);

			JLabel lblActName = new JLabel(act.getActName());
			springLayout.putConstraint(SpringLayout.NORTH, lblActName, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblActName, mainFrame.getWidth()/4, SpringLayout.WEST, this);
			lblActName.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblActName);

			JButton btnSeeMore = new JButton("See more");
			springLayout.putConstraint(SpringLayout.NORTH, btnSeeMore, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, btnSeeMore, mainFrame.getWidth()/4, SpringLayout.WEST, lblActName);
			btnSeeMore.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnSeeMore.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seeMore(act);
				}
			});
			add(btnSeeMore);


			JButton btnDelete = new JButton("Delete");
			springLayout.putConstraint(SpringLayout.NORTH, btnDelete,(int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, btnDelete, mainFrame.getWidth()/4, SpringLayout.WEST, btnSeeMore);
			btnDelete.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					delete(act);
				}
			});
			add(btnDelete);


			JButton btnEvents = new JButton("Events");
			springLayout.putConstraint(SpringLayout.NORTH, btnEvents,(int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, btnEvents, 40, SpringLayout.WEST, btnDelete);
			btnEvents.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnEvents.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					events(act);
				}
			});
			add(btnEvents);
		}
	}

	private void seeMore(Activity act){
		this.mainFrame.changePanel(new PanelActDetails(this.mainFrame, this.facade, act));
	}

	private void addOne(){
		this.mainFrame.changePanel(new PanelActDetails(this.mainFrame, this.facade, null));
	}

	private void events(Activity act)
	{
		this.mainFrame.changePanel(new PanelEvents(this.mainFrame,this.facade,act));

	}

	private void delete(Activity act)
	{
		DeleteActDialog delete = new DeleteActDialog(this.mainFrame, this.facade, act);
		delete.setVisible(true);
	}
}


