package com.sinius15.VACM;


public enum Argument {
	
	Input("Input", "Default"), 
	Output("Output", "Default"), 
	SamplingRate("SamplingRate", ""), 
	BitsPerSample("BitsPerSample", ""), 
	Channels("Channels", ""), 
	ChanCfg("ChanCfg", ""), 
	BufferMs("BufferMs", ""), 
	Buffers("Buffers", ""), 
	Priority("Priority", ""),
	WindowName("WindowName", ""),
	Autostart("Autostart", "");
	
	private String title, defaul;
	
	Argument(String title, String defaul){
		this.defaul = defaul;
		this.title = title;
	}
	
	@Override
	public String toString() {
		return getTitle();
	}

	public String getTitle() {
		return title;
	}

	public String getDefaul() {
		return defaul;
	}
	
}
