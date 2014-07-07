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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
			
			PopupMenu popupMenu = new PopupMenu();
			
			MenuItem exit = new MenuItem("Exit");
			exit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			
			MenuItem restore = new MenuItem("Restore");
			restore.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(true);
					setExtendedState(JFrame.NORMAL);
				}
			});
			
			MenuItem restart = new MenuItem("Restart all cables");
			restart.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VirtualAudioCableManager.getManager().restartAllCables();
				}
			});
			
			popupMenu.add(restart);
			popupMenu.add(restore);
			popupMenu.add(exit);
			
			trayIcon = new TrayIcon(image.getScaledInstance(image.getWidth(null), -1, Image.SCALE_SMOOTH), "Virtual Audio Cable Manager", popupMenu);
			trayIcon.setImageAutoSize(true);
			
			trayIcon.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					if(e.getButton() == MouseEvent.BUTTON1){
						setVisible(true);
						setExtendedState(JFrame.NORMAL);
					}
				}
			});
			
			addWindowStateListener(new WindowStateListener() {
				public void windowStateChanged(WindowEvent e) {
					if (e.getNewState() == ICONIFIED) {
						try {
							tray.add(trayIcon);
							setVisible(false);
						} catch (AWTException ex) {
							ex.printStackTrace();
						}
					}
					if (e.getNewState() == MAXIMIZED_BOTH) {
						tray.remove(trayIcon);
						setVisible(true);
					}
					if (e.getNewState() == NORMAL) {
						tray.remove(trayIcon);
						setVisible(true);
					}
				}
			});	
		}
		
		
	}
	
	private static final long serialVersionUID = 2595653495897980319L;
	
}
