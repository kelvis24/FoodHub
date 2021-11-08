package foodhub.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import foodhub.ioObjects.CategoryInfo;
import foodhub.ioObjects.Entitled;

/**
 * Specifies the table of categories.
 * 
 * @author 1_CW_2
 */
@Entity
@Table(name="categories")
public class Category implements Entitled {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false)
	private long firmId;
	@Column(nullable = false, length = 100)
	private String title;
	@Column(nullable = false, length = 100)
	private String description;
	
	/**
	 * Constructs a new category given a firm to which it belongs, and other information
	 * @param firmId The id of the firm to which the new category belongs
	 * @param category The title and description information of that category
	 */
	public Category(long firmId, CategoryInfo category) {
		this.firmId = firmId;
		this.title = category.getTitle();
		this.description = category.getDescription();
	}

	/**
	 * Constructs a new category given all the enumerated requisite information thereof
	 * @param firmId The id of the firm to which the new category belongs
	 * @param title The title of the new category
	 * @param description The description of the new category
	 */
	public Category(long firmId, String title, String description) {
		this.firmId = firmId;
		this.title = title;
		this.description = description;
	}
	
	/**
	 * An default constructor.
	 */
	public Category() {}
	
	/**
	 * A getter for the id field
	 * @return The id of the category
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * A getter for the firmId field
	 * @return The id of the firm to which the category belongs
	 */
	public Long getFirmId() {
		return firmId;
	}

	/**
	 * A getter for the title field
	 * @return the title of the category
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * A getter for the description field
	 * @return the description of the category
	 */
	public String getDescription() {
		return description;
	}
	
}
