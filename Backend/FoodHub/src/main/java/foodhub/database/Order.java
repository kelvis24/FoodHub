package foodhub.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import foodhub.ioObjects.Entitled;

@Entity
@Table(name="orders")
public class Order implements Entitled {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false)
	private long firmId;
	@Column(nullable = false)
	private long customerId;
	@Column(nullable = false, length = 100)
	private String title;
	@Column(nullable = false)
	private int status;
	
	public Order(long firmId, long customerId, String title, int status) {
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
	
	public String getTitle() {
		return title;
	}
	
	public int getStatus() {
		return status;
	}
	
}