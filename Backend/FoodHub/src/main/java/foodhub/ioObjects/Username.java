package foodhub.ioObjects;

/**
 * An input protocol for specifying a username
 * @author 1_CW_2
 */
public class Username {
	
	private String username;
	
	/**
	 * Constructs a new Username object from a username
	 * @param username A username
	 */
	public Username(String username) {
		this.username = username;
	}
	
	/**
	 * A getter for the username field
	 * @return The username carried herein
	 */
	public String getUsername() {
		return username;
	}
	
}