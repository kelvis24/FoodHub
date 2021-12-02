package foodhub.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import foodhub.ioObjects.FirmInfo;

/**
 * Specifies the field of the firm table
 * @author 1_CW_2
 */
@Entity
@Table(name="firms")
public class Firm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, unique = true, length = 100)
	private String username;
	@Column(nullable = false, length = 100)
	private String password;
	@Column(nullable = false, length = 100)
	private String name;
	@Column(nullable = false, length = 100)
	private String location;
	@Column(nullable = false, length = 100)
	private String cuisine;
	@Column(nullable = false)
	private int open_time;
	@Column(nullable = false)
	private int close_time;
	@Column(nullable = false)
	private int employee_count;

	/**
	 * Constructs a new firm given information for it
	 * @param firm Information for the new firm
	 */
	public Firm(FirmInfo firm) {
		this.username = firm.getUsername();
		this.password = firm.getPassword();
		this.name = firm.getName();
		this.location = firm.getLocation();
		this.cuisine = firm.getCuisine();
		this.open_time = firm.getOpen_time();
		this.close_time = firm.getClose_time();
		this.employee_count = firm.getEmployee_count();
	}
	
	/**
	 * Constructs a new firm given enumerated information
	 * @param username The username of the new firm
	 * @param password The password of the new firm
	 * @param name The name of the new firm
	 * @param location The location of the new firm
	 * @param cuisine The cuisine of the new firm
	 * @param open_time The opening time of the new firm, expressed as an int
	 * @param close_time The closing time of the new firm, expressed as an int
	 * @param employee_count The number of employees at that firm, expressed as an int
	 */
	public Firm(String username, String password, String name, String location,
				String cuisine, int open_time,   int close_time,  int employee_count) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.location = location;
		this.cuisine = cuisine;
		this.open_time = open_time;
		this.close_time = close_time;
		this.employee_count = employee_count;
	}
	
	/**
	 * A default constructor
	 */
	public Firm() {}

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
	 * @return The opening time of the firm, as expressed as an int
	 */
	public int getOpen_time() {
		return open_time;
	}

	/**
	 * A getter for the close_time field
	 * @return The closing time of the firm, as expressed as an int
	 */
	public int getClose_time() {
		return close_time;
	}

	/**
	 * A getter for the employee_count field
	 * @return The number of employees at the firm
	 */
	public int getEmployee_count() {
		return employee_count;
	}
}
