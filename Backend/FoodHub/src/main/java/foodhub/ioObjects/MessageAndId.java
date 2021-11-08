package foodhub.ioObjects;

/**
 * An output protocol for sending an message and an id; used in authenticating a firm
 * @author 1_CW_2
 */
public class MessageAndId extends Message {

	private long id;

	/**
	 * Constructs a MessageAndId object specifying a message and error
	 * @param message Specifies a message
	 * @param error Specifies an error
	 * @see Message
	 */
	public MessageAndId(String message, String error) {
		super(message, error);
		this.id = -1;
	}

	/**
	 * Constructs a MessageAndId object specifying a message and id
	 * @param message Specifies a message
	 * @param id Specifies an id
	 */
	public MessageAndId(String message, long id) {
		super(message);
		this.id = id;
	}
	
	/**
	 * A getter for the id field
	 * @return Returns the specified id, or -1 if not specified
	 */
	public long getId() {
		return id;
	}

}
