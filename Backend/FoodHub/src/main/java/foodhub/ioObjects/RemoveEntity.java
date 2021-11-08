package foodhub.ioObjects;

/**
 * An input protocol used in removing an entity
 * @author 1_CW_2
 */
public class RemoveEntity extends Authentication {

	private long id;

	/**
	 * Constructs a RemoveEntity object from enumerated information
	 * @param username The username of the actor
	 * @param password The password of the actor
	 * @param id The id of the entity that is to be removed
	 * @see Authentication
	 */
	public RemoveEntity(String username, String password, long id) {
		super(username, password);
		this.id = id;
	}

	/**
	 * A getter for the id field
	 * @return The id of the item that is to be removed
	 */
	public long getId() {
		return id;
	}
	
}