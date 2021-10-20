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
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage(String message) {
		return message;
	}

}
