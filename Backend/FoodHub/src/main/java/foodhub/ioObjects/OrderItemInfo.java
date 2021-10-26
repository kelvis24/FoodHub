package foodhub.ioObjects;

public class OrderItemInfo {

	private long itemId;
	private int quantity;
	private String notes;
	
	public OrderItemInfo(long itemId, int quantity, String notes) {
		this.itemId = itemId;
		this.quantity = quantity;
		this.notes = notes;
	}
	
	public OrderItemInfo() {}

	public long getItemId() {
		return itemId;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public String getNotes() {
		return notes;
	}
	
}
