package com.sinius15.VACM;

import java.io.File;
import java.io.FileNotFoundException;

import com.sinius15.api.YAMLFile;

public class ConfigLoader {
	
	public static void loadData(File file) throws FileNotFoundException{
		if(!file.exists())
			throw new FileNotFoundException("Could not find file.");
		YAMLFile data = new YAMLFile(true);
		data.Load(file);
		
		VirtualAudioCableManager.getManager().frame.getExField().setText(data.getString("exeFolder"));
		
		VirtualAudioCableManager.getManager().cables.clear();
		
		for(String key : data.keySet()){
			if(key.startsWith("cable") && key.endsWith("WindowName")){  //we found a new cable!
				String name = key.split("\\.")[1];
				VirtualAudioCable cable = new VirtualAudioCable(name);
				for(Argument arg : Argument.values()){
					if(data.getString("cable." + name + "." + arg.getTitle()) != null){
						cable.setArgument(arg, data.getString("cable." + name + "." + arg.getTitle()));
					}
				}
				VirtualAudioCableManager.getManager().cables.add(cable);
			}
		}
		VirtualAudioCableManager.getManager().frame.updateList();
	}
	
	public static void saveData(File file) throws Exception{
		YAMLFile data = new YAMLFile(true);
		data.addString("exeFolder", VirtualAudioCableManager.getManager().frame.getExField().getText());
		for(VirtualAudioCable cable: VirtualAudioCableManager.getManager().cables){
			for(Argument arg : cable.arguments.keySet()){
				if(arg.equals(Argument.Autostart))
					continue;
				data.addString("cable." +cable.getName() + "." + arg.getTitle(), cable.getArgument(arg));
			}
		}
		data.Save(file);
	}
}
