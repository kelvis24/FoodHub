package foodhub.ioObjects;

import foodhub.database.OrderItem;
import foodhub.database.Item;

public class OrderItemOutput {
	
	private String title;
	private String description;
	private int quantity;
	private double price;
	private String notes;
	
	public OrderItemOutput(OrderItem itemOrder, Item item) {
		this.title = item.getTitle();
		this.description = item.getDescription();
		this.quantity = itemOrder.getQuantity();
		this.price = this.quantity * item.getPrice();
		this.notes = itemOrder.getNotes();
	}
	
	public OrderItemOutput(String title, String description, int quantity, double price, String notes) {
		this.title = title;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.notes = notes;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getNotes() {
		return notes;
	}

}
