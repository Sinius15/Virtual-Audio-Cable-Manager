package com.sinius15.VACM;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VACEditFrame extends JFrame {
	
	private static final long serialVersionUID = -4698554134567470651L;
	private JPanel contentPane;

	private VirtualAudioCable cable;
	private JComboBox<String> priority;
	private JComboBox<String> bufferMs;
	private JComboBox<String> channelConfig;
	private JSpinner buffers;
	private JComboBox<String> sampleRate;
	private JComboBox<String> waveIn;
	private JComboBox<String> waveOut;
	private JComboBox<String> bitsPerSample;
	
	public VACEditFrame(VirtualAudioCable cab) {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				VirtualAudioCableManager.getManager().frame.setEnabled(true);
				VirtualAudioCableManager.getManager().frame.requestFocus();
			}
		});
		this.cable = cab;
		setTitle("Virtual Audio Cable (" + cable.getName() + ")");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 416, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWaveIn = new JLabel("Wave in");
		lblWaveIn.setBounds(10, 14, 75, 14);
		contentPane.add(lblWaveIn);
		
		waveIn = new JComboBox<String>(new DefaultComboBoxModel<String>(Util.getInputDeviceNames()));
		waveIn.setBounds(65, 11, 237, 20);
		contentPane.add(waveIn);
		
		JLabel lblWaveOut = new JLabel("Wave out");
		lblWaveOut.setBounds(10, 42, 75, 14);
		contentPane.add(lblWaveOut);
		
		waveOut = new JComboBox<String>(new DefaultComboBoxModel<String>(Util.getOutputDeviceNames()));
		waveOut.setBounds(65, 39, 237, 20);
		contentPane.add(waveOut);
		
		JLabel lblSampleRate = new JLabel("Sample rate");
		lblSampleRate.setBounds(10, 73, 75, 14);
		contentPane.add(lblSampleRate);
		
		sampleRate = new JComboBox<String>();
		sampleRate.setModel(new DefaultComboBoxModel<String>(new String[] {"5000", "8000", "11025", "22050", "44100", "48000", "88200", "96000", "192000"}));
		sampleRate.setSelectedIndex(5);
		sampleRate.setEditable(true);
		sampleRate.setBounds(75, 70, 75, 20);
		contentPane.add(sampleRate);
		
		JLabel lblBitsPerSample = new JLabel("Bits per sample");
		lblBitsPerSample.setBounds(10, 104, 75, 14);
		contentPane.add(lblBitsPerSample);
		
		bitsPerSample = new JComboBox<String>();
		bitsPerSample.setModel(new DefaultComboBoxModel<String>(new String[] {"8", "16", "18", "20", "22", "24", "32"}));
		bitsPerSample.setSelectedIndex(1);
		bitsPerSample.setEditable(true);
		bitsPerSample.setBounds(95, 101, 55, 20);
		contentPane.add(bitsPerSample);
		
		JLabel lblChannelConfig = new JLabel("Channel config");
		lblChannelConfig.setBounds(10, 134, 75, 14);
		contentPane.add(lblChannelConfig);
		
		channelConfig = new JComboBox<String>();
		channelConfig.setModel(new DefaultComboBoxModel<String>(new String[] {"Mono", "Stereo", "Quadraphonic", "Surround", "5.1 back", "5.1 surround", "7.1 wide", "7.1 surround"}));
		channelConfig.setSelectedIndex(1);
		channelConfig.setBounds(90, 130, 96, 20);
		contentPane.add(channelConfig);
		
		bufferMs = new JComboBox<String>();
		bufferMs.setModel(new DefaultComboBoxModel<String>(new String[] {"50", "100", "200", "400", "500", "800", "1000", "2000", "4000", "8000"}));
		bufferMs.setSelectedIndex(4);
		bufferMs.setEditable(true);
		bufferMs.setBounds(292, 70, 75, 20);
		contentPane.add(bufferMs);
		
		JLabel lblTotalBufferms = new JLabel("Total buffer (ms)");
		lblTotalBufferms.setBounds(201, 73, 81, 14);
		contentPane.add(lblTotalBufferms);
		
		buffers = new JSpinner();
		buffers.setModel(new SpinnerNumberModel(new Integer(12), new Integer(1), null, new Integer(1)));
		buffers.setBounds(321, 101, 46, 20);
		contentPane.add(buffers);
		
		JLabel lblBuffers = new JLabel("Buffers");
		lblBuffers.setBounds(277, 104, 46, 14);
		contentPane.add(lblBuffers);
		
		JLabel lblPriority = new JLabel("Priority");
		lblPriority.setBounds(251, 134, 46, 14);
		contentPane.add(lblPriority);
		
		priority = new JComboBox<String>();
		priority.setModel(new DefaultComboBoxModel<String>(new String[] {"Normal", "High", "Realtime"}));
		priority.setBounds(292, 131, 75, 20);
		contentPane.add(priority);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VirtualAudioCable newCab = new VirtualAudioCable(cable.getName());
				waveIn.getSelectedItem();
			}
		});
		btnSave.setBounds(278, 178, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(179, 178, 89, 23);
		contentPane.add(btnCancel);
	}
	public JComboBox<String> getPriority() {
		return priority;
	}
	public JComboBox<String> getBufferMs() {
		return bufferMs;
	}
	public JComboBox<String> getChannelConfig() {
		return channelConfig;
	}
	public JSpinner getBuffers() {
		return buffers;
	}
	public JComboBox<String> getSampleRate() {
		return sampleRate;
	}
	public JComboBox<String> getWaveIn() {
		return waveIn;
	}
	public JComboBox<String> getWaveOut() {
		return waveOut;
	}
	public JComboBox<String> getBitsPerSample() {
		return bitsPerSample;
	}
}
