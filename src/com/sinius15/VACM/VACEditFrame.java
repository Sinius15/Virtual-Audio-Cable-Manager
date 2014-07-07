package com.sinius15.VACM;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VACEditFrame extends JFrame {
	
	private static final long serialVersionUID = -4698554134567470651L;
	private JPanel contentPane;

	public VACEditFrame(VirtualAudioCable cable) {
		setTitle("Virtual Audio Cable (" +cable.getName() + ")");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
}
