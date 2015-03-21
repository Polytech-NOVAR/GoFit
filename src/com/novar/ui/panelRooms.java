package com.novar.ui;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

import com.novar.business.FacadeMain;
import com.novar.business.Room;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class panelRooms extends JPanel {

	private FacadeMain facade;
	
	private ConnectedWindow mainFrame;
	/**
	 * Create the panel.
	 */
	public panelRooms(ConnectedWindow frame, FacadeMain facade) {
		this.facade = facade;
		this.mainFrame = frame;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblRoomNumber = new JLabel("Room number");
		springLayout.putConstraint(SpringLayout.NORTH, lblRoomNumber, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblRoomNumber, (mainFrame.getWidth()/9)/2, SpringLayout.WEST, this);
		lblRoomNumber.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblRoomNumber);
		
		JLabel lblArea = new JLabel("area");
		springLayout.putConstraint(SpringLayout.NORTH, lblArea, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblArea, mainFrame.getWidth()/9, SpringLayout.WEST, lblRoomNumber);
		lblArea.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblArea);
		
		JLabel lblStreet = new JLabel("street");
		springLayout.putConstraint(SpringLayout.NORTH, lblStreet, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblStreet, (mainFrame.getWidth()/9)/2, SpringLayout.WEST, lblArea);
		lblStreet.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblStreet);
		
		JLabel lblCity = new JLabel("city");
		springLayout.putConstraint(SpringLayout.NORTH, lblCity, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblCity, (mainFrame.getWidth()/9)*2, SpringLayout.WEST, lblStreet);
		lblCity.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblCity);
		
		JLabel lblZipcode = new JLabel("zipcode");
		springLayout.putConstraint(SpringLayout.NORTH, lblZipcode, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblZipcode, mainFrame.getWidth()/9, SpringLayout.WEST, lblCity);
		lblZipcode.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblZipcode);
		
		JLabel lblCountry = new JLabel("country");
		springLayout.putConstraint(SpringLayout.NORTH, lblCountry, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblCountry, mainFrame.getWidth()/9, SpringLayout.WEST, lblZipcode);
		lblCountry.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblCountry);
		
		JButton btnAddOne = new JButton("Add one");
		springLayout.putConstraint(SpringLayout.SOUTH, btnAddOne, -25, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnAddOne, -25, SpringLayout.WEST, this);
		btnAddOne.setFont(new Font("Calibri", Font.PLAIN, 14));
		add(btnAddOne);
		
		JLabel lblRooms = new JLabel("Rooms");
		springLayout.putConstraint(SpringLayout.NORTH, lblRooms, 30, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblRooms, mainFrame.getWidth()/2, SpringLayout.WEST, this);
		lblRooms.setFont(new Font("Calibri", Font.BOLD, 24));
		add(lblRooms);
		
		ArrayList<Room> rooms = facade.getRoomFacade().getAllRooms();
		for(int i=0; i<rooms.size(); i++)
		{
			double multiplier = 1.5 + 0.5*i;
			Room roomi = rooms.get(i);
			
			JLabel lblRoomiNum = new JLabel(roomi.getNum());
			springLayout.putConstraint(SpringLayout.NORTH, lblRoomiNum, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblRoomiNum, (mainFrame.getWidth()/9)/2, SpringLayout.WEST, this);
			lblRoomiNum.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblRoomiNum);
	
			JLabel lblRoomiArea= new JLabel(Integer.toString(roomi.getArea()));
			springLayout.putConstraint(SpringLayout.NORTH, lblRoomiArea, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblRoomiArea, mainFrame.getWidth()/9, SpringLayout.WEST, lblRoomiNum);
			lblRoomiArea.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblRoomiArea);
			
			JLabel lblRoomiStreet = new JLabel(roomi.getStreet());
			springLayout.putConstraint(SpringLayout.NORTH, lblRoomiStreet, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblRoomiStreet, (mainFrame.getWidth()/9)/2, SpringLayout.WEST, lblRoomiArea);
			lblRoomiStreet.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblRoomiStreet);
			
			JLabel lblRoomiCity = new JLabel(roomi.getTown());
			springLayout.putConstraint(SpringLayout.NORTH, lblRoomiCity, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblRoomiCity, (mainFrame.getWidth()/9)*2, SpringLayout.WEST, lblRoomiStreet);;
			lblRoomiCity.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblRoomiCity);
			
			JLabel lblRoomiZipCode = new JLabel(roomi.getZipCode());
			springLayout.putConstraint(SpringLayout.NORTH, lblRoomiZipCode, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblRoomiZipCode, mainFrame.getWidth()/9, SpringLayout.WEST, lblRoomiCity);
			lblRoomiZipCode.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblRoomiZipCode);
			
			JLabel lblRoomiCountry = new JLabel(roomi.getCountry());
			springLayout.putConstraint(SpringLayout.NORTH, lblRoomiCountry, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblRoomiCountry, mainFrame.getWidth()/9, SpringLayout.WEST, lblRoomiZipCode);
			lblRoomiCountry.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblRoomiCountry);
			
			JButton btnSeeMore = new JButton("See more");
			springLayout.putConstraint(SpringLayout.NORTH, btnSeeMore, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, btnSeeMore, mainFrame.getWidth()/9, SpringLayout.WEST, lblRoomiCountry);
			btnSeeMore.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnSeeMore.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seeMore(roomi.getRoomID());
				}
			});
			add(btnSeeMore);
			
			JButton btnDelete = new JButton("Delete");
			springLayout.putConstraint(SpringLayout.NORTH, btnDelete,(int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, btnDelete, mainFrame.getWidth()/9, SpringLayout.WEST, btnSeeMore);
			btnDelete.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					delete(roomi.getRoomID());
				}
			});
			add(btnDelete);
		}
		
		JLabel test = new JLabel("test");
	}
	
	private void seeMore(int roomID){
		
	}
	
	private void delete(int roomID){
		
	}
}
