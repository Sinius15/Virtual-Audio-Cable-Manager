package com.sinius15.VACM;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer.Info;

public class Util {
	
	public static String[] getInputDeviceNames(){
		
	}
	
	public static String[] getOutputDeviceNames(){
		Info[] mixerInfo = AudioSystem.getMixerInfo();
		 
		for(int i = 0; i < mixerInfo.length; i++){
			if(mixerInfo[i].getDescription().equals())
				System.out.println("Input: " + mixerInfo[i].getName());
			
				System.out.println("Output: " + mixerInfo[i].getName());
		}
		int amount = 0;
		for(Info i : mixerInfo){
			if(i.getDescription().equals("Direct Audio Device: DirectSound Playback"))
				amount++;
		}
		
		
		return out;
	}
	
}
//"Direct Audio Device: DirectSound Capture"