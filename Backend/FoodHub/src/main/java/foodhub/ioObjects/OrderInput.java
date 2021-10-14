package foodhub.ioObjects;

import foodhub.database.Item;

public class OrderInput {
	
	private String username;
	private String password;
	private Long orderId;
	private Item item;
	private int quantity;
	private String notes;

	public OrderInput(String username, String password, Long orderId) {
		this.username = username;
		this.password = password;
		this.orderId = orderId;
	}
	
	public OrderInput(String username, String password, Long orderId, Item item, int quantity, String notes) {
		this.username = username;
		this.password = password;
		this.orderId = orderId;
		this.item = item;
		this.quantity = quantity;
		this.notes = notes;
	}
		
	public OrderInput() {}
		
	public String getUsername() {
		return username;
	}
		
	public String getPassword() {
		return password;
	}
		
	public Long getOrderId() {
		return orderId;
	}
	
	public Item getItem() {
		return item;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public String getNotes() {
		return notes;
	}
}
