package foodhub.ioObjects;

/**
 * An input protocol for specifying the qualities of an admin that is to be added or updated
 * @author 1_CW_2
 */
public class AdminInfo {

	private String username;
	private String password;
	private String name;
	
	/**
	 * Constructs a new AdminInfo object given enumerated information
	 * @param username The username of the admin
	 * @param password The password of the admin
	 * @param name The name of the admin
	 */
	public AdminInfo(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
	}
	
	/**
	 * A getter for the username field
	 * @return The username of the admin
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * A getter for the password field
	 * @return The password of the admin
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * A getter for the name field
	 * @return The name of the admin
	 */
	public String getName() {
		return name;
	}
	
}
