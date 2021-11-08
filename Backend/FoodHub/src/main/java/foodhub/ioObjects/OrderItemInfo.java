package foodhub.ioObjects;

/**
 * An input protocol for the qualities of OrderItem relations
 * @author 1_CW_2
 */
public class OrderItemInfo {

	private long itemId;
	private int quantity;
	private String notes;
	
	/**
	 * Constructs a new OrderItemInfo object from enumerated information
	 * @param itemId The id of the item related
	 * @param quantity The quantity of the item related
	 * @param notes The notes regarding the item related
	 */
	public OrderItemInfo(long itemId, int quantity, String notes) {
		this.itemId = itemId;
		this.quantity = quantity;
		this.notes = notes;
	}
	
	/**
	 * A default constructor
	 */
	public OrderItemInfo() {}

	/**
	 * A getter for the itemId field
	 * @return The id of the item related
	 */
	public long getItemId() {
		return itemId;
	}
	
	/**
	 * A getter for the quantity field
	 * @return The quantity of the item related
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * A getter for the notes field
	 * @return The notes regarding the item related
	 */
	public String getNotes() {
		return notes;
	}
	
}
