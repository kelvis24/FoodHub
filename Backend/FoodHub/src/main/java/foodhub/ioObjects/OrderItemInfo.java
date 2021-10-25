package foodhub.ioObjects;

public class OrderItemInfo {

	private String category;
	private String title;
	private int quantity;
	private String notes;
	
	public OrderItemInfo(String category, String title, int quantity, String notes) {
		this.category = category;
		this.title = title;
		this.quantity = quantity;
		this.notes = notes;
	}
	
	public OrderItemInfo() {}
	
	public String getCategory() {
		return category;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public String getNotes() {
		return notes;
	}
	
}
