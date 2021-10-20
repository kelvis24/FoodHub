package foodhub.ioObjects;

public class Message {
	
	private String message;
	private String error;
	
	public Message(String message, String error) {
		this.message = message;
		this.error = error;
	}
	
	public Message(String message) {
		this.message = message;
		this.error = "";
	}
	
	public Message() {}
	
	public String getMessage() {
		return message;
	}
	
	public String getError() {
		return error;
	}

}
