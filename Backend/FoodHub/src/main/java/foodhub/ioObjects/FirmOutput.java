package foodhub.ioObjects;

import foodhub.database.Firm;

/**
 * An output protocol for specifying the qualities of a firm
 * @author 1_CW_2
 */
public class FirmOutput {

	private long id;
	private String username;
	private String name;
	private String location;
	private String cuisine;
	private int open_time;
	private int close_time;
	private int employee_count;

	/**
	 * Constructs a FirmOutput object from a firm entity, from the database
	 * @param f A firm entity, from the database
	 */
	public FirmOutput(Firm f) {
		this.id = f.getId();
		this.name = f.getName();
		this.username = f.getUsername();
		this.location = f.getLocation();
		this.cuisine = f.getCuisine();
		this.open_time = f.getOpen_time();
		this.close_time = f.getClose_time();
		this.employee_count = f.getEmployee_count();
	}

	/**
	 * Constructs a FirmOutput object from enumerated information
	 * @param id The id of the firm
	 * @param name The name of the firm
	 * @param username The username of the firm
	 * @param location The location of the firm
	 * @param cuisine The cuisine of the firm
	 * @param open_time The time the firm opens, expressed as an int
	 * @param close_time The time the firm closes, expressed as an int
	 * @param employee_count The numer of employees that the firm has
	 */
	public FirmOutput(long id, String name, String username, String location, String cuisine,
					int open_time,   int close_time,  int employee_count, String imageSource) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.location = location;
		this.cuisine = cuisine;
		this.open_time = open_time;
		this.close_time = close_time;
		this.employee_count = employee_count;
	}
	
	/**
	 * A default constructor
	 */
	public FirmOutput() {}
	
	/**
	 * A getter for the id field
	 * @return The id of the firm
	 */
	public long getId() {
		return id;
	}

	/**
	 * A getter for the username field
	 * @return The username of the firm
	 */
	public String getUsername() {
		return username;
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
	 * @return The location of the firm
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * A getter for the cuisine field
	 * @return The cuisine of the firm
	 */
	public String getCuisine() {
		return cuisine;
	}

	/**
	 * A getter for the open_time field
	 * @return The time that the firm opens, expressed as an int
	 */
	public int getOpen_time() {
		return open_time;
	}

	/**
	 * A getter for the close_time field
	 * @return The time that the firm closes, expressed as an int
	 */
	public int getClose_time() {
		return close_time;
	}

	/**
	 * A getter for the employee_count field
	 * @return The number of employees that work at the firm
	 */
	public int getEmployee_count() {
		return employee_count;
	}
}
