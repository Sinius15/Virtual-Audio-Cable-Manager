package com.sinius15.VACM;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class VirtualAudioCableManager {
	
	ArrayList<VirtualAudioCable> cables = new ArrayList<>();
	
	public static final String VERSION = "v1.2";
	
	public VACMFrame frame;
	
	public VirtualAudioCableManager() {
		frame = new VACMFrame();
		
	}
	
	public void startAllCables() {
		for (VirtualAudioCable cable : cables) {
			cable.startAudioCable();
		}
	}
	
	public void stopAllCables() {
		for (VirtualAudioCable cable : cables) {
			cable.stopAudioCable();
		}
	}
	
	public void restartAllCables() {
		stopAllCables();
		startAllCables();
	}
	
	public VirtualAudioCable getCable(String name) {
		for (VirtualAudioCable cable : cables) {
			if (cable.getName().equals(name))
				return cable;
		}
		return null;
	}
	
	private static VirtualAudioCableManager thiss;
	
	public static VirtualAudioCableManager getManager() {
		return thiss;
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		thiss = new VirtualAudioCableManager();
		
		if (args.length > 0) {
			try {
				ConfigLoader.loadData(new File(args[0]));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		getManager().frame.setVisible(true);
		for (int i = 1; i < args.length; i++) {
			if (args[i].equalsIgnoreCase("autoStart"))
				getManager().startAllCables();
			if (args[i].equalsIgnoreCase("autoStop"))
				getManager().stopAllCables();
			if (args[i].equalsIgnoreCase("quit"))
				System.exit(0);
			if (args[i].equalsIgnoreCase("min"))
				getManager().frame.setExtendedState(JFrame.ICONIFIED);
		}
		
	}
	
}
