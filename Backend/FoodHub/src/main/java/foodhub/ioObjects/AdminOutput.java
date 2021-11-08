package foodhub.ioObjects;

import foodhub.database.Admin;

/**
 * An output protocol for the qualities of admins
 * @author gusta
 *
 */
public class AdminOutput {
	
	private long id;
	private String username;
	private String name;
	private int type;

	/**
	 * Constructs an AdminOutput object from an Admin entity
	 * @param admin An Admin entity, of the database
	 */
	public AdminOutput(Admin admin) {
		this.id = admin.getId();
		this.username = admin.getUsername();
		this.name = admin.getName();
		this.type = admin.getType();
	}
	
	/**
	 * Constructs an AdminOutput object from enumerated information
	 * @param id The id of the admin
	 * @param username The username of the admin
	 * @param name The name of the admin
	 * @param type The type of admin (1 if the admin is an owner)
	 */
	public AdminOutput(long id, String username, String name, int type) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.type = type;
	}
	
	/**
	 * A getter for the id field
	 * @return The id of the admin
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * A getter for the usernmae field
	 * @return The username of the admin
	 */
	public String getUsername() {
		return username;
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
	 * @return The type of admin (1 if the admin is an owner)
	 */
	public int getType() {
		return type;
	}
	
}
