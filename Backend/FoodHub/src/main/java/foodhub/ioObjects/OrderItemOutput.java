package foodhub.ioObjects;

import foodhub.database.OrderItem;
import foodhub.database.Item;

/**
 * An output protocol for specifying the qualities of an OrderItem relation
 * @author 1_CW_2
 */
public class OrderItemOutput {
	
	private String title;
	private String description;
	private int quantity;
	private double price;
	private String notes;
	
	/**
	 * Constructs a new OrderItemOutput object from an OrderItem entity, from the database and the item it's related to
	 * @param itemOrder The OrderItem entity from the database
	 * @param item The item related by the OrderItem entity
	 */
	public OrderItemOutput(OrderItem itemOrder, Item item) {
		this.title = item.getTitle();
		this.description = item.getDescription();
		this.quantity = itemOrder.getQuantity();
		this.price = this.quantity * item.getPrice();
		this.notes = itemOrder.getNotes();
	}
	
	/**
	 * Constructs a new OrderItemOutput object from enumerated information
	 * @param title The title of the item related
	 * @param description The description of the item related
	 * @param quantity The quantity of the item related according to the relation
	 * @param price The price of the item related
	 * @param notes The notes regarding the item related
	 */
	public OrderItemOutput(String title, String description, int quantity, double price, String notes) {
		this.title = title;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.notes = notes;
	}
	
	/**
	 * A getter for the title field
	 * @return The title of the item related
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * A getter for the description field
	 * @return The description of the item related
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * A getter for the quantity field
	 * @return The quantity of the item related according to the relation
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * A getter for the price field
	 * @return The price of the item related
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * A getter for the notes field
	 * @return The notes regarding the item related
	 */
	public String getNotes() {
		return notes;
	}

}
