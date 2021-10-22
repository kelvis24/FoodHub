package foodhub.ioObjects;

import foodhub.database.Item;

public class ItemInfo {
	
	private String title;
	private String description;
	private double price;
	
	public ItemInfo(Item item) {
		this.title = item.getTitle();
		this.description = item.getDescription();
		this.price = item.getPrice();
	}
	
	public ItemInfo(String title, String description, double price) {
		this.title = title;
		this.description = description;
		this.price = price;
	}

	public ItemInfo() {}

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
