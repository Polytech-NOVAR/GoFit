package com.novar.ui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;

import com.novar.business.User;
import com.novar.exception.FalseFieldsException;

public class UsersPanel extends JPanel
{

	/**
	 * Create the panel.
	 */
	public UsersPanel(ConnectedWindow frame)
	{
		setBounds(new Rectangle(0, 0, 980, 800));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		int position = 20;
		
		JLabel lblUsers = new JLabel("Users");
		lblUsers.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		springLayout.putConstraint(SpringLayout.NORTH, lblUsers, position, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblUsers, 0, SpringLayout.HORIZONTAL_CENTER, this);
		lblUsers.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblUsers);
		
		JButton btnAddOne = new JButton("Add one");
		btnAddOne.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.changePanel(new AddUserPanel(frame));
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnAddOne, 100, SpringLayout.EAST, lblUsers);
		springLayout.putConstraint(SpringLayout.SOUTH, btnAddOne, 0, SpringLayout.SOUTH, lblUsers);
		add(btnAddOne);
		
		ArrayList<User> users = null;
		
		try
		{
			users = frame.getFacade().getUsersFacade().getUsers();
		}
		catch (SQLException | FalseFieldsException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int ind = 0; ind < users.size(); ind++)
		{
			User user = users.get(ind);
			
			if (ind == 0)
			{
				position += 75;
			}
			else
			{
				position += 40;
			}
			
			JButton btnSeeMore = new JButton("See more");
			btnSeeMore.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					frame.changePanel(new SeeMoreUserPanel(frame, user));
				}
			});
			springLayout.putConstraint(SpringLayout.NORTH, btnSeeMore, position, SpringLayout.NORTH, lblUsers);
			springLayout.putConstraint(SpringLayout.EAST, btnSeeMore, 0, SpringLayout.WEST, lblUsers);
			add(btnSeeMore);
			
			JButton btnUpdate = new JButton("Update");
			btnUpdate.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					frame.changePanel(new UpdateUserPanel(frame, user));
				}
			});
			springLayout.putConstraint(SpringLayout.NORTH, btnUpdate, 0, SpringLayout.NORTH, btnSeeMore);
			springLayout.putConstraint(SpringLayout.WEST, btnUpdate, 0, SpringLayout.EAST, lblUsers);
			add(btnUpdate);
			
			JButton btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					int returnValue = JOptionPane.showConfirmDialog(frame, "Are you sure to delete this account ?", "Detete", JOptionPane.YES_NO_OPTION);
					
					if (returnValue == JOptionPane.YES_OPTION)
					{
						try
						{
							frame.getFacade().getUsersFacade().delete(user);
							frame.changePanel(new UsersPanel(frame));
						}
						catch (SQLException e1)
						{
							e1.printStackTrace();
						};
					}
				}
			});
			springLayout.putConstraint(SpringLayout.NORTH, btnDelete, 0, SpringLayout.NORTH, btnSeeMore);
			springLayout.putConstraint(SpringLayout.WEST, btnDelete, 70, SpringLayout.EAST, btnUpdate);
			add(btnDelete);
			
			JLabel lblPseudoDynamic = new JLabel("");
			lblPseudoDynamic.setText(users.get(ind).getPseudo());
			springLayout.putConstraint(SpringLayout.NORTH, lblPseudoDynamic, 0, SpringLayout.NORTH, btnSeeMore);
			springLayout.putConstraint(SpringLayout.EAST, lblPseudoDynamic, -70, SpringLayout.WEST, btnSeeMore);
			add(lblPseudoDynamic);
		}
	}
}
