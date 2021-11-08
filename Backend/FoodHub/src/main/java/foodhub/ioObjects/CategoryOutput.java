package foodhub.ioObjects;

import foodhub.database.Category;

/**
 * An output protocol for the qualities of a category
 * @author 1_CW_2
 */
public class CategoryOutput {
	
	private long id;
	private String title;
	private String description;

	/**
	 * Constructs a CategoryOutput object given a category entity, of the database
	 * @param category A category entity, of the database
	 */
	public CategoryOutput(Category category) {
		this.id = category.getId();
		this.title = category.getTitle();
		this.description = category.getDescription();
	}

	/**
	 * Constructs a CategoryOutput object from enumerated information of a category
	 * @param id The id of the category
	 * @param title The title of the category
	 * @param description The description of a category
	 */
	public CategoryOutput(long id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
	}
	
	/**
	 * A getter for the id field
	 * @return The id of the category
	 */
	public long getId() {
		return id;
	}

	/**
	 * A getter for the title field
	 * @return The title of the category
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * A getter for the description field
	 * @return The description of the category
	 */
	public String getDescription() {
		return description;
	}
	
}
