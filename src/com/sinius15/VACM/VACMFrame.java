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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class VACMFrame extends JFrame {
	
	TrayIcon trayIcon;
	SystemTray tray;
	
	private VACMFrame thiss = this;
	
	public VACMFrame() {
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setVgap(5);
		borderLayout.setHgap(5);
		setTitle("Virtual Audio Cable Manager");
		setBounds(100, 100, 402, 414);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
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
				if(name == null || name.equals(""))
					return;
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
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VirtualAudioCable cable = cableList.getSelectedValue();
				if(cable == null)
					return;
				VirtualAudioCableManager.getManager().cables.remove(cable);
				updateList();
			}
		});
		bottom.add(btnRemove);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VirtualAudioCable cable = cableList.getSelectedValue();
				if(cable == null)
					return;
				VACEditFrame frame = new VACEditFrame(cable);
				thiss.setEnabled(false);
				frame.setVisible(true);
			}
		});
		bottom.add(btnEdit);
		
		JButton btnStartAll = new JButton("Start All");
		btnStartAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VirtualAudioCableManager.getManager().startAllCables();
			}
		});
		bottom.add(btnStartAll);
		
		JButton btnStopAll = new JButton("Stop All");
		btnStopAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VirtualAudioCableManager.getManager().stopAllCables();
			}
		});
		bottom.add(btnStopAll);
		
		JPanel top = new JPanel();
		FlowLayout flowLayout = (FlowLayout) top.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(top, BorderLayout.NORTH);
		
		JLabel lblAudioRepeaterExcecutable = new JLabel("Audio Repeater Excecutable");
		top.add(lblAudioRepeaterExcecutable);
		
		txtCprogramFilesvirtualAudio = new JTextField("C:\\Program Files\\Virtual Audio Cable");
		top.add(txtCprogramFilesvirtualAudio);
		txtCprogramFilesvirtualAudio.setColumns(28);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSaveConfiguration = new JMenuItem("Save Configuration");
		mntmSaveConfiguration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
				filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				filechooser.setAcceptAllFileFilterUsed(true);
				filechooser.setDialogType(JFileChooser.SAVE_DIALOG);
				int returnErrorNR = filechooser.showSaveDialog(thiss);
				if(returnErrorNR != 0)
					return;
				try {
					ConfigLoader.saveData(filechooser.getSelectedFile());
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(thiss, "Sometihng went wrong while saving the file." + System.lineSeparator() 
							+ e1.getLocalizedMessage(), "Fatal Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnFile.add(mntmSaveConfiguration);
		
		JMenuItem mntmLoadConfiguration = new JMenuItem("Load Configuration");
		mntmLoadConfiguration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
				filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				filechooser.setAcceptAllFileFilterUsed(true);
				filechooser.setDialogType(JFileChooser.OPEN_DIALOG);
				int returnErrorNR = filechooser.showSaveDialog(thiss);
				if(returnErrorNR != 0)
					return;
				try {
					ConfigLoader.loadData(filechooser.getSelectedFile());
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(thiss, "Sometihng went wrong while loading the file." + System.lineSeparator() 
							+ e1.getLocalizedMessage(), "Fatal Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnFile.add(mntmLoadConfiguration);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAboutVacm = new JMenuItem("About VacM");
		mntmAboutVacm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(thiss, "Insert verry long about text here.", "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnHelp.add(mntmAboutVacm);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnHelp.add(mntmExit);
			
		
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
	private JTextField txtCprogramFilesvirtualAudio;
	private JList<VirtualAudioCable> cableList;
	
	public JTextField getExField() {
		return txtCprogramFilesvirtualAudio;
	}
}
