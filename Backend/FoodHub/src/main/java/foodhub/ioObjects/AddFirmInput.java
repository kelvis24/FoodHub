package foodhub.ioObjects;

/**
 * An input protocol used in adding a firm
 * @author 1_CW_2
 */
public class AddFirmInput extends Authentication {
	
	private FirmInfo data;

	/**
	 * Constructs an AddFirmInput object given enumerated information
	 * @param username The username of the admin adding the firm
	 * @param password The password of the admin adding the firm
	 * @param data The qualities of the firm that is being added
	 * @see Authentication
	 */
	public AddFirmInput(String username, String password, FirmInfo data) {
		super(username, password);
		this.data = data;
	}
	
	/**
	 * A getter method for the data field
	 * @return The qualities of the firm that is being added
	 */
	public FirmInfo getData() {
		return data;
	}
	
}
