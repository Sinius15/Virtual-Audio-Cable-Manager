package com.sinius15.VACM;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer.Info;

public class Util {
	
	public static String[] getInputDeviceNames(){
		Info[] mixerInfo = AudioSystem.getMixerInfo();

		int amount = 0;
		for(Info i : mixerInfo){
			if(i.getDescription().equals("Direct Audio Device: DirectSound Capture"))
				amount++;
		}
		String[] out = new String[amount];
		amount = 0;
		for(Info i : mixerInfo){
			if(i.getDescription().equals("Direct Audio Device: DirectSound Capture")){
				out[amount] = i.getName();
				amount++;
			}
		}
		
		return cutLength(out, 31);
	}
	
	public static String[] getOutputDeviceNames(){
		Info[] mixerInfo = AudioSystem.getMixerInfo();

		int amount = 2;
		for(Info i : mixerInfo){
			if(i.getDescription().equals("Direct Audio Device: DirectSound Playback"))
				amount++;
		}
		String[] out = new String[amount];
		out[0] = "Default";
		out[0] = "None";
		amount = 1;
		for(Info i : mixerInfo){
			if(i.getDescription().equals("Direct Audio Device: DirectSound Playback")){
				out[amount] = i.getName();
				amount++;
			}
		}
		
		return cutLength(out, 31);
	}
	
	private static String[] cutLength(String[] in, int length){
		for(int i = 0; i < in.length; i++){
			if(in[i].length() > length){
				in[i] = in[i].substring(0, 31);
				
			}
		}
		return in;
	}
	
}
