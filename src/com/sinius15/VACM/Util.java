package com.sinius15.VACM;

import java.util.List;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer.Info;

public class Util {
	
	public static String[] getInputDeviceNames(){
		Info[] mixerInfo = AudioSystem.getMixerInfo();

		List<String> ls = new ArrayList<>();
		ls.add("Default");
		for(Info i : mixerInfo){
			if(i.getDescription().equals("Direct Audio Device: DirectSound Capture")){
				ls.add(i.getName());
			}
		}

		return cutLength(ls.toArray(new String[ls.size()]), 31);
	}
	
	public static String[] getOutputDeviceNames(){
		Info[] mixerInfo = AudioSystem.getMixerInfo();

		List<String> ls = new ArrayList<>();
		ls.add("Default");
		ls.add("None");
		for(Info i : mixerInfo){
			if(i.getDescription().equals("Direct Audio Device: DirectSound Playback")){
				ls.add(i.getName());
			}
		}
		
		return cutLength(ls.toArray(new String[ls.size()]), 31);
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
