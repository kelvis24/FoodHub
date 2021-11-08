package foodhub.ioObjects;

/**
 * An input protocol used in adding an item
 * @author 1_CW_2
 */
public class AddItemInput extends Authentication {

	private long categoryId;
	private ItemInfo data;
	
	/**
	 * Constructs an AddItemInput given enumerated information
	 * @param username The username of the firm adding the item
	 * @param password The password of the firm adding the item
	 * @param categoryId The id of the category to which this item will belong
	 * @param data The qualities of the item that is to be added
	 * @see Authentication
	 */
	public AddItemInput(String username, String password, long categoryId, ItemInfo data) {
		super(username, password);
		this.categoryId = categoryId;
		this.data = data;
	}
	
	/**
	 * A getter for the categoryId field
	 * @return The id of the category to which the item will be added
	 */
	public long getCategoryId() {
		return categoryId;
	}
	
	/**
	 * A getter for the data field
	 * @return The qualities of the item that will be added
	 */
	public ItemInfo getData() {
		return data;
	}
	
}
