package foodhub.ioObjects;

/**
 * An input protocol used in editing a category
 * @author 1_CW_2
 */
public class EditCategoryInput extends AddCategoryInput {
	
	private long categoryId;

	/**
	 * Constructs a new EditCategoryInput from enumerated information
	 * @param username The username of the firm editing the category
	 * @param password The password of the firm editing the category
	 * @param data The new qualities of the category
	 * @param categoryId The id of the category which is to be changed
	 * @see AddCategoryInput
	 */
	public EditCategoryInput(String username, String password, CategoryInfo data, long categoryId) {
		super(username, password, data);
		this.categoryId = categoryId;
	}

	/**
	 * A getter for the categoryId field
	 * @return The id of the category which is to be changed
	 */
	public long getCategoryId() {
		return categoryId;
	}
	
}
