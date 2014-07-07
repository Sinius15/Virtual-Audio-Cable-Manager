package com.sinius15.VACM;

public enum Argument {
	
	Input("Input", String.class), 
	Output("Output", String.class), 
	SamplingRate("Sampling Rate", ), 
	BitsPerSample("Bits per Sample", ), 
	Channels("Channels", ), 
	ChanCfg("Channel Config", ), 
	BufferMs("", ), 
	Buffers("", ), 
	Priority("", ), 
	AutoStart("", );
	
	Argument(String title, Class<?> type){
		
	}
	
}
