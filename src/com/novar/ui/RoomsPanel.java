package com.novar.ui;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

import com.novar.business.ClassRoom;
import com.novar.business.MainFacade;
import com.novar.business.Office;
import com.novar.business.Room;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomsPanel extends JPanel {

	private MainFacade facade;
	
	private ConnectedWindow mainFrame;
	/**
	 * Create the panel.
	 */
	public RoomsPanel(ConnectedWindow frame, MainFacade facade) {
		this.facade = facade;
		this.mainFrame = frame;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblRoomNumber = new JLabel("Number");
		springLayout.putConstraint(SpringLayout.NORTH, lblRoomNumber, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblRoomNumber, (mainFrame.getWidth()/11)/2, SpringLayout.WEST, this);
		lblRoomNumber.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblRoomNumber);
		
		JLabel lblArea = new JLabel("Area");
		springLayout.putConstraint(SpringLayout.NORTH, lblArea, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblArea, mainFrame.getWidth()/11, SpringLayout.WEST, lblRoomNumber);
		lblArea.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblArea);
		
		JLabel lblStreet = new JLabel("Street");
		springLayout.putConstraint(SpringLayout.NORTH, lblStreet, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblStreet, (mainFrame.getWidth()/11)/2, SpringLayout.WEST, lblArea);
		lblStreet.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblStreet);
		
		JLabel lblCity = new JLabel("City");
		springLayout.putConstraint(SpringLayout.NORTH, lblCity, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblCity, (mainFrame.getWidth()/11)*2, SpringLayout.WEST, lblStreet);
		lblCity.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblCity);
		
		JLabel lblZipcode = new JLabel("Zipcode");
		springLayout.putConstraint(SpringLayout.NORTH, lblZipcode, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblZipcode, mainFrame.getWidth()/11, SpringLayout.WEST, lblCity);
		lblZipcode.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblZipcode);
		
		JLabel lblCountry = new JLabel("Country");
		springLayout.putConstraint(SpringLayout.NORTH, lblCountry, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblCountry, mainFrame.getWidth()/11, SpringLayout.WEST, lblZipcode);
		lblCountry.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblCountry);
		
		JLabel lblType = new JLabel("Type");
		springLayout.putConstraint(SpringLayout.NORTH, lblType, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblType, mainFrame.getWidth()/11, SpringLayout.WEST, lblCountry);
		lblType.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblType);
		
		JLabel lblSeats = new JLabel("Seats");
		springLayout.putConstraint(SpringLayout.NORTH, lblSeats, 90, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblSeats, mainFrame.getWidth()/11, SpringLayout.WEST, lblType);
		lblSeats.setFont(new Font("Calibri", Font.BOLD, 14));
		add(lblSeats);
		
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
			springLayout.putConstraint(SpringLayout.WEST, lblRoomiNum, (mainFrame.getWidth()/11)/2, SpringLayout.WEST, this);
			lblRoomiNum.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblRoomiNum);
	
			JLabel lblRoomiArea= new JLabel(Integer.toString(roomi.getArea()));
			springLayout.putConstraint(SpringLayout.NORTH, lblRoomiArea, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblRoomiArea, mainFrame.getWidth()/11, SpringLayout.WEST, lblRoomiNum);
			lblRoomiArea.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblRoomiArea);
			
			JLabel lblRoomiStreet = new JLabel(roomi.getStreet());
			springLayout.putConstraint(SpringLayout.NORTH, lblRoomiStreet, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblRoomiStreet, (mainFrame.getWidth()/11)/2, SpringLayout.WEST, lblRoomiArea);
			lblRoomiStreet.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblRoomiStreet);
			
			JLabel lblRoomiCity = new JLabel(roomi.getTown());
			springLayout.putConstraint(SpringLayout.NORTH, lblRoomiCity, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblRoomiCity, (mainFrame.getWidth()/11)*2, SpringLayout.WEST, lblRoomiStreet);;
			lblRoomiCity.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblRoomiCity);
			
			JLabel lblRoomiZipCode = new JLabel(roomi.getZipCode());
			springLayout.putConstraint(SpringLayout.NORTH, lblRoomiZipCode, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblRoomiZipCode, mainFrame.getWidth()/11, SpringLayout.WEST, lblRoomiCity);
			lblRoomiZipCode.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblRoomiZipCode);
			
			JLabel lblRoomiCountry = new JLabel(roomi.getCountry());
			springLayout.putConstraint(SpringLayout.NORTH, lblRoomiCountry, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblRoomiCountry, mainFrame.getWidth()/11, SpringLayout.WEST, lblRoomiZipCode);
			lblRoomiCountry.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblRoomiCountry);
			
			JLabel lblRoomiType;
			JLabel lblRoomiSeats;
			if(roomi.getType() instanceof ClassRoom)
			{
				lblRoomiType = new JLabel("Classroom");
				lblRoomiSeats = new JLabel(Integer.toString(((ClassRoom)roomi.getType()).getSeats()));
			}
			else 
			{
				lblRoomiSeats = new JLabel("");
				if(roomi.getType() instanceof Office)
				{
					lblRoomiType = new JLabel("Office");
				}
				else
				{
					lblRoomiType = new JLabel("");
				}
				
			}
			springLayout.putConstraint(SpringLayout.NORTH, lblRoomiType, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblRoomiType, mainFrame.getWidth()/11, SpringLayout.WEST, lblRoomiCountry);
			lblRoomiType.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblRoomiType);
			
			springLayout.putConstraint(SpringLayout.NORTH, lblRoomiSeats, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblRoomiSeats, mainFrame.getWidth()/11, SpringLayout.WEST, lblRoomiType);
			lblRoomiSeats.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblRoomiSeats);
			
			JButton btnSeeMore = new JButton("See more");
			springLayout.putConstraint(SpringLayout.NORTH, btnSeeMore, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, btnSeeMore, mainFrame.getWidth()/11, SpringLayout.WEST, lblRoomiSeats);
			btnSeeMore.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnSeeMore.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seeMore(roomi);
				}
			});
			add(btnSeeMore);
			
			JButton btnDelete = new JButton("Delete");
			springLayout.putConstraint(SpringLayout.NORTH, btnDelete,(int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, btnDelete, mainFrame.getWidth()/11, SpringLayout.WEST, btnSeeMore);
			btnDelete.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					delete(roomi);
				}
			});
			add(btnDelete);
		}
		
		JScrollPane scrollPane = new JScrollPane(this);
	}
	
	private void seeMore(Room room){
		this.mainFrame.changePanel(new RoomDetailsPanel(this.mainFrame, this.facade, room));
	}
	
	private void addOne(){
		this.mainFrame.changePanel(new RoomDetailsPanel(this.mainFrame, this.facade, null));
	}
	
	private void delete(Room room)
	{
		DeleteRoomDialog delete = new DeleteRoomDialog(this.mainFrame, this.facade, room);
		delete.setVisible(true);
	}
}
