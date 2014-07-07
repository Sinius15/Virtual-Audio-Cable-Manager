package com.sinius15.VACM;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JButton;

public class VACEditFrame extends JFrame {
	
	private static final long serialVersionUID = -4698554134567470651L;
	private JPanel contentPane;

	private VirtualAudioCable cable;
	
	public VACEditFrame(VirtualAudioCable cable) {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				VirtualAudioCableManager.getManager().frame.setEnabled(true);
				VirtualAudioCableManager.getManager().frame.requestFocus();
			}
		});
		this.cable = cable;
		setTitle("Virtual Audio Cable (" + cable.getName() + ")");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 416, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWaveIn = new JLabel("Wave in");
		lblWaveIn.setBounds(10, 14, 75, 14);
		contentPane.add(lblWaveIn);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(65, 11, 237, 20);
		contentPane.add(comboBox);
		
		JLabel lblWaveOut = new JLabel("Wave out");
		lblWaveOut.setBounds(10, 42, 75, 14);
		contentPane.add(lblWaveOut);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(65, 39, 237, 20);
		contentPane.add(comboBox_1);
		
		JLabel lblSampleRate = new JLabel("Sample rate");
		lblSampleRate.setBounds(10, 73, 75, 14);
		contentPane.add(lblSampleRate);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"5000", "8000", "11025", "22050", "44100", "48000", "88200", "96000", "192000"}));
		comboBox_2.setEditable(true);
		comboBox_2.setBounds(75, 70, 75, 20);
		contentPane.add(comboBox_2);
		
		JLabel lblBitsPerSample = new JLabel("Bits per sample");
		lblBitsPerSample.setBounds(10, 104, 75, 14);
		contentPane.add(lblBitsPerSample);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"8", "16", "18", "20", "22", "24", "32"}));
		comboBox_3.setEditable(true);
		comboBox_3.setBounds(95, 101, 55, 20);
		contentPane.add(comboBox_3);
		
		JLabel lblChannelConfig = new JLabel("Channel config");
		lblChannelConfig.setBounds(10, 134, 75, 14);
		contentPane.add(lblChannelConfig);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"Mono", "Stereo", "Quadraphonic", "Surround", "5. 1 back", "5. 1 surround", "7. 1 wide", "7. 1 surround"}));
		comboBox_4.setEditable(true);
		comboBox_4.setBounds(90, 130, 96, 20);
		contentPane.add(comboBox_4);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"50", "100", "200", "400", "800", "1000", "2000", "4000", "8000"}));
		comboBox_5.setEditable(true);
		comboBox_5.setBounds(292, 70, 75, 20);
		contentPane.add(comboBox_5);
		
		JLabel lblTotalBufferms = new JLabel("Total buffer (ms)");
		lblTotalBufferms.setBounds(201, 73, 81, 14);
		contentPane.add(lblTotalBufferms);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(321, 101, 46, 20);
		contentPane.add(spinner);
		
		JLabel lblBuffers = new JLabel("Buffers");
		lblBuffers.setBounds(277, 104, 46, 14);
		contentPane.add(lblBuffers);
		
		JLabel lblPriority = new JLabel("Priority");
		lblPriority.setBounds(251, 134, 46, 14);
		contentPane.add(lblPriority);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"Normal", "High", "Realtime"}));
		comboBox_6.setBounds(292, 131, 75, 20);
		contentPane.add(comboBox_6);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(278, 171, 89, 23);
		contentPane.add(btnSave);
	}
}
