package foodhub.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AMessages")
public class AMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false)
	private int sequence;
	@Column(nullable = false)
	private long sender; //who sent this message
	@Column(nullable = false)
	private String message;
	
	public AMessage(int sequence, long sender, String message) {
		this.sequence = sequence;
		this.sender = sender;
		this.message = message;
	}
	
	public AMessage() {}
	
	public long getId() {
		return id;
	}
	
	public int getSequence() {
		return sequence;
	}
	
	public long getSender() {
		return sender;
	}
	
	public String getMessage() {
		return message;
	}
	
}
