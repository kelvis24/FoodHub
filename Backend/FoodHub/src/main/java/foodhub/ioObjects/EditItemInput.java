package foodhub.ioObjects;

/**
 * An input protocol used in editing an item
 * @author 1_CW_2
 */
public class EditItemInput extends Authentication {

	private long itemId;
	private ItemInfo data;

	/**
	 * Constructs a new EditItemInput from enumerated information
	 * @param username The username of the firm editing the item
	 * @param password The password of the firm editing the item
	 * @param itemId The id of the item which is to be edited
	 * @param data The new qualities of the item
	 * @see AddItemInput
	 */
	public EditItemInput(String username, String password, long itemId, ItemInfo data) {
		super(username, password);
		this.itemId = itemId;
		this.data = data;
	}
	
	/**
	 * A getter for the itemId field
	 * @return The id of the item that is to be changed
	 */
	public long getItemId() {
		return itemId;
	}
	
	/**
	 * A getter for the data field
	 * @return The new qualities of the item that
	 */
	public ItemInfo getData() {
		return data;
	}
	
}
