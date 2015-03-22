package com.novar.ui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.novar.business.Accessory;
import com.novar.business.FacadeMain;
import com.novar.business.Have;
import com.novar.business.Room;
import com.novar.exception.FalseFieldsException;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTextField;

public class PanelRoomDetails extends JPanel {

	private FacadeMain facade;
	private ConnectedWindow mainFrame;
	private Room room = null;
	
	private JTextField textFieldNum;
	private JTextField textFieldArea;
	private JTextField textFieldStreet;
	private JTextField textFieldCountry;
	private JTextField textFieldZip;
	private JTextField textFieldCity;
	private JLabel lblError;
	/**
	 * Create the panel.
	 */
	public PanelRoomDetails(ConnectedWindow frame, FacadeMain facade, Room room) {
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
		
		textFieldNum = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldNum, 0, SpringLayout.NORTH, lblNum);
		springLayout.putConstraint(SpringLayout.WEST, textFieldNum, 6, SpringLayout.EAST, lblNum);
		add(textFieldNum);
		textFieldNum.setColumns(10);
		
		textFieldArea = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldArea, 0, SpringLayout.NORTH, lblArea);
		springLayout.putConstraint(SpringLayout.EAST, textFieldArea, 0, SpringLayout.EAST, textFieldNum);
		add(textFieldArea);
		textFieldArea.setColumns(10);
		
		textFieldStreet = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldStreet, 0, SpringLayout.NORTH, lblStreet);
		springLayout.putConstraint(SpringLayout.EAST, textFieldStreet, 0, SpringLayout.EAST, textFieldNum);
		add(textFieldStreet);
		textFieldStreet.setColumns(10);
		
		textFieldCountry = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldCountry, -3, SpringLayout.NORTH, lblCountry);
		springLayout.putConstraint(SpringLayout.EAST, textFieldCountry, 0, SpringLayout.EAST, textFieldNum);
		add(textFieldCountry);
		textFieldCountry.setColumns(10);
		
		textFieldZip = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldZip, 0, SpringLayout.NORTH, lblZipcode);
		springLayout.putConstraint(SpringLayout.EAST, textFieldZip, 0, SpringLayout.EAST, textFieldNum);
		add(textFieldZip);
		textFieldZip.setColumns(10);
		
		textFieldCity = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldCity, 0, SpringLayout.NORTH, lblCity);
		springLayout.putConstraint(SpringLayout.WEST, textFieldCity, 0, SpringLayout.WEST, textFieldNum);
		add(textFieldCity);
		textFieldCity.setColumns(10);
		
		lblError = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, lblError, 0, SpringLayout.NORTH, lblStreet);
		springLayout.putConstraint(SpringLayout.EAST, lblError, -115, SpringLayout.EAST, this);
		add(lblError);
		
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
			
			textFieldNum.setText(room.getNum());
			textFieldArea.setText(Integer.toString(room.getArea()));
			textFieldStreet.setText(room.getStreet());
			textFieldCity.setText(room.getTown());
			textFieldZip.setText(room.getZipCode());
			textFieldCountry.setText(room.getCountry());
			
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
		HashMap<String,Object> mapRoom = new HashMap<String,Object>();
		mapRoom.put("num", textFieldNum.getText());
		mapRoom.put("area", Integer.parseInt((textFieldArea.getText())));
		mapRoom.put("street", textFieldStreet.getText());
		mapRoom.put("town", textFieldCity.getText());
		mapRoom.put("zipCode", textFieldZip.getText());
		mapRoom.put("country", textFieldCountry.getText());
		
		try 
		{
			facade.getRoomFacade().createRoom(mapRoom);
			this.mainFrame.changePanel(new PanelRooms(this.mainFrame, this.facade));
		} 
		catch (FalseFieldsException e1) 
		{
			lblError.setForeground(Color.RED);
			lblError.setText(e1.getMessage());
		}
	}
	
	private void update()
	{
		HashMap<String,Object> mapRoom = new HashMap<String,Object>();
		mapRoom.put("roomID", room.getRoomID());
		mapRoom.put("num", textFieldNum.getText());
		mapRoom.put("area", Integer.parseInt((textFieldArea.getText())));
		mapRoom.put("street", textFieldStreet.getText());
		mapRoom.put("town", textFieldCity.getText());
		mapRoom.put("zipCode", textFieldZip.getText());
		mapRoom.put("country", textFieldCountry.getText());
		try 
		{
			facade.getRoomFacade().updateRoom(mapRoom);
			this.mainFrame.changePanel(new PanelRooms(this.mainFrame, this.facade));
		} 
		catch (FalseFieldsException e1) 
		{
			lblError.setForeground(Color.RED);
			lblError.setText(e1.getMessage());
		}
	}
	
	private void cancel()
	{
		this.mainFrame.changePanel(new PanelRooms(this.mainFrame, this.facade));
	}

	private void removeAccessory(Have have)
	{
		this.facade.getRoomFacade().removeHave(have);
		this.mainFrame.changePanel(new PanelRoomDetails(this.mainFrame, this.facade, this.room));
	}
	
	private void updateAccessory(Have have, int quantity)
	{
		this.facade.getRoomFacade().updateHave(have, quantity);
		this.mainFrame.changePanel(new PanelRoomDetails(this.mainFrame, this.facade, this.room));
	}
	
	private void addAccessory()
	{
		AddAccDialog addAcc = new AddAccDialog(this.mainFrame, this.facade, this.room);
		addAcc.setVisible(true);
	}
}
