package foodhub.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import foodhub.ioObjects.Entitled;
import foodhub.ioObjects.ItemInfo;

/**
 * Specifies the qualities of the table of items
 * @author 1_CW_2
 */
@Entity
@Table(name="items")
public class Item implements Entitled {
	
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
	
	/**
	 * Constructs a new item given the id of its firm, the id of its category, and
	 * Data about its qualities
	 * @param firmId The id of the firm to which this item belongs
	 * @param categoryId The id of the category to which this item belongs
	 * @param item The qualities about the item that is to be created
	 */
	public Item(long firmId, long categoryId, ItemInfo item) {
		this.firmId = firmId;
		this.categoryId = categoryId;
		this.title = item.getTitle();
		this.description = item.getDescription();
		this.price = item.getPrice();
	}
	
	/**
	 * Constructs a new item given its full, enumerated qualities
	 * @param firmId The id of the firm to which this item belongs
	 * @param categoryId The category of the firm to which this item belongs
	 * @param title The title of the new item
	 * @param description The description of the new item
	 * @param price The price of the new item
	 */
	public Item(long firmId, long categoryId, String title, String description, double price) {
		this.firmId = firmId;
		this.categoryId = categoryId;
		this.title = title;
		this.description = description;
		this.price = price;
	}

	/**
	 * A default constructor
	 */
	public Item() {}

	/**
	 * A getter for the id field
	 * @return The id of the item
	 */
	public long getId() {
		return id;
	}

	/**
	 * A getter for the firmId field
	 * @return The id of the firm to which this item belongs
	 */
	public long getFirmId() {
		return firmId;
	}

	/**
	 * A getter for the categoryId field
	 * @return The id of the category to which this item belongs
	 */
	public long getCategoryId() {
		return categoryId;
	}

	/**
	 * A getter for the title field
	 * @return The title of this item
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * A getter for the description field
	 * @return The description of this item
	 */
	public String getDescription() {
		return description;
	}

	/** 
	 * A getter for the price field
	 * @return The price of this item
	 */
	public double getPrice() {
		return price;
	}
	
}
