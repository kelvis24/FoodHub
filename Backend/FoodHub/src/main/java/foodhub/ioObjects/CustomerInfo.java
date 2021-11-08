package foodhub.ioObjects;

/**
 * An input protocol for the qualities of a customer
 * @author 1_CW_2
 */
public class CustomerInfo {
	
	private String username;
	private String password;
	private String name;
	private String location;
	
	/**
	 * Constructs a new CustomerInfo object
	 * @param username The username of the customer
	 * @param password The password of the customer
	 * @param name The name of the customer
	 * @param location The location of the customer
	 */
	public CustomerInfo(String username, String password, String name, String location) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.location = location;
	}
	
	/**
	 * A getter for the username field
	 * @return The username of the customer
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * A getter for the password field
	 * @return The password of the customer
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * A getter for the name field
	 * @return The name of the customer
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * A getter for the location field
	 * @return The location of the customer
	 */
	public String getLocation() {
		return location;
	}

}
