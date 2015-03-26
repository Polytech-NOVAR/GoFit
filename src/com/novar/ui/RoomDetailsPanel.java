package com.novar.ui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import com.novar.business.Accessory;
import com.novar.business.ClassRoom;
import com.novar.business.MainFacade;
import com.novar.business.Have;
import com.novar.business.Office;
import com.novar.business.Room;
import com.novar.business.TypeRoom;
import com.novar.exception.FalseFieldsException;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JRadioButton;

public class RoomDetailsPanel extends JPanel {

	private MainFacade facade;
	private ConnectedWindow mainFrame;
	private Room room = null;
	
	private JTextField numTextField;
	private JTextField areaTextField;
	private JTextField streetTextField;
	private JTextField countryTextField;
	private JTextField zipTextField;
	private JTextField cityTextField;
	private JTextField seatsTextField;
	private JLabel lblSeats;
	private JLabel streetLabelError;
	private JLabel cityLabelError;
	private JLabel zipLabelError;
	private JLabel countryLabelError;
	private JLabel areaLabelError;
	private JLabel seatsLabelError;
	private Border defaultBorder;
	private JRadioButton officeRadioButton;
	private JRadioButton classroomRadioButton;
	
	
	/**
	 * Create the panel.
	 */
	public RoomDetailsPanel(ConnectedWindow frame, MainFacade facade, Room room) {
		this.facade = facade;
		this.mainFrame = frame;
		this.room = room;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblNum = new JLabel("Number :");
		springLayout.putConstraint(SpringLayout.WEST, lblNum, mainFrame.getWidth()/3, SpringLayout.WEST, this);
		lblNum.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblNum);
		
		JLabel lblArea = new JLabel("Area :");
		springLayout.putConstraint(SpringLayout.WEST, lblArea, mainFrame.getWidth()/3, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNum, -6, SpringLayout.NORTH, lblArea);
		lblArea.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblArea);
		
		JLabel lblStreet = new JLabel("Street :");
		springLayout.putConstraint(SpringLayout.WEST, lblStreet, mainFrame.getWidth()/3, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblArea, -6, SpringLayout.NORTH, lblStreet);
		lblStreet.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblStreet);
		
		JLabel lblCountry = new JLabel("Country :");
		springLayout.putConstraint(SpringLayout.WEST, lblCountry, 0, SpringLayout.WEST, lblNum);
		lblCountry.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblCountry);
		
		JLabel lblZipcode = new JLabel("ZipCode :");
		springLayout.putConstraint(SpringLayout.NORTH, lblCountry, 12, SpringLayout.SOUTH, lblZipcode);
		springLayout.putConstraint(SpringLayout.NORTH, lblZipcode, 135, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblStreet, -28, SpringLayout.NORTH, lblZipcode);
		lblZipcode.setFont(new Font("Calibri", Font.PLAIN, 12));
		springLayout.putConstraint(SpringLayout.WEST, lblZipcode, 0, SpringLayout.WEST, lblNum);
		add(lblZipcode);
		
		JLabel lblCity = new JLabel("City :");
		springLayout.putConstraint(SpringLayout.WEST, lblCity, 0, SpringLayout.WEST, lblNum);
		springLayout.putConstraint(SpringLayout.SOUTH, lblCity, -6, SpringLayout.NORTH, lblZipcode);
		lblCity.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblCity);
		
		numTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, numTextField, 0, SpringLayout.NORTH, lblNum);
		springLayout.putConstraint(SpringLayout.WEST, numTextField, 6, SpringLayout.EAST, lblNum);
		add(numTextField);
		numTextField.setColumns(10);
		
		defaultBorder = numTextField.getBorder();
		
		areaTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, areaTextField, 0, SpringLayout.NORTH, lblArea);
		springLayout.putConstraint(SpringLayout.EAST, areaTextField, 0, SpringLayout.EAST, numTextField);
		add(areaTextField);
		areaTextField.setColumns(10);
		
		streetTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, streetTextField, 0, SpringLayout.NORTH, lblStreet);
		springLayout.putConstraint(SpringLayout.EAST, streetTextField, 0, SpringLayout.EAST, numTextField);
		add(streetTextField);
		streetTextField.setColumns(10);
		
		countryTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, countryTextField, -3, SpringLayout.NORTH, lblCountry);
		springLayout.putConstraint(SpringLayout.EAST, countryTextField, 0, SpringLayout.EAST, numTextField);
		add(countryTextField);
		countryTextField.setColumns(10);
		
		zipTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, zipTextField, 0, SpringLayout.NORTH, lblZipcode);
		springLayout.putConstraint(SpringLayout.EAST, zipTextField, 0, SpringLayout.EAST, numTextField);
		add(zipTextField);
		zipTextField.setColumns(10);
		
		cityTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, cityTextField, 0, SpringLayout.NORTH, lblCity);
		springLayout.putConstraint(SpringLayout.WEST, cityTextField, 0, SpringLayout.WEST, numTextField);
		add(cityTextField);
		cityTextField.setColumns(10);
		
		streetLabelError = new JLabel("The street field must contains 1 to 50 letters or numbers.");
		springLayout.putConstraint(SpringLayout.NORTH, streetLabelError, 0, SpringLayout.NORTH, lblStreet);
		springLayout.putConstraint(SpringLayout.EAST, streetLabelError, -115, SpringLayout.EAST, this);
		streetLabelError.setFont(new Font("Calibri", Font.PLAIN, 11));
		streetLabelError.setVisible(false);
		add(streetLabelError);
		
		cityLabelError = new JLabel("The city field must contains 1 to 50 letters.");
		springLayout.putConstraint(SpringLayout.NORTH, cityLabelError, 0, SpringLayout.NORTH, lblCity);
		springLayout.putConstraint(SpringLayout.EAST, cityLabelError, -115, SpringLayout.EAST, this);
		cityLabelError.setFont(new Font("Calibri", Font.PLAIN, 11));
		cityLabelError.setVisible(false);
		add(cityLabelError);
		
		zipLabelError = new JLabel("The zipCode field must contains 5 numbers.");
		springLayout.putConstraint(SpringLayout.NORTH, zipLabelError, 0, SpringLayout.NORTH, lblZipcode);
		springLayout.putConstraint(SpringLayout.EAST, zipLabelError, -115, SpringLayout.EAST, this);
		zipLabelError.setFont(new Font("Calibri", Font.PLAIN, 11));
		zipLabelError.setVisible(false);
		add(zipLabelError);
		
		countryLabelError = new JLabel("The country field must contains 2 to 50 letters.");
		springLayout.putConstraint(SpringLayout.NORTH, countryLabelError, 0, SpringLayout.NORTH, lblCountry);
		springLayout.putConstraint(SpringLayout.EAST, countryLabelError, -115, SpringLayout.EAST, this);
		countryLabelError.setFont(new Font("Calibri", Font.PLAIN, 11));
		countryLabelError.setVisible(false);
		add(countryLabelError);
		
		areaLabelError = new JLabel("The area field must contains only numbers.");
		springLayout.putConstraint(SpringLayout.NORTH, areaLabelError, 0, SpringLayout.NORTH, lblArea);
		springLayout.putConstraint(SpringLayout.EAST, areaLabelError, -115, SpringLayout.EAST, this);
		areaLabelError.setFont(new Font("Calibri", Font.PLAIN, 11));
		areaLabelError.setVisible(false);
		add(areaLabelError);
		
		seatsLabelError = new JLabel("The seats field must contains only numbers.");
		springLayout.putConstraint(SpringLayout.NORTH, seatsLabelError, 20, SpringLayout.NORTH, lblCountry);
		springLayout.putConstraint(SpringLayout.EAST, seatsLabelError, -115, SpringLayout.EAST, this);
		seatsLabelError.setFont(new Font("Calibri", Font.PLAIN, 11));
		seatsLabelError.setVisible(false);
		add(seatsLabelError);
				
		if(room != null)
		{
			JLabel lblRoomDetails = new JLabel("Room details");
			springLayout.putConstraint(SpringLayout.NORTH, lblRoomDetails, 10, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblRoomDetails, mainFrame.getWidth()/3, SpringLayout.WEST, this);
			lblRoomDetails.setFont(new Font("Calibri", Font.BOLD, 24));
			add(lblRoomDetails);
			
			JLabel lblAccessoriesList = new JLabel("Accessories list");
			springLayout.putConstraint(SpringLayout.WEST, lblAccessoriesList, 0, SpringLayout.WEST, lblRoomDetails);
			springLayout.putConstraint(SpringLayout.NORTH, lblAccessoriesList, 50, SpringLayout.NORTH, lblCountry);
			lblAccessoriesList.setFont(new Font("Calibri", Font.BOLD, 18));
			add(lblAccessoriesList);
			
			JButton btnAddAccessory = new JButton("Add an accessory");
			springLayout.putConstraint(SpringLayout.NORTH, btnAddAccessory, 0, SpringLayout.NORTH, lblAccessoriesList);
			springLayout.putConstraint(SpringLayout.EAST, btnAddAccessory, -80, SpringLayout.EAST, this);
			btnAddAccessory.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnAddAccessory.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addAccessory();
				}
			});
			add(btnAddAccessory);
			
			numTextField.setText(room.getNum());
			areaTextField.setText(Integer.toString(room.getArea()));
			streetTextField.setText(room.getStreet());
			cityTextField.setText(room.getTown());
			zipTextField.setText(room.getZipCode());
			countryTextField.setText(room.getCountry());
			
			if(room.getType() instanceof ClassRoom)
			{
				lblSeats = new JLabel("Seats :");
				springLayout.putConstraint(SpringLayout.WEST, lblSeats, 0, SpringLayout.WEST, lblCountry);
				springLayout.putConstraint(SpringLayout.NORTH, lblSeats, 20, SpringLayout.NORTH, lblCountry);
				lblSeats.setFont(new Font("Calibri", Font.PLAIN, 12));
				add(lblSeats);
				
				seatsTextField = new JTextField();
				seatsTextField.setText(Integer.toString(((ClassRoom)room.getType()).getSeats()));
				springLayout.putConstraint(SpringLayout.WEST, seatsTextField, 0, SpringLayout.WEST, countryTextField);
				springLayout.putConstraint(SpringLayout.NORTH, seatsTextField, 0, SpringLayout.NORTH, lblSeats);
				seatsTextField.setFont(new Font("Calibri", Font.PLAIN, 12));
				add(seatsTextField);
				seatsTextField.setColumns(10);
			}
			
			JLabel lblName = new JLabel("Name");
			springLayout.putConstraint(SpringLayout.NORTH, lblName, 50, SpringLayout.NORTH, lblAccessoriesList);
			springLayout.putConstraint(SpringLayout.WEST, lblName, mainFrame.getWidth()/5, SpringLayout.WEST, this);
			lblName.setFont(new Font("Calibri", Font.BOLD, 14));
			add(lblName);
			
			JLabel lblQuantity = new JLabel("Quantity");
			springLayout.putConstraint(SpringLayout.NORTH, lblQuantity, 50, SpringLayout.NORTH, lblAccessoriesList);
			springLayout.putConstraint(SpringLayout.WEST, lblQuantity, mainFrame.getWidth()/5, SpringLayout.WEST, lblName);
			lblQuantity.setFont(new Font("Calibri", Font.BOLD, 14));
			add(lblQuantity);
			
			room = this.facade.getRoomFacade().getRoom(room);
			ArrayList<Have> accs = room.getAccessories();
			for(int i=0; i<accs.size(); i++)
			{
				double multiplier = 1 + 0.5*i;
				Have havei = accs.get(i);
				
				JLabel lblHavei = new JLabel(havei.getAcc().getName());
				springLayout.putConstraint(SpringLayout.WEST, lblHavei, mainFrame.getWidth()/5, SpringLayout.WEST, this);
				springLayout.putConstraint(SpringLayout.NORTH, lblHavei, (int)(50*multiplier), SpringLayout.NORTH, lblName);
				lblHavei.setFont(new Font("Calibri", Font.PLAIN, 12));
				add(lblHavei);
				
				JTextField textFieldQuantityi = new JTextField(Integer.toString(havei.getQuantity()));
				springLayout.putConstraint(SpringLayout.WEST, textFieldQuantityi, mainFrame.getWidth()/5, SpringLayout.WEST, lblHavei);
				springLayout.putConstraint(SpringLayout.NORTH, textFieldQuantityi, (int)(50*multiplier), SpringLayout.NORTH, lblQuantity);
				textFieldQuantityi.setFont(new Font("Calibri", Font.PLAIN, 12));
				add(textFieldQuantityi);
				textFieldQuantityi.setColumns(10);
				
				JButton btnUpdate = new JButton("Update accessory");
				springLayout.putConstraint(SpringLayout.NORTH, btnUpdate, (int)(50*multiplier), SpringLayout.NORTH, lblQuantity);
				springLayout.putConstraint(SpringLayout.WEST, btnUpdate, mainFrame.getWidth()/5, SpringLayout.WEST, textFieldQuantityi);
				btnUpdate.setFont(new Font("Calibri", Font.PLAIN, 12));
				btnUpdate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						updateAccessory(havei, Integer.parseInt(textFieldQuantityi.getText()));
					}
				});
				add(btnUpdate);
				
				JButton btnRemove = new JButton("Remove accessory");
				springLayout.putConstraint(SpringLayout.NORTH, btnRemove, (int)(50*multiplier), SpringLayout.NORTH, lblQuantity);
				springLayout.putConstraint(SpringLayout.WEST, btnRemove, mainFrame.getWidth()/5, SpringLayout.WEST, btnUpdate);
				btnRemove.setFont(new Font("Calibri", Font.PLAIN, 12));
				btnRemove.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removeAccessory(havei);
					}
				});
				add(btnRemove);
			}
			
			JButton btnUpdate = new JButton("Update");
			springLayout.putConstraint(SpringLayout.NORTH, btnUpdate, 30, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.EAST, btnUpdate, -80, SpringLayout.EAST, this);
			btnUpdate.setFont(new Font("Calibri", Font.PLAIN, 14));
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					update();
				}
			});
			add(btnUpdate);
		}
		else
		{
			JLabel lblRoomDetails = new JLabel("Add a room");
			springLayout.putConstraint(SpringLayout.NORTH, lblRoomDetails, 10, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblRoomDetails, mainFrame.getWidth()/3, SpringLayout.WEST, this);
			lblRoomDetails.setFont(new Font("Calibri", Font.BOLD, 24));
			add(lblRoomDetails);
			
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
			
			JLabel lblType = new JLabel("Type :");
			springLayout.putConstraint(SpringLayout.WEST, lblType, 0, SpringLayout.WEST, lblNum);
			springLayout.putConstraint(SpringLayout.SOUTH, lblType, 50, SpringLayout.NORTH, lblCountry);
			lblType.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblType);
			
			officeRadioButton = new JRadioButton("Office");
			springLayout.putConstraint(SpringLayout.WEST, officeRadioButton, 100, SpringLayout.WEST, lblType);
			springLayout.putConstraint(SpringLayout.SOUTH, officeRadioButton, 0, SpringLayout.SOUTH, lblType);
			officeRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					typeOffice();
				}
			});
			add(officeRadioButton);
			officeRadioButton.setSelected(true);
			
			classroomRadioButton = new JRadioButton("ClassRoom");
			springLayout.putConstraint(SpringLayout.WEST, classroomRadioButton, 100, SpringLayout.WEST, officeRadioButton);
			springLayout.putConstraint(SpringLayout.SOUTH, classroomRadioButton, 0, SpringLayout.SOUTH, officeRadioButton);
			classroomRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					typeClass();
				}
			});
			add(classroomRadioButton);
			
			lblSeats = new JLabel("Number of seats :");
			springLayout.putConstraint(SpringLayout.WEST, lblSeats, 0, SpringLayout.WEST, lblType);
			springLayout.putConstraint(SpringLayout.SOUTH, lblSeats, 50, SpringLayout.NORTH, lblType);
			lblSeats.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblSeats);
			lblSeats.setVisible(false);
			
			seatsTextField = new JTextField();
			springLayout.putConstraint(SpringLayout.WEST, seatsTextField, 100, SpringLayout.WEST, lblSeats);
			springLayout.putConstraint(SpringLayout.NORTH, seatsTextField, 0, SpringLayout.NORTH, lblSeats);
			seatsTextField.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(seatsTextField);
			seatsTextField.setColumns(10);
			seatsTextField.setVisible(false);
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
	
	private void create(){
		hideErrors();
		try
		{
			HashMap<String,Object> mapRoom = new HashMap<String,Object>();
			mapRoom.put("num", numTextField.getText());
			mapRoom.put("area", Integer.parseInt((areaTextField.getText())));
			mapRoom.put("street", streetTextField.getText());
			mapRoom.put("town", cityTextField.getText());
			mapRoom.put("zipCode", zipTextField.getText());
			mapRoom.put("country", countryTextField.getText());
			
			if(officeRadioButton.isSelected())
			{
				TypeRoom o = new Office();
				mapRoom.put("type", o);
			}
			else if(classroomRadioButton.isSelected())
			{
				try
				{
					TypeRoom c = new ClassRoom();
					((ClassRoom)c).setSeats(Integer.parseInt(seatsTextField.getText()));
					mapRoom.put("type", c);
				}
				catch (NumberFormatException e)
				{
					seatsTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
					seatsLabelError.setVisible(true);
				}
			}
			
			try 
			{
				facade.getRoomFacade().createRoom(mapRoom);
				this.mainFrame.changePanel(new RoomsPanel(this.mainFrame, this.facade));
			} 
			catch (FalseFieldsException e1) 
			{
				showErrors(e1);
			}
			
		}
		catch (NumberFormatException e2)
		{
			areaTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			areaLabelError.setVisible(true);
		}
	}
	
	private void update()
	{
		hideErrors();	
		try
		{
			HashMap<String,Object> mapRoom = new HashMap<String,Object>();
			mapRoom.put("num", numTextField.getText());
			mapRoom.put("area", Integer.parseInt((areaTextField.getText())));
			mapRoom.put("street", streetTextField.getText());
			mapRoom.put("town", cityTextField.getText());
			mapRoom.put("zipCode", zipTextField.getText());
			mapRoom.put("country", countryTextField.getText());
			
			if(room.getType() instanceof ClassRoom)
			{
				try
				{
					TypeRoom c = room.getType();
					((ClassRoom)c).setSeats(Integer.parseInt((seatsTextField.getText())));
					mapRoom.put("type", c);
				}
				catch (NumberFormatException e)
				{
					seatsTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
					seatsLabelError.setVisible(true);
				}
			}
			
			try 
			{
				facade.getRoomFacade().createRoom(mapRoom);
				this.mainFrame.changePanel(new RoomsPanel(this.mainFrame, this.facade));
			} 
			catch (FalseFieldsException e1) 
			{
				showErrors(e1);
			}
			
		}
		catch (NumberFormatException e2)
		{
			areaTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			areaLabelError.setVisible(true);
		}	
	}
	
	private void cancel()
	{
		this.mainFrame.changePanel(new RoomsPanel(this.mainFrame, this.facade));
	}

	private void removeAccessory(Have have)
	{
		this.facade.getRoomFacade().removeHave(have);
		this.mainFrame.changePanel(new RoomDetailsPanel(this.mainFrame, this.facade, this.room));
	}
	
	private void updateAccessory(Have have, int quantity)
	{
		this.facade.getRoomFacade().updateHave(have, quantity);
		this.mainFrame.changePanel(new RoomDetailsPanel(this.mainFrame, this.facade, this.room));
	}
	
	private void addAccessory()
	{
		AddAccDialog addAcc = new AddAccDialog(this.mainFrame, this.facade, this.room);
		addAcc.setVisible(true);
	}
	
	private void showErrors(FalseFieldsException e)
	{
		ArrayList<String> errors = e.getFalseFields();
		for(int i = 0 ; i <errors.size() ; i++)
		{
			switch(errors.get(i))
			{
				case "street" : streetTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
								streetTextField.setText("");
								streetLabelError.setVisible(true);
				break;
				case "town" : cityTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
							  cityTextField.setText("");
							  cityLabelError.setVisible(true);
				break;
				case "zipCode" : zipTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
								 zipTextField.setText("");
								 zipLabelError.setVisible(true);
				break;
				case "country" : countryTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
								countryTextField.setText("");
								 countryLabelError.setVisible(true);
				break;

			}
		}
	}
	
	private void hideErrors()
	{
		streetLabelError.setVisible(false);
		cityLabelError.setVisible(false);
		zipLabelError.setVisible(false);
		countryLabelError.setVisible(false);
		areaLabelError.setVisible(false);		
		seatsLabelError.setVisible(false);
		numTextField.setBorder(defaultBorder);
		areaTextField.setBorder(defaultBorder);
		streetTextField.setBorder(defaultBorder);
		countryTextField.setBorder(defaultBorder);
		zipTextField.setBorder(defaultBorder);
		cityTextField.setBorder(defaultBorder);
		seatsTextField.setBorder(defaultBorder);
	}
	
	private void typeOffice()
	{
		classroomRadioButton.setSelected(false);
		lblSeats.setVisible(false);
		seatsTextField.setVisible(false);
	}
	
	private void typeClass()
	{
		officeRadioButton.setSelected(false);
		lblSeats.setVisible(true);
		seatsTextField.setVisible(true);
	}
}
