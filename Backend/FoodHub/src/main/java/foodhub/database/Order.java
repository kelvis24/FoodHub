package foodhub.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	public Order(long firmId, long customerId, int status) {
		this.firmId = firmId;
		this.customerId = customerId;
		this.status = status;
	}
	
	public Order() {}
	
	public long getId() {
		return id;
	}
	
	public long getFirmId() {
		return firmId;
	}
	
	public long getCustomerId() {
		return customerId;
	}
	
	public int getStatus() {
		return status;
	}
	
}