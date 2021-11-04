package foodhub.ioObjects;

public class MessageAndId extends Message {

	private long id;

	public MessageAndId(String message, String error) {
		super(message, error);
		this.id = -1;
	}

	public MessageAndId(String message, long id) {
		super(message);
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

}
