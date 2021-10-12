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
	@Column(nullable = false, unique = true, length = 100)
	private String title;
	@Column(nullable = false, length = 100)
	private String description;
	@Column(nullable = false)
	private Double price;
	
	public Item(long id, long firmId, long categoryId, String title, String description, Double price) {
		this.id = id;
		this.firmId = firmId;
		this.categoryId = categoryId;
		this.title = title;
		this.description = description;
		this.price = price;
	}
	
	public Item(String title, String description, Double price) {
		this.title = title;
		this.description = description;
		this.price = price;
	}

	public Item() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFirmId() {
		return firmId;
	}

	public void setFirmId(long firmId) {
		this.firmId = firmId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", firmId=" + firmId + ", categoryId=" + categoryId + ", title=" + title
				+ ", description=" + description + ", price=" + price + "]";
	}
	
}
