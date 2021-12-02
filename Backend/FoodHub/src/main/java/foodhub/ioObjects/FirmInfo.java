package foodhub.ioObjects;

/**
 * An input protocol for specifying the qualities of a firm
 * @author 1_CW_2
 */
public class FirmInfo {

	private String username;
	private String password;
	private String name;
	private String location;
	private String cuisine;
	private int open_time;
	private int close_time;
	private int employee_count;
	private String imageSource;

	/**
	 * Constructs a FirmInfo object given enumerated information
	 * @param username The username of the firm
	 * @param password The password of the firm
	 * @param name The name of the firm
	 * @param location The location of the firm
	 * @param cuisine The cuisine of the firm
	 * @param open_time The time the firm opens, expressed as an int
	 * @param close_time The time the firm closes, expressed as an int
	 * @param employee_count The number of employees that a firm has
	 */
	public FirmInfo(String username, String password, String name, String location, String cuisine,
					int open_time,   int close_time,  int employee_count, String imageSource) {
		this.name = name;
		this.password = password;
		this.username = username;
		this.location = location;
		this.cuisine = cuisine;
		this.open_time = open_time;
		this.close_time = close_time;
		this.employee_count = employee_count;
		this.imageSource = imageSource;
	}
	
	/**
	 * A default constructor
	 */
	public FirmInfo() {}

	/**
	 * A getter for the username field
	 * @return The username of the firm
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * A getter for the password field
	 * @return The password of the firm
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * A getter for the name field
	 * @return The name of the firm
	 */
	public String getName() {
		return name;
	}

	/**
	 * A getter for the location field
	 * @return The location of a firm
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * A getter for the cuisine field
	 * @return The cuisine of a firm
	 */
	public String getCuisine() {
		return cuisine;
	}

	/**
	 * A getter for the open_time field
	 * @return The time a firm opens, expressed as an int
	 */
	public int getOpen_time() {
		return open_time;
	}

	/**
	 * A getter for the close_time field
	 * @return The time a firm closes, expressed as an int
	 */
	public int getClose_time() {
		return close_time;
	}

	/**
	 * A getter for the employee_count field
	 * @return The number of employees a firm has
	 */
	public int getEmployee_count() {
		return employee_count;
	}
	
	/**
	 * A getter for the imageSource field
	 * @return The string of the image
	 */
	public String getImageSource() {
		return imageSource;
	}
	
}
