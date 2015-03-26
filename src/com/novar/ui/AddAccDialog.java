package com.novar.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import com.novar.business.MainFacade;
import com.novar.business.Accessory;
import com.novar.business.Room;

import javax.swing.SpringLayout;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class AddAccDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private MainFacade facade;
	private ConnectedWindow mainFrame;
	private Room room;
	private JTextField textFieldQuantity;
	private JComboBox comboBoxAccs;
	private JLabel lblErrorQuantity;
	
	/**
	 * Create the dialog.
	 */
	public AddAccDialog(ConnectedWindow frame, MainFacade facade, Room room) {
		super(frame, "Delete", Dialog.ModalityType.DOCUMENT_MODAL);
		this.mainFrame = frame;
		this.facade = facade;
		this.room = room;
		setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit(); 
		Dimension d = tk.getScreenSize();
		int screenHeight = d.height;
		int screenWidth = d.width; 
		setBounds(screenWidth/4, screenHeight/4, 500, 300);
		setAlwaysOnTop(true);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setFont(new Font("Calibri", Font.PLAIN, 12));
		springLayout.putConstraint(SpringLayout.NORTH, lblQuantity, 45, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblQuantity, 41, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblQuantity);
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setFont(new Font("Calibri", Font.PLAIN, 12));
		springLayout.putConstraint(SpringLayout.NORTH, textFieldQuantity, 0, SpringLayout.NORTH, lblQuantity);
		springLayout.putConstraint(SpringLayout.WEST, textFieldQuantity, 6, SpringLayout.EAST, lblQuantity);
		getContentPane().add(textFieldQuantity);
		textFieldQuantity.setColumns(10);
		
		comboBoxAccs = new JComboBox();
		comboBoxAccs.setFont(new Font("Calibri", Font.PLAIN, 12));
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxAccs, 0, SpringLayout.NORTH, lblQuantity);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxAccs, 6, SpringLayout.EAST, textFieldQuantity);
		getContentPane().add(comboBoxAccs);
		
		lblErrorQuantity = new JLabel("The quantity field must contains only numbers.");
		springLayout.putConstraint(SpringLayout.NORTH, lblErrorQuantity, 100, SpringLayout.NORTH, lblQuantity);
		springLayout.putConstraint(SpringLayout.WEST, lblErrorQuantity, 0, SpringLayout.WEST, lblQuantity);
		lblErrorQuantity.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblErrorQuantity.setVisible(false);
		add(lblErrorQuantity);
		
		ArrayList<Accessory> accs = facade.getRoomFacade().getAllAccessories();
		for(int i=0; i<accs.size(); i++)
		{
			Accessory acci = accs.get(i);
			comboBoxAccs.addItem(acci);
		}
		comboBoxAccs.setRenderer(new MyListRenderer());
		
		JButton btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.WEST, btnCancel, 0, SpringLayout.WEST, lblQuantity);
		springLayout.putConstraint(SpringLayout.SOUTH, btnCancel, -64, SpringLayout.SOUTH, getContentPane());
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		getContentPane().add(btnCancel);
		
		JButton btnAdd = new JButton("Add");
		springLayout.putConstraint(SpringLayout.NORTH, btnAdd, 0, SpringLayout.NORTH, btnCancel);
		springLayout.putConstraint(SpringLayout.EAST, btnAdd, -43, SpringLayout.EAST, getContentPane());
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add();
			}
		});
		getContentPane().add(btnAdd);
		
		
	}
	
	private void cancel()
	{
		this.dispose();
	}
	
	private void add()
	{
		lblErrorQuantity.setVisible(false);
		try
		{
			Accessory acc = ((Accessory)comboBoxAccs.getSelectedItem());
			int quantity = Integer.parseInt(textFieldQuantity.getText());
			this.facade.getRoomFacade().addAccessory(this.room, acc, quantity);
			this.mainFrame.changePanel(new RoomDetailsPanel(this.mainFrame, this.facade, this.room));
			this.dispose();
		}
		catch (NumberFormatException e)
		{
			lblErrorQuantity.setVisible(true);
		}
	}
}

class MyListRenderer extends JLabel implements ListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object acc, int index, boolean isSelected, boolean cellHasFocus) {
        setText(((Accessory)acc).getName());

        return this;
    }
}
