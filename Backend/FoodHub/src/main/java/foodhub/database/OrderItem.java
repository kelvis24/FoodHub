package foodhub.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Specifies the qualities of the table of OrderItems, relating orders to items
 * @author 1_CW_2
 */
@Entity
@Table(name="Order_Item")
public class OrderItem {
	
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
	
	/**
	 * Constructs a new OrderItem entity given enumerated information
	 * @param orderId The order to which the item is added
	 * @param itemId The item that is added to the order
	 * @param quantity The quantity of the item that is ordered
	 * @param notes Noted about the specific item regarding the order
	 */
	public OrderItem(long orderId, long itemId, int quantity, String notes) {
		this.orderId = orderId;
		this.itemId = itemId;
		this.quantity = quantity;
		this.notes = notes;
	}

	/**
	 * A default constructor
	 */
	public OrderItem() {}
	
	/**
	 * A getter for the id field
	 * @return The id of the relation
	 */
	public long getId() {
		return id;
	}

	/**
	 * A getter for the orderId field
	 * @return The id of the order to which this relation belongs
	 */
	public long getOrderId() {
		return orderId;
	}

	/**
	 * A getter for the item field
	 * @return The id of the item to which this relation belongs
	 */
	public long getItemId() {
		return itemId;
	}

	/**
	 * A getter for the quantity field
	 * @return The quantity of the item that is ordered via this relation
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * A getter for the notes field
	 * @return The notes of the item that is ordered via this relation as it regards its order
	 */
	public String getNotes() {
		return notes;
	}
	
}
