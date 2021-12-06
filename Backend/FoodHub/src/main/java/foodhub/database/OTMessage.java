package foodhub.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="otmessages")
public class OTMessage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false)
	private long orderId;
	@Column(nullable = false)
	private int sequence;
	@Column(nullable = false)
	private int who; // 0 => Firm; 1 => Customer
	@Column(nullable = false, length = 250)
	private String message;
	
	public OTMessage(long orderId, int sequence, int who, String message) {
		this.orderId = orderId;
		this.sequence = sequence;
		this.who = who;
		this.message = message;
	}
	
	public OTMessage() {}
	
	public long getId() {
		return id;
	}
	
	public long getOrderId() {
		return orderId;
	}
	
	public int getSequence() {
		return sequence;
	}
	
	public int getWho() {
		return who;
	}
	
	public String getMessage() {
		return message;
	}
	
}
