package foodhub.ioObjects;

/**
 * An input protocol for specifying the qualities of a category
 * @author 1_CW_2
 */
public class CategoryInfo {
	
	private String title;
	private String description;

	/**
	 * Constructs a CategoryInfo object
	 * @param title The title of the category
	 * @param description The description of the category
	 */
	public CategoryInfo(String title, String description) {
		this.title = title;
		this.description = description;
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
