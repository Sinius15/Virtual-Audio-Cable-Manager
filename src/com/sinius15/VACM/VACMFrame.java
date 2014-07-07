package com.sinius15.VACM;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class VACMFrame extends JFrame {
	
	TrayIcon trayIcon;
	SystemTray tray;
	
	private VACMFrame thiss = this;
	
	public VACMFrame() {
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setVgap(5);
		borderLayout.setHgap(5);
		setTitle("Virtual Audio Cable Manager");
		setBounds(100, 100, 402, 403);
		
		JScrollPane center = new JScrollPane();
		getContentPane().add(center, BorderLayout.CENTER);
		
		cableList = new JList<VirtualAudioCable>();
		cableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		center.setViewportView(cableList);
		
		JLabel lblAudioCables = new JLabel("Audio Cables: ");
		center.setColumnHeaderView(lblAudioCables);
		
		JPanel bottom = new JPanel();
		getContentPane().add(bottom, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(
						thiss,
						"What is the title of this new AudioRepeater? " + System.lineSeparator()
								+ "The name must exist of only letters and must be uniqe", "name?",
						JOptionPane.QUESTION_MESSAGE);
				if (!name.matches("[a-zA-Z]+")) {
					JOptionPane.showMessageDialog(thiss,
							"The name must exist of only letters!", "Fatal Error!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (VirtualAudioCableManager.getManager().getCable(name) != null) {
					JOptionPane.showMessageDialog(thiss,
							"The name must be uniqe.", "Fatal Error!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				VirtualAudioCable cable = new VirtualAudioCable(name);
				VirtualAudioCableManager.getManager().cables.add(cable);
				updateList();
				cableList.setSelectedValue(cable, true);
			}
		});
		bottom.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		bottom.add(btnRemove);
		
		JButton btnEdit = new JButton("Edit");
		bottom.add(btnEdit);
		
		JButton btnStartAll = new JButton("Start All");
		bottom.add(btnStartAll);
		
		JButton btnStopAll = new JButton("Stop All");
		bottom.add(btnStopAll);
		
		JPanel top = new JPanel();
		FlowLayout flowLayout = (FlowLayout) top.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(top, BorderLayout.NORTH);
		
		JLabel lblAudioRepeaterExcecutable = new JLabel("Audio Repeater Excecutable");
		top.add(lblAudioRepeaterExcecutable);
		
		textField = new JTextField("C:\\Program Files\\Virtual Audio Cable\\audiorepeater.exe");
		top.add(textField);
		textField.setColumns(28);
		
		BufferedImage icon = null;
		
		try {
			icon = ImageIO.read(VirtualAudioCableManager.class.getResourceAsStream("icon.png"));
			setIconImage(icon);
		} catch (IOException e1) {}
		
		if (SystemTray.isSupported()) {
			tray = SystemTray.getSystemTray();
			
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
			
			trayIcon = new TrayIcon(icon, "Virtual Audio Cable Manager", popupMenu);
			trayIcon.setImageAutoSize(true);
			
			trayIcon.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					if (e.getButton() == MouseEvent.BUTTON1) {
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
	
	public void updateList() {
		cableList.removeAll();
		cableList
				.setListData(VirtualAudioCableManager.getManager().cables
						.toArray(new VirtualAudioCable[VirtualAudioCableManager.getManager().cables
								.size()]));
	}
	
	private static final long serialVersionUID = 2595653495897980319L;
	private JTextField textField;
	private JList<VirtualAudioCable> cableList;
	
	public JTextField getExField() {
		return textField;
	}
}
