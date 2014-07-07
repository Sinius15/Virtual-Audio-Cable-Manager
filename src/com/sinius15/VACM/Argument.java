package com.sinius15.VACM;


public enum Argument {
	
	Input("Input"), 
	Output("Output"), 
	SamplingRate("SamplingRate"), 
	BitsPerSample("BitsPerSample"), 
	Channels("Channels"), 
	ChanCfg("ChanCfg"), 
	BufferMs("BufferMs"), 
	Buffers("Buffers"), 
	Priority("Priority"),
	WindowName("WindowName"),
	Autostart("Autostart");
	
	private String title;
	
	Argument(String title){
		this.title = title;
	}
	
	@Override
	public String toString() {
		return getTitle();
	}

	public String getTitle() {
		return title;
	}
	
}
