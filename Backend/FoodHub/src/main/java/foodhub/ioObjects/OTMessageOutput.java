package foodhub.ioObjects;

import foodhub.database.OTMessage;

public class OTMessageOutput {
	
	private int sequence;
	private int who;
	private String message;
	
	public OTMessageOutput(OTMessage otm) {
		this.sequence = otm.getSequence();
		this.who = otm.getWho();
		this.message = otm.getMessage();
	}
	
	public OTMessageOutput(int sequence, int who, String message) {
		this.sequence = sequence;
		this.who = who;
		this.message = message;
	}
	
	public OTMessageOutput() {}
	
	public int getSequence() {
		return sequence;
	}
	
	public int getWho() {
		return who;
	}
	
	public String getMessage() {
		return message;
	}

}
