package foodhub.ioObjects;

/**
 * An input protocol used in creating a category
 * @author 1_CW_2
 */
public class AddCategoryInput extends Authentication {

	private CategoryInfo data;
	
	/**
	 * Constructs an AddCategoryInput object given enumerated information
	 * @param username The username of the firm adding the category
	 * @param password The password of the firm adding the category
	 * @param data The qualities of the category that are added
	 * @see Authentication
	 */
	public AddCategoryInput(String username, String password, CategoryInfo data) {
		super(username, password);
		this.data = data;
	}
	
	/**
	 * A getter for the data field
	 * @return The qualities of the category that are added
	 */
	public CategoryInfo getData() {
		return data;
	}
	
}
