package foodhub.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Order_Items")
public class OrderItems {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false)
	private long orderId;
	@Column(nullable = false)
	private long itemId;
	@Column(nullable = false)
	private int quantity;
	@Column()
	private String notes;
	
	public OrderItems(long orderId, long itemId, int quantity, String notes) {
		this.orderId = orderId;
		this.itemId = itemId;
		this.quantity = quantity;
		this.notes = notes;
	}

	public OrderItems() {}
	
	public long getId() {
		return id;
	}

	public long getOrderId() {
		return orderId;
	}

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
