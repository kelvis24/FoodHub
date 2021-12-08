package foodhub.ioObjects;

public class MessageAndString extends Message {

	private String data;

	public MessageAndString(String message, String error, String data) {
		super(message, error);
		this.data = data;
	}
	
	public String getData() {
		return data;
	}

}
