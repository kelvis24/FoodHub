package foodhub.ioObjects;

/**
 * An input protocol used in requests returning lists of categories and items.
 * It holds a single id.
 * @author 1_CW_2
 */
public class Id {
	
	private long id;
	
	/**
	 * Constructs a new Id object given an id
	 * @param id An id
	 */
	public Id(long id) {
		this.id = id;
	}
	
	/**
	 * A default constructor
	 */
	public Id() {}
	
	/**
	 * A getter method for the id field
	 * @return The id held by this object
	 */
	public long getId() {
		return id;
	}

}
