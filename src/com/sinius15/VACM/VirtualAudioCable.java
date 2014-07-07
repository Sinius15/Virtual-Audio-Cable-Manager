package com.sinius15.VACM;

import java.util.HashMap;

public class VirtualAudioCable {
	
	HashMap<String, String> arguments = new HashMap<String, String>();
	
	/**
	 * 
	 * @param windowName must exist of letters.
	 */
	public VirtualAudioCable(String windowName){
		if(!windowName.matches("[a-zA-Z]+")){
			throw new IllegalArgumentException("windowName must exist of letters.");
			
		}
			
		arguments.put("WindowName:", "\""+windowName+"\"");
	}
	
	
	
}
