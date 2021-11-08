package foodhub.ioObjects;

import foodhub.database.Item;

/**
 * An output protocol for specifying the qualities of an item
 * @author 1_CW_2
 */
public class ItemOutput {
	
	private long id;
	private String title;
	private String description;
	private double price;
	
	/**
	 * Constructs an ItemOutput from an item entity, from the database
	 * @param item An item entity, from the database
	 */
	public ItemOutput(Item item) {
		this.id = item.getId();
		this.title = item.getTitle();
		this.description = item.getDescription();
		this.price = item.getPrice();
	}

	/**
	 * Constructs an ItemOutput from enumerated information
	 * @param id The id of the item
	 * @param title The title of the item
	 * @param description The description of the item
	 * @param price The price of the item
	 */
	public ItemOutput(long id, String title, String description, double price) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
	}
	
	/**
	 * A getter for the id field
	 * @return The id of the item
	 */
	public long getId() {
		return id;
	}

	/**
	 * A getter for the title field
	 * @return The title of the item
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * A getter for the description field
	 * @return The description of the item
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * A getter for the price field
	 * @return The price of the item
	 */
	public double getPrice() {
		return price;
	}
	
}
