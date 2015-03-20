package com.novar.ui;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

import com.novar.business.FacadeMain;
import com.novar.business.Room;

public class panelRooms extends JPanel {

	FacadeMain facade;
	/**
	 * Create the panel.
	 */
	public panelRooms(FacadeMain facade) {
		this.facade = facade;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		ArrayList<Room> rooms = facade.getAllRooms();
		JLabel[] lab = new JLabel[rooms.size()]; 
		for(int roomi=0; roomi<rooms.size(); roomi++)
		{
			lab[roomi] = new JLabel(rooms.get(roomi).getNum());
			add(lab[roomi]);
		}
		
		JLabel test = new JLabel("test");
	}
}
