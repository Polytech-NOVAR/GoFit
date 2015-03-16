package com.novar.ui;

import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;

public class JPanelShop extends JPanel {

	/**
	 * Create the panel.
	 */
	public JPanelShop() 
	{
		
		JLabel lblNewLabel = new JLabel("Shop");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(286, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(276))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addContainerGap(332, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

}
