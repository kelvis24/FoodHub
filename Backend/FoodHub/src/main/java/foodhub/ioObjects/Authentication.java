package foodhub.ioObjects;

/**
 * An input protocol for the authentication of users, as one who does actions
 * @author 1_CW_2
 */
public class Authentication extends Username {
	
	private String password;

	/**
	 * Constructs an Authentication object given enumerated information
	 * @param username The username of the actor
	 * @param password The password of the actor
	 * @see Username
	 */
	public Authentication(String username, String password) {
		super(username);
		this.password = password;
	}
	
	/**
	 * A getter for the password field
	 * @return The password of the actor
	 */
	public String getPassword() {
		return password;
	}
	
}
