package com.sinius15.VACM;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class VirtualAudioCable {
	
	private String name;
	
	public HashMap<Argument, String> arguments = new HashMap<Argument, String>();
	
	/**
	 * Creates a new {@link VirtualAudioCable}
	 * 
	 * @param windowName
	 *            the windowName of the AudioRepeater. windowName must exist of
	 *            letters.
	 */
	public VirtualAudioCable(String windowName) {
		this.name = windowName;
		if (!windowName.matches("[a-zA-Z]+"))
			throw new IllegalArgumentException("windowName must exist of letters.");
		arguments.put(Argument.Autostart, null);
		arguments.put(Argument.WindowName, this.name);
		
	}

	public void startAudioCable(){
		
		String argumentString = "";
		for(Argument key : arguments.keySet()){
			String bui = "/" + key;
			if(arguments.get(key) != null){ //argument has a second parameter
				if(arguments.get(key).matches("[0-9]+"))  //argument is a number
					bui += ": " + arguments.get(key);
				else  //argument is a string and needs "
					bui += ": " + "\"" + arguments.get(key) + "\"";
			}
			argumentString += " " + bui;
		}

		
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "start /min audiorepeater.exe" + argumentString);
		builder.directory(new File(VirtualAudioCableManager.getManager().frame.getExField().getText()));
		
		try {
			builder.start();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stopAudioCable(){
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "start audiorepeater.exe /CloseInstance: " + name);
		builder.directory(new File(VirtualAudioCableManager.getManager().frame.getExField().getText()));
		
		try {
			builder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setArgument(Argument a, String value){
		arguments.put(a, value);
	}
	
	public String getArgument(Argument a){
		String out = arguments.get(a);
		if(out == null)
			return a.getDefaul();
		return out;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
