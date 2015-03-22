package com.novar.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.novar.business.FacadeMain;
import com.novar.business.Accessory;

public class PanelAccessories extends JPanel {

	private FacadeMain facade;
	
	private ConnectedWindow mainFrame;
	/**
	 * Create the panel.
	 */
	public PanelAccessories(ConnectedWindow frame, FacadeMain facade) {
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
		
		JLabel lblRooms = new JLabel("Accessories");
		springLayout.putConstraint(SpringLayout.NORTH, lblRooms, 30, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblRooms, mainFrame.getWidth()/2, SpringLayout.WEST, this);
		lblRooms.setFont(new Font("Calibri", Font.BOLD, 24));
		
		add(lblRooms);
		
		ArrayList<Accessory> accs = facade.getRoomFacade().getAllAccessories();
		for(int i=0; i<accs.size(); i++)
		{
			double multiplier = 1.5 + 0.5*i;
			Accessory acci = accs.get(i);
			
			JLabel lblAcciName = new JLabel(acci.getName());
			springLayout.putConstraint(SpringLayout.NORTH, lblAcciName, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, lblAcciName, mainFrame.getWidth()/4, SpringLayout.WEST, this);
			lblAcciName.setFont(new Font("Calibri", Font.PLAIN, 12));
			add(lblAcciName);
			
			JButton btnSeeMore = new JButton("See more");
			springLayout.putConstraint(SpringLayout.NORTH, btnSeeMore, (int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, btnSeeMore, mainFrame.getWidth()/4, SpringLayout.WEST, lblAcciName);
			btnSeeMore.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnSeeMore.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seeMore(acci);
				}
			});
			add(btnSeeMore);
			
			JButton btnDelete = new JButton("Delete");
			springLayout.putConstraint(SpringLayout.NORTH, btnDelete,(int)(90*multiplier), SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, btnDelete, mainFrame.getWidth()/4, SpringLayout.WEST, btnSeeMore);
			btnDelete.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					delete(acci);
				}
			});
			add(btnDelete);
		}
	}
	
	private void seeMore(Accessory acc){
		this.mainFrame.changePanel(new PanelAccDetails(this.mainFrame, this.facade, acc));
	}
	
	private void addOne(){
		this.mainFrame.changePanel(new PanelAccDetails(this.mainFrame, this.facade, null));
	}
	
	private void delete(Accessory acc)
	{
		DeleteAccDialog delete = new DeleteAccDialog(this.mainFrame, this.facade, acc);
		delete.setVisible(true);
	}
}
