package com.sinius15.VACM;

import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class VirtualAudioCableManager {
	
	ArrayList<VirtualAudioCable> cables = new ArrayList<>();
	
	public VACMFrame frame;
	
	public VirtualAudioCableManager(){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame = new VACMFrame();
				frame.setVisible(true);
			}
		});
	}
	
	public void startAllCables(){
		for(VirtualAudioCable cable: cables){
			cable.startAudioCable();
		}
	}
	public void stopAllCables(){
		for(VirtualAudioCable cable: cables){
			cable.stopAudioCable();
		}
	}
	public void restartAllCables(){
		stopAllCables();
		startAllCables();
	}
	public boolean hasCableWithName(String name) {
		for(VirtualAudioCable cable: cables){
			if(cable.getName().equals(name))
				return true;
		}
		return false;
	}
	
	private static VirtualAudioCableManager thiss;
	public static VirtualAudioCableManager getManager(){
		return thiss;
	}
	
	public static void main(String[] args) {
		try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){
            e.printStackTrace();
        }
		
		thiss = new VirtualAudioCableManager();
	}

	
}
