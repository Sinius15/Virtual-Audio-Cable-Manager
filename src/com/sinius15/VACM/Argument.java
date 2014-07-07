package com.sinius15.VACM;


public enum Argument {
	
	Input("Input", "Default"), 
	Output("Output", "Default"), 
	SamplingRate("SamplingRate", "48000"), 
	BitsPerSample("BitsPerSample", "16"), 
	ChanCfg("ChanCfg", "Stereo"), 
	BufferMs("BufferMs", "500"), 
	Buffers("Buffers", "12"), 
	Priority("Priority", "Normal"),
	WindowName("WindowName", null),
	Autostart("Autostart", null);
	
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
