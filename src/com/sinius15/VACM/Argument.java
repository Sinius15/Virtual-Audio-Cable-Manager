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
	private String value;
	
	Argument(String title){
		this.title = title;
	}
	Argument(String title, String value) {
		this.title = title;
		this.setValue(value);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
