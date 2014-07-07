package com.sinius15.VACM;

public enum Argument {
	
	Input("Input", String.class), 
	Output("Output", String.class), 
	SamplingRate, 
	BitsPerSample, 
	Channels, 
	ChanCfg, 
	BufferMs, 
	Buffers, 
	Priority, 
	AutoStart;
	
	Argument(String title, Class<?> type){
		
	}
	
}
