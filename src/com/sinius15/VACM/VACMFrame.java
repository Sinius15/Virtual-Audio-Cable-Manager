package com.sinius15.VACM;

import javax.swing.JFrame;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JButton;

public class VACMFrame extends JFrame{
	public VACMFrame() {
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setVgap(5);
		borderLayout.setHgap(5);
		setTitle("Virtual Audio Cable Manager");
		setBounds(new Rectangle(0, 0, 100, 100));
		
		JLabel lblCable = new JLabel("     Cables: ");
		lblCable.setVerticalAlignment(SwingConstants.BOTTOM);
		getContentPane().add(lblCable, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JList cableList = new JList();
		scrollPane.setViewportView(cableList);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add");
		panel.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		panel.add(btnRemove);
		
		JButton btnEdit = new JButton("Edit");
		panel.add(btnEdit);
		
		JButton btnStartAll = new JButton("Start All");
		panel.add(btnStartAll);
		
		JButton btnStopAll = new JButton("Stop All");
		panel.add(btnStopAll);
	}

	private static final long serialVersionUID = 2595653495897980319L;

	
}
