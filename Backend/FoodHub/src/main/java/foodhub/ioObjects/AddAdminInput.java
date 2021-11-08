package foodhub.ioObjects;

/**
 * An input protocol used in creating an admin
 * @author 1_CW_2
 */
public class AddAdminInput extends Authentication {
	
	private AdminInfo data;

	/**
	 * Constructs a new AddAdminInput object given enumerated qualities
	 * @param username The username of the owner adding the admin
	 * @param password The password of the owner adding the admin
	 * @param data The data of the admin that is to be added
	 * @see Authentication
	 */
	public AddAdminInput(String username, String password, AdminInfo data) {
		super(username, password);
		this.data = data;
	}
	
	/**
	 * A getter for the data field
	 * @return The data of the admin that is to be added
	 */
	public AdminInfo getData() {
		return data;
	}
	
}
