package foodhub.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import foodhub.ioObjects.AdminInfo;

/**
 * Specifies a table in the database, where each entity is an admin
 * 
 * @author 1_CW_2
 */
@Entity
@Table(name="admins")
public class Admin {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, unique = true, length = 100)
	private String username;
	@Column(nullable = false, length = 100)
	private String password;
	@Column(nullable = false, length = 100)
	private String name;
	@Column(nullable = false)
	private int type; // 0 => Admin; 1 => Owner
	
	/**
	 * Creates a new admin given an AdminInfo object
	 * 
	 * @param admin information about a new admin
	 */
	public Admin(AdminInfo admin) {
		this.username = admin.getUsername();
		this.password = admin.getPassword();
		this.name = admin.getName();
		this.type = 0;
	}
	
	/**
	 * Creates a new Admin given each property thereof
	 * 
	 * @param username username of the admin
	 * @param password password of the admin
	 * @param name name of the admin
	 * @param type 1 for owner, 0 for not owner
	 */
	public Admin(String username, String password, String name, int type) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.type = type;
	}

	/**
	 * Default constructor
	 */
	public Admin() {}
	
	/**
	 * A getter for the id field
	 * @return the id of an admin
	 */
	public long getId() {
		return id;
	}

	/**
	 * A setter for the id field
	 * @param id The new id of the admin
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * A getter for the username field
	 * @return the username of an admin
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

	/**
	 * A getter for the type field
	 * @return The type of the admin
	 */
	public int getType() {
		return type;
	}
	
}
