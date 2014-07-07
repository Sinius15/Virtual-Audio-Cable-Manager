package com.sinius15.VACM;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer.Info;

public class Util {
	
	public static String[] getInputDeviceNames(){
		
	}
	
	public static String[] getOutputDeviceNames(){
		Info[] mixerInfo = AudioSystem.getMixerInfo();

		int amount = 0;
		for(Info i : mixerInfo){
			if(i.getDescription().equals("Direct Audio Device: DirectSound Playback"))
				amount++;
		}
		String[] out = new String[amount];
		amount = 0;
		for(Info i : mixerInfo){
			if(i.getDescription().equals("Direct Audio Device: DirectSound Playback")){
				out[amount] = i.getName();
				amount++;
			}
		}
		
		return out;
	}
	
}
//"Direct Audio Device: DirectSound Capture"