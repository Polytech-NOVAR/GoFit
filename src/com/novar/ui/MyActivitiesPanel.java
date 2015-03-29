package com.novar.ui;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import com.novar.business.Activity;

public class MyActivitiesPanel extends JPanel
{

	/**
	 * Create the panel.
	 */
	public MyActivitiesPanel(ConnectedWindow frame)
	{
		setBounds(new Rectangle(0, 0, 980, 800));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		int position = 20;
		
		JLabel lblMyActivities = new JLabel("My activities");
		lblMyActivities.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		springLayout.putConstraint(SpringLayout.NORTH, lblMyActivities, position, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblMyActivities, 0, SpringLayout.HORIZONTAL_CENTER, this);
		lblMyActivities.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblMyActivities);
		
		position += 100;
		
		JLabel lblActivity = new JLabel("Activity");
		springLayout.putConstraint(SpringLayout.NORTH, lblActivity, position, SpringLayout.NORTH, lblMyActivities);
		springLayout.putConstraint(SpringLayout.EAST, lblActivity, 0, SpringLayout.WEST, lblMyActivities);
		add(lblActivity);
		
		JLabel lblDescribe = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, lblDescribe, position, SpringLayout.NORTH, lblMyActivities);
		springLayout.putConstraint(SpringLayout.WEST, lblDescribe, 0, SpringLayout.EAST, lblMyActivities);
		add(lblDescribe);
		
		ArrayList<Activity> activities = null;
		activities = frame.getFacade().getActEvFacade().getAllActivities();
		
		for (int ind = 0; ind < activities.size(); ind++)
		{
			Activity activity = activities.get(ind);
			
			position += 20;
			
			JLabel lblActivityDynamic = new JLabel("");
			lblActivityDynamic.setText(activity.getActName());
			springLayout.putConstraint(SpringLayout.NORTH, lblActivityDynamic, position, SpringLayout.SOUTH, lblActivity);
			springLayout.putConstraint(SpringLayout.WEST, lblActivityDynamic, 0, SpringLayout.WEST, lblActivity);
			add(lblActivityDynamic);
			
			JButton btnDescribeDynamic = new JButton("Describe");
			springLayout.putConstraint(SpringLayout.NORTH, btnDescribeDynamic, position, SpringLayout.SOUTH, lblDescribe);
			springLayout.putConstraint(SpringLayout.WEST, btnDescribeDynamic, 0, SpringLayout.WEST, lblDescribe);
			btnDescribeDynamic.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					events(frame, activity);
				}
			});
			add(btnDescribeDynamic);
		}
	}
	
	private void events(ConnectedWindow frame, Activity activity)
	{
		frame.changePanel(new PanelEvents(frame,frame.getFacade(),activity));

	}
}
