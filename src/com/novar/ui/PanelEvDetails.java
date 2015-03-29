package com.novar.ui;

import java.util.GregorianCalendar;
import java.util.Calendar;
import javax.swing.JButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.novar.business.Activity;
import com.novar.business.Event;
import com.novar.business.MainFacade;
import com.novar.business.Accessory;
import com.novar.exception.FalseFieldsException;
import com.novar.exception.LoginFailedException;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

import java.util.Calendar;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import com.toedter.calendar.JDateChooser;
public class PanelEvDetails extends JPanel {

	private MainFacade facade;
	private ConnectedWindow mainFrame;
	private JTextField textFieldName;
	private JTextField textFieldPrice;
	private JTextField textFieldDuration;
	private JTextField textFieldRoomNumber;
	private JTextField textFieldManager;
	private JComboBox  comboBoxType; 
	private JComboBox  comboBoxFrequence;
	private JDateChooser dateChooser;
	private JButton  btnUpdate;
	private JLabel lblDate;
	private Activity act;
	private Event Ev=null;
	private JTextField textFieldNbOccur;

	/**
	 * Create the panel.
	 */
	public PanelEvDetails(ConnectedWindow frame, MainFacade facade, Event Ev,Activity act) {
		this.facade = facade;
		this.mainFrame = frame;
		this.Ev=Ev;
		this.act=act;
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		JLabel lblActDetails = new JLabel("Events details");
		springLayout.putConstraint(SpringLayout.NORTH, lblActDetails, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblActDetails, mainFrame.getWidth()/3, SpringLayout.WEST, this);
		lblActDetails.setFont(new Font("Calibri", Font.BOLD, 24));
		add(lblActDetails);

		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblName);

		JLabel lblPrice = new JLabel("Price :");
		springLayout.putConstraint(SpringLayout.EAST, lblName, 0, SpringLayout.EAST, lblPrice);
		add(lblPrice);

		JLabel lblDuration = new JLabel("Duration :");
		springLayout.putConstraint(SpringLayout.NORTH, lblDuration, 235, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblPrice, 0, SpringLayout.EAST, lblDuration);
		add(lblDuration);

		JLabel lblManager = new JLabel("Manager:");
		springLayout.putConstraint(SpringLayout.EAST, lblManager, 0, SpringLayout.EAST, lblName);
		add(lblManager);

		textFieldName = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 5, SpringLayout.NORTH, textFieldName);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldName, 110, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, textFieldName, 212, SpringLayout.WEST, this);
		add(textFieldName);
		textFieldName.setColumns(10);

		textFieldPrice = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldPrice, 170, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblPrice, 3, SpringLayout.NORTH, textFieldPrice);
		springLayout.putConstraint(SpringLayout.EAST, textFieldPrice, 0, SpringLayout.EAST, textFieldName);
		add(textFieldPrice);
		textFieldPrice.setColumns(10);

		textFieldDuration = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldDuration, 232, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblDuration, -28, SpringLayout.WEST, textFieldDuration);
		springLayout.putConstraint(SpringLayout.EAST, textFieldDuration, 0, SpringLayout.EAST, textFieldName);
		add(textFieldDuration);
		textFieldDuration.setColumns(10);

		textFieldRoomNumber = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldRoomNumber, 41, SpringLayout.SOUTH, textFieldDuration);
		springLayout.putConstraint(SpringLayout.WEST, textFieldRoomNumber, 0, SpringLayout.WEST, textFieldName);
		add(textFieldRoomNumber);
		textFieldRoomNumber.setColumns(10);

		textFieldManager = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, lblManager, 3, SpringLayout.NORTH, textFieldManager);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldManager, 45, SpringLayout.SOUTH, textFieldRoomNumber);
		springLayout.putConstraint(SpringLayout.EAST, textFieldManager, 0, SpringLayout.EAST, textFieldName);
		add(textFieldManager);
		textFieldManager.setColumns(10);

		JLabel lblRoomNumber = new JLabel("Room number :");
		springLayout.putConstraint(SpringLayout.NORTH, lblRoomNumber, 0, SpringLayout.NORTH, textFieldRoomNumber);
		springLayout.putConstraint(SpringLayout.EAST, lblRoomNumber, 0, SpringLayout.EAST, lblName);
		add(lblRoomNumber);

		comboBoxType = new JComboBox();
		comboBoxType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				visibility();
			}
		});


		springLayout.putConstraint(SpringLayout.WEST, comboBoxType, 0, SpringLayout.WEST, textFieldName);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxType, 0, SpringLayout.EAST, textFieldName);
		comboBoxType.setModel(new DefaultComboBoxModel(new String[] {"Repetitive", "One time"}));
		comboBoxType.setSelectedIndex(0);
		comboBoxType.setToolTipText("def");
		add(comboBoxType);


		comboBoxType.getSelectedItem();
		JLabel lblType = new JLabel("Type");
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxType, -3, SpringLayout.NORTH, lblType);
		springLayout.putConstraint(SpringLayout.NORTH, lblType, 60, SpringLayout.SOUTH, lblManager);
		springLayout.putConstraint(SpringLayout.WEST, lblType, 0, SpringLayout.WEST, lblName);
		add(lblType);



		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

		JLabel lblNombreOccurence = new JLabel("Nombre Occurence");
		springLayout.putConstraint(SpringLayout.WEST, lblNombreOccurence, 0, SpringLayout.WEST, lblRoomNumber);
		add(lblNombreOccurence);

		textFieldNbOccur = new JTextField();

		springLayout.putConstraint(SpringLayout.NORTH, lblNombreOccurence, 3, SpringLayout.NORTH, textFieldNbOccur);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldNbOccur, 27, SpringLayout.SOUTH, comboBoxType);
		springLayout.putConstraint(SpringLayout.EAST, textFieldNbOccur, 0, SpringLayout.EAST, textFieldName);
		add(textFieldNbOccur);
		textFieldNbOccur.setColumns(10);

		comboBoxFrequence = new JComboBox();
		springLayout.putConstraint(SpringLayout.NORTH, comboBoxFrequence, 30, SpringLayout.SOUTH, textFieldNbOccur);
		springLayout.putConstraint(SpringLayout.WEST, comboBoxFrequence, 0, SpringLayout.WEST, textFieldName);
		springLayout.putConstraint(SpringLayout.EAST, comboBoxFrequence, 0, SpringLayout.EAST, textFieldName);
		comboBoxFrequence.setModel(new DefaultComboBoxModel(new String[] {"weekly", "monthly"}));
		comboBoxFrequence.setSelectedIndex(0);
		add(comboBoxFrequence);

		JLabel lblFrequence = new JLabel("Frequence");
		springLayout.putConstraint(SpringLayout.NORTH, lblFrequence, 3, SpringLayout.NORTH, comboBoxFrequence);
		springLayout.putConstraint(SpringLayout.WEST, lblFrequence, 0, SpringLayout.WEST, lblDuration);
		add(lblFrequence);


		lblDate = new JLabel("Date");
		springLayout.putConstraint(SpringLayout.NORTH, lblDate, 28, SpringLayout.SOUTH, lblFrequence);
		springLayout.putConstraint(SpringLayout.EAST, lblDate, 0, SpringLayout.EAST, lblType);
		add(lblDate);


		dateChooser = new JDateChooser();
		springLayout.putConstraint(SpringLayout.NORTH, dateChooser, 0, SpringLayout.NORTH, lblDate);
		springLayout.putConstraint(SpringLayout.WEST, dateChooser, 0, SpringLayout.WEST, textFieldName);
		springLayout.putConstraint(SpringLayout.EAST, dateChooser, 0, SpringLayout.EAST, textFieldName);
		add(dateChooser);





		if(Ev != null)
		{
			String type = Ev.getType();
			
			if(type.equals("One Time"))
			{
				this.comboBoxType.setSelectedIndex(1);
				this.textFieldNbOccur.setEnabled(false);
			}
			textFieldName.setText(Ev.getEventName());
			textFieldPrice.setText(Integer.toString(Ev.getEventPrice()));
			textFieldDuration.setText(Integer.toString(Ev.getEventDuration()));
			textFieldRoomNumber.setText(Integer.toString(Ev.getRoomID()));
			textFieldManager.setText(Ev.getPseudo());
			textFieldNbOccur.setText(Integer.toString(Ev.getOccurence()));

			dateChooser.setDate(Ev.getEventDate());
			btnUpdate = new JButton("Update");
			springLayout.putConstraint(SpringLayout.NORTH, btnUpdate, 30, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.EAST, btnUpdate, -80, SpringLayout.EAST, this);
			btnUpdate.setFont(new Font("Calibri", Font.PLAIN, 14));

			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						update();
					} catch (FalseFieldsException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});


			add(btnUpdate);
		}
		else
		{
			JButton btnCreate = new JButton("Create");
			springLayout.putConstraint(SpringLayout.NORTH, btnCreate, 30, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.EAST, btnCreate, -80, SpringLayout.EAST, this);
			btnCreate.setFont(new Font("Calibri", Font.PLAIN, 14));
			btnCreate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {

						create();
					} catch (FalseFieldsException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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

	private void create() throws FalseFieldsException
	{
		HashMap<String,Object> mapEv = new HashMap<String,Object>();


		mapEv.put("eventName", textFieldName.getText());
		mapEv.put("eventPrice", Integer.parseInt(textFieldPrice.getText()));
		mapEv.put("eventDuration", Integer.parseInt(textFieldDuration.getText()));
		mapEv.put("roomID", Integer.parseInt(textFieldRoomNumber.getText()));
		mapEv.put("actID", act.getActID());
		mapEv.put("pseudo", textFieldManager.getText());
		mapEv.put("eventDate", this.dateChooser.getDate());
		if(this.comboBoxType.getSelectedIndex()==0)
		{
			mapEv.put("type","Repetitive");
			mapEv.put("occurence", Integer.parseInt(textFieldNbOccur.getText()));

		}
		else
		{
			mapEv.put("type", "One Time");
			mapEv.put("occurence", 1);

		}


		if(this.comboBoxFrequence.getSelectedIndex()==0)
		{
			mapEv.put("frequency", "Weekly");

		}
		else if(this.comboBoxFrequence.getSelectedIndex()==1)
		{
			mapEv.put("frequency", "Monthly");
		}
		else
		{
			mapEv.put("frequency", null);
		}
		if(this.comboBoxType.getSelectedIndex()==0)
		{
			facade.getActEvFacade().createEvent(mapEv,0);
		}
		else
		{
			facade.getActEvFacade().createEvent(mapEv,1);
		}
		this.mainFrame.changePanel(new PanelEvents(this.mainFrame, this.facade,act));
	}

	private void update() throws FalseFieldsException
	{
		HashMap<String,Object> mapEv = new HashMap<String,Object>();



		mapEv.put("eventID", Ev.getEventID());
		mapEv.put("eventName", textFieldName.getText());
		mapEv.put("eventPrice", Integer.parseInt(textFieldPrice.getText()));
		mapEv.put("eventDuration", Integer.parseInt(textFieldDuration.getText()));
		mapEv.put("roomID", Integer.parseInt(textFieldRoomNumber.getText()));
		mapEv.put("actID", act.getActID());
		mapEv.put("pseudo", textFieldManager.getText());
		mapEv.put("eventDate", this.dateChooser.getDate());
		if(this.comboBoxType.getSelectedIndex()==0)
		{
			mapEv.put("type","Repetitive");
		}
		else
		{
			mapEv.put("type","One time");

		}

		mapEv.put("occurence", Integer.parseInt(textFieldNbOccur.getText()));
		if(this.comboBoxFrequence.getSelectedIndex()==0)
		{
			mapEv.put("frequency", "Weekly");
		}
		else if(this.comboBoxFrequence.getSelectedIndex()==1)
		{
			mapEv.put("frequency", "Monthly");
		}


		if(this.comboBoxType.getSelectedIndex()==0)
		{
			facade.getActEvFacade().updateEvent(mapEv,0);
		}
		else
		{
			facade.getActEvFacade().updateEvent(mapEv,1);
		}
		this.mainFrame.changePanel(new PanelEvents(this.mainFrame, this.facade,act));
	}
	private void visibility()
	{
		if(this.comboBoxType.getSelectedIndex()==0)
		{
			this.textFieldNbOccur.setEnabled(true);		
			this.comboBoxFrequence.setEnabled(true);
			this.lblDate.setText("First Date:");
		}
		else
		{
			this.textFieldNbOccur.setEnabled(false);
			this.comboBoxFrequence.setEnabled(false);
		}
	}
	private void cancel()
	{
		this.mainFrame.changePanel(new PanelEvents(this.mainFrame, this.facade, act));
	}
}
