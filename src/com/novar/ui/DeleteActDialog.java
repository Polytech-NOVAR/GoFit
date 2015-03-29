package com.novar.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.novar.business.Activity;
import com.novar.business.MainFacade;
import com.novar.business.Accessory;

import javax.swing.SpringLayout;
import javax.swing.JLabel;

public class DeleteActDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private MainFacade facade;
	private ConnectedWindow mainFrame;
	private Activity act;

	/**
	 * Create the dialog.
	 */
	public DeleteActDialog(ConnectedWindow frame, MainFacade facade, Activity act) {
		super(frame, "Delete", Dialog.ModalityType.DOCUMENT_MODAL);
		this.mainFrame = frame;
		this.facade = facade;
		this.act = act;
		setResizable(false);
		Toolkit tk = Toolkit.getDefaultToolkit(); 
		Dimension d = tk.getScreenSize();
		int screenHeight = d.height;
		int screenWidth = d.width; 
		setBounds(screenWidth/4, screenHeight/4, 400, 300);
		setAlwaysOnTop(true);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		String text = "Do you really want to delete the activity "+act.getActName()+" ?";
		JLabel labelConfirm = new JLabel(text);
		springLayout.putConstraint(SpringLayout.NORTH, labelConfirm, 34, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, labelConfirm, -42, SpringLayout.EAST, getContentPane());
		getContentPane().add(labelConfirm);

		JButton btnYes = new JButton("Yes");
		springLayout.putConstraint(SpringLayout.SOUTH, btnYes, -64, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnYes, 0, SpringLayout.EAST, labelConfirm);
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		getContentPane().add(btnYes);

		JButton btnNo = new JButton("No");
		springLayout.putConstraint(SpringLayout.WEST, btnNo, 0, SpringLayout.WEST, labelConfirm);
		springLayout.putConstraint(SpringLayout.SOUTH, btnNo, 0, SpringLayout.SOUTH, btnYes);
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		getContentPane().add(btnNo);
	}

	private void cancel()
	{
		this.dispose();
	}

	private void delete()
	{
		this.facade.getActEvFacade().deleteActivity(act);
		this.mainFrame.changePanel(new ActivitiesPanel(this.mainFrame, this.facade));
		this.dispose();
	}

}
