package com.sinius15.VACM;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class VirtualAudioCable {
	
	private String name;
	
	private HashMap<Argument, String> arguments = new HashMap<Argument, String>();
	
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
		arguments.put(Argument.WindowName, this.name);
		arguments.put(Argument.Autostart, null);
		
	}

	public void startAudioCable(){
		ArrayList<String> args = new ArrayList<>();
		args.add("cmd.exe");
		//args.add("/c");
		args.add("start");
		args.add("/min");
		args.add("audiorepeater.exe");
		for(Argument key : arguments.keySet()){
			String bui = "/" + key;
			if(arguments.get(key) != null){ //argument has a second parameter
				if(arguments.get(key).matches("[0-9]+"))  //argument is a number
					bui += ": " + arguments.get(key);
				else  //argument is a string and needs "
					bui += ": " + "'" + arguments.get(key) + "'";
			}
			args.add(bui);
		}
		
		ProcessBuilder builder = new ProcessBuilder(args);
		builder.directory(new File(VirtualAudioCableManager.getManager().frame.getExField().getText()));
		
		System.out.println(Arrays.toString(args.toArray()));
		
		try {
			builder.start();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stopAudioCable(){
		ProcessBuilder builder = new ProcessBuilder("start audiorepeater.exe /CloseInstance: " + name);
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
