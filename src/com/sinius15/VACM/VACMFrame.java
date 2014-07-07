package com.sinius15.VACM;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class VACMFrame extends JFrame {
	
	TrayIcon trayIcon;
	SystemTray tray;
	
	public VACMFrame() {
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setVgap(5);
		borderLayout.setHgap(5);
		setTitle("Virtual Audio Cable Manager");
		setBounds(100, 100, 402, 403);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JList<VirtualAudioCable> cableList = new JList<VirtualAudioCable>();
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
		
		if (SystemTray.isSupported()) {
			tray = SystemTray.getSystemTray();
			
			Image image = Toolkit.getDefaultToolkit().getImage("/res/icon.png");
			ActionListener exitListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			};
			PopupMenu popup = new PopupMenu();
			MenuItem defaultItem = new MenuItem("Exit");
			defaultItem.addActionListener(exitListener);
			popup.add(defaultItem);
			defaultItem = new MenuItem("Open");
			defaultItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(true);
					setExtendedState(JFrame.NORMAL);
				}
			});
			popup.add(defaultItem);
			trayIcon = new TrayIcon(image, "SystemTray Demo", popup);
			trayIcon.setImageAutoSize(true);
		}
		addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent e) {
				if (e.getNewState() == ICONIFIED) {
					try {
						tray.add(trayIcon);
						setVisible(false);
						System.out.println("added to SystemTray");
					} catch (AWTException ex) {
						System.out.println("unable to add to tray");
					}
				}
				if (e.getNewState() == 7) {
					try {
						tray.add(trayIcon);
						setVisible(false);
						System.out.println("added to SystemTray");
					} catch (AWTException ex) {
						System.out.println("unable to add to system tray");
					}
				}
				if (e.getNewState() == MAXIMIZED_BOTH) {
					tray.remove(trayIcon);
					setVisible(true);
					System.out.println("Tray icon removed");
				}
				if (e.getNewState() == NORMAL) {
					tray.remove(trayIcon);
					setVisible(true);
					System.out.println("Tray icon removed");
				}
			}
		});
		
	}
	
	private static final long serialVersionUID = 2595653495897980319L;
	
}