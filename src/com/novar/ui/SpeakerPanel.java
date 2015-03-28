package com.novar.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.novar.business.Accessory;
import com.novar.business.MainFacade;
import com.novar.business.Speaker;
import com.novar.business.User;

public class SpeakerPanel extends JPanel {

private MainFacade facade;

private ConnectedWindow mainFrame;
/**
 * Create the panel.
 */
public SpeakerPanel(ConnectedWindow frame, MainFacade facade) {
	this.facade = facade;
	this.mainFrame = frame;
	SpringLayout springLayout = new SpringLayout();
	setLayout(springLayout);
	
	JLabel lblPseudo = new JLabel("Pseudo");
	springLayout.putConstraint(SpringLayout.NORTH, lblPseudo, 90, SpringLayout.NORTH, this);
	springLayout.putConstraint(SpringLayout.WEST, lblPseudo, mainFrame.getWidth()/8, SpringLayout.WEST, this);
	lblPseudo.setFont(new Font("Calibri", Font.BOLD, 14));
	add(lblPseudo);
	
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
	
	JLabel lblSpeaker = new JLabel("Speaker");
	springLayout.putConstraint(SpringLayout.NORTH, lblSpeaker, 30, SpringLayout.NORTH, this);
	springLayout.putConstraint(SpringLayout.WEST, lblSpeaker, mainFrame.getWidth()/2, SpringLayout.WEST, this);
	lblSpeaker.setFont(new Font("Calibri", Font.BOLD, 24));
	
	add(lblSpeaker);
	
	User speaker = facade.getUser();
	ArrayList<User> users = facade.getManagerFacade().getAllSpeaker(speaker);
	for(int i=0; i<users.size(); i++)
	{
		double multiplier = 1.5 + 0.5*i;
		User speakeri = users.get(i);
		
		JLabel lblSpeakeri= new JLabel(speakeri.getPseudo());
		springLayout.putConstraint(SpringLayout.NORTH, lblSpeakeri, (int)(90*multiplier), SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblSpeakeri, mainFrame.getWidth()/8, SpringLayout.WEST, this);
		lblSpeakeri.setFont(new Font("Calibri", Font.PLAIN, 12));
		add(lblSpeakeri);
		
		
		JButton btnSeeMore = new JButton("See more");
		springLayout.putConstraint(SpringLayout.NORTH, btnSeeMore, (int)(90*multiplier), SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnSeeMore, mainFrame.getWidth()/4, SpringLayout.WEST, lblSpeakeri);
		btnSeeMore.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnSeeMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seeMore(speakeri);
			}
		});
		add(btnSeeMore);
		
		JButton btnDelete = new JButton("Delete");
		springLayout.putConstraint(SpringLayout.NORTH, btnDelete,(int)(90*multiplier), SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnDelete, mainFrame.getWidth()/4, SpringLayout.WEST, btnSeeMore);
		btnDelete.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete(speakeri);
			}
		});
		add(btnDelete);
	}
}

private void seeMore(User speaker){
	this.mainFrame.changePanel(new SpeakerDetailsPanel(this.mainFrame, this.facade, speaker));
}

private void addOne(){
	this.mainFrame.changePanel(new SpeakerDetailsPanel(this.mainFrame, this.facade, null));
}

private void delete(User speaker)
{
	DeleteSpeakerDialog delete = new DeleteSpeakerDialog(this.mainFrame, this.facade, speaker);
	delete.setVisible(true);
}

}