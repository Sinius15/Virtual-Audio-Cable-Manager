package com.sinius15.VACM;

import java.awt.BorderLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class VACMFrame extends JFrame{
	public VACMFrame() {
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setVgap(5);
		borderLayout.setHgap(5);
		setTitle("Virtual Audio Cable Manager");
		setBounds(new Rectangle(0, 0, 100, 100));
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JList cableList = new JList();
		scrollPane.setViewportView(cableList);
		
		JLabel lblAudioCables = new JLabel("Audio Cables: ");
		scrollPane.setColumnHeaderView(lblAudioCables);
		
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
