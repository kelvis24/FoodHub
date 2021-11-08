package foodhub.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import foodhub.ioObjects.CustomerInfo;

/**
 * Specifies the format of the table of customers
 * @author 1_CW_2
 */
@Entity
@Table(name="customers")
public class Customer {
	
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
	
	/**
	 * Constructs a customer given information thereon
	 * @param customer Information about a customer, typically received through a request body
	 */
	public Customer(CustomerInfo customer) {
		this.username = customer.getUsername();
		this.password = customer.getPassword();
		this.name = customer.getName();
		this.location = customer.getLocation();
	}
	
	/**
	 * Constructs a customer given all information thereon
	 * @param username The username of the new customer
	 * @param password The password of the new customer
	 * @param name The name of the new customer
	 * @param location The location of the new customer
	 */
	public Customer(String username, String password, String name, String location) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.location = location;
	}
	
	/**
	 * A default constructor
	 */
	public Customer() {}
	
	/**
	 * A getter for the id field
	 * @return The id of the customer
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * A getter for the username field
	 * @return The usernmae of the customer
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
	 * A getter for the name of the customer
	 * @return The name of the customer
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * A getter for the location of the customer
	 * @return The location of the customer
	 */
	public String getLocation() {
		return location;
	}

}
