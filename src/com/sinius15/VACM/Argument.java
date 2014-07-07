package com.sinius15.VACM;

public enum Argument {
	
	Input("Input", String.class), 
	Output("Output", String.class), 
	SamplingRate("Sampling Rate", Integer.class), 
	BitsPerSample("Bits per Sample", Integer.class), 
	Channels("Channels", Integer.class), 
	ChanCfg("Channel Config", String.class), 
	BufferMs("Total Buffer (ms)", Integer.class), 
	Buffers("Buffers", Integer.class), 
	Priority("Priority", String.class);
	
	
	
	Argument(String title, Class<?> type){
		
	}
	
}
