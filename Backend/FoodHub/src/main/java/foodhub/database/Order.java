package foodhub.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Specified the nature of the table of orders
 * @author 1_CW_2
 */
@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false)
	private long firmId;
	@Column(nullable = false)
	private long customerId;
	@Column(nullable = false)
	private int status;
	// 0 -> pending (initial status, incomplete)
	// 1 -> delivered (but still visible to the customer)
	// 2 -> Obsolete (in case of category removal, or item edit/removal)
	// 3 -> failed (in case of firm's removal)
	//   An order can be discarded by a customer when status is not 0
	//   An order is deleted upon customer removal
	
	/**
	 * Constructs a new order given enumerated information
	 * @param firmId The id of the firm to which this order is placed
	 * @param customerId The id of the customer whereby this order is placed
	 * @param status The initial status of the order
	 */
	public Order(long firmId, long customerId, int status) {
		this.firmId = firmId;
		this.customerId = customerId;
		this.status = status;
	}
	
	/**
	 * A default constructor
	 */
	public Order() {}
	
	/**
	 * A getter for the id field
	 * @return The id of the order
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * A getter for the firmId field
	 * @return The id of the firm to which this order is placed
	 */
	public long getFirmId() {
		return firmId;
	}
	
	/**
	 * A getter for the customerId field
	 * @return The id of the customer whereby this order is placed
	 */
	public long getCustomerId() {
		return customerId;
	}
	
	/**
	 * A getter for the status field
	 * @return The status of the order
	 */
	public int getStatus() {
		return status;
	}
	
}