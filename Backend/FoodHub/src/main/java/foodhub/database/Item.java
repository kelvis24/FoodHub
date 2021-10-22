package foodhub.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="items")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false)
	private long firmId;
	@Column(nullable = false)
	private long categoryId;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false, length = 100)
	private String description;
	@Column(nullable = false)
	private double price;
	
	public Item(long firmId, long categoryId, String title, String description, double price) {
		this.firmId = firmId;
		this.categoryId = categoryId;
		this.title = title;
		this.description = description;
		this.price = price;
	}

	public Item() {}

	public long getId() {
		return id;
	}

	public long getFirmId() {
		return firmId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}
	
}
