package com.sinius15.VACM;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class VirtualAudioCable {
	
	private String name;
	
	private HashMap<String, String> arguments = new HashMap<String, String>();
	
	/**
	 * Creates a new {@link VirtualAudioCable}
	 * 
	 * @param windowName
	 *            the windowName of the AudioRepeater. windowName must exist of
	 *            letters.
	 */
	public VirtualAudioCable(String windowName) {
		if (!windowName.matches("[a-zA-Z]+"))
			throw new IllegalArgumentException("windowName must exist of letters.");
		arguments.put("WindowName:", "\"" + windowName + "\"");
		arguments.put("Autostart", null);
		this.name = windowName;
	}

	public void startAudioCable(){
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "start", "/min", "audiorepeater", "");
		builder.directory(new File(System.getProperty("user.dir")));
		builder.redirectErrorStream(true);
		try {
			Process p = builder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setArgument(Argument a){
		arguments.put(a.getTitle(), a.getValue());
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
