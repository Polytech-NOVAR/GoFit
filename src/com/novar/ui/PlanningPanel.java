package com.novar.ui;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import com.novar.business.Planning;
import com.novar.business.User;
import com.novar.exception.FalseFieldsException;
import com.toedter.calendar.JDateChooser;

public class PlanningPanel extends JPanel
{
	/**
	 * Create the panel.
	 */
	public PlanningPanel(ConnectedWindow frame)
	{
		setBounds(new Rectangle(0, 0, 980, 800));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		int position = 20;
		
		JLabel lblPlanningOneTime = new JLabel("Planning One Time");
		lblPlanningOneTime.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		springLayout.putConstraint(SpringLayout.NORTH, lblPlanningOneTime, position, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblPlanningOneTime, 0, SpringLayout.HORIZONTAL_CENTER, this);
		lblPlanningOneTime.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPlanningOneTime);
		
		position += 50;
		
		JLabel lblSpeakerOneTime = new JLabel("Speaker");
		springLayout.putConstraint(SpringLayout.NORTH, lblSpeakerOneTime, position, SpringLayout.NORTH, lblPlanningOneTime);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblSpeakerOneTime, 0, SpringLayout.HORIZONTAL_CENTER, this);
		add(lblSpeakerOneTime);
		
		JLabel lblActivityOneTime = new JLabel("Activity");
		springLayout.putConstraint(SpringLayout.NORTH, lblActivityOneTime, 0, SpringLayout.NORTH, lblSpeakerOneTime);
		springLayout.putConstraint(SpringLayout.EAST, lblActivityOneTime, -150, SpringLayout.WEST, lblSpeakerOneTime);
		add(lblActivityOneTime);
		
		JLabel lblEventOneTime = new JLabel("Event");
		springLayout.putConstraint(SpringLayout.NORTH, lblEventOneTime, 0, SpringLayout.NORTH, lblSpeakerOneTime);
		springLayout.putConstraint(SpringLayout.EAST, lblEventOneTime, -150, SpringLayout.WEST, lblActivityOneTime);
		add(lblEventOneTime);
		
		JLabel lblRoomOneTime = new JLabel("Room");
		springLayout.putConstraint(SpringLayout.NORTH, lblRoomOneTime, 0, SpringLayout.NORTH, lblSpeakerOneTime);
		springLayout.putConstraint(SpringLayout.WEST, lblRoomOneTime, 150, SpringLayout.EAST, lblSpeakerOneTime);
		add(lblRoomOneTime);
		
		JLabel lblDateOneTime = new JLabel("Date");
		springLayout.putConstraint(SpringLayout.NORTH, lblDateOneTime, 0, SpringLayout.NORTH, lblSpeakerOneTime);
		springLayout.putConstraint(SpringLayout.WEST, lblDateOneTime, 150, SpringLayout.EAST, lblRoomOneTime);
		add(lblDateOneTime);
		
		position = 0;
		
		ArrayList<Planning> planningOneTime = null;
		
		try
		{
			planningOneTime = frame.getFacade().getPlanningFacade().getPlanningOneTime();
		}
		catch (SQLException | FalseFieldsException e)
		{
			e.printStackTrace();
		}
		
		for (int ind = 0; ind < planningOneTime.size(); ind++)
		{
			Planning planning = planningOneTime.get(ind);
			
			position += 20;
			
			JLabel lblSpeakerDynamic = new JLabel("");
			lblSpeakerDynamic.setText(planning.getSpeaker());
			springLayout.putConstraint(SpringLayout.NORTH, lblSpeakerDynamic, position, SpringLayout.SOUTH, lblSpeakerOneTime);
			springLayout.putConstraint(SpringLayout.WEST, lblSpeakerDynamic, 0, SpringLayout.WEST, lblSpeakerOneTime);
			add(lblSpeakerDynamic);
			
			JLabel lblActivityDynamic = new JLabel("");
			lblActivityDynamic.setText(planning.getActivity());
			springLayout.putConstraint(SpringLayout.NORTH, lblActivityDynamic, position, SpringLayout.SOUTH, lblActivityOneTime);
			springLayout.putConstraint(SpringLayout.WEST, lblActivityDynamic, 0, SpringLayout.WEST, lblActivityOneTime);
			add(lblActivityDynamic);
			
			JLabel lblEventDynamic = new JLabel("");
			lblEventDynamic.setText(planning.getEvent());
			springLayout.putConstraint(SpringLayout.NORTH, lblEventDynamic, position, SpringLayout.SOUTH, lblEventOneTime);
			springLayout.putConstraint(SpringLayout.WEST, lblEventDynamic, 0, SpringLayout.WEST, lblEventOneTime);
			add(lblEventDynamic);
			
			JLabel lblRoomDynamic = new JLabel("");
			lblRoomDynamic.setText(planning.getRoom());
			springLayout.putConstraint(SpringLayout.NORTH, lblRoomDynamic, position, SpringLayout.SOUTH, lblRoomOneTime);
			springLayout.putConstraint(SpringLayout.WEST, lblRoomDynamic, 0, SpringLayout.WEST, lblRoomOneTime);
			add(lblRoomDynamic);
			
			JDateChooser dtDateDynamic = new JDateChooser(planning.getDate());
			dtDateDynamic.setEnabled(false);
			springLayout.putConstraint(SpringLayout.NORTH, dtDateDynamic, position, SpringLayout.SOUTH, lblDateOneTime);
			springLayout.putConstraint(SpringLayout.WEST, dtDateDynamic, 0, SpringLayout.WEST, lblDateOneTime);
			add(dtDateDynamic);
		}
		
		position += 150;
		
		JLabel lblPlanninRepetitive = new JLabel("Planning Repetitive");
		lblPlanninRepetitive.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		springLayout.putConstraint(SpringLayout.NORTH, lblPlanninRepetitive, position, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblPlanninRepetitive, 0, SpringLayout.HORIZONTAL_CENTER, this);
		lblPlanninRepetitive.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPlanninRepetitive);
		
		position = 70;
		
		JLabel lblRoomRepetitive = new JLabel("Room");
		springLayout.putConstraint(SpringLayout.NORTH, lblRoomRepetitive, position, SpringLayout.NORTH, lblPlanninRepetitive);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblRoomRepetitive, 0, SpringLayout.HORIZONTAL_CENTER, this);
		add(lblRoomRepetitive);
		
		JLabel lblSpeakerRepetitive = new JLabel("Speaker");
		springLayout.putConstraint(SpringLayout.NORTH, lblSpeakerRepetitive, 0, SpringLayout.NORTH, lblRoomRepetitive);
		springLayout.putConstraint(SpringLayout.EAST, lblSpeakerRepetitive, -100, SpringLayout.WEST, lblRoomRepetitive);
		add(lblSpeakerRepetitive);
		
		JLabel lblActivityRepetitive = new JLabel("Activity");
		springLayout.putConstraint(SpringLayout.NORTH, lblActivityRepetitive, 0, SpringLayout.NORTH, lblRoomRepetitive);
		springLayout.putConstraint(SpringLayout.EAST, lblActivityRepetitive, -100, SpringLayout.WEST, lblSpeakerRepetitive);
		add(lblActivityRepetitive);
		
		JLabel lblEventRepetitive = new JLabel("Event");
		springLayout.putConstraint(SpringLayout.NORTH, lblEventRepetitive, 0, SpringLayout.NORTH, lblRoomRepetitive);
		springLayout.putConstraint(SpringLayout.EAST, lblEventRepetitive, -100, SpringLayout.WEST, lblActivityRepetitive);
		add(lblEventRepetitive);
		
		JLabel lblDateRepetitive = new JLabel("Date");
		springLayout.putConstraint(SpringLayout.NORTH, lblDateRepetitive, 0, SpringLayout.NORTH, lblRoomRepetitive);
		springLayout.putConstraint(SpringLayout.WEST, lblDateRepetitive, 100, SpringLayout.EAST, lblRoomRepetitive);
		add(lblDateRepetitive);
		
		JLabel lblFrequencyRepetitive = new JLabel("Frequency");
		springLayout.putConstraint(SpringLayout.NORTH, lblFrequencyRepetitive, 0, SpringLayout.NORTH, lblRoomRepetitive);
		springLayout.putConstraint(SpringLayout.WEST, lblFrequencyRepetitive, 100, SpringLayout.EAST, lblDateRepetitive);
		add(lblFrequencyRepetitive);
		
		JLabel lblNbOccRepetitive = new JLabel("Occurrence");
		springLayout.putConstraint(SpringLayout.NORTH, lblNbOccRepetitive, 0, SpringLayout.NORTH, lblRoomRepetitive);
		springLayout.putConstraint(SpringLayout.WEST, lblNbOccRepetitive, 100, SpringLayout.EAST, lblFrequencyRepetitive);
		add(lblNbOccRepetitive);
		
		position = 0;
		
		ArrayList<Planning> planningRepetitive = null;
		
		try
		{
			planningRepetitive = frame.getFacade().getPlanningFacade().getPlanningRepetitive();
		}
		catch (SQLException | FalseFieldsException e)
		{
			e.printStackTrace();
		}
		
		for (int ind = 0; ind < planningRepetitive.size(); ind++)
		{
			Planning planning = planningRepetitive.get(ind);
			
			position += 20;
			
			JLabel lblRoomDynamic = new JLabel("");
			lblRoomDynamic.setText(planning.getRoom());
			springLayout.putConstraint(SpringLayout.NORTH, lblRoomDynamic, position, SpringLayout.SOUTH, lblRoomRepetitive);
			springLayout.putConstraint(SpringLayout.WEST, lblRoomDynamic, 0, SpringLayout.WEST, lblRoomRepetitive);
			add(lblRoomDynamic);
			
			JLabel lblSpeakerDynamic = new JLabel("");
			lblSpeakerDynamic.setText(planning.getSpeaker());
			springLayout.putConstraint(SpringLayout.NORTH, lblSpeakerDynamic, position, SpringLayout.SOUTH, lblSpeakerRepetitive);
			springLayout.putConstraint(SpringLayout.WEST, lblSpeakerDynamic, 0, SpringLayout.WEST, lblSpeakerRepetitive);
			add(lblSpeakerDynamic);
			
			JLabel lblActivityDynamic = new JLabel("");
			lblActivityDynamic.setText(planning.getActivity());
			springLayout.putConstraint(SpringLayout.NORTH, lblActivityDynamic, position, SpringLayout.SOUTH, lblActivityRepetitive);
			springLayout.putConstraint(SpringLayout.WEST, lblActivityDynamic, 0, SpringLayout.WEST, lblActivityRepetitive);
			add(lblActivityDynamic);
			
			JLabel lblEventDynamic = new JLabel("");
			lblEventDynamic.setText(planning.getEvent());
			springLayout.putConstraint(SpringLayout.NORTH, lblEventDynamic, position, SpringLayout.SOUTH, lblEventRepetitive);
			springLayout.putConstraint(SpringLayout.WEST, lblEventDynamic, 0, SpringLayout.WEST, lblEventRepetitive);
			add(lblEventDynamic);
			
			JDateChooser dtDateDynamic = new JDateChooser(planning.getDate());
			dtDateDynamic.setEnabled(false);
			springLayout.putConstraint(SpringLayout.NORTH, dtDateDynamic, position, SpringLayout.SOUTH, lblDateRepetitive);
			springLayout.putConstraint(SpringLayout.WEST, dtDateDynamic, 0, SpringLayout.WEST, lblDateRepetitive);
			add(dtDateDynamic);
			
			JLabel lblFrequencyDynamic = new JLabel("");
			lblFrequencyDynamic.setText(planning.getFrequency());
			springLayout.putConstraint(SpringLayout.NORTH, lblFrequencyDynamic, position, SpringLayout.SOUTH, lblFrequencyRepetitive);
			springLayout.putConstraint(SpringLayout.WEST, lblFrequencyDynamic, 0, SpringLayout.WEST, lblFrequencyRepetitive);
			add(lblFrequencyDynamic);
			
			JLabel lblNbOccDynamic = new JLabel("");
			lblNbOccDynamic.setText(planning.getNbOccurrence());
			springLayout.putConstraint(SpringLayout.NORTH, lblNbOccDynamic, position, SpringLayout.SOUTH, lblNbOccRepetitive);
			springLayout.putConstraint(SpringLayout.WEST, lblNbOccDynamic, 0, SpringLayout.WEST, lblNbOccRepetitive);
			add(lblNbOccDynamic);
		}
	}
}
