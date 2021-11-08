package foodhub.ioObjects;

/**
 * An input protocol used in editing an admin
 * @author 1_CW_2
 *
 */
public class EditAdminInput extends AddAdminInput {
	
	private long adminId;

	/**
	 * Constructs a new EditAdminInput object with enumerated information
	 * @param username The username of the owner editing the admin
	 * @param password The password of the owner editing the admin
	 * @param data The new qualities of the admin
	 * @param adminId The id of the admin that is to be changed
	 * @see AddAdminInput
	 */
	public EditAdminInput(String username, String password, AdminInfo data, long adminId) {
		super(username, password, data);
		this.adminId = adminId;
	}
	
	/**
	 * A getter for the adminId field
	 * @return The id of the admin that is to be changed
	 */
	public long getAdminId() {
		return adminId;
	}
	
}
