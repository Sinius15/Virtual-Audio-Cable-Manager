package com.sinius15.VACM;

import java.io.File;
import java.io.FileNotFoundException;

public class ConfigLoader {
	
	public static Exception loadData(File file){
		if(file.exists())
			return new FileNotFoundException("Could not find file");
		
		return null;
	}
	
	public static void saveData(File file){
		
	}
}
