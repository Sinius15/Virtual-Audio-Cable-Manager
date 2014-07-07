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
	private String value;
	
	Argument(String title){
		this.title = title;
	}
	
	Argument(String title, String value) {
		this.title = title;
		this.setValue(value);
	}
	
	@Override
	public String toString() {
		return getTitle();
	}

	public String getTitle() {
		return title;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
}
