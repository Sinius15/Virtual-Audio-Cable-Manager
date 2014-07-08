package com.sinius15.VACM;

import java.io.File;
import java.io.FileNotFoundException;

import com.sinius15.api.YAMLFile;

public class ConfigLoader {
	
	public static Exception loadData(File file) throws FileNotFoundException{
		if(!file.exists())
			throw new FileNotFoundException("Could not find file");
		YAMLFile data = new YAMLFile(true);
		data.Load(file);
		
		for(String key : data.keySet()){
			System.out.println(key);
		}
		
		return null;
	}
	
	public static void saveData(File file){
		
	}
}
