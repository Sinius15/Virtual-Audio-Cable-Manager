package com.sinius15.VACM;

public enum Argument {
	
	Input("Input"), 
	Output("Output"), 
	SamplingRate("Sampling Rate"), 
	BitsPerSample("Bits per Sample"), 
	Channels("Channels"), 
	ChanCfg("Channel Config"), 
	BufferMs("Total Buffer (ms)"), 
	Buffers("Buffers"), 
	Priority("Priority");
	
	private String title;
	
	Argument(String title){
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
