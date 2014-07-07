package com.sinius15.VACM;

import java.util.ArrayList;

public class VirtualAudioCableManager {
	
	ArrayList<VirtualAudioCable> cables = new ArrayList<>();
	
	public VACMFrame frame;
	
	public VirtualAudioCableManager(){
		this.frame = new VACMFrame();
		this.frame.setVisible(true);
	}
	
	private static VirtualAudioCableManager thiss;
	public static VirtualAudioCableManager getManager(){
		return thiss;
	}
	
	public static void main(String[] args) {
		thiss = new VirtualAudioCableManager();
	}
}
