package com.sinius15.VACM;

import java.util.ArrayList;

import javax.swing.JFrame;

public class VirtualAudioCableManager {
	
	ArrayList<VirtualAudioCable> cables = new ArrayList<>();
	
	public VACMFrame frame;
	
	public VirtualAudioCableManager(){
		this.frame = new VACMFrame();
		
	}
	
	public static void main(String[] args) {
		new VirtualAudioCableManager();
	}
}
