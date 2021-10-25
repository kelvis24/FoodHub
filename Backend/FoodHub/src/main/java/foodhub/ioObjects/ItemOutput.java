package foodhub.ioObjects;

import foodhub.database.Item;

public class ItemOutput {
	
	private long id;
	private String title;
	private String description;
	private double price;
	
	public ItemOutput(Item item) {
		this.id = item.getId();
		this.title = item.getTitle();
		this.description = item.getDescription();
		this.price = item.getPrice();
	}
	
	public ItemOutput(long id, String title, String description, double price) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
	}
	
	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}
	
}
