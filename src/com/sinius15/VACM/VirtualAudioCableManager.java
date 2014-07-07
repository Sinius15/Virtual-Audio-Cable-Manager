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
		
	}
	public void stopAllCables(){
		
	}
	public void restartAllCables(){
		stopAllCables();
		startAllCables();
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
