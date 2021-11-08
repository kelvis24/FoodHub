package foodhub.ioObjects;

import foodhub.database.Item;

/**
 * An input protocol for specifying the qualities of an item
 * @author 1_CW_2
 */
public class ItemInfo {
	
	private String title;
	private String description;
	private double price;
	
	/**
	 * Constructs a new ItemInfo object from an item entity, from the database
	 * @param item An item entity, from the database
	 */
	public ItemInfo(Item item) {
		this.title = item.getTitle();
		this.description = item.getDescription();
		this.price = item.getPrice();
	}
	
	/**
	 * Constructs a new ItemInfo object from enumerated information
	 * @param title The title of the item
	 * @param description The description of the item
	 * @param price The price of an item
	 */
	public ItemInfo(String title, String description, double price) {
		this.title = title;
		this.description = description;
		this.price = price;
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
