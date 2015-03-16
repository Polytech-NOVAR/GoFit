package com.novar.ui;

import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

public class testPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public testPanel() {
		setBounds(new Rectangle(0, 0, 980, 800));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblShop = new JLabel("Shop");
		springLayout.putConstraint(SpringLayout.NORTH, lblShop, 20, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblShop, 0, SpringLayout.HORIZONTAL_CENTER, this);
		add(lblShop);
		
		JLabel lblPseudo = new JLabel("Pseudo :");
		springLayout.putConstraint(SpringLayout.NORTH, lblPseudo, 50, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblPseudo, -50, SpringLayout.HORIZONTAL_CENTER, this);
		add(lblPseudo);
		
		JLabel lblLepseudo = new JLabel("lePseudo");
		springLayout.putConstraint(SpringLayout.NORTH, lblLepseudo, 0, SpringLayout.NORTH, lblPseudo);
		springLayout.putConstraint(SpringLayout.WEST, lblLepseudo, 50, SpringLayout.EAST, lblPseudo);
		add(lblLepseudo);
		
		JLabel lblFirst = new JLabel("First");
		springLayout.putConstraint(SpringLayout.NORTH, lblFirst, 20, SpringLayout.SOUTH, lblPseudo);
		springLayout.putConstraint(SpringLayout.EAST, lblFirst, 0, SpringLayout.EAST, lblPseudo);
		add(lblFirst);
		
		JLabel lblAze = new JLabel("aze");
		springLayout.putConstraint(SpringLayout.NORTH, lblAze, 0, SpringLayout.NORTH, lblFirst);
		springLayout.putConstraint(SpringLayout.WEST, lblAze, 0, SpringLayout.WEST, lblLepseudo);
		add(lblAze);

	}
}
